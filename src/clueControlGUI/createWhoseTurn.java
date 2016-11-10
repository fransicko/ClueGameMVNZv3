package clueControlGUI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class createWhoseTurn extends JPanel{
	private JTextArea whosTurn;
	private String playerTurn = "";
	
	public createWhoseTurn() {
		//JPanel panel = new JPanel();
		setLayout((new GridLayout(2,1)));
		JLabel guessLabel = new JLabel("Whose turn?");
		//name = new JTextField(5);
		add(guessLabel);
		//panel.add(name);
		whosTurn = new JTextArea(1, 10);
		updateWhosTurn();
		
		add(whosTurn);
		
	}
	
	private void updateWhosTurn() {
		whosTurn.setText(playerTurn);
	}
	
	public void setWhosTurn(String whosTurn) {
		playerTurn = whosTurn;
		updateWhosTurn();
	}

}
