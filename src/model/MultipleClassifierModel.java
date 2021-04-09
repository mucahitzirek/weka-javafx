package model;

public class MultipleClassifierModel {
	
	private String classifierName;
	private double correct,mae,mse;
	public MultipleClassifierModel(String classifierName, double correct, double mae, double mse) {
		
		this.classifierName = classifierName;
		this.correct = correct;
		this.mae = mae;
		this.mse = mse;
	}
	public String getClassifierName() {
		return classifierName;
	}
	public void setClassifierName(String classifierName) {
		this.classifierName = classifierName;
	}
	public double getCorrect() {
		return correct;
	}
	public void setCorrect(double correct) {
		this.correct = correct;
	}
	public double getMae() {
		return mae;
	}
	public void setMae(double mae) {
		this.mae = mae;
	}
	public double getMse() {
		return mse;
	}
	public void setMse(double mse) {
		this.mse = mse;
	}
	
	
	

}
