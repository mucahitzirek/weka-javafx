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

public class RandomForestSettingsController implements Initializable {
	@FXML
	private JFXTextField bagSizePercent_TextField;

	@FXML
	private JFXTextField batchSize_TextField;

	@FXML
	private JFXTextField maxDepth_TextField;

	@FXML
	private JFXTextField numDecimalPlaces_TextField;

	@FXML
	private JFXTextField numExecutionSlots_TextField;

	@FXML
	private JFXTextField numFeatures_TextField;

	@FXML
	private JFXTextField numIterations_TextField;

	@FXML
	private JFXTextField seed_TextField;

	@FXML
	private JFXCheckBox breakTiesRandomly_Chk;

	@FXML
	private JFXCheckBox calcOuntOfBag_Chk;

	@FXML
	private JFXCheckBox computeAttribudeImportance_Chk;

	@FXML
	private JFXCheckBox debug_Chk;

	@FXML
	private JFXCheckBox doNotCheckCapabilities_Chk;

	@FXML
	private JFXCheckBox outputOutOfBagComplexityStatistic_Chk;

	@FXML
	private JFXCheckBox printClassifiers_Chk;

	@FXML
	private JFXCheckBox storeOutOfBagPredictions_Chk;

	@FXML
	private JFXButton idCancelBtn;

	@FXML
	private JFXButton idSaveBtn;

	public static String lblbagSizePercent_TextField = null;
	public static String lblbatchSize_TextField = null;
	public static String lblmaxDepth_TextField = null;
	public static String lblnumDecimalPlaces_TextField = null;
	public static String lblnumExecutionSlots_TextField = null;
	public static String lblnumFeatures_TextField = null;
	public static String lblnumIterations_TextField = null;
	public static String lblseed_TextField = null;

	public static Boolean lblbreakTiesRandomly_RadioBtn = null;
	public static Boolean lblcalcOuntOfBag_RadioBtn = null;
	public static Boolean lblcomputeAttribudeImportance_RadioBtn = null;
	public static Boolean lbldebug_RadioBtn = null;
	public static Boolean lbldoNotCheckCapabilities_RadioBtn = null;
	public static Boolean lbloutputOutOfBagComplexityStatistic_RadioBtn = null;
	public static Boolean lblprintClassifiers_RadioBtn = null;
	public static Boolean lblstoreOutOfBagPredictions_RadioBtn = null;

	@FXML
	void cancel_Btn(ActionEvent event) {

		Stage stage = (Stage) idCancelBtn.getScene().getWindow();
		stage.close();

		fonkDefault();
	}

	@FXML
	void save_Btn(ActionEvent event) {

		settings();

		SettingsToClassifiers ss = new SettingsToClassifiers();
		ss.randomForest(ClassificationController.randomForest);

		Stage stage = (Stage) idSaveBtn.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		fonkDefault();

		bagSizePercent_TextField.setText("100");
		batchSize_TextField.setText("100");
		maxDepth_TextField.setText("0");
		numDecimalPlaces_TextField.setText("2");
		numExecutionSlots_TextField.setText("1");
		numFeatures_TextField.setText("0");
		numIterations_TextField.setText("100");
		seed_TextField.setText("1");

		lblbreakTiesRandomly_RadioBtn = false;
		lblcalcOuntOfBag_RadioBtn = false;
		lblcomputeAttribudeImportance_RadioBtn = false;
		lbldebug_RadioBtn = false;
		lbloutputOutOfBagComplexityStatistic_RadioBtn = false;
		lblprintClassifiers_RadioBtn = false;
		lblstoreOutOfBagPredictions_RadioBtn = false;

	}

	public void settings() {

		if (lblbagSizePercent_TextField.toString() != null && lblbatchSize_TextField.toString() != null
				&& lblmaxDepth_TextField.toString() != null && lblnumDecimalPlaces_TextField.toString() != null
				&& lblnumExecutionSlots_TextField.toString() != null && lblnumFeatures_TextField.toString() != null
				&& lblnumIterations_TextField.toString() != null && lblseed_TextField.toString() != null) {

			lblbagSizePercent_TextField = bagSizePercent_TextField.getText();
			lblbatchSize_TextField = batchSize_TextField.getText();
			lblmaxDepth_TextField = maxDepth_TextField.getText();
			lblnumDecimalPlaces_TextField = numDecimalPlaces_TextField.getText();
			lblnumExecutionSlots_TextField = numExecutionSlots_TextField.getText();
			lblnumFeatures_TextField = numFeatures_TextField.getText();
			lblnumIterations_TextField = numIterations_TextField.getText();
			lblseed_TextField = seed_TextField.getText();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
		}

		if (breakTiesRandomly_Chk.isSelected()) {
			lblbreakTiesRandomly_RadioBtn = true;
		} else {
			lblbreakTiesRandomly_RadioBtn = false;
		}

		if (calcOuntOfBag_Chk.isSelected()) {
			lblcalcOuntOfBag_RadioBtn = true;
		} else {
			lblcalcOuntOfBag_RadioBtn = false;
		}

		if (computeAttribudeImportance_Chk.isSelected()) {
			lblcomputeAttribudeImportance_RadioBtn = true;
		} else {
			lblcomputeAttribudeImportance_RadioBtn = false;
		}

		if (debug_Chk.isSelected()) {
			lbldebug_RadioBtn = true;
		} else {
			lbldebug_RadioBtn = false;
		}

		if (doNotCheckCapabilities_Chk.isSelected()) {
			lbldoNotCheckCapabilities_RadioBtn = true;
		} else {
			lbldoNotCheckCapabilities_RadioBtn = false;
		}

		if (outputOutOfBagComplexityStatistic_Chk.isSelected()) {
			lbloutputOutOfBagComplexityStatistic_RadioBtn = true;
		} else {
			lbloutputOutOfBagComplexityStatistic_RadioBtn = false;
		}

		if (printClassifiers_Chk.isSelected()) {
			lblprintClassifiers_RadioBtn = true;
		} else {
			lblprintClassifiers_RadioBtn = false;
		}

		if (storeOutOfBagPredictions_Chk.isSelected()) {
			lblstoreOutOfBagPredictions_RadioBtn = true;
		} else {
			lblstoreOutOfBagPredictions_RadioBtn = false;
		}

	}

	public void fonkDefault() {

		lblbagSizePercent_TextField = "100";
		lblbatchSize_TextField = "100";
		lblbreakTiesRandomly_RadioBtn = false;
		lblcalcOuntOfBag_RadioBtn = false;
		lblcomputeAttribudeImportance_RadioBtn = false;
		lbldebug_RadioBtn = false;
		lblmaxDepth_TextField = "0";
		lblnumDecimalPlaces_TextField = "2";
		lblnumExecutionSlots_TextField = "1";
		lblnumFeatures_TextField = "0";
		lblnumIterations_TextField = "100";
		lbloutputOutOfBagComplexityStatistic_RadioBtn = false;
		lblprintClassifiers_RadioBtn = false;
		lblseed_TextField = "1";
		lblstoreOutOfBagPredictions_RadioBtn = false;

	}

}
