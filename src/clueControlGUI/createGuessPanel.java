package clueControlGUI;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class createGuessPanel extends JPanel{
	private JTextArea guess;
	private JTextField name;
	
	public createGuessPanel() {
		
		//JPanel panel = new JPanel();
		setLayout(new GridLayout(1,2));
		//JLabel guessLabel = new JLabel("Guess");
		name = new JTextField(5);
		//panel.add(guessLabel);
		add(name);
		setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		//return panel;
	}
}
