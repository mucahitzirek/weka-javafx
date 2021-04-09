package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.AlertOlustur;
import model.MlpModel;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class MlpTabloController implements Initializable {

	@FXML
	private TableView<MlpModel> tblvw;

	@FXML
	private TableColumn<MlpModel, String> tblvwepoch;

	@FXML
	private TableColumn<MlpModel, String> tblvwlearning;

	@FXML
	private TableColumn<MlpModel, String> tblvwnoronBir;

	@FXML
	private TableColumn<MlpModel, String> tblvwnoronIki;

	@FXML
	private TableColumn<MlpModel, String> tblvwnoronUc;

	@FXML
	private TableColumn<MlpModel, String> tblvwCorrect;

	@FXML
	private TableColumn<MlpModel, String> tblvwMSE;

	@FXML
	private Label time;

	@FXML
	private Label startDateTraning;

	@FXML
	private Label endDateTraning;

	@FXML
	private TableColumn<MlpModel, String> tblvwMAE;

	public static Thread thread;
	public static int sayac = 0, sayacEpoch = 0;
	public static String modelName = null;
	ObservableList<MlpModel> liste = FXCollections.observableArrayList();
	double newPctCorrect = 0, oldPctCorrect = 0;
	Date startDate = Calendar.getInstance().getTime();
	public static MultilayerPerceptron mlp = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		startDateTraning.setText("" + startDate);

		tblvw.getItems().clear();
		liste.clear();

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				return null;
			}

			@Override
			public void run() {
				try {

					if (MainController.secilen != null) {
						DataSource s = new DataSource(MainController.secilen);
						Instances dataset = s.getDataSet();
						dataset.setClassIndex(dataset.numAttributes() - 1);
						Evaluation eval;
						// mlp = new MultilayerPerceptron();
						eval = new Evaluation(dataset);

						double epochSayisi = Double.parseDouble(MlpController.maxepoch)
								- Double.parseDouble(MlpController.minepoch) + 1;
						double learningSayisi = Math.round(
								(Double.parseDouble(MlpController.maxlr) - Double.parseDouble(MlpController.minlr))
										* 10)
								+ 1;
						double noronSayisiBir = Double.parseDouble(MlpController.birBuyukNoron)
								- Double.parseDouble(MlpController.birKucukNoron) + 1;
						// For One HiddenLayer MaxValue -> ProgressBar
						double getProgressBarMaxValue = epochSayisi * learningSayisi * noronSayisiBir;
						double noronSayisiIki = Double.parseDouble(MlpController.ikiBuyukNoron)
								- Double.parseDouble(MlpController.ikiKucukNoron) + 1;
						// For Two HiddenLayer MaxValue -> ProgressBar
						double getProgressBarMaxValueTwo = getProgressBarMaxValue * noronSayisiIki;
						double noronSayisiUc = Double.parseDouble(MlpController.ucBuyukNoron)
								- Double.parseDouble(MlpController.ucKucukNoron) + 1;
						// For Three HiddenLayer MaxValue -> ProgressBar
						double getProgressBarMaxValueThree = getProgressBarMaxValueTwo * noronSayisiUc;

						for (int k = (Integer.parseInt(MlpController.minepoch)); k <= (Integer
								.parseInt(MlpController.maxepoch)); k++) {
							mlp.setTrainingTime(k);
							for (double j = (Double.parseDouble(MlpController.minlr)); j <= (Double
									.parseDouble(MlpController.maxlr) + 0.05); j += 0.1) {
								mlp.setLearningRate(j);
								// One Hidden Layer Select

								if (MlpController.countHiddenLayer == 1) {
									for (int i = (Integer.parseInt(MlpController.birKucukNoron)); i <= (Integer
											.parseInt(MlpController.birBuyukNoron)); i++) {
										mlp.setHiddenLayers(String.valueOf(i));
										sayac++;
										mlp.buildClassifier(dataset);

										if (ClassificationController.crossMlp == true) {
											Random rand = new Random(1);
											eval.crossValidateModel(mlp, dataset, ClassificationController.folds, rand);
											System.out.println(ClassificationController.folds);
										}

										else if (ClassificationController.percentMlp == true) {
											int trainSize = dataset.numInstances() * ClassificationController.percent
													/ 100;
											int testSize = dataset.numInstances() - trainSize;

											Instances test = new Instances(dataset, trainSize, testSize);
											eval.evaluateModel(mlp, test);
										}

										else if (ClassificationController.mlpUseTraining == true) {
											eval.evaluateModel(mlp, dataset);
										}

										liste.add(new MlpModel(k, j, i, 0, 0, eval.pctCorrect(),
												eval.rootMeanSquaredError(), eval.meanAbsoluteError()));
										updateProgress(sayac, (int) getProgressBarMaxValue);

										newPctCorrect = eval.pctCorrect();
										if (newPctCorrect > oldPctCorrect) {
											try {
												weka.core.SerializationHelper.write(MainController.asdasd
														+ String.valueOf(newPctCorrect) + ".model", mlp);
												modelName = MainController.asdasd + String.valueOf(newPctCorrect)
														+ ".model";

											} catch (Exception e) {
												System.out.println("model kaydedilemedi : " + e.getMessage());
											}

											if (oldPctCorrect != 0) {
												File deleteFile = new File(MainController.asdasd
														+ String.valueOf(oldPctCorrect) + ".model");
												if (deleteFile.delete()) {
												} else {
													System.out.println("Silinmedi : " + deleteFile);
												}
											} else {
											}

											oldPctCorrect = newPctCorrect;
										}
										Platform.runLater(() -> {
											time.setText(modelName);

										});
									}
								}

								// Tow Hidden Layer Select
								else if (MlpController.countHiddenLayer == 2) {

									for (int i = (Integer.parseInt(MlpController.birKucukNoron)); i < (Integer
											.parseInt(MlpController.birBuyukNoron)); i++) {
										for (int x = (Integer.parseInt(MlpController.ikiKucukNoron)); x < (Integer
												.parseInt(MlpController.ikiBuyukNoron)); x++) {
											sayac++;
											String tum = i + "," + x;
											mlp.setHiddenLayers(tum);
											eval = new Evaluation(dataset);
											mlp.buildClassifier(dataset);
											eval.evaluateModel(mlp, dataset);
											liste.add(new MlpModel(k, j, i, x, 0, eval.pctCorrect(),
													eval.rootMeanSquaredError(), eval.meanAbsoluteError()));
											updateProgress(sayac, (int) getProgressBarMaxValueTwo);
											newPctCorrect = eval.pctCorrect();

											if (newPctCorrect > oldPctCorrect) {
												try {
													weka.core.SerializationHelper.write(MainController.asdasd
															+ String.valueOf(newPctCorrect) + ".model", mlp);
													modelName = MainController.asdasd + String.valueOf(newPctCorrect)
															+ ".model";

												} catch (Exception e) {
													System.out.println("model kaydedilemedi : " + e.getMessage());
												}

												if (oldPctCorrect != 0) {
													File deleteFile = new File(MainController.asdasd
															+ String.valueOf(oldPctCorrect) + ".model");
													if (deleteFile.delete()) {
													} else {
														System.out.println("Silinmedi : " + deleteFile);
													}
												} else {
												}
												oldPctCorrect = newPctCorrect;
											}
											Platform.runLater(() -> {
												time.setText(modelName);
											});
										}
									}

								} else {
									for (int i = (Integer.parseInt(MlpController.birKucukNoron)); i < (Integer
											.parseInt(MlpController.birBuyukNoron)); i++) {
										for (int x = (Integer.parseInt(MlpController.ikiKucukNoron)); x < (Integer
												.parseInt(MlpController.ikiBuyukNoron)); x++) {
											for (int y = (Integer.parseInt(MlpController.ucKucukNoron)); y <= (Integer
													.parseInt(MlpController.ucBuyukNoron)); y++) {
												sayac++;
												String tum = i + "," + x + "," + y;
												mlp.setHiddenLayers(tum);
												eval = new Evaluation(dataset);
												mlp.buildClassifier(dataset);
												eval.evaluateModel(mlp, dataset);
												liste.add(new MlpModel(k, j, i, x, y, eval.pctCorrect(),
														eval.rootMeanSquaredError(), eval.meanAbsoluteError()));
												updateProgress(sayac, (int) getProgressBarMaxValueThree);
												newPctCorrect = eval.pctCorrect();

												if (newPctCorrect > oldPctCorrect) {
													try {
														weka.core.SerializationHelper.write(MainController.asdasd
																+ String.valueOf(newPctCorrect) + ".model", mlp);
														modelName = MainController.asdasd
																+ String.valueOf(newPctCorrect) + ".model";

													} catch (Exception e) {
														System.out.println("model kaydedilemedi : " + e.getMessage());
													}

													if (oldPctCorrect != 0) {
														File deleteFile = new File(MainController.asdasd
																+ String.valueOf(oldPctCorrect) + ".model");
														if (deleteFile.delete()) {
														} else {
															System.out.println("Silinmedi : " + deleteFile);
														}
													} else {
													}

													oldPctCorrect = newPctCorrect;
												}
												Platform.runLater(() -> {
													time.setText(modelName);

												});
											}
										}
									}
								}
							}
							sayacEpoch++;
							// ShowNotification
							if (MlpController.selectedShowNotification) {
								Platform.runLater(() -> {
									notificationShow();
								});
							}
							System.out.println(MlpController.selectedShowNotification + "select");
							System.out.println(MlpController.nonSelectedShowNotification);
						}
						if (MlpController.nonSelectedShowNotification) {
							Platform.runLater(() -> {
								notificationShow();
							});
						}
						System.out.println(MlpController.nonSelectedShowNotification);
						// Show End Date Time..
						Date endDate = Calendar.getInstance().getTime();
						Platform.runLater(() -> {
							endDateTraning.setText("" + endDate);
						});
						MlpController.selectedShowNotification = false;
						MlpController.nonSelectedShowNotification = false;
					} else {
						AlertOlustur.showAlert(AlertType.ERROR, "Secilmedi", null,
								"Lutfen bir classifier secin \nBos dosya secmediginizden emin olun");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				super.run();
			}

		};

		MlpController.progressBarJfx.progressProperty().unbind();
		MlpController.progressBarJfx.progressProperty().bind(task.progressProperty());
		thread = new Thread(task);
		thread.start();

		Platform.runLater(() -> {

			tblvwepoch.setCellValueFactory(new PropertyValueFactory<>("epoch"));
			tblvwCorrect.setCellValueFactory(new PropertyValueFactory<>("correct"));
			tblvwlearning.setCellValueFactory(new PropertyValueFactory<>("learningRate"));
			tblvwMAE.setCellValueFactory(new PropertyValueFactory<>("MAE"));
			tblvwMSE.setCellValueFactory(new PropertyValueFactory<>("MSE"));
			tblvwnoronBir.setCellValueFactory(new PropertyValueFactory<>("noronBir"));
			tblvwnoronIki.setCellValueFactory(new PropertyValueFactory<>("noronIki"));
			tblvwnoronUc.setCellValueFactory(new PropertyValueFactory<>("noronUc"));

			tblvw.setItems(liste);

		});
		sayacEpoch = 0;

	}

	public void notificationShow() {
		try {
			int sumEpoch = Integer.parseInt(MlpController.minepoch) + sayacEpoch - 1;
			FileInputStream inputstream = new FileInputStream(
					"D:\\Workspace\\WekaWorkspace\\wekaFileSelect\\src\\img\\ok.png");
			Image image = new Image(inputstream);
			Notifications notifications = Notifications.create()
					.title("Traning Complate : " + (sumEpoch) + " For Epoch Number").text(null)
					.graphic(new ImageView(image)).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
			notifications.show();
		} catch (FileNotFoundException e) {
			System.out.println("Notification Hata : " + e.getMessage());
		}
	}

}
