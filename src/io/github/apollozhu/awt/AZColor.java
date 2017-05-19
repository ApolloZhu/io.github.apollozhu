package io.github.apollozhu.awt;

import java.awt.Color;

public class AZColor {

	private static int randomComponent() {
		return (int) (Math.random() * 256);
	}

	private static Color randColorWithAlpha(boolean hasAlpha) {
		return new Color(randomComponent(), randomComponent(), randomComponent(), hasAlpha ? randomComponent() : 255);
	}

	/** @return Color with RGB components. */
	public static Color randomOpaque() {
		return randColorWithAlpha(false);
	}

	/** @return Color with RGBA components. */
	public static Color randomTranslucent() {
		return randColorWithAlpha(true);
	}

	public static boolean isDark(Color color) {
		double darkness = 1 - (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255.;
		return darkness < 0.5;

	}

}
