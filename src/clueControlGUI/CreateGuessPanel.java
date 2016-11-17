package clueControlGUI;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Solution;

public class CreateGuessPanel extends JPanel{
	private JTextArea guess;
	private String g = "";	// Couldn't use guess again
	
	public CreateGuessPanel() {
		
		setLayout(new GridLayout(1,2));
		setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		guess = new JTextArea(1, 20);
		updateGuess();
		
		add(guess);
		//return panel;
	}
	
	private void updateGuess() {
		guess.setText(g);
	}
	
	public void setGuess(Solution sugg) {
		this.g = sugg.toString();
		updateGuess();
	}
}
