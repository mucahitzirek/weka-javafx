package model;

import controller.J48SettingsController;
import controller.KnnSettingsController;
import controller.LinearRegressionSettingsController;
import controller.LogisticSettingsController;
import controller.MultilayerPerceptronSettingsController;
import controller.NaiveBayesSettingsController;
import controller.RandomForestSettingsController;
import controller.SMOSettingsController;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;

public class SettingsToClassifiers {

	public void j48(J48 classifier) {

		classifier.setBatchSize(J48SettingsController.lblbatchSize_TextField);
		classifier.setMinNumObj(Integer.parseInt(J48SettingsController.lblminNumObj_TextField));
		classifier.setConfidenceFactor(Float.parseFloat(J48SettingsController.lblconfidenceFactor_TextField));
		classifier.setNumDecimalPlaces(Integer.parseInt(J48SettingsController.lblnumDecimalPlaces_TextField));
		classifier.setNumFolds(Integer.parseInt(J48SettingsController.lblnumFolds_TextField));
		classifier.setSeed(Integer.parseInt(J48SettingsController.lblseed_TextField));

		classifier.setBinarySplits(J48SettingsController.lblbinarySplits_Chk);
		classifier.setCollapseTree(J48SettingsController.lblcollapseTree_Chk);
		classifier.setDebug(J48SettingsController.lbldoNotCheckCapabilities_Chk);
		classifier.setReducedErrorPruning(J48SettingsController.lblreduceErrorPruning_Chk);
		classifier.setSaveInstanceData(J48SettingsController.lblsubtreeRaising_Chk);
		classifier.setUnpruned(J48SettingsController.lblunproned_Chk);
		classifier.setUseLaplace(J48SettingsController.lbluseLaplece_Chk);
		classifier.setUseMDLcorrection(J48SettingsController.lbluseMDLcorrection_Chk);

	}

	public void Knn(IBk classifier) {

		classifier.setBatchSize(KnnSettingsController.lblbatchSize_TextField);
		classifier.setNumDecimalPlaces(Integer.parseInt(KnnSettingsController.lblnumDecimalPlaces_TextField));
		classifier.setWindowSize(Integer.parseInt(KnnSettingsController.lblwindowSize_TextField));

		classifier.setCrossValidate(KnnSettingsController.lblcrossValidate_Chk);
		classifier.setDebug(KnnSettingsController.lbldebug_Chk);
		classifier.setDoNotCheckCapabilities(KnnSettingsController.lbldoNotCheckCapabilities_Chk);
		classifier.setMeanSquared(KnnSettingsController.lblmeanSquared_Chk);

		if (KnnSettingsController.wichRadio == true) {
			classifier.setKNN(Integer.parseInt(KnnSettingsController.lblknn_TextField));
		}

		else {

		}

	}

	public void randomForest(RandomForest classifier) {

		classifier.setBagSizePercent(Integer.parseInt(RandomForestSettingsController.lblbagSizePercent_TextField));
		classifier.setBatchSize(RandomForestSettingsController.lblbatchSize_TextField);
		classifier.setMaxDepth(Integer.parseInt(RandomForestSettingsController.lblmaxDepth_TextField));
		classifier.setNumDecimalPlaces(Integer.parseInt(RandomForestSettingsController.lblnumDecimalPlaces_TextField));
		classifier
				.setNumExecutionSlots(Integer.parseInt(RandomForestSettingsController.lblnumExecutionSlots_TextField));
		classifier.setNumFeatures(Integer.parseInt(RandomForestSettingsController.lblnumFeatures_TextField));
		classifier.setNumIterations(Integer.parseInt(RandomForestSettingsController.lblnumIterations_TextField));
		classifier.setSeed(Integer.parseInt(RandomForestSettingsController.lblseed_TextField));

		classifier.setBreakTiesRandomly(RandomForestSettingsController.lblbreakTiesRandomly_RadioBtn);
		classifier.setCalcOutOfBag(RandomForestSettingsController.lblcalcOuntOfBag_RadioBtn);
		classifier.setComputeAttributeImportance(RandomForestSettingsController.lblcomputeAttribudeImportance_RadioBtn);
		classifier.setDebug(RandomForestSettingsController.lbldebug_RadioBtn);
		classifier.setDoNotCheckCapabilities(RandomForestSettingsController.lbldoNotCheckCapabilities_RadioBtn);
		classifier.setOutputOutOfBagComplexityStatistics(
				RandomForestSettingsController.lbloutputOutOfBagComplexityStatistic_RadioBtn);
		classifier.setPrintClassifiers(RandomForestSettingsController.lblprintClassifiers_RadioBtn);
		classifier.setStoreOutOfBagPredictions(RandomForestSettingsController.lblstoreOutOfBagPredictions_RadioBtn);

	}

