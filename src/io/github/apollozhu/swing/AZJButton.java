/**
 MIT License
 Copyright (c) 2017 Apollo Zhu (朱智语).

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
package io.github.apollozhu.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.beans.ConstructorProperties;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.UIManager;

/**
 * A subclass of JButton with {@link #setBackground(Color)} working on macOS/OS X.
 * 
 * @author Apollo Zhu
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AZJButton extends JButton {

	/**
	 * Creates a button with no set text or icon.
	 */
	public AZJButton() {
		super();
	}

	/**
	 * Creates a button where properties are taken from the <code>Action</code>
	 * supplied.
	 *
	 * @param a the <code>Action</code> used to specify the new button
	 */
	public AZJButton(Action a) {
		super(a);
	}

	/**
	 * Creates a button with an icon.
	 *
	 * @param icon the Icon image to display on the button
	 */
	public AZJButton(Icon icon) {
		super(icon);
	}

	/**
	 * Creates a button with text.
	 *
	 * @param text the text of the button
	 */
	@ConstructorProperties({ "text" })
	public AZJButton(String text) {
		super(text);
	}

	/**
	 * Creates a button with initial text and an icon.
	 *
	 * @param text the text of the button
	 * @param icon the Icon image to display on the button
	 */
	public AZJButton(String text, Icon icon) {
		super(text, icon);
	}

	/** Selected menu item color from Apple's Developer Swatch. */
	protected static final Color SELECTED_MENU_ITEM_COLOR = new Color(3, 100, 236);

	/** Header color from Apple's Developer Swatch. */
	protected static final Color HEADER_COLOR = new Color(174, 174, 174);

	/** Keyboard focus indicator color from Apple's Developer Swatch. */
	protected static final Color KEYBOARD_FOCUS_INDICATOR_COLOR = new Color(76, 149, 255);

	/** Selected control color from Apple's Developer Swatch. */
	protected static final Color SELECTED_CONTROL_COLOR = new Color(164, 205, 255);

	/**
	 * Check if UIManager is using Apple Aqua Look and Feel.
	 * 
	 * @return true if current look and feel is aqua.
	 */
	private boolean isAquaUI() {
		try {
			return getBorder().getClass().getName().contains("Aqua");
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Calls the super implementation, unless UIManager is using Apple Aqua Look
	 * and Feel.
	 * 
	 * If it is, and if border is required, and has a different background than
	 * the default one, the button will be drawn differently to match the actual
	 * look and feel for a button with background color on OS X Yosemite (10.10)
	 * and other macOSs above.
	 *
	 * @param g the <code>Graphics</code> object to protect
	 * 
	 * @see #setBackground(Color)
	 */
	@Override
	public void paintComponent(Graphics g) {
		if (!isAquaUI()) {
			super.paintComponent(g);
			return;
		}

		final Color backgroundColor = getBackground(), foregroundColor = getForeground();
		final boolean hasCustomBackground = !backgroundColor.equals(UIManager.getColor("Button.background"));
		final boolean isBorderPainted = isBorderPainted();
		final boolean isOpaque = isOpaque();

		if (isBorderPainted && hasCustomBackground) {
			final boolean isPressed = getModel().isPressed();
			Insets i = getBorder().getBorderInsets(this);
			int r = 8, offset = i.top, x = offset, y = offset, w = getWidth() - 2 * offset,
					h = getHeight() - offset - i.bottom;

			Graphics2D g2 = (Graphics2D) g;

			if (isPressed) {
				int midX = getWidth() / 2;
				g2.setPaint(new GradientPaint(midX, 0, KEYBOARD_FOCUS_INDICATOR_COLOR, midX, getHeight(), SELECTED_MENU_ITEM_COLOR));
				setForeground(Color.white);
			} else {
				g2.setColor(backgroundColor);
			}
			g2.fillRoundRect(x, y, w, h, r, r);

			g2.setColor(HEADER_COLOR);
			g2.drawRoundRect(x, y, w, h, r, r);

			if (isPressed || isFocusOwner()) {
				g2.setColor(SELECTED_CONTROL_COLOR);
				g2.setStroke(new BasicStroke(2));
				g2.drawRoundRect(x, y, w, h, r, r);
			}

			super.setBorderPainted(false);
			super.setOpaque(false);
		}

		super.paintComponent(g);
		super.setBorderPainted(isBorderPainted);
		super.setOpaque(isOpaque);
		super.setForeground(foregroundColor);
	}

}
