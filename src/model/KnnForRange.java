package model;

public class KnnForRange {

	private int kValue;
	private Double correct, mae, rae, rmse, rrse, recall, precision;

	public KnnForRange(
			int kValue, Double correct, Double mae, Double rae, Double rmse, Double rrse, Double recall,
			Double precision) {
		this.kValue = kValue;
		this.correct = correct;
		this.mae = mae;
		this.rae = rae;
		this.rmse = rmse;
		this.rrse = rrse;
		this.recall = recall;
		this.precision = precision;
	}
	


	public int getkValue() {
		return kValue;
	}

	public void setkValue(int kValue) {
		this.kValue = kValue;
	}

	public Double getCorrect() {
		return correct;
	}

	public void setCorrect(Double correct) {
		this.correct = correct;
	}

	public Double getMae() {
		return mae;
	}

	public void setMae(Double mae) {
		this.mae = mae;
	}

	public Double getRae() {
		return rae;
	}

	public void setRae(Double rae) {
		this.rae = rae;
	}

	public Double getRmse() {
		return rmse;
	}

	public void setRmse(Double rmse) {
		this.rmse = rmse;
	}

	public Double getRrse() {
		return rrse;
	}

	public void setRrse(Double rrse) {
		this.rrse = rrse;
	}

	public Double getRecall() {
		return recall;
	}

	public void setRecall(Double recall) {
		this.recall = recall;
	}

	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}



}
