package io.github.apollozhu.awt;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class AZRandomFont {
	public static Font ofSiZe(float size) {
		Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		int idx = (int) (Math.random() * fonts.length);
		return fonts[idx].deriveFont(style(), size);
	}

	public static int style() {
		return (int) (Math.random() * 4);
	}
}
