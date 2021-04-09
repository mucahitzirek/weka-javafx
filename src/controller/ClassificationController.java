package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.control.Alert.AlertType;
import model.AlertOlustur;
import weka.attributeSelection.PrincipalComponents;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.IndependentComponents;

public class ClassificationController implements Initializable {

	@FXML
	private JFXComboBox<String> classifier_ComboBox;

	@FXML
	private JFXButton start_Btn;

	@FXML
	private Label correct_Label;

	@FXML
	private Label correctPercent_Label;

	@FXML
	private Label inCorrect_Label;

	@FXML
	private Label unclassified_Label;

	@FXML
	private Label auc_Label;

	@FXML
	private Label kappa_Label;

	@FXML
	private Label mae_Label;

	@FXML
	private Label rmse_Label;

	@FXML
	private Label rae_Label;

	@FXML
	private Label rrse_Label;

	@FXML
	private Label precision_Label;

	@FXML
	private Label recall_Label;

	@FXML
	private Label fmesaure_Label;

	@FXML
	private Label errorRate_Label;

	@FXML
	private JFXRadioButton traningSet_RadioBtn;

	@FXML
	private ToggleGroup testOptionsRadioGroup;

	@FXML
	private JFXRadioButton crossValidation_RadioBtn;

	@FXML
	private JFXRadioButton percentageSplit_RadioBtn;

	@FXML
	private TextField crossValidationFolds_TextField;

	@FXML
	private TextField percentageSplitPercent_TextField;

	@FXML
	private ListView<String> listview;

	@FXML
	private JFXButton saveModel_Btn;

	@FXML
	private JFXButton getSummary_Btn;

	@FXML
	private VBox numericVbox;

	@FXML
	private VBox nominalVbox;

	@FXML
	private Label correlationcoefficient1;

	@FXML
	private Label mae_Label1;

	@FXML
	private Label rmse_Label1;

	@FXML
	private Label rae_Label1;

	@FXML
	private Label rrse_Label1;

	@FXML
	private Label errorRate_Label1;

	@FXML
	private JFXTextField modelismi;

	@FXML
	private JFXButton stopBtn;

	@FXML
	private StackPane stackPane;
	public static JFXSpinner progressBarJfx = null;

	public static boolean knnCross, knnPercent, knnUseTraining;
	public static boolean mlpCross, mlpPercent, mlpUseTraining;
	public static int folds, percent;

	String cmbOutput = null;
	public static String getSummary = null;

	Classifier classifier = null;
	Instances data = MainController.data;
	DataSource source = null;
	Evaluation eval = null;
	ArrayList<String> list = new ArrayList<String>();
	// List<Boolean> booleans = new ArrayList<Boolean>();
	public static Boolean wichlinearReg = false;
	public static Logistic logistic = null;
	public static LinearRegression linearRegression = null;
	public static J48 j48 = null;
	public static IBk ibk = null;
	public static RandomForest randomForest = null;
	public static NaiveBayes bayes = null;
	public static MultilayerPerceptron multilayerPerceptron = null;
	public static SMO smo;
	public static boolean trainMlp = false, crossMlp = false, percentMlp = false;