	public void naiveBayes(NaiveBayes classifier) {

		classifier.setBatchSize(NaiveBayesSettingsController.lblbatchSize_TextField);
		classifier.setNumDecimalPlaces(Integer.parseInt(NaiveBayesSettingsController.lblnumDecimalPlaces_TextField));

		classifier.setDebug(NaiveBayesSettingsController.lbldebug_Chk);
		classifier.setDisplayModelInOldFormat(NaiveBayesSettingsController.lbldisplayModelnOldFormat_Chk);
		classifier.setUseKernelEstimator(NaiveBayesSettingsController.lbluseKernelEstimator_Chk);
		classifier.setUseSupervisedDiscretization(NaiveBayesSettingsController.lbluseSupervisedDiscretization_Chk);

	}

	public void multiplayerPerceptronSettingsController(MultilayerPerceptron classifier) {

		classifier.setBatchSize(MultilayerPerceptronSettingsController.lblbatchSize_TextField);
		classifier.setHiddenLayers(MultilayerPerceptronSettingsController.lblhiddenLayers_TextField);
		classifier
				.setLearningRate(Double.parseDouble(MultilayerPerceptronSettingsController.lbllearningRate_TextField));
		classifier.setNumDecimalPlaces(
				Integer.parseInt(MultilayerPerceptronSettingsController.lblnumDecimalPlaces_TextField));
		classifier.setMomentum(Double.parseDouble(MultilayerPerceptronSettingsController.lblmomentum_TextField));

		classifier.setSeed(Integer.parseInt(MultilayerPerceptronSettingsController.lblseed_TextField));
		classifier.setValidationSetSize(
				Integer.parseInt(MultilayerPerceptronSettingsController.lblvalidationSetSize_TextField));
		classifier.setTrainingTime(Integer.parseInt(MultilayerPerceptronSettingsController.lbltraningTime_TextField));
		classifier.setValidationThreshold(
				Integer.parseInt(MultilayerPerceptronSettingsController.lblvalidationThreshold_TextField));

		classifier.setGUI(MultilayerPerceptronSettingsController.lblgui_Chk);
		classifier.setAutoBuild(MultilayerPerceptronSettingsController.lblaoutoBuild_Chk);
		classifier.setDebug(MultilayerPerceptronSettingsController.lbldebug_Chk);
		classifier.setDecay(MultilayerPerceptronSettingsController.lbldecay_Chk);
		classifier.setDoNotCheckCapabilities(MultilayerPerceptronSettingsController.lbldoNotCheckCapabilities_Chk);
		classifier.setNominalToBinaryFilter(MultilayerPerceptronSettingsController.lblnominalToBinaryFilter_Chk);
		classifier.setNormalizeAttributes(MultilayerPerceptronSettingsController.lblnormalizeAttributes_Chk);
		classifier.setNormalizeNumericClass(MultilayerPerceptronSettingsController.lblnormalizeNumericClass_Chk);
		classifier.setReset(MultilayerPerceptronSettingsController.lblreset_Chk);
		classifier.setResume(MultilayerPerceptronSettingsController.lblresume_Chk);

	}
/*
	public void multiplayerPerceptronSettingsControllerToRobotAnn(MultilayerPerceptron classifier) {

		classifier.setBatchSize(MultilayerPerceptronSettingsController.lblbatchSize_TextField);
		classifier.setNumDecimalPlaces(
				Integer.parseInt(MultilayerPerceptronSettingsController.lblnumDecimalPlaces_TextField));
		classifier.setMomentum(Double.parseDouble(MultilayerPerceptronSettingsController.lblmomentum_TextField));

		classifier.setSeed(Integer.parseInt(MultilayerPerceptronSettingsController.lblseed_TextField));
		classifier.setValidationSetSize(
				Integer.parseInt(MultilayerPerceptronSettingsController.lblvalidationSetSize_TextField));
		classifier.setValidationThreshold(
				Integer.parseInt(MultilayerPerceptronSettingsController.lblvalidationThreshold_TextField));

		classifier.setGUI(MultilayerPerceptronSettingsController.lblgui_Chk);
		classifier.setAutoBuild(MultilayerPerceptronSettingsController.lblaoutoBuild_Chk);
		classifier.setDebug(MultilayerPerceptronSettingsController.lbldebug_Chk);
		classifier.setDecay(MultilayerPerceptronSettingsController.lbldecay_Chk);
		classifier.setDoNotCheckCapabilities(MultilayerPerceptronSettingsController.lbldoNotCheckCapabilities_Chk);
		classifier.setNominalToBinaryFilter(MultilayerPerceptronSettingsController.lblnominalToBinaryFilter_Chk);
		classifier.setNormalizeAttributes(MultilayerPerceptronSettingsController.lblnormalizeAttributes_Chk);
		classifier.setNormalizeNumericClass(MultilayerPerceptronSettingsController.lblnormalizeNumericClass_Chk);
		classifier.setReset(MultilayerPerceptronSettingsController.lblreset_Chk);
		classifier.setResume(MultilayerPerceptronSettingsController.lblresume_Chk);

	}
*/
	public void smoSettingsController(SMO classifier) {

		classifier.setBatchSize(SMOSettingsController.lblbatchSize_TextField);
		classifier.setC(Double.parseDouble(SMOSettingsController.lblc_TextField));
		classifier.setEpsilon(Double.parseDouble(SMOSettingsController.lblepsilon_TextField));
		classifier.setNumDecimalPlaces(Integer.parseInt(SMOSettingsController.lblnumDecimalPlaces_TextField));
		classifier.setNumFolds(Integer.parseInt(SMOSettingsController.lblnumFolds_TextField));
		classifier.setRandomSeed(Integer.parseInt(SMOSettingsController.lblrandomSeed_TextField));
		classifier.setToleranceParameter(Double.parseDouble(SMOSettingsController.lbltoleranceParameter_TextField));

		classifier.setBuildCalibrationModels(SMOSettingsController.lblbuildCalibrationModels_Chkk);
		classifier.setChecksTurnedOff(SMOSettingsController.lblchecksTurnedOff_Chk);
		classifier.setDebug(SMOSettingsController.lbldebug_Chk);
		classifier.setDoNotCheckCapabilities(SMOSettingsController.lbldoNotCheckCapabilities_Chk);

	}

