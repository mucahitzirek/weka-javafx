package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class PCASettingsController implements Initializable {

	@FXML
	private JFXTextField maximumAttributesNames_TextField;

	@FXML
	private JFXTextField varianceCovered_TextField;

	@FXML
	private JFXCheckBox centerData_Chk;

	@FXML
	private JFXCheckBox doNotCheckCapabilities_Chk;

	@FXML
	private JFXCheckBox transformBackToOriginal_Chk;

	@FXML
	private JFXButton idSaveButton;

	@FXML
	private JFXButton idCancelButton;

	public static String lblmaximumAttributesNames_TextField;
	public static String lblvarianceCovered_TextField;

	public static boolean lblcenterData_Chk;
	public static boolean lbldoNotCheckCapabilities_Chk;
	public static boolean lbltransformBackToOriginal_Chk;

	@FXML
	void cancel_Btn(ActionEvent event) {
		defaultt();
		Stage stage = (Stage) idSaveButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void save_Btn(ActionEvent event) {
		lblmaximumAttributesNames_TextField=maximumAttributesNames_TextField.getText();
		lblvarianceCovered_TextField=varianceCovered_TextField.getText();
		
		if(centerData_Chk.isSelected()) {
			lblcenterData_Chk=true;
		}
		else {
			lblcenterData_Chk=false;
		}
		if(doNotCheckCapabilities_Chk.isSelected()) {
			lbldoNotCheckCapabilities_Chk=true;
		}
		else {
			lbldoNotCheckCapabilities_Chk=false;
		}
		if(transformBackToOriginal_Chk.isSelected()) {
			lbltransformBackToOriginal_Chk=true;
		}
		else {
			lbltransformBackToOriginal_Chk=false;
		}
		
		Stage stage = (Stage) idSaveButton.getScene().getWindow();
		stage.close();
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		defaultt();
		
	
		
	}
	
	public void defaultt() {
		maximumAttributesNames_TextField.setText("5");
		varianceCovered_TextField.setText("0.95");
		
		
		lblmaximumAttributesNames_TextField="5";
		lblvarianceCovered_TextField="0.95";
		lbltransformBackToOriginal_Chk=false;
		lbldoNotCheckCapabilities_Chk=false;
		lblcenterData_Chk=false;
	}

}
