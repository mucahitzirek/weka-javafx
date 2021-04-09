package model;

public class MlpModel {
	private double epoch;
	private double learningRate;
	private int noronBir;
	private int noronIki;
	private int noronUc;

	private double correct;
	private double MSE;
	private double MAE;

	public MlpModel() {

	}

	public MlpModel(double epoch, double learningRate, int noronBir, int noronIki, int noronUc, double correct,
			double MSE, double MAE) {

		this.epoch = epoch;
		this.learningRate = learningRate;
		this.noronBir = noronBir;
		this.noronIki = noronIki;
		this.noronUc = noronUc;
		this.correct = correct;
		this.MSE = MSE;
		this.MAE = MAE;
	}

	public double getEpoch() {
		return epoch;
	}

	public void setEpoch(double epoch) {
		this.epoch = epoch;
	}

	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public int getNoronBir() {
		return noronBir;
	}

	public void setNoronBir(int noronBir) {
		this.noronBir = noronBir;
	}

	public int getNoronIki() {
		return noronIki;
	}

	public void setNoronIki(int noronIki) {
		this.noronIki = noronIki;
	}

	public int getNoronUc() {
		return noronUc;
	}

	public void setNoronUc(int noronUc) {
		this.noronUc = noronUc;
	}

	public double getCorrect() {
		return correct;
	}

	public void setCorrect(double correct) {
		this.correct = correct;
	}

	public double getMSE() {
		return MSE;
	}

	public void setMSE(double mSE) {
		MSE = mSE;
	}

	public double getMAE() {
		return MAE;
	}

	public void setMAE(double mAE) {
		MAE = mAE;
	}

	@Override
	public String toString() {
		return "MlpModel [epoch=" + epoch + ", learningRate=" + learningRate + ", noronBir=" + noronBir + ", noronIki="
				+ noronIki + ", noronUc=" + noronUc + ", correct=" + correct + ", MSE=" + MSE + ", MAE=" + MAE + "]";
	}

}
