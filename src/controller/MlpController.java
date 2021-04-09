package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ToolTip;

public class MlpController implements Initializable {

	@FXML
	private JFXRadioButton radioOne;

	@FXML
	private ToggleGroup hiddenLayerGroup;

	@FXML
	private JFXRadioButton radioThree;

	@FXML
	private JFXRadioButton radioTwo;

	@FXML
	private Label lbl1min;

	@FXML
	private Label lbl1max;

	@FXML
	private JFXTextField txtMinNoronBir;

	@FXML
	private JFXTextField txtMaxNoronBir;

	@FXML
	private Label lbl2min;

	@FXML
	private Label lbl2max;

	@FXML
	private JFXTextField txtMinNoronIki;

	@FXML
	private JFXTextField txtMaxNoronIki;

	@FXML
	private Label lbl3min;

	@FXML
	private Label lbl3max;

	@FXML
	private JFXTextField txtMinNoronUc;

	@FXML
	private JFXTextField txtMaxNoronUc;

	@FXML
	private Label lblminlearning;

	@FXML
	private Label lblmaxlearning;

	@FXML
	private JFXTextField txtMaxLearningrate;

	@FXML
	private JFXTextField txtMinLearningrate;

	@FXML
	private Label lblminepoch;

	@FXML
	private Label lblmaxepoch;

	@FXML
	private JFXTextField txtMaxEpoch;

	@FXML
	private JFXTextField txtMinEpoch;

	@FXML
	private JFXCheckBox showNotification;

	public static int countHiddenLayer;

	static JFXSpinner progressBarJfx = null;

	@FXML
	private JFXButton stopBtn;

	@FXML
	private JFXButton startBtn;

	@FXML
	private StackPane stackPane;

	public static boolean selectedShowNotification, nonSelectedShowNotification;

	@FXML
	void fonkradioOne(ActionEvent event) {

		if (radioOne.isSelected()) {

			lbl2max.setVisible(false);
			lbl2min.setVisible(false);
			txtMaxNoronIki.setVisible(false);
			txtMinNoronIki.setVisible(false);
			lbl3max.setVisible(false);
			lbl3min.setVisible(false);
			txtMinNoronUc.setVisible(false);
			txtMaxNoronUc.setVisible(false);
			countHiddenLayer = 1;

		}
	}

	@FXML
	void fonkradioThree(ActionEvent event) {

		if (radioThree.isSelected()) {
			lbl3max.setVisible(true);
			lbl3min.setVisible(true);
			txtMinNoronUc.setVisible(true);
			txtMaxNoronUc.setVisible(true);
			lbl2max.setVisible(true);
			lbl2min.setVisible(true);
			txtMaxNoronIki.setVisible(true);
			txtMinNoronIki.setVisible(true);
			countHiddenLayer = 3;

		}
	}

	@FXML
	void fonkradioTwo(ActionEvent event) {
		if (radioTwo.isSelected()) {
			lbl2max.setVisible(true);
			lbl2min.setVisible(true);
			txtMaxNoronIki.setVisible(true);
			txtMinNoronIki.setVisible(true);

			lbl3max.setVisible(false);
			lbl3min.setVisible(false);
			txtMinNoronUc.setVisible(false);
			txtMaxNoronUc.setVisible(false);
			countHiddenLayer = 2;

		}
	}

	public static String birKucukNoron;
	public static String minepoch;
	public static String maxepoch;
	public static String minlr;
	public static String maxlr;
	public static String ucBuyukNoron;
	public static String ucKucukNoron;
	public static String ikiBuyukNoron;
	public static String birBuyukNoron;
	public static String ikiKucukNoron;
	public static Stage stage = null;

	@FXML
	void fonkBaslat(ActionEvent event) throws Exception {

		if (showNotification.isSelected()) {
			selectedShowNotification = true;

		} else {
			nonSelectedShowNotification = true;
		}

		progressBarJfx = new JFXSpinner();
		progressBarJfx.setVisible(true);
		stackPane.getChildren().add(progressBarJfx);

		birKucukNoron = txtMinNoronBir.getText();
		minepoch = txtMinEpoch.getText();
		maxepoch = txtMaxEpoch.getText();
		minlr = txtMinLearningrate.getText().toString();
		maxlr = txtMaxLearningrate.getText().toString();
		ucBuyukNoron = txtMaxNoronUc.getText();
		ucKucukNoron = txtMinNoronUc.getText();
		ikiBuyukNoron = txtMaxNoronIki.getText();
		birBuyukNoron = txtMaxNoronBir.getText();
		ikiKucukNoron = txtMinNoronIki.getText();
		try {
			URL url = new File("src/view/MlpTablo.fxml").toURI().toURL();
			Parent fxml = FXMLLoader.load(url);
			stage = new Stage();
			Scene scene = new Scene(fxml);
			stage.setTitle("MlpTablo");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {

			System.out.println("MlpTablo" + " Hata :" + e.getMessage());
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		showNotification.setTooltip(ToolTip.tolltipRobotAnnNotif());
		txtMinNoronBir.setText("50");
		txtMinEpoch.setText("50");
		txtMaxEpoch.setText("53");
		txtMinLearningrate.setText("0.1");
		txtMaxLearningrate.setText("0.3");
		txtMaxNoronUc.setText("300");
		txtMinNoronUc.setText("250");
		txtMaxNoronIki.setText("100");
		txtMaxNoronBir.setText("80");
		txtMinNoronIki.setText("80");

		radioOne.setToggleGroup(hiddenLayerGroup);
		radioTwo.setToggleGroup(hiddenLayerGroup);
		radioThree.setToggleGroup(hiddenLayerGroup);
		countHiddenLayer = 1;

		stopBtn.setOnAction(a -> {

			if (MlpTabloController.thread != null) {
				MlpTabloController.thread.stop();
				stackPane.getChildren().clear();
				progressBarJfx.setVisible(false);
				MlpTabloController.sayac = 0;
			}

		});

	}

}
