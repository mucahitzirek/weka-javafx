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

public class J48SettingsController implements Initializable {

	@FXML
	private JFXTextField batchSize_TextField;

	@FXML
	private JFXTextField minNumObj_TextField;

	@FXML
	private JFXTextField confidenceFactor_TextField;

	@FXML
	private JFXTextField numDecimalPlaces_TextField;

	@FXML
	private JFXTextField numFolds_TextField;

	@FXML
	private JFXTextField seed_TextField;

	@FXML
	private JFXCheckBox binarySplits_Chk;

	@FXML
	private JFXCheckBox collapseTree_Chk;

	@FXML
	private JFXCheckBox debug_Chk;

	@FXML
	private JFXCheckBox doNotCheckCapabilities_Chk;

	@FXML
	private JFXCheckBox doNotMakeSplitPointActualValue_Chk;

	@FXML
	private JFXCheckBox reduceErrorPruning_Chk;

	@FXML
	private JFXCheckBox saveinstanceData_Chk;

	@FXML
	private JFXCheckBox subtreeRaising_Chk;

	@FXML
	private JFXCheckBox unproned_Chk;

	@FXML
	private JFXCheckBox useLaplece_Chk;

	@FXML
	private JFXCheckBox useMDLcorrection_Chk;

	@FXML
	private JFXButton idSaveBtn;

	@FXML
	private JFXButton idCancelBtn;

	public static String lblbatchSize_TextField = null;
	public static String lblminNumObj_TextField = null;
	public static String lblconfidenceFactor_TextField = null;
	public static String lblnumDecimalPlaces_TextField = null;
	public static String lblnumFolds_TextField = null;
	public static String lblseed_TextField = null;

	public static Boolean lblbinarySplits_Chk = null;
	public static Boolean lblcollapseTree_Chk = null;
	public static Boolean lbldebug_Chk = null;
	public static Boolean lbldoNotCheckCapabilities_Chk = null;
	public static Boolean lbldoNotMakeSplitPointActualValue_Chk = null;
	public static Boolean lblreduceErrorPruning_Chk = null;
	public static Boolean lblsaveinstanceData_Chk = null;
	public static Boolean lblsubtreeRaising_Chk = null;
	public static Boolean lblunproned_Chk = null;
	public static Boolean lbluseLaplece_Chk = null;
	public static Boolean lbluseMDLcorrection_Chk = null;

	@FXML
	void cancel_Btn(ActionEvent event) {
		fonkDefault();

		Stage stage = (Stage) idCancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save_Btn(ActionEvent event) {

		settings();
		
		SettingsToClassifiers ss = new SettingsToClassifiers();
		ss.j48(ClassificationController.j48);
		
		Stage stage = (Stage) idSaveBtn.getScene().getWindow();
		stage.close();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		batchSize_TextField.setText("100");
		minNumObj_TextField.setText("2");
		confidenceFactor_TextField.setText("0.25");
		numDecimalPlaces_TextField.setText("2");
		numFolds_TextField.setText("3");
		seed_TextField.setText("1");

		fonkDefault();

	}

	public void settings() {

		if (batchSize_TextField.toString() != null && confidenceFactor_TextField.toString() != null
				&& minNumObj_TextField.toString() != null && numDecimalPlaces_TextField.toString() != null
				&& numFolds_TextField.toString() != null && seed_TextField.toString() != null) {

			lblbatchSize_TextField = batchSize_TextField.getText();
			lblconfidenceFactor_TextField = confidenceFactor_TextField.getText();
			lblminNumObj_TextField = minNumObj_TextField.getText();
			lblnumDecimalPlaces_TextField = numDecimalPlaces_TextField.getText();
			lblnumFolds_TextField = numFolds_TextField.getText();
			lblseed_TextField = seed_TextField.getText();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
		}

		if (binarySplits_Chk.isSelected()) {
			lblbinarySplits_Chk = true;
		} else {
			lblbinarySplits_Chk = false;
		}

		if (collapseTree_Chk.isSelected()) {
			lblcollapseTree_Chk = true;
		} else {
			lblcollapseTree_Chk = false;
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

		if (doNotMakeSplitPointActualValue_Chk.isSelected()) {
			lbldoNotMakeSplitPointActualValue_Chk = true;
		} else {
			lbldoNotMakeSplitPointActualValue_Chk = false;
		}

		if (reduceErrorPruning_Chk.isSelected()) {
			lblreduceErrorPruning_Chk = true;
		} else {
			lblreduceErrorPruning_Chk = false;
		}

		if (saveinstanceData_Chk.isSelected()) {
			lblsaveinstanceData_Chk = true;
		} else {
			lblsaveinstanceData_Chk = false;
		}

		if (subtreeRaising_Chk.isSelected()) {
			lblsubtreeRaising_Chk = true;
		} else {
			lblsubtreeRaising_Chk = false;
		}

		if (unproned_Chk.isSelected()) {
			lblunproned_Chk = true;
		} else {
			lblunproned_Chk = false;
		}

		if (useLaplece_Chk.isSelected()) {
			lbluseLaplece_Chk = true;
		} else {
			lbluseLaplece_Chk = false;
		}

		if (useMDLcorrection_Chk.isSelected()) {
			lbluseMDLcorrection_Chk = true;
		} else {
			lbluseMDLcorrection_Chk = false;
		}

	}

	public void fonkDefault() {

		lblbatchSize_TextField = "100";
		lblminNumObj_TextField = "2";
		lblconfidenceFactor_TextField = "0.25";
		lblnumDecimalPlaces_TextField = "2";
		lblnumFolds_TextField = "3";
		lblseed_TextField = "1";

		lblbinarySplits_Chk = false;
		lblcollapseTree_Chk = true;
		lbldebug_Chk = false;
		lbldoNotCheckCapabilities_Chk = false;
		lbldoNotMakeSplitPointActualValue_Chk = false;
		lblreduceErrorPruning_Chk = false;
		lblsaveinstanceData_Chk = false;
		lblsubtreeRaising_Chk = true;
		lblunproned_Chk = false;
		lbluseLaplece_Chk = false;
		lbluseMDLcorrection_Chk = true;

	}

}
