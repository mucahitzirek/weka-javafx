package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.AlertOlustur;
import javafx.scene.control.Alert.AlertType;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ClusteringController implements Initializable {

	@FXML
	private JFXComboBox<String> classifier_ComboBox;

	@FXML
	private JFXButton start_Btn;

	@FXML
	private Label correct_Label;

	@FXML
	private Label numClusters_Label;

	@FXML
	private Label squaredError_Label;

	@FXML
	private JFXRadioButton traningSet_RadioBtn;

	@FXML
	private ToggleGroup radioGroup;

	@FXML
	private JFXRadioButton percentageSplit_RadioBtn;

	@FXML
	private TextField percentageSplitPercent_TextField;

	@FXML
	private JFXButton saveModel_Btn;

	@FXML
	private TextArea clusterOutput_TextArea;

	ArrayList<String> list = new ArrayList<String>();

	String cmbOutput = null;
	public static int folds, percent;
	Instances data = null;
	DataSource source = null;
	ClusterEvaluation clusterEval = null;
	Clusterer clusterer;
	public static SimpleKMeans simpleKmeans;
	public static HierarchicalClusterer hierarchicalClusterer;
	boolean clusterSelect = false;

	@FXML
	void fonkCmbClustering(ActionEvent event) {

		cmbOutput = classifier_ComboBox.getSelectionModel().getSelectedItem().toString();

		if (cmbOutput.equals("SimpleKmeans")) {
			clusterSelect = true;

			simpleKmeans = new SimpleKMeans();

			ShowPage.settingShow("src/view/SimpleKmeansSettings.fxml", "Simple Kmeans Settings");
			clusterer = simpleKmeans;

		} else if (cmbOutput.equals("Hierarchical")) {
			clusterSelect = false;
			hierarchicalClusterer = new HierarchicalClusterer();

			ShowPage.settingShow("src/view/HierarchicalClusteringSettings.fxml", "Hierarchical Clustering Settings");
			clusterer = hierarchicalClusterer;
		}

	}

	@FXML
	void fonkSaveModel(ActionEvent event) {
		if (cmbOutput.equals("SimpleKmeans")) {

			try {
				weka.core.SerializationHelper.write(MainController.asdasd + "model", simpleKmeans);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (cmbOutput.equals("Hierarchical")) {
			try {
				weka.core.SerializationHelper.write(MainController.asdasd + "model", hierarchicalClusterer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		AlertOlustur.showAlert(AlertType.INFORMATION, "Model kaydedildi", null, MainController.asdasd + "model");

	}

	@FXML
	void fonkStart(ActionEvent event) throws Exception {

		if (cmbOutput != null && MainController.secilen != null) {

			source = new DataSource(MainController.secilen);
			data = source.getDataSet();

			evalu(clusterer, data);

			clusterOutput_TextArea.setText(clusterEval.clusterResultsToString());
		} else {
		}

	}

	public void evalu(Clusterer clusterer, Instances trainData) throws Exception {

		percent = Integer.parseInt(percentageSplitPercent_TextField.getText());

		clusterEval = new ClusterEvaluation();
		clusterEval.setClusterer(clusterer);
		clusterer.buildClusterer(trainData);

		if (percentageSplit_RadioBtn.isSelected()) {

			if (percent > 0 && percent < 100) {
				int trainSize = data.numInstances() * percent / 100;
				int testSize = data.numInstances() - trainSize;

				// Instances train = new Instances(data, 0, trainSize);
				Instances test = new Instances(data, trainSize, testSize);

				clusterEval.evaluateClusterer(test);

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.show();
			}

		} else if (traningSet_RadioBtn.isSelected()) {

			clusterEval.evaluateClusterer(trainData);

		}

		numClusters_Label.setText(String.valueOf(clusterEval.getNumClusters()));

		if (cmbOutput.equals("SimpleKmeans")) {

			squaredError_Label.setText(String.valueOf(simpleKmeans.getSquaredError()));

		} else {
			squaredError_Label.setText(null);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		traningSet_RadioBtn.setToggleGroup(radioGroup);
		percentageSplit_RadioBtn.setToggleGroup(radioGroup);
		list.add("SimpleKmeans");
		list.add("Hierarchical");
		classifier_ComboBox.getItems().addAll(list);
		percentageSplitPercent_TextField.setText("80");

	}

}
