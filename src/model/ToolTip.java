package model;

import javafx.scene.control.Tooltip;

public class ToolTip {

	static Tooltip tooltip = new Tooltip();

	public static final Tooltip tolltipAnn() {

		tooltip.setText(
				"Multilayer Perceptron  egitimlerinizi otomatiklestirmek icin tiklayin.\nBurda yaptiginiz ayarlar learningRate, hiddenLayer ve epoch haricinde gecerli olacaktir.");
		return tooltip;
	}

	public static final Tooltip tolltipRobotAnnNotif() {

		tooltip.setText(
				"Her epoch adiminda bildirim gormek icin seciniz\nSecilmez ise varsayilan olarak egitim bitiminde bildirim gosterilecektir.");
		return tooltip;
	}

}
