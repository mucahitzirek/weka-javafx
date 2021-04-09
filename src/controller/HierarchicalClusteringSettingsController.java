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
import model.SettingsToClustering;

public class HierarchicalClusteringSettingsController implements Initializable {

	@FXML
	private JFXTextField numClusters_TextField;

	@FXML
	private JFXCheckBox debug_Chk;

	@FXML
	private JFXCheckBox distancelsBranchLength_Chk;

	@FXML
	private JFXCheckBox doNotCheckCapabilities_Chk;

	@FXML
	private JFXCheckBox printNewick_Chk;

	@FXML
	private JFXButton idSaveBtn;

	@FXML
	private JFXButton idCancelBtn;

	public static String lblnumClusters_TextField = null;

	public static Boolean lbldebug_Chk = null;
	public static Boolean lbldistancelsBranchLength_Chk = null;
	public static Boolean lbldoNotCheckCapabilities_Chk = null;
	public static Boolean lblprintNewick_Chk = null;

	@FXML
	void cancel_Btn(ActionEvent event) {
		fonkDefauld();

		Stage stage = (Stage) idCancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save_Btn(ActionEvent event) {
		settings();
		SettingsToClustering settingsToClustering = new SettingsToClustering();
		settingsToClustering.hierarchical(ClusteringController.hierarchicalClusterer);
		Stage stage = (Stage) idSaveBtn.getScene().getWindow();
		stage.close();
	}

	public void settings() {
		if (numClusters_TextField.toString() != null) {
			lblnumClusters_TextField = numClusters_TextField.getText();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
		}

		if (debug_Chk.isSelected()) {
			lbldebug_Chk = true;
		} else {
			lbldebug_Chk = false;
		}

		if (distancelsBranchLength_Chk.isSelected()) {
			lbldistancelsBranchLength_Chk = true;
		} else {
			lbldistancelsBranchLength_Chk = false;
		}

		if (doNotCheckCapabilities_Chk.isSelected()) {
			lbldoNotCheckCapabilities_Chk = true;
		} else {
			lbldoNotCheckCapabilities_Chk = false;
		}

		if (printNewick_Chk.isSelected()) {
			lblprintNewick_Chk = true;
		} else {
			lblprintNewick_Chk = false;
		}

	}

	public void fonkDefauld() {

		lblnumClusters_TextField = "2";
		lbldebug_Chk = false;
		lbldistancelsBranchLength_Chk = false;
		lbldoNotCheckCapabilities_Chk = false;
		lblprintNewick_Chk = true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		numClusters_TextField.setText("2");

		fonkDefauld();
	}

}