	@FXML
	void fonkCmbClassifiers(ActionEvent event) {

		cmbOutput = classifier_ComboBox.getSelectionModel().getSelectedItem().toString();

		if (cmbOutput.equals("Decision Tree")) {
			if (MainController.fileIsNumeric == false) {
				j48 = new J48();
				ShowPage.settingShow("src/view/J48Settings.fxml", "Decision Tree");

				classifier = j48;
			} else {
				cmbOutput = null;
				AlertOlustur.showAlert(AlertType.ERROR, "baslik", null, "nominal dosya sec");
			}
			stackPane.getChildren().clear();
		} else if (cmbOutput.equals("Navie Bayes")) {
			if (MainController.fileIsNumeric == false) {
				bayes = new NaiveBayes();
				ShowPage.settingShow("src/view/NaiveBayes.fxml", "Navie Bayes");

				classifier = bayes;
			} else {
				cmbOutput = null;
				AlertOlustur.showAlert(AlertType.ERROR, "baslik", null, "nominal dosya sec");
			}
			stackPane.getChildren().clear();
		} else if (cmbOutput.equals("Knn")) {

			ibk = new IBk();
			ShowPage.settingShow("src/view/KnnSettings.fxml", "Knn");

			classifier = ibk;
			stackPane.getChildren().clear();
		} else if (cmbOutput.equals("LibSVM")) {
			if (MainController.fileIsNumeric == false) {
				LibSVM lsv = new LibSVM();

				classifier = lsv;

			} else {
				cmbOutput = null;
				AlertOlustur.showAlert(AlertType.ERROR, "baslik", null, "nominal dosya sec");
			}
			stackPane.getChildren().clear();
		} else if (cmbOutput.equals("Random Forest")) {

			randomForest = new RandomForest();
			ShowPage.settingShow("src/view/RandomForestSettings.fxml", "Random Forest");

			classifier = randomForest;
			stackPane.getChildren().clear();
		} else if (cmbOutput.equals("ANN")) {

			multilayerPerceptron = new MultilayerPerceptron();
			MlpTabloController.mlp = multilayerPerceptron;
			ShowPage.settingShow("src/view/MultilayerPerceptronSettings.fxml", "Multilayer Perceptron");

			classifier = multilayerPerceptron;
			KnnSettingsController.wichRadio = true;

			stackPane.getChildren().clear();
		} else if (cmbOutput.equals("Support Vektor Machine")) {
			if (MainController.fileIsNumeric == false) {
				smo = new SMO();
				ShowPage.settingShow("src/view/SMOSettings.fxml", "SMO");

				classifier = smo;
			} else {
				cmbOutput = null;
				AlertOlustur.showAlert(AlertType.ERROR, "baslik", null, "nominal dosya sec");
			}
			stackPane.getChildren().clear();
		} else if (cmbOutput.equals("Linear Regression")) {

			wichlinearReg = true;

			if (MainController.fileIsNumeric == true) {
				linearRegression = new LinearRegression();
				ShowPage.settingShow("src/view/LinearRegressionSettings.fxml", "Linear Regression Settings");
			} else {
				AlertOlustur.showAlert(AlertType.ERROR, "baslik", null, "numeric dosya sec");

			}

			classifier = linearRegression;
			stackPane.getChildren().clear();
		} else if (cmbOutput.equals("Logistic")) {
			if (MainController.fileIsNumeric == false) {
				logistic = new Logistic();
				ShowPage.settingShow("src/view/LogisticSettings.fxml", "Logistic Settings");

				classifier = logistic;

			} else {
				cmbOutput = null;
				AlertOlustur.showAlert(AlertType.ERROR, "baslik", null, "nominal dosya sec");
			}
			stackPane.getChildren().clear();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.show();
		}
		if (crossValidation_RadioBtn.isSelected()) {

			crossMlp = true;

		} else if (percentageSplit_RadioBtn.isSelected()) {

			percentMlp = true;

		} else if (traningSet_RadioBtn.isSelected()) {

			mlpUseTraining = true;

		}

	}

	@FXML
	void fonkGerSummary(ActionEvent event) {
		ShowPage.settingShow("src/view/GetSummary.fxml", "Get Summary");

	}

