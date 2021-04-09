package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShowPage {

	public static void settingShow(String filePath, String title) {

		try {
			URL url = new File(filePath).toURI().toURL();
			Parent fxml = FXMLLoader.load(url);
			Stage stage = new Stage();
			Scene scene = new Scene(fxml);
			stage.setTitle(title);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {

			System.out.println(title + " Hata :" + e.getMessage());
		}
	}

}
