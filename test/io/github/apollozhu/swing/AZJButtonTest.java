package io.github.apollozhu.swing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import io.github.apollozhu.awt.AZRandomColor;

class AZJButtonTest {

	static final class ColorChanger implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			((JButton) e.getSource()).setBackground(AZRandomColor.translucent());
		}
	}

	@SuppressWarnings("serial")
	private static class Panel extends JPanel {
		public Panel() {
			setLayout(new FlowLayout());

			ColorChanger listener = new ColorChanger();

			JButton jBtn = new JButton("<html>JButton has background issue <br />"
					// http://stackoverflow.com/questions/1065691/how-to-set-the-background-color-of-a-jbutton-on-the-mac-os\
					+ "since <b style=\"color:red;\">2009</b></html>");
			jBtn.setBackground(Color.orange);
			jBtn.addActionListener(listener);
			add(jBtn);

			jBtn = new JButton("The solution is to make it less like a button, or --");
			jBtn.setBackground(Color.magenta);
			jBtn.setBorderPainted(false);
			jBtn.setOpaque(true);
			jBtn.addActionListener(listener);
			add(jBtn);

			AZJButton btn = new AZJButton(
					"<html><div style=\"color:white;\">Hello Background by using AZJButton</div></html>");
			btn.setBackground(Color.orange);
			btn.addActionListener(listener);
			add(btn);
		}
	}

	public static void main(String[] args) {
		JFrame frame;

		try {
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {

		}

		frame = new JFrame("J\"Button\"s with background color");
		frame.setSize(400, 350);
		frame.setLocation(650, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel panel = new Panel();
		frame.setContentPane(panel);
		frame.setVisible(true);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {

		}

		frame = new JFrame("Set Background for JButton");
		frame.setSize(400, 350);
		frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new Panel());
		frame.setVisible(true);
		// SwingUtilities.updateComponentTreeUI(panel);
	}

}