	@FXML
	void fonkSaveModel(ActionEvent event) {

		if (modelismi.toString() != null) {
			try {
				weka.core.SerializationHelper.write(MainController.asdasd + modelismi.getText() + ".model", classifier);
				System.out.println(MainController.asdasd + "  " + modelismi.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {
			System.out.println("model ismi gir");
		}
	}

	@SuppressWarnings("deprecation")
	@FXML
	void fonkStart(ActionEvent event) throws Exception {

		progressBarJfx = new JFXSpinner();
		progressBarJfx.setVisible(true);
		stackPane.getChildren().add(progressBarJfx);

		if (cmbOutput != null && MainController.secilen != null) {

			if (classifier == ibk && KnnSettingsController.wichRadio == false) {

				folds = Integer.parseInt(crossValidationFolds_TextField.getText());
				percent = Integer.parseInt(percentageSplitPercent_TextField.getText());

				if (crossValidation_RadioBtn.isSelected()) {
					knnCross = true;
					knnPercent = false;
					knnUseTraining = false;

				} else if (percentageSplit_RadioBtn.isSelected()) {

					knnCross = false;
					knnPercent = true;
					knnUseTraining = false;

				} else if (traningSet_RadioBtn.isSelected()) {
					knnCross = false;
					knnPercent = false;
					knnUseTraining = true;

				}
				ShowPage.settingShow("src/view/KnnRangeTable.fxml", "Multipble choose k");

			} else {
				KnnSettingsController.wichGetSummary = false;

				if (MainController.fileIsNumeric == false) {

					Task<Void> task2 = new Task<Void>() {

						@Override
						protected Void call() throws Exception {
							return null;
						}

						@Override
						public void run() {
							try {
								if (MainController.pca == true) {
									PrincipalComponents pca = new PrincipalComponents();
									pca.buildEvaluator(data);
									pca.setMaximumAttributeNames(Integer
											.parseInt(PCASettingsController.lblmaximumAttributesNames_TextField));
									pca.setVarianceCovered(
											Double.parseDouble(PCASettingsController.lblvarianceCovered_TextField));
									pca.setTransformBackToOriginal(
											PCASettingsController.lbltransformBackToOriginal_Chk);
									pca.setDoNotCheckCapabilities(PCASettingsController.lbldoNotCheckCapabilities_Chk);
									pca.setCenterData(PCASettingsController.lblcenterData_Chk);
								}

								if (MainController.ica == true) {
									Filter filter = new IndependentComponents();
									filter.setInputFormat(data);
									((IndependentComponents) filter).setOutputNumAtts(10);

									for (int i = 0; i < data.numInstances(); i++) {
										filter.input(data.instance(i));
									}
									data = null;
									filter.batchFinished();
									data = filter.getOutputFormat();

									Instance processed;
									while ((processed = filter.output()) != null) {
										data.add(processed);
									}
								}

								evalu(classifier, data);

							} catch (Exception e) {
								System.out.println("Runnable evalu startFonk hata : " + e.getMessage());
							}
							super.run();
						}
					};

					Thread thread = new Thread(task2);
					thread.start();
					// stopBtn thread stop progressBarJfx false digerlerine stop uygulanmadi baska
					// bi yol bulamassan uygula
					stopBtn.setOnAction(stopThread -> {
						thread.stop();
						progressBarJfx.setVisible(false);
						if (KnnRangeTableController.thread != null) {
							KnnRangeTableController.thread.stop();
						}

					});

				} else {

					/*
					 * NumericEval thread
					 */
					Task<Void> task2 = new Task<Void>() {

						@Override
						protected Void call() throws Exception {

							return null;
						}

						@Override
						public void run() {
							try {

								numerikEvul(classifier, data);

							} catch (Exception e) {
								System.out.println("Runnable numericEvalu startFonk hata : " + e.getMessage());
							}
							super.run();
						}
					};

					Thread thread = new Thread(task2);
					thread.start();

					stopBtn.setOnAction(a -> {
						thread.stop();
						progressBarJfx.setVisible(false);

					});
				}

			}
		} else {
			AlertOlustur.showAlert(AlertType.ERROR, "Secilmedi", null,
					"Lutfen bir classifier secin \nBos dosya secmediginizden emin olun");

		}

	}

	public void evalu(Classifier classifier, Instances trainData) throws Exception {

		folds = Integer.parseInt(crossValidationFolds_TextField.getText());
		percent = Integer.parseInt(percentageSplitPercent_TextField.getText());
		Random rand = new Random(1);
		eval = new Evaluation(trainData);
		classifier.buildClassifier(trainData);

		if (crossValidation_RadioBtn.isSelected()) {
			mlpCross = true;
			mlpUseTraining = false;
			mlpPercent = false;

			eval.crossValidateModel(classifier, trainData, folds, rand);

		} else if (percentageSplit_RadioBtn.isSelected()) {
			mlpCross = false;
			mlpUseTraining = false;
			mlpPercent = true;

			if (percent > 0 && percent < 100) {

				int trainSize = data.numInstances() * percent / 100;
				int testSize = data.numInstances() - trainSize;

				// Instances train = new Instances(data, 0, trainSize);
				Instances test = new Instances(data, trainSize, testSize);

				eval.evaluateModel(classifier, test);

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.show();
			}

		} else if (traningSet_RadioBtn.isSelected()) {
			mlpCross = false;
			mlpUseTraining = true;
			mlpPercent = false;

			eval.evaluateModel(classifier, trainData);

		}

		/*
		 * setLabel, getSummary ve notificaton icin thread bittikten sonra ekranda
		 * gosterme istegi.
		 */

		Platform.runLater(() -> {

			try {

				correct_Label.setText(Double.toString(eval.correct()));
				correctPercent_Label.setText(Double.toString(eval.pctCorrect()));
				inCorrect_Label.setText(Double.toString(eval.pctIncorrect()));
				unclassified_Label.setText(Double.toString(eval.unclassified()));
				mae_Label.setText(Double.toString(eval.meanAbsoluteError()));
				rmse_Label.setText(Double.toString(eval.rootMeanSquaredError()));
				rae_Label.setText(Double.toString(eval.relativeAbsoluteError()));
				rrse_Label.setText(Double.toString(eval.rootRelativeSquaredError()));
				errorRate_Label.setText(Double.toString(eval.errorRate()));

				auc_Label.setText(Double.toString(eval.areaUnderROC(1)));
				kappa_Label.setText(Double.toString(eval.kappa()));
				precision_Label.setText(Double.toString(eval.precision(1)));
				recall_Label.setText(Double.toString(eval.recall(1)));
				fmesaure_Label.setText(Double.toString(eval.fMeasure(1)));

				getSummary = data.toSummaryString() + "\n" + eval.toClassDetailsString() + "\n" + "\n"
						+ eval.toSummaryString("Results", true) + "\n" + eval.toMatrixString("Confusion Matrix");

				notificationShow();

				progressBarJfx.setProgress(100);
			} catch (Exception e) {
				System.out.println("SetLabel + notificationShow Platform.runLaterhata evalu Hata : " + e.getMessage());
			}
		});

	}

	public void numerikEvul(Classifier classifier, Instances trainData) throws Exception {

		folds = Integer.parseInt(crossValidationFolds_TextField.getText());
		percent = Integer.parseInt(percentageSplitPercent_TextField.getText());
		Random rand = new Random(1);
		eval = new Evaluation(trainData);

		classifier.buildClassifier(trainData);

		if (crossValidation_RadioBtn.isSelected()) {

			eval.crossValidateModel(classifier, trainData, folds, rand);

		} else if (percentageSplit_RadioBtn.isSelected()) {

			if (percent > 0 && percent < 100) {

				int trainSize = data.numInstances() * percent / 100;
				int testSize = data.numInstances() - trainSize;

				// Instances train = new Instances(data, 0, trainSize);
				Instances test = new Instances(data, trainSize, testSize);

				eval.evaluateModel(classifier, test);

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.show();
			}

		} else if (traningSet_RadioBtn.isSelected()) {

			eval.evaluateModel(classifier, trainData);
		}

		/*
		 * setLabel thread sonrasi ekranda gosterimleri...
		 */
		Platform.runLater(() -> {

			try {
				correlationcoefficient1.setText(Double.toString(eval.correlationCoefficient()));
				mae_Label1.setText(Double.toString(eval.meanAbsoluteError()));
				rmse_Label1.setText(Double.toString(eval.rootMeanSquaredError()));
				rae_Label1.setText(Double.toString(eval.relativeAbsoluteError()));
				rrse_Label1.setText(Double.toString(eval.rootRelativeSquaredError()));
				errorRate_Label1.setText(Double.toString(eval.errorRate()));

				getSummary = data.toSummaryString() + "\n" + eval.toSummaryString("Results", true);
				notificationShow();

				progressBarJfx.setProgress(100);

			} catch (Exception e) {
				System.out.println("setLabel + notificationShow numerikEvalu Hata : " + e.getMessage());
			}
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		list.add("ANN");
		list.add("Knn");
		list.add("Support Vektor Machine");

		list.add("Navie Bayes");
		list.add("Decision Tree");
		list.add("LibSVM");
		list.add("Random Forest");
		list.add("Linear Regression");
		list.add("Logistic");

		crossValidation_RadioBtn.setToggleGroup(testOptionsRadioGroup);
		traningSet_RadioBtn.setToggleGroup(testOptionsRadioGroup);
		percentageSplit_RadioBtn.setToggleGroup(testOptionsRadioGroup);
		classifier_ComboBox.getItems().addAll(list);
		percentageSplitPercent_TextField.setText("80");
		crossValidationFolds_TextField.setText("10");

		if (MainController.fileIsNumeric == true) {
			numericVbox.setVisible(true);
			nominalVbox.setVisible(false);

		} else {
			numericVbox.setVisible(false);
			nominalVbox.setVisible(true);
		}

	}

	public void notificationShow() {
		try {
			FileInputStream inputstream = new FileInputStream(
					"D:\\Workspace\\WekaWorkspace\\wekaFileSelect\\src\\img\\ok.png");
			Image image = new Image(inputstream);
			Notifications notifications = Notifications.create().title("Traning Complate").text(null)
					.graphic(new ImageView(image)).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
			notifications.show();
		} catch (FileNotFoundException e) {
			System.out.println("Notification Hata : " + e.getMessage());
		}
	}

}
