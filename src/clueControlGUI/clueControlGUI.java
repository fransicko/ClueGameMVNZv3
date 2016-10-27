package clueControlGUI;

import java.awt.Dimension;

import javax.swing.JFrame;

public class clueControlGUI extends JFrame {
	public clueControlGUI() {
		setSize(new Dimension(800,400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		clueControlGUI frame = new clueControlGUI();
		frame.setVisible(true);
	}
}
