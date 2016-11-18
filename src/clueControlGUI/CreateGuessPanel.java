package clueControlGUI;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.Solution;

public class CreateGuessPanel extends JPanel{
	private JTextArea guess;
	private String g = "";	// Couldn't use guess again
	
	// variable used for singleton pattern
	private static CreateGuessPanel theInstance = new CreateGuessPanel();

	// ctor is private to ensure only one can be created
	private CreateGuessPanel() {
		setLayout(new GridLayout(1,2));
		setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		guess = new JTextArea(1, 20);
		updateGuess();
		
		add(guess);
	}

	// this method returns the only Board
	public static CreateGuessPanel getInstance() {
		return theInstance;
	}
	
	private void updateGuess() {
		guess.setText(g);
	}
	
	public void setGuess(Solution sugg) {
		this.g = sugg.toString();
		updateGuess();
	}
}
