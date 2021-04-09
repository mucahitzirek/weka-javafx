package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertOlustur {
	
	public static void showAlert(AlertType type, String title, String header, String text) {
		
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.showAndWait();
	}
}
