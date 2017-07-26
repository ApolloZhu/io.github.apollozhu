package io.github.apollozhu.swing;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.List;

/**
 * A subclass of JFrame with {@link #setIconImage(Image)} and
 * {@link #setIconImages(List)} working on macOS/OS X.
 *
 * @author Apollo Zhu
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AZJFrame extends JFrame {
    /**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public AZJFrame() throws HeadlessException {
        super();
    }

    /**
     * Creates a <code>Frame</code> in the specified
     * <code>GraphicsConfiguration</code> of
     * a screen device and a blank title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param gc the <code>GraphicsConfiguration</code> that is used
     *           to construct the new <code>Frame</code>;
     *           if <code>gc</code> is <code>null</code>, the system
     *           default <code>GraphicsConfiguration</code> is assumed
     * @throws IllegalArgumentException if <code>gc</code> is not from
     *                                  a screen device.  This exception is always thrown when
     *                                  GraphicsEnvironment.isHeadless() returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     * @since 1.3
     */
    public AZJFrame(GraphicsConfiguration gc) {
        super(gc);
    }

    /**
     * Creates a new, initially invisible <code>Frame</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title for the frame
     * @throws HeadlessException if GraphicsEnvironment.isHeadless()
     *                           returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public AZJFrame(String title) throws HeadlessException {
        super(title);
    }

    /**
     * Creates a <code>JFrame</code> with the specified title and the
     * specified <code>GraphicsConfiguration</code> of a screen device.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title to be displayed in the
     *              frame's border. A <code>null</code> value is treated as
     *              an empty string, "".
     * @param gc    the <code>GraphicsConfiguration</code> that is used
     *              to construct the new <code>JFrame</code> with;
     *              if <code>gc</code> is <code>null</code>, the system
     *              default <code>GraphicsConfiguration</code> is assumed
     * @throws IllegalArgumentException if <code>gc</code> is not from
     *                                  a screen device.  This exception is always thrown when
     *                                  GraphicsEnvironment.isHeadless() returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     * @since 1.3
     */
    public AZJFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
    }

    /**
     * Sets the sequence of images to be displayed as the icon
     * for this window. Subsequent calls to {@code getIconImages} will
     * always return a copy of the {@code icons} list.
     * <p>
     * Depending on the platform capabilities one or several images
     * of different dimensions will be used as the window's icon.
     * <p>
     * The {@code icons} list is scanned for the images of most
     * appropriate dimensions from the beginning. If the list contains
     * several images of the same size, the first will be used.
     * <p>
     * Ownerless windows with no icon specified use platfrom-default icon.
     * The icon of an owned window may be inherited from the owner
     * unless explicitly overridden.
     * Setting the icon to {@code null} or empty list restores
     * the default behavior.
     * <p>
     * Note : Native windowing systems may use different images of differing
     * dimensions to represent a window, depending on the context (e.g.
     * window decoration, window list, taskbar, etc.). They could also use
     * just a single image for all contexts or no image at all.
     *
     * @param icons the list of icon images to be displayed.
     * @see #getIconImages()
     * @see #setIconImage(Image)
     * @since 1.6
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public synchronized void setIconImages(List<? extends Image> icons) {
        super.setIconImages(icons);
        try {
            Class NSApplication = Class.forName("com.apple.eawt.Application");
            Method sharedApplication = NSApplication.getMethod("getApplication");
            Object shared = sharedApplication.invoke(NSApplication);
            Method setApplicationIconImage = NSApplication.getMethod("setDockIconImage", Image.class);
            setApplicationIconImage.invoke(shared, getIconImage());
        } catch (Exception e) {
        }
    }
}
