package io.github.apollozhu.swing;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AZJLabelTest {
	public static void main(String[] args) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());		
		panel.add(new AZJLabel("<html>Your HTML co\nde is <b>FINE</b></html>"));
		panel.add(new AZJLabel("It knows\nwher to have\nnew lines"));
		panel.add(new AZJLabel("It knows \\n is not a new line"));
		
		JFrame frame = new JFrame("JLabel + \\n");
		frame.setSize(200, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
