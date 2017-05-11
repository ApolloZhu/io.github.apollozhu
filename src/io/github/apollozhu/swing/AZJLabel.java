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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.event.EventListenerList;

/**
 * A subclass of JLabel with {@link #setText(String)} working with \n.
 * 
 * @author Apollo Zhu
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AZJLabel extends JLabel {
    /**
     * Creates a <code>JLabel</code> instance with the specified
     * text, image, and horizontal alignment.
     * The label is centered vertically in its display area.
     * The text is on the trailing edge of the image.
     *
     * @param text  The text to be displayed by the label.
     * @param icon  The image to be displayed by the label.
     * @param horizontalAlignment  One of the following constants
     *           defined in <code>SwingConstants</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code>,
     *           <code>RIGHT</code>,
     *           <code>LEADING</code> or
     *           <code>TRAILING</code>.
     */
    public AZJLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }

    /**
     * Creates a <code>JLabel</code> instance with the specified
     * text and horizontal alignment.
     * The label is centered vertically in its display area.
     *
     * @param text  The text to be displayed by the label.
     * @param horizontalAlignment  One of the following constants
     *           defined in <code>SwingConstants</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code>,
     *           <code>RIGHT</code>,
     *           <code>LEADING</code> or
     *           <code>TRAILING</code>.
     */
    public AZJLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    /**
     * Creates a <code>JLabel</code> instance with the specified text.
     * The label is aligned against the leading edge of its display area,
     * and centered vertically.
     *
     * @param text  The text to be displayed by the label.
     */
    public AZJLabel(String text) {
        super(text);
    }

    /**
     * Creates a <code>JLabel</code> instance with the specified
     * image and horizontal alignment.
     * The label is centered vertically in its display area.
     *
     * @param image  The image to be displayed by the label.
     * @param horizontalAlignment  One of the following constants
     *           defined in <code>SwingConstants</code>:
     *           <code>LEFT</code>,
     *           <code>CENTER</code>,
     *           <code>RIGHT</code>,
     *           <code>LEADING</code> or
     *           <code>TRAILING</code>.
     */
    public AZJLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    /**
     * Creates a <code>JLabel</code> instance with the specified image.
     * The label is centered vertically and horizontally
     * in its display area.
     *
     * @param image  The image to be displayed by the label.
     */
    public AZJLabel(Icon image) {
        super(image);
    }

    /**
     * Creates a <code>JLabel</code> instance with
     * no image and with an empty string for the title.
     * The label is centered vertically
     * in its display area.
     * The label's contents, once set, will be displayed on the leading edge
     * of the label's display area.
     */
    public AZJLabel() {
        super();
    }

	/**
	 * Defines the single line of text this component will display. If the value
	 * of text is null or empty string, nothing is displayed. If the text
	 * contains \n, it is converted to <br />
	 * tag, and the whole text is wrapped around by \<html\> tag. Then fires an
	 * action event after text changed.
	 * <p>
	 * The default value of this property is null.
	 * <p>
	 * This is a JavaBeans bound property.
	 *
	 * @see #setVerticalTextPosition
	 * @see #setHorizontalTextPosition
	 * @see #setIcon
	 * @beaninfo
	 *    preferred: true
	 *        bound: true
	 *    attribute: visualUpdate true
	 *  description: Defines the single line of text this component will display.
	 */
	@Override
	public void setText(String text) {
		if (text != null && !text.startsWith("<html>") && !text.endsWith("</html>") && text.contains("\n"))
			text = "<html>" + text.replaceAll("\n", "<br />") + "</html>";
		super.setText(text);
		if (listenerList != null)
			for (ActionListener listener : listenerList.getListeners(ActionListener.class))
				listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "setText"));
	}

	private EventListenerList listenerList = new EventListenerList();

	public void addActionListener(ActionListener listener) {
		listenerList.add(ActionListener.class, listener);
	}

	public void removeActionListener(ActionListener listener) {
		listenerList.remove(ActionListener.class, listener);
	}
}
