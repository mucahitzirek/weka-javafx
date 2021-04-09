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

public class NaiveBayesSettingsController implements Initializable {

	@FXML
	private JFXTextField batchSize_TextField;

	@FXML
	private JFXTextField numDecimalPlaces_TextField;

	@FXML
	private JFXCheckBox debug_Chk;

	@FXML
	private JFXCheckBox displayModelnOldFormat_Chk;

	@FXML
	private JFXCheckBox useKernelEstimator_Chk;

	@FXML
	private JFXCheckBox useSupervisedDiscretization_Chk;

	@FXML
	private JFXButton idSaveBtn;

	@FXML
	private JFXButton idCancelBtn;

	public static String lblbatchSize_TextField = null;
	public static String lblnumDecimalPlaces_TextField = null;

	public static Boolean lbldebug_Chk = null;
	public static Boolean lbldisplayModelnOldFormat_Chk = null;
	public static Boolean lbluseKernelEstimator_Chk = null;
	public static Boolean lbluseSupervisedDiscretization_Chk = null;

	@FXML
	void cancel_Btn(ActionEvent event) {
		Stage stage = (Stage) idCancelBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save_Btn(ActionEvent event) {
		settings();
		SettingsToClassifiers ss = new SettingsToClassifiers();
		ss.naiveBayes(ClassificationController.bayes);
		Stage stage = (Stage) idSaveBtn.getScene().getWindow();
		stage.close();
	}

	public void settings() {

		if (batchSize_TextField.toString() != null && numDecimalPlaces_TextField.toString() != null) {

			lblbatchSize_TextField = batchSize_TextField.getText();
			lblnumDecimalPlaces_TextField = numDecimalPlaces_TextField.getText();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
		}

		if (debug_Chk.isSelected()) {
			lbldebug_Chk = true;
		} else {
			lbldebug_Chk = false;
		}

		if (displayModelnOldFormat_Chk.isSelected()) {
			lbldisplayModelnOldFormat_Chk = true;
		} else {
			lbldisplayModelnOldFormat_Chk = false;
		}

		if (useKernelEstimator_Chk.isSelected()) {
			lbluseKernelEstimator_Chk = true;
		} else {
			lbluseKernelEstimator_Chk = false;
		}

		if (useSupervisedDiscretization_Chk.isSelected()) {
			lbluseSupervisedDiscretization_Chk = true;
		} else {
			lbluseSupervisedDiscretization_Chk = false;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		batchSize_TextField.setText("100");
		numDecimalPlaces_TextField.setText("2");

		lbldebug_Chk = false;
		lbldisplayModelnOldFormat_Chk = false;
		lbluseKernelEstimator_Chk = false;
		lbluseSupervisedDiscretization_Chk = false;

		fonDefalut();
	}

	public void fonDefalut() {

		lblbatchSize_TextField = "100";
		lblnumDecimalPlaces_TextField = "2";

		lbldebug_Chk = false;
		lbldisplayModelnOldFormat_Chk = false;
		lbluseKernelEstimator_Chk = false;
		lbluseSupervisedDiscretization_Chk = false;

	}

}
