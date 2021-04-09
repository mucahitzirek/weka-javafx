package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AlertOlustur;
import model.Main;
import weka.core.AttributeStats;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;

public class MainController implements Initializable {

	@FXML
	private JFXButton openFile_Btn;

	@FXML
	private JFXButton saveFile_Btn;

	@FXML
	private JFXButton classifaction_Btn;

	@FXML
	private JFXButton clustering_Btn;

	@FXML
	private JFXButton openFile_Btn1;

	@FXML
	private JFXButton multipleChooser_Btn;

	@FXML
	private Label fileName_Label;

	@FXML
	private Label saveFileName_Label;

	@FXML
	private ListView<String> listview;

	@FXML
	private Label relation_Label;

	@FXML
	private Label instances_Label;

	@FXML
	private Label attributes_Label;

	@FXML
	private Label sumOfWeights_Label;

	@FXML
	private Label name_Label;

	@FXML
	private Label missing_Label;

	@FXML
	private Label distinct_Label;

	@FXML
	private Label type_Label;

	@FXML
	private Label unique_Label;

	@FXML
	private JFXComboBox<String> selectAttributes_Cmb;

	@FXML
	private TextArea selectedAttr_TextArea;

	public static Instances data;

	public static boolean pca = false;
	public static boolean ica = false;

	String sec = null;

	FileChooser fileChooser = new FileChooser();
	File selectedFile;
	public static String secilen;
	ArrayList<String> list = new ArrayList<>();
	ArrayList<String> listAttributes = new ArrayList<>();
	public static String getParentFileName;
	String fileName = null;
	String uzanti = null;

	public static String asdasd = null;

	ConversionArffTo converter = new ConversionArffTo();

	public static boolean fileIsNumeric;

