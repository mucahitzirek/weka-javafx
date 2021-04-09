package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.SettingsToClassifiers;

public class SMOSettingsController implements Initializable {

	@FXML
	private JFXTextField batchSize_TextField;

	@FXML
	private JFXTextField c_TextField;

	@FXML
	private JFXTextField epsilon_TextField;

	@FXML
	private JFXTextField numFolds_TextField;

	@FXML
	private JFXTextField numDecimalPlaces_TextField;

	@FXML
	private JFXTextField randomSeed_TextField;

	@FXML
	private JFXTextField toleranceParameter_TextField;

	@FXML
	private JFXCheckBox buildCalibrationModels_Chk;

	@FXML
	private JFXCheckBox checksTurnedOff_Chk;

	@FXML
	private JFXCheckBox debug_Chk;

	@FXML
	private JFXCheckBox doNotCheckCapabilities_Chk;

	@FXML
	private JFXButton idSaveBtn;

	@FXML
	private JFXButton idCancelBtn;

	public static String lblbatchSize_TextField = null;
	public static String lblc_TextField = null;
	public static String lblepsilon_TextField = null;
	public static String lblnumDecimalPlaces_TextField = null;
	public static String lblnumFolds_TextField = null;
	public static String lblrandomSeed_TextField = null;
	public static String lbltoleranceParameter_TextField = null;

	public static Boolean lblbuildCalibrationModels_Chkk = null;
	public static Boolean lblchecksTurnedOff_Chk = null;
	public static Boolean lbldebug_Chk = null;
	public static Boolean lbldoNotCheckCapabilities_Chk = null;

	@FXML
	void cancel_Btn(ActionEvent event) {
		fonkDefault();

		Stage stage = (Stage) idCancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save_Btn(ActionEvent event) {

		Settings();
		
		SettingsToClassifiers ss = new SettingsToClassifiers();
		ss.smoSettingsController(ClassificationController.smo);
		
		Stage stage = (Stage) idSaveBtn.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		batchSize_TextField.setText("100");
		c_TextField.setText("1.0");
		epsilon_TextField.setText("1.0E-12");
		numDecimalPlaces_TextField.setText("2");
		numFolds_TextField.setText("2");
		randomSeed_TextField.setText("1");
		toleranceParameter_TextField.setText("0.001");

		lblbuildCalibrationModels_Chkk = false;
		lblchecksTurnedOff_Chk = false;
		lbldebug_Chk = false;
		lbldoNotCheckCapabilities_Chk = false;

		fonkDefault();
	}

	public void Settings() {
		if (batchSize_TextField.toString() != null && c_TextField.toString() != null
				&& epsilon_TextField.toString() != null && numDecimalPlaces_TextField.toString() != null
				&& numFolds_TextField.toString() != null && randomSeed_TextField.toString() != null
				&& toleranceParameter_TextField.toString() != null) {

			lblbatchSize_TextField = batchSize_TextField.getText();
			lblc_TextField = c_TextField.getText();
			lblepsilon_TextField = epsilon_TextField.getText();
			lblnumDecimalPlaces_TextField = numDecimalPlaces_TextField.getText();
			lblnumFolds_TextField = numFolds_TextField.getText();
			lblrandomSeed_TextField = randomSeed_TextField.getText();
			lbltoleranceParameter_TextField = toleranceParameter_TextField.getText();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
		}

		if (buildCalibrationModels_Chk.isSelected()) {
			lblbuildCalibrationModels_Chkk = true;
		} else {
			lblbuildCalibrationModels_Chkk = false;
		}

		if (checksTurnedOff_Chk.isSelected()) {
			lblchecksTurnedOff_Chk = true;
		} else {
			lblchecksTurnedOff_Chk = false;
		}

		if (debug_Chk.isSelected()) {
			lbldebug_Chk = true;
		} else {
			lbldebug_Chk = false;
		}

		if (doNotCheckCapabilities_Chk.isSelected()) {
			lbldoNotCheckCapabilities_Chk = true;
		} else {
			lbldoNotCheckCapabilities_Chk = false;
		}

	}

	public void fonkDefault() {

		lblbatchSize_TextField = "100";
		lblc_TextField = "1.0";
		lblepsilon_TextField = "1.0E-12";
		lblnumDecimalPlaces_TextField = "2";
		lblnumFolds_TextField = "2";
		lblrandomSeed_TextField = "1";
		lbltoleranceParameter_TextField = "0.001";
		lblbuildCalibrationModels_Chkk = false;
		lblchecksTurnedOff_Chk = false;
		lbldebug_Chk = false;
		lbldoNotCheckCapabilities_Chk = false;

	}

}
