package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.AlertOlustur;
import model.SettingsToClassifiers;

public class KnnSettingsController implements Initializable {

	@FXML
	private JFXRadioButton forKnn_RadioBtn;

	@FXML
	private ToggleGroup knn_RadiToggleGroup;

	@FXML
	private Label knn_Label;

	@FXML
	private JFXTextField knn_TextField;

	@FXML
	private JFXRadioButton forKnnRange_RadioBtn;

	@FXML
	private Label minKnn_Label;

	@FXML
	private JFXTextField minKnn_TextField;

	@FXML
	private Label maxKnn_Label;

	@FXML
	private JFXTextField maxKnn_TextField;

	@FXML
	private JFXTextField batchSize_TextField;

	@FXML
	private JFXTextField numDecimalPlaces_TextField;

	@FXML
	private JFXTextField windowSize_TextField;

	@FXML
	private JFXCheckBox crossValidate_Chk;

	@FXML
	private JFXCheckBox debug_Chk;

	@FXML
	private JFXCheckBox doNotCheckCapabilities_Chk;

	@FXML
	private JFXCheckBox meanSquared_Chk;

	public static String lblknn_TextField = null;
	public static String lblminKnn_TextField = null;
	public static String lblmaxKnn_TextField = null;
	public static String lblbatchSize_TextField = null;
	public static String lblnumDecimalPlaces_TextField = null;
	public static String lblwindowSize_TextField = null;
	public static Boolean lblcrossValidate_Chk = null;
	public static Boolean lbldebug_Chk = null;
	public static Boolean lbldoNotCheckCapabilities_Chk = null;
	public static Boolean lblmeanSquared_Chk = null;

	public static Boolean wichRadio = null;

	public static Boolean wichGetSummary = null;

	@FXML
	private JFXButton idSaveButton;

	@FXML
	private JFXButton idCancelButton;

	@FXML
	void cancel_Btn(ActionEvent event) {
		Stage stage = (Stage) idCancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save_Btn(ActionEvent event) {
		settings();
		oneK();
		multipleK();
		SettingsToClassifiers ss = new SettingsToClassifiers();
		ss.Knn(ClassificationController.ibk);
		Stage stage = (Stage) idSaveButton.getScene().getWindow();
		stage.close();

	}

	@FXML
	void fonkForKnn(ActionEvent event) {
		wichRadio = true;
		wichGetSummary=true;
		if (forKnn_RadioBtn.isSelected()) {
			knn_Label.setVisible(true);
			knn_TextField.setVisible(true);

			minKnn_Label.setVisible(false);
			maxKnn_Label.setVisible(false);
			minKnn_TextField.setVisible(false);
			maxKnn_TextField.setVisible(false);
		}

	}

	@FXML
	void fonkForKnnRange(ActionEvent event) {
		if(MainController.fileIsNumeric==false) {
			
		
		wichGetSummary=true;
		wichRadio = false;
		if (forKnnRange_RadioBtn.isSelected()) {

			knn_Label.setVisible(false);
			knn_TextField.setVisible(false);

			minKnn_Label.setVisible(true);
			maxKnn_Label.setVisible(true);
			minKnn_TextField.setVisible(true);
			maxKnn_TextField.setVisible(true);

		}
		}	
		
		else {
			AlertOlustur.showAlert(AlertType.INFORMATION, "Bilgi", null, "dosya numerik ise aralýk alýnamaz");
			Stage stage = (Stage) idSaveButton.getScene().getWindow();
			stage.close();
			
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		forKnn_RadioBtn.setToggleGroup(knn_RadiToggleGroup);
		forKnnRange_RadioBtn.setToggleGroup(knn_RadiToggleGroup);

		knn_Label.setVisible(true);
		knn_TextField.setVisible(true);
		wichRadio = true;
		minKnn_Label.setVisible(false);
		maxKnn_Label.setVisible(false);
		minKnn_TextField.setVisible(false);
		maxKnn_TextField.setVisible(false);

		knn_TextField.setText("1");
		minKnn_TextField.setText("1");
		maxKnn_TextField.setText("5");
		numDecimalPlaces_TextField.setText("2");
		batchSize_TextField.setText("100");
		windowSize_TextField.setText("0");

		lbldebug_Chk = false;
		lbldoNotCheckCapabilities_Chk = false;
		lblcrossValidate_Chk = false;
		lblmeanSquared_Chk = false;
	}

	public void settings() {

		if (batchSize_TextField.toString() != null && numDecimalPlaces_TextField.toString() != null
				&& windowSize_TextField.toString() != null) {
			lblnumDecimalPlaces_TextField = numDecimalPlaces_TextField.getText();
			lblbatchSize_TextField = batchSize_TextField.getText();
			lblwindowSize_TextField = windowSize_TextField.getText();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
		}

		if (crossValidate_Chk.isSelected()) {
			lblcrossValidate_Chk = true;
		} else {
			lblcrossValidate_Chk = false;
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

		if (meanSquared_Chk.isSelected()) {
			lblmeanSquared_Chk = true;
		} else {
			lblmeanSquared_Chk = false;
		}

	}

	public void oneK() {
		if (knn_TextField != null) {
			lblknn_TextField = knn_TextField.getText();

		}
	}

	public void multipleK() {
		if (minKnn_TextField.toString() != null && maxKnn_TextField.toString() != null) {
			lblminKnn_TextField = minKnn_TextField.getText();
			lblmaxKnn_TextField = maxKnn_TextField.getText();

		}
	}

}