	@FXML
	void fonkClassification(ActionEvent event) {

		try {
			URL url = new File("src/view/Classification.fxml").toURI().toURL();
			Parent fxml = FXMLLoader.load(url);
			Stage stage = new Stage();
			Scene scene = new Scene(fxml);
			stage.setTitle("Classifaction");
			// Close Resizable
			stage.resizableProperty().setValue(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {

			System.out.println("Classifaction" + " Hata :" + e.getMessage());
		}

	}

	@FXML
	void fonkClustering(ActionEvent event) {

		ShowPage.settingShow("src/view/Clustering.fxml", "Clustering");

	}

	String outPutCmb = null;

	@FXML
	void cmbfonkMultipilChooser(ActionEvent event) {
		outPutCmb = selectAttributes_Cmb.getSelectionModel().getSelectedItem().toString();

		if (outPutCmb.equals("PCA")) {
			ShowPage.settingShow("src/view/PCASettings.fxml", "PCA");
		}
	}

	@FXML
	void fonkMultipilChooser(ActionEvent event) throws Exception {
		DataSource source = new DataSource(secilen);
		data = source.getDataSet();
		data.setClassIndex(data.numAttributes() - 1);
		if (outPutCmb.equals("PCA")) {

			pca = true;

		}

		else if (outPutCmb.equals("ICA")) {

			ica = true;

		}
	}

	@FXML
	void fonkOpenFile(ActionEvent event) throws IOException {

		selectedFile = fileChooser.showOpenDialog(Main.stage);
		fileChooser.setTitle("Select File");
		listview.getItems().clear();
		list.clear();

		if (selectedFile != null) {

			fileName = selectedFile.getCanonicalPath();
			secilen = selectedFile.toString();
			getParentFileName = selectedFile.getParent();
			
			int ifade = secilen.lastIndexOf(".") + 1;
			uzanti = secilen.substring(ifade);

			if (uzanti.equals("csv")) {

				CSVLoader loader = new CSVLoader();
				loader.setSource(new File(secilen));
				data = loader.getDataSet();
				ArffSaver saver = new ArffSaver();

				saver.setInstances(data);
				secilen = secilen.substring(0, ifade);
				asdasd = secilen;
				secilen += "arff";

				saver.setFile(new File(secilen));
				saver.writeBatch();

				selectedFile = new File(secilen);

			} else if (uzanti.equals("arff")) {
				try {

					DataSource source = new DataSource(secilen);
					data = source.getDataSet();
					data.setClassIndex(data.numAttributes() - 1);
					int attSayisi = data.numAttributes();

					asdasd = secilen.substring(0, ifade);

					for (int i = 0; i < attSayisi; i++) {
						String name = data.attribute(i).name();
						list.add(name);

					}

					listview.getItems().addAll(list);
					String dataName = data.relationName();
					int attributeSayisi = data.numAttributes();
					int dataSayisi = data.numInstances();

					String dsayi = Integer.toString(dataSayisi);
					String asayi = Integer.toString(attributeSayisi);

					relation_Label.setText(dataName);
					attributes_Label.setText(asayi);
					instances_Label.setText(dsayi);
					double weigth = data.sumOfWeights();
					String wsayi = Double.toString(weigth);
					sumOfWeights_Label.setText(wsayi);

					DataSource sourcea;
					try {
						sourcea = new DataSource(secilen);
						data = sourcea.getDataSet();
						data.setClassIndex(data.numAttributes() - 1);

						for (int i = 0; i < data.numAttributes(); i++) {
							if (data.attribute(i).isNominal()) {

								fileIsNumeric = false;
								break;

							} else {
								fileIsNumeric = true;
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
				}

			}

			else {
				AlertOlustur.showAlert(AlertType.ERROR, "\"Dosya Desteklenmiyor\"", null, "Dosya Desteklenmiyor");
				secilen = null;
			}
		} else {
			AlertOlustur.showAlert(AlertType.INFORMATION, "Dosya Secilmedi", null, "Dosya Secilmedi");

		}

	}

	@FXML
	void fonkSaveFile(ActionEvent event) {

		if (selectedFile != null) {

			fileChooser.setTitle("Save File");
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("CSV JSON Xrff", "*.csv *.json .xrff"),
					new FileChooser.ExtensionFilter("CSV", "*.csv"), new FileChooser.ExtensionFilter("JSON", "*.json"),
					new FileChooser.ExtensionFilter("Xrff", "*.xrff"));

			File file = fileChooser.showSaveDialog(Main.stage);

			if (file != null) {

				fileChooser.setInitialDirectory(file.getParentFile());

				try {

					String kaydedilen = file.toString();

					int ifade = kaydedilen.lastIndexOf(".") + 1;

					uzanti = kaydedilen.substring(ifade);

					if (uzanti.equals("csv")) {

						converter.convertArfftoCsv(new File(fileName), new File(file.getCanonicalPath()));

					} else if (uzanti.equals("json")) {

						converter.convertAffToJson(new File(fileName), new File(file.getCanonicalPath()));

					} else if (uzanti.equals("xrff")) {

						converter.convertArfftoXrff(new File(fileName), new File(file.getCanonicalPath()));

					}

					// Dosya kaydedildi alert
					// AlertOlustur.showAlert(AlertType.INFORMATION, "Dosya Kaydedildi", null,
					// file.getCanonicalPath());

				} catch (IOException e) {
					System.out.println("Hata : " + e.getMessage());
				}

			} else {
				AlertOlustur.showAlert(AlertType.INFORMATION, "Kaydedilmedi", null, "Dosya Konumu Belirtilmedi");

			}

		} else {
			AlertOlustur.showAlert(AlertType.INFORMATION, "Information", null,
					"Dosya kaydetmek icin lutfen once cevirmek istediginiz dosyayi secin");
		}

		/*
		 * .csv, .xrff, .json tekrarini engellemek icin (save - > FileChooser)
		 */
		fileChooser.getExtensionFilters().clear();

	}

	String name, mis, dist, type, uniqe;

	@FXML
	void listview_Clicked(MouseEvent event) throws Exception {

		if (listview.getSelectionModel().getSelectedItem() != null) {

			String secilenAtt = listview.getSelectionModel().getSelectedItem().toString();

			DataSource source = new DataSource(secilen);
			Instances data = source.getDataSet();
			data.setClassIndex(data.numAttributes() - 1);
			int attSayisi = data.numAttributes();

			for (int i = 0; i < attSayisi; i++) {
				if (data.attribute(secilenAtt).isNumeric()) {
					type_Label.setText("isNumeric");

					data.attribute(secilenAtt).type();
				} else {
					type_Label.setText("isNominal");
				}

				String label = data.attribute(i).name();
				name = data.attribute(i).name();
				if (label.equals(secilenAtt)) {
					AttributeStats as = data.attributeStats(i);
					sec = as.toString();

				}
			}

			name_Label.setText(secilenAtt);

			selectedAttr_TextArea.setText(sec);
		} else {

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listAttributes.add("PCA");
		listAttributes.add("ICA");

		selectAttributes_Cmb.getItems().addAll(listAttributes);

	}

}
