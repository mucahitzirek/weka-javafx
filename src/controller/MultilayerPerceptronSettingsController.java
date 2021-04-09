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
import model.ToolTip;

public class MultilayerPerceptronSettingsController implements Initializable {

	@FXML
	private JFXTextField batchSize_TextField;

	@FXML
	private JFXTextField hiddenLayers_TextField;

	@FXML
	private JFXTextField learningRate_TextField;

	@FXML
	private JFXTextField numDecimalPlaces_TextField;

	@FXML
	private JFXTextField momentum_TextField;

	@FXML
	private JFXTextField seed_TextField;

	@FXML
	private JFXTextField traningTime_TextField;

	@FXML
	private JFXTextField validationSetSize_TextField;

	@FXML
	private JFXTextField validationThreshold_TextField;

	@FXML
	private JFXCheckBox gui;

	@FXML
	private JFXCheckBox aoutoBuild_Chk;

	@FXML
	private JFXCheckBox debug_Chk;

	@FXML
	private JFXCheckBox decay_Chk;

	@FXML
	private JFXCheckBox doNotCheckCapabilities_Chk;

	@FXML
	private JFXCheckBox nominalToBinaryFilter_Chk;

	@FXML
	private JFXCheckBox normalizeAttributes_Chk;

	@FXML
	private JFXCheckBox normalizeNumericClass_Chk;

	@FXML
	private JFXCheckBox reset_Chk;

	@FXML
	private JFXCheckBox resume_Chk;

	@FXML
	private JFXButton idSaveBtn;

	@FXML
	private JFXButton robotAnn;

	@FXML
	private JFXButton idCancelBtn;

	public static String lblbatchSize_TextField = null;
	public static String lblhiddenLayers_TextField = null;
	public static String lbllearningRate_TextField = null;
	public static String lblnumDecimalPlaces_TextField = null;
	public static String lblmomentum_TextField = null;
	public static String lblseed_TextField = null;
	public static String lblvalidationSetSize_TextField = null;
	public static String lbltraningTime_TextField = null;
	public static String lblvalidationThreshold_TextField = null;

	public static Boolean lblgui_Chk = null;
	public static Boolean lblaoutoBuild_Chk = null;
	public static Boolean lbldebug_Chk = null;
	public static Boolean lbldecay_Chk = null;
	public static Boolean lbldoNotCheckCapabilities_Chk = null;
	public static Boolean lblnominalToBinaryFilter_Chk = null;
	public static Boolean lblnormalizeAttributes_Chk = null;
	public static Boolean lblnormalizeNumericClass_Chk = null;
	public static Boolean lblreset_Chk = null;
	public static Boolean lblresume_Chk = null;

	@FXML
	void cancel_Btn(ActionEvent event) {

		fonkDefalut();

		Stage stage = (Stage) idCancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void fonkGit(ActionEvent event) {

		settings();

		SettingsToClassifiers classifiers = new SettingsToClassifiers();
		classifiers.multiplayerPerceptronSettingsController(MlpTabloController.mlp);

		ShowPage.settingShow("src/view/Mlp.fxml", "Mlp");

	}

	@FXML
	void save_Btn(ActionEvent event) {

		settings();

		SettingsToClassifiers ss = new SettingsToClassifiers();
		ss.multiplayerPerceptronSettingsController(ClassificationController.multilayerPerceptron);

		Stage stage = (Stage) idSaveBtn.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		robotAnn.setTooltip(ToolTip.tolltipAnn());

		batchSize_TextField.setText("100");
		hiddenLayers_TextField.setText("a");
		learningRate_TextField.setText("0.3");
		momentum_TextField.setText("0.3");
		numDecimalPlaces_TextField.setText("2");
		seed_TextField.setText("0");
		traningTime_TextField.setText("500");
		validationSetSize_TextField.setText("0");
		validationThreshold_TextField.setText("20");

		fonkDefalut();

	}

	public void settings() {

		if (batchSize_TextField.toString() != null && hiddenLayers_TextField.toString() != null
				&& learningRate_TextField.toString() != null && numDecimalPlaces_TextField.toString() != null
				&& momentum_TextField.toString() != null && seed_TextField.toString() != null
				&& validationSetSize_TextField.toString() != null && traningTime_TextField.toString() != null
				&& validationThreshold_TextField.toString() != null) {

			lblbatchSize_TextField = batchSize_TextField.getText();
			lblhiddenLayers_TextField = hiddenLayers_TextField.getText();
			lbllearningRate_TextField = learningRate_TextField.getText();
			lblnumDecimalPlaces_TextField = numDecimalPlaces_TextField.getText();
			lblseed_TextField = seed_TextField.getText();
			lblvalidationSetSize_TextField = validationSetSize_TextField.getText();
			lbltraningTime_TextField = traningTime_TextField.getText();
			lblvalidationThreshold_TextField = validationThreshold_TextField.getText();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
		}

		if (gui.isSelected()) {
			lblgui_Chk = true;
		} else {
			lblgui_Chk = false;
		}

		if (aoutoBuild_Chk.isSelected()) {
			lblaoutoBuild_Chk = true;
		} else {
			lblaoutoBuild_Chk = false;
		}

		if (debug_Chk.isSelected()) {
			lbldebug_Chk = true;
		} else {
			lbldebug_Chk = false;
		}

		if (decay_Chk.isSelected()) {
			lbldecay_Chk = true;
		} else {
			lbldecay_Chk = false;
		}

		if (doNotCheckCapabilities_Chk.isSelected()) {
			lbldoNotCheckCapabilities_Chk = true;
		} else {
			lbldoNotCheckCapabilities_Chk = false;
		}

		if (nominalToBinaryFilter_Chk.isSelected()) {
			lblnominalToBinaryFilter_Chk = true;
		} else {
			lblnominalToBinaryFilter_Chk = false;
		}

		if (normalizeAttributes_Chk.isSelected()) {
			lblnormalizeAttributes_Chk = true;
		} else {
			lblnormalizeAttributes_Chk = false;
		}

		if (normalizeNumericClass_Chk.isSelected()) {
			lblnormalizeNumericClass_Chk = true;
		} else {
			lblnormalizeNumericClass_Chk = false;
		}

		if (reset_Chk.isSelected()) {
			lblreset_Chk = true;
		} else {
			lblreset_Chk = false;
		}

		if (resume_Chk.isSelected()) {
			lblresume_Chk = true;
		} else {
			lblresume_Chk = false;
		}

	}

	public void fonkDefalut() {

		lblbatchSize_TextField = "100";
		lblhiddenLayers_TextField = "a";
		lbllearningRate_TextField = "0.3";
		lblmomentum_TextField = "0.3";
		lblnumDecimalPlaces_TextField = "2";
		lblseed_TextField = "0";
		lbltraningTime_TextField = "500";
		lblvalidationSetSize_TextField = "0";
		lblvalidationThreshold_TextField = "=";

		lblgui_Chk = true;
		lblaoutoBuild_Chk = true;
		lbldebug_Chk = false;
		lbldecay_Chk = false;
		lbldoNotCheckCapabilities_Chk = false;
		lblnominalToBinaryFilter_Chk = true;
		lblnormalizeAttributes_Chk = true;
		lblnormalizeNumericClass_Chk = true;
		lblreset_Chk = true;
		lblresume_Chk = false;

	}

}