	public void linearRegression(LinearRegression classifier) {

		classifier.setBatchSize(LinearRegressionSettingsController.lblbatchSize_TextField);
		classifier.setNumDecimalPlaces(
				Integer.parseInt(LinearRegressionSettingsController.lblnumDecimalPlaces_TextField));
		classifier.setRidge(Double.parseDouble(LinearRegressionSettingsController.lblridge_TextField));

		classifier.setDebug(LinearRegressionSettingsController.lbldebug_Chk);
		classifier.setDoNotCheckCapabilities(LinearRegressionSettingsController.lbldoNotCheckCapabilities_Chk);
		classifier
				.setEliminateColinearAttributes(LinearRegressionSettingsController.lbleliminateColinearAttributes_Chk);
		classifier.setOutputAdditionalStats(LinearRegressionSettingsController.lbloutputAdditionalStats_Chk);
		classifier.setUseQRDecomposition(LinearRegressionSettingsController.lbluseQRDecomposition_useQRDecomposition);

	}

	public void logistic(Logistic classifier) {
		classifier.setBatchSize(LogisticSettingsController.lblbatchSize_TextField);
		classifier.setMaxIts(Integer.parseInt(LogisticSettingsController.lblmaxIts_TextField));
		classifier.setNumDecimalPlaces(Integer.parseInt(LogisticSettingsController.lblnumDecimalPlaces_TextField));
		classifier.setRidge(Double.parseDouble(LogisticSettingsController.lblridge_TextField));

		classifier.setDebug(LogisticSettingsController.lbldebug_Chk);
		classifier.setDoNotCheckCapabilities(LogisticSettingsController.lbldoNotCheckCapabilities_Chk);
		classifier.setUseConjugateGradientDescent(LogisticSettingsController.lbluseConjugateDradientDescent_Chk);

	}

}
