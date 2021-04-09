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

public class LogisticSettingsController implements Initializable {

	@FXML
	private JFXTextField batchSize_TextField;

	@FXML
	private JFXTextField maxIts_TextField;

	@FXML
	private JFXTextField numDecimalPlaces_TextField;

	@FXML
	private JFXTextField ridge_TextField;

	@FXML
	private JFXCheckBox debug_Chk;

	@FXML
	private JFXCheckBox doNotCheckCapabilities_Chk;

	@FXML
	private JFXCheckBox useConjugateDradientDescent_Chk;

	@FXML
	private JFXButton idSaveBtn;

	@FXML
	private JFXButton idCancelBtn;

	public static String lblbatchSize_TextField = null;
	public static String lblmaxIts_TextField = null;
	public static String lblnumDecimalPlaces_TextField = null;
	public static String lblridge_TextField = null;

	public static Boolean lbldebug_Chk = null;
	public static Boolean lbldoNotCheckCapabilities_Chk = null;
	public static Boolean lbluseConjugateDradientDescent_Chk = null;

	@FXML
	void cancel_Btn(ActionEvent event) {
		
		fonkDefault();
		
		Stage stage = (Stage) idSaveBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save_Btn(ActionEvent event) {

		settings();
		
		SettingsToClassifiers settingsToClassifiers = new SettingsToClassifiers();
		settingsToClassifiers.logistic(ClassificationController.logistic);

		Stage stage = (Stage) idSaveBtn.getScene().getWindow();
		stage.close();
	}

	public void settings() {

		if (batchSize_TextField.toString() != null && numDecimalPlaces_TextField.toString() != null
				&& ridge_TextField.toString() != null && maxIts_TextField.toString() != null) {

			lblbatchSize_TextField = batchSize_TextField.getText();
			lblmaxIts_TextField = maxIts_TextField.getText();
			lblnumDecimalPlaces_TextField = numDecimalPlaces_TextField.getText();
			lblridge_TextField = ridge_TextField.getText();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
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
		if (useConjugateDradientDescent_Chk.isSelected()) {
			lbluseConjugateDradientDescent_Chk = true;
		} else {
			lbluseConjugateDradientDescent_Chk = false;
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		batchSize_TextField.setText("100");
		maxIts_TextField.setText("-1");
		numDecimalPlaces_TextField.setText("4");
		ridge_TextField.setText("1.0E-8");
		
		fonkDefault();
	}

	public void fonkDefault() {

		lblbatchSize_TextField = "100";
		lblmaxIts_TextField = "-1";
		lblnumDecimalPlaces_TextField = "4";
		lblridge_TextField = "1.0E-8";

		lbldebug_Chk = false;
		lbldoNotCheckCapabilities_Chk = false;
		lbluseConjugateDradientDescent_Chk = false;
	}
}
