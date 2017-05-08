package io.github.apollozhu.awt;

import java.awt.Color;

public class AZRandomColor {

	private static int randomComponent() {
		return (int) (Math.random() * 255);
	}

	private static Color randColorWithAlpha(boolean hasAlpha) {
		return new Color(randomComponent(), randomComponent(), randomComponent(), hasAlpha ? randomComponent() : 255);
	}

	/** @return Color with RGB components. */
	public static Color opaque() {
		return randColorWithAlpha(false);
	}

	/** @return Color with RGBA components. */
	public static Color translucent() {
		return randColorWithAlpha(true);
	}

}
