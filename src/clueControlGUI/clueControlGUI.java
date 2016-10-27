package clueControlGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class clueControlGUI extends JPanel{
	private JTextField name;
	
	public clueControlGUI() {
		setLayout(new GridLayout(2,3));
		JPanel panel = createNamePanel();
		add(panel);
		panel = createButtonPanel();
		add(panel);
		
		
	}
	
	private JPanel createNamePanel() {
		
	}
	
	private JPanel createButtonPanel() {
		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		JPanel panel = new JPanel();
		panel.add(nextPlayer);
		panel.add(makeAccusation);
		return panel;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(800,400));
		frame.setVisible(true);
		
		clueControlGUI control = new clueControlGUI();
		frame.add(control, BorderLayout.CENTER);
		
		
	}
}
