package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.KnnForRange;
import model.SettingsToClassifiers;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class KnnRangeTableController implements Initializable {

	@FXML
	private TableView<KnnForRange> tblvw;

	@FXML
	private TableColumn<KnnForRange, String> ktblvw;

	@FXML
	private TableColumn<KnnForRange, Double> correcttblvw;

	@FXML
	private TableColumn<KnnForRange, Double> maetblvw;

	@FXML
	private TableColumn<KnnForRange, Double> raetblvw;

	@FXML
	private TableColumn<KnnForRange, Double> rmsetblvw;

	@FXML
	private TableColumn<KnnForRange, Double> rrsetblvw;

	@FXML
	private TableColumn<KnnForRange, Double> recalltblvw;

	@FXML
	private TableColumn<KnnForRange, Double> precisiontblvw;

	SettingsToClassifiers sts = new SettingsToClassifiers();

	ArrayList<KnnForRange> list = new ArrayList<>();
	public ObservableList<KnnForRange> list2 = FXCollections.observableArrayList();
	double maxCorrect, maxKValue;
	ArrayList<Double> corrects = new ArrayList<>();
	ClassificationController cnn = new ClassificationController();

	HashMap<String, Double> map = new HashMap<String, Double>();

	double newPctCorrect = 0, oldPctCorrect = 0;
	int kValue;
	public static String getSuymmaryString = null;
	public static Thread thread;
	public static int sayac = 0;
	@FXML
	private JFXButton idGetSummaryForKnn;

	@FXML
	void getSummaryForKnn(ActionEvent event) {
		try {
			URL url = new File("src/view/GetSummary.fxml").toURI().toURL();
			Parent fxml = FXMLLoader.load(url);
			Stage stage = new Stage();
			Scene scene = new Scene(fxml);
			stage.setTitle("Get Summary");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.out.println("Hata getSummaryForKnn :" + e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				return null;
			}

			@Override
			public void run() {

				if (KnnSettingsController.wichRadio == false) {

					try {

						DataSource source = new DataSource(MainController.secilen);
						Instances data = source.getDataSet();
						data.setClassIndex(data.numAttributes() - 1);

						IBk ibk = new IBk();

						int min = Integer.parseInt(KnnSettingsController.lblminKnn_TextField);
						int max = Integer.parseInt(KnnSettingsController.lblmaxKnn_TextField);
						kValue = max - min;
						sts.Knn(ibk);

						for (int i = min; i <= max; i++) {
							sayac++;
							ibk.setKNN(i);
							ibk.buildClassifier(data);
							Evaluation eval = new Evaluation(data);

							if (ClassificationController.knnCross == true) {
								Random rand = new Random(1);
								eval.crossValidateModel(ibk, data, ClassificationController.folds, rand);
								System.out.println(ClassificationController.folds);
							}

							else if (ClassificationController.knnPercent == true) {
								int trainSize = data.numInstances() * ClassificationController.percent / 100;
								int testSize = data.numInstances() - trainSize;

								Instances test = new Instances(data, trainSize, testSize);
								eval.evaluateModel(ibk, test);
							}

							else if (ClassificationController.knnUseTraining == true) {
								eval.evaluateModel(ibk, data);
							}

							KnnForRange obj = new KnnForRange(i, eval.pctCorrect(), eval.meanAbsoluteError(),
									eval.relativeAbsoluteError(), eval.rootMeanSquaredError(),
									eval.rootRelativeSquaredError(), eval.recall(1), eval.precision(1));

							list2.add(obj);

							// get best knn map.put summary - correct
							map.put("Best Value For Knn : " + i + "\n" + eval.toSummaryString() + "\n"
									+ eval.toClassDetailsString() + "\n" + "\n" + eval.toSummaryString("Results", true)
									+ "\n" + eval.toMatrixString("Confusion Matrix"), eval.pctCorrect());

							// Save best Model
							newPctCorrect = eval.pctCorrect();
							if (newPctCorrect > oldPctCorrect) {
								try {
									weka.core.SerializationHelper.write(
											MainController.asdasd + String.valueOf(newPctCorrect) + ".model", ibk);
								} catch (Exception e) {
									System.out.println("model kaydedilemedi : " + e.getMessage());
								}

								if (oldPctCorrect != 0) {
									File deleteFile = new File(
											MainController.asdasd + String.valueOf(oldPctCorrect) + ".model");
									if (deleteFile.delete()) {
									} else {
										System.out.println("Silinmedi : " + deleteFile);
									}
								} else {
								}
								oldPctCorrect = newPctCorrect;
							}
							updateProgress(sayac, kValue);
						}
						Platform.runLater(() -> {
							notificationShow();
						});
						for (KnnForRange gecici : list2) {
							corrects.add(gecici.getCorrect());
						}

						Collections.sort(corrects);

						maxCorrect = corrects.get(corrects.size() - 1);

						for (KnnForRange gecici : list2) {

							if (gecici.getCorrect() == maxCorrect) {
								maxKValue = gecici.getkValue();
							}
						}

						Alert a = new Alert(AlertType.INFORMATION);
						a.setTitle("inform");
						a.setHeaderText("en yuksek baþarým saðlayan K deðeri: " + (int) maxKValue);
						a.show();

					} catch (Exception e) {

						System.out.println("Hata KnnRangeTableController :" + e.getMessage());

					}

					// get max pctcorrect
					double maxValueMaps = Collections.max(map.values());
					for (Entry<String, Double> entrys : map.entrySet()) {
						if (entrys.getValue() == maxValueMaps) {
							getSuymmaryString = entrys.getKey();
						}

					}

				}

				super.run();
			}
		};

		ClassificationController.progressBarJfx.progressProperty().unbind();
		ClassificationController.progressBarJfx.progressProperty().bind(task.progressProperty());
		thread = new Thread(task);
		thread.start();

		Platform.runLater(() -> {

			ktblvw.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getkValue())));
			correcttblvw.setCellValueFactory(new PropertyValueFactory<>("correct"));
			maetblvw.setCellValueFactory(new PropertyValueFactory<>("mae"));
			raetblvw.setCellValueFactory(new PropertyValueFactory<>("rae"));
			rmsetblvw.setCellValueFactory(new PropertyValueFactory<>("rmse"));
			rrsetblvw.setCellValueFactory(new PropertyValueFactory<>("rrse"));
			recalltblvw.setCellValueFactory(new PropertyValueFactory<>("recall"));
			precisiontblvw.setCellValueFactory(new PropertyValueFactory<>("precision"));
			tblvw.setItems(list2);

		});

	}

	public void notificationShow() {
		try {
			FileInputStream inputstream = new FileInputStream(
					"D:\\Workspace\\WekaWorkspace\\wekaFileSelect\\src\\img\\ok.png");
			Image image = new Image(inputstream);
			Notifications notifications = Notifications.create()
					.title("Traning Complate  Best Accuarcy : " + oldPctCorrect).text(null)
					.graphic(new ImageView(image)).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
			notifications.show();
		} catch (FileNotFoundException e) {
			System.out.println("Notification Hata : " + e.getMessage());
		}
	}

}
