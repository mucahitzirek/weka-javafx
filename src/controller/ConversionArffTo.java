package controller;

import java.io.File;
import java.io.IOException;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVSaver;
import weka.core.converters.JSONSaver;
import weka.core.converters.XRFFSaver;

public class ConversionArffTo {

	/*
	 * Converter Arff File -> Csv File
	 * 
	 */
	public void convertArfftoCsv(File selectedFile, File converterFile) {

		try {
			ArffLoader arffLoader = new ArffLoader();
			arffLoader.setSource(selectedFile);

			Instances instancesData = arffLoader.getDataSet();
			CSVSaver csvSaver = new CSVSaver();

			csvSaver.setInstances(instancesData);
			csvSaver.setFile(converterFile);
			csvSaver.writeBatch();
		} catch (IOException e) {
		}

	}

	public void convertAffToJson(File selectedFile, File converterFile) throws IOException {

		ArffLoader arffLoader = new ArffLoader();
		arffLoader.setSource(selectedFile);

		Instances instancesData = arffLoader.getDataSet();
		JSONSaver jsonSaver = new JSONSaver();

		jsonSaver.setInstances(instancesData);
		jsonSaver.setFile(converterFile);
		jsonSaver.writeBatch();

	}

	public void convertArfftoXrff(File selectedFile, File converterFile) throws IOException {

		ArffLoader arffLoader = new ArffLoader();
		arffLoader.setSource(selectedFile);

		Instances instancesData = arffLoader.getDataSet();
		XRFFSaver xrffSaver = new XRFFSaver();

		xrffSaver.setInstances(instancesData);
		xrffSaver.setFile(converterFile);
		xrffSaver.writeBatch();

	}

}
