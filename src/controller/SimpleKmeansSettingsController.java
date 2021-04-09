package controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.SettingsToClustering;
import weka.core.SelectedTag;

public class SimpleKmeansSettingsController implements Initializable {

	@FXML
	private JFXTextField canopyMaxNumCanoplacesToHaldnMemory_TextField;

	@FXML
	private JFXTextField canopyMinimumCanopyDensity_TextField;

	@FXML
	private JFXTextField canopyPeriodicPruningRate_TextField;

	@FXML
	private JFXTextField canopyT1_TextField;

	@FXML
	private JFXTextField canopyT2_TextField;

	@FXML
	private JFXTextField maxIterations_TextField;

	@FXML
	private JFXTextField numClusters_TextField;

	@FXML
	private JFXTextField numExecutionSlots_TextField;

	@FXML
	private JFXTextField seed_TextField;

	@FXML
	private JFXCheckBox debug_Chk;

	@FXML
	private JFXCheckBox displayStdDevs_Chk;

	@FXML
	private JFXCheckBox doNotCheckCapabilities_Chk;

	@FXML
	private JFXCheckBox dontReplaceMissingValues_Chk;

	@FXML
	private JFXCheckBox fastDistanceCalc_Chk;

	@FXML
	private JFXComboBox<SelectedTag> initializationMethod_Cmb;

	@FXML
	private JFXCheckBox preserveInstancesSlots_Chk;

	@FXML
	private JFXCheckBox reduceNumberOfDistanceCalcsViaCanopies_Chk;

	@FXML
	private JFXButton idSaveBtn;

	@FXML
	private JFXButton idCancelBtn;

	public static String lblcanopyMaxNumCanoplacesToHaldnMemory_TextField = null;
	public static String lblcanopyMinimumCanopyDensity_TextField = null;
	public static String lblcanopyPeriodicPruningRate_TextField = null;
	public static String lblcanopyT1_TextField = null;
	public static String lblcanopyT2_TextField = null;
	public static String lblmaxIterations_TextField = null;
	public static String lblnumClusters_TextField = null;
	public static String lblnumExecutionSlots_TextField = null;
	public static String lblseed_TextField = null;

	public static Boolean lbldebug_Chk = null;
	public static Boolean lbldisplayStdDevs_Chk = null;
	public static Boolean lbldoNotCheckCapabilities_Chk = null;
	public static Boolean lbldontReplaceMissingValues_Chk = null;
	public static Boolean lblfastDistanceCalc_Chk = null;
	public static Boolean lblpreserveInstancesSlots_Chk = null;
	public static Boolean lblreduceNumberOfDistanceCalcsViaCanopies_Chk = null;

	public static SelectedTag lblSelectedTag;
	public static String initCbmString;

	@FXML
	void cancel_Btn(ActionEvent event) {
		fonDefault();

		Stage stage = (Stage) idCancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save_Btn(ActionEvent event) throws NumberFormatException, Exception {

		settings();

		SettingsToClustering settingsToClustering = new SettingsToClustering();
		settingsToClustering.kmeans(ClusteringController.simpleKmeans);

		Stage stage = (Stage) idSaveBtn.getScene().getWindow();
		stage.close();
	}

	public void settings() {

		if (canopyMaxNumCanoplacesToHaldnMemory_TextField.toString() != null
				&& canopyMinimumCanopyDensity_TextField.toString() != null
				&& canopyPeriodicPruningRate_TextField.toString() != null && canopyT1_TextField.toString() != null
				&& canopyT2_TextField.toString() != null && maxIterations_TextField.toString() != null
				&& numClusters_TextField.toString() != null && numExecutionSlots_TextField.toString() != null
				&& seed_TextField.toString() != null) {

			lblcanopyMaxNumCanoplacesToHaldnMemory_TextField = canopyMaxNumCanoplacesToHaldnMemory_TextField.getText();
			lblcanopyMinimumCanopyDensity_TextField = canopyMinimumCanopyDensity_TextField.getText();
			lblcanopyPeriodicPruningRate_TextField = canopyPeriodicPruningRate_TextField.getText();
			lblcanopyT1_TextField = canopyT1_TextField.getText();
			lblmaxIterations_TextField = maxIterations_TextField.getText();
			lblnumClusters_TextField = numClusters_TextField.getText();
			lblnumExecutionSlots_TextField = numExecutionSlots_TextField.getText();
			lblseed_TextField = seed_TextField.getText();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
		}

		if (debug_Chk.isSelected()) {
			lbldebug_Chk = true;
		} else {
			lbldebug_Chk = false;
		}

		if (displayStdDevs_Chk.isSelected()) {
			lbldisplayStdDevs_Chk = true;
		} else {
			lbldisplayStdDevs_Chk = false;
		}

		if (doNotCheckCapabilities_Chk.isSelected()) {
			lbldoNotCheckCapabilities_Chk = true;
		} else {
			lbldoNotCheckCapabilities_Chk = false;
		}

		if (dontReplaceMissingValues_Chk.isSelected()) {
			lbldontReplaceMissingValues_Chk = true;
		} else {
			lbldontReplaceMissingValues_Chk = false;
		}

		if (fastDistanceCalc_Chk.isSelected()) {
			lblfastDistanceCalc_Chk = true;
		} else {
			lblfastDistanceCalc_Chk = false;
		}

		if (preserveInstancesSlots_Chk.isSelected()) {
			lblpreserveInstancesSlots_Chk = true;
		} else {
			lblpreserveInstancesSlots_Chk = false;
		}

		if (reduceNumberOfDistanceCalcsViaCanopies_Chk.isSelected()) {
			lblreduceNumberOfDistanceCalcsViaCanopies_Chk = true;
		} else {
			lblreduceNumberOfDistanceCalcsViaCanopies_Chk = false;
		}

		if (initCbmString.equals("Random")) {

		} else if (initCbmString.equals("k-means++")) {

		} else if (initCbmString.equals("Canopy")) {

		} else if (initCbmString.equals("Farthers first")) {

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		canopyMaxNumCanoplacesToHaldnMemory_TextField.setText("100");
		canopyMinimumCanopyDensity_TextField.setText("2.0");
		canopyPeriodicPruningRate_TextField.setText("10000");
		canopyT1_TextField.setText("-1.25");
		canopyT2_TextField.setText("-1.0");
		maxIterations_TextField.setText("500");
		numClusters_TextField.setText("2");
		numExecutionSlots_TextField.setText("1");
		seed_TextField.setText("10");

		// initializationMethod_Cmb.getItems().addAll(list);

		fonDefault();
	}

	public void fonDefault() {

		lblcanopyMaxNumCanoplacesToHaldnMemory_TextField = "100";
		lblcanopyMinimumCanopyDensity_TextField = "2.0";
		lblcanopyPeriodicPruningRate_TextField = "10000";
		lblcanopyT1_TextField = "-1.25";
		lblcanopyT2_TextField = "-1.0";
		lblmaxIterations_TextField = "500";
		lblnumClusters_TextField = "2";
		lblnumExecutionSlots_TextField = "1";
		lblseed_TextField = "10";

		initCbmString = "Random";

		lbldebug_Chk = false;
		lbldisplayStdDevs_Chk = false;
		lbldoNotCheckCapabilities_Chk = false;
		lbldontReplaceMissingValues_Chk = false;
		lblfastDistanceCalc_Chk = false;
		lblpreserveInstancesSlots_Chk = false;
		lblreduceNumberOfDistanceCalcsViaCanopies_Chk = false;

	}

}
