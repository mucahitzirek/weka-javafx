package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class GetSummaryController implements Initializable {

	@FXML
	private TextArea summary_TextArea;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (KnnSettingsController.wichGetSummary== true) {
			summary_TextArea.setText(KnnRangeTableController.getSuymmaryString);

		}else {
			summary_TextArea.setText(ClassificationController.getSummary);	
		}
		

	}

}
