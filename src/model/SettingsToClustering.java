package model;

import controller.HierarchicalClusteringSettingsController;
import controller.SimpleKmeansSettingsController;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;

public class SettingsToClustering {

	public void kmeans(SimpleKMeans clusterer) throws NumberFormatException, Exception {

		clusterer.setCanopyMaxNumCanopiesToHoldInMemory(
				Integer.parseInt(SimpleKmeansSettingsController.lblcanopyMaxNumCanoplacesToHaldnMemory_TextField));

		clusterer.setCanopyMinimumCanopyDensity(
				Double.parseDouble(SimpleKmeansSettingsController.lblcanopyMinimumCanopyDensity_TextField));
		clusterer.setCanopyPeriodicPruningRate(
				Integer.parseInt(SimpleKmeansSettingsController.lblcanopyPeriodicPruningRate_TextField));
		clusterer.setCanopyT1(Double.parseDouble(SimpleKmeansSettingsController.lblcanopyT1_TextField));

		clusterer.setCanopyT2(Double.parseDouble(SimpleKmeansSettingsController.lblcanopyT2_TextField));

		clusterer.setMaxIterations(Integer.parseInt(SimpleKmeansSettingsController.lblmaxIterations_TextField));
		clusterer.setNumClusters(Integer.parseInt(SimpleKmeansSettingsController.lblnumClusters_TextField));
		clusterer.setNumExecutionSlots(Integer.parseInt(SimpleKmeansSettingsController.lblnumExecutionSlots_TextField));
		clusterer.setSeed(Integer.parseInt(SimpleKmeansSettingsController.lblseed_TextField));

		// clusterer.setInitializationMethod();

		clusterer.setDebug(SimpleKmeansSettingsController.lbldebug_Chk);
		clusterer.setDisplayStdDevs(SimpleKmeansSettingsController.lbldisplayStdDevs_Chk);
		clusterer.setDoNotCheckCapabilities(SimpleKmeansSettingsController.lbldoNotCheckCapabilities_Chk);
		clusterer.setDontReplaceMissingValues(SimpleKmeansSettingsController.lbldontReplaceMissingValues_Chk);
		clusterer.setFastDistanceCalc(SimpleKmeansSettingsController.lblfastDistanceCalc_Chk);
		clusterer.setPreserveInstancesOrder(SimpleKmeansSettingsController.lblpreserveInstancesSlots_Chk);
		clusterer.setReduceNumberOfDistanceCalcsViaCanopies(
				SimpleKmeansSettingsController.lblreduceNumberOfDistanceCalcsViaCanopies_Chk);

	}

	public void hierarchical(HierarchicalClusterer clusterer) {

		clusterer.setNumClusters(Integer.parseInt(HierarchicalClusteringSettingsController.lblnumClusters_TextField));
		clusterer.setDistanceIsBranchLength(HierarchicalClusteringSettingsController.lbldistancelsBranchLength_Chk);
		clusterer.setDoNotCheckCapabilities(HierarchicalClusteringSettingsController.lbldoNotCheckCapabilities_Chk);
		clusterer.setPrintNewick(HierarchicalClusteringSettingsController.lblprintNewick_Chk);

	}

}
