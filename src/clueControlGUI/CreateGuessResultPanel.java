package clueControlGUI;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.Card;
import clueGame.Solution;

public class CreateGuessResultPanel extends JPanel{
	private JTextArea response;
	private String r = "";	// Couldn't use guess again
	public Board board = Board.getInstance();
	
	// variable used for singleton pattern
	private static CreateGuessResultPanel theInstance = new CreateGuessResultPanel();

	// ctor is private to ensure only one can be created
	private CreateGuessResultPanel() {
		setLayout(new GridLayout(1,2));
		setBorder(new TitledBorder(new EtchedBorder(), "Response"));
		response = new JTextArea(1, 20);
		updateResponse();
		
		add(response);
	}

	// this method returns the only Board
	public static CreateGuessResultPanel getInstance() {
		return theInstance;
	}
	
	private void updateResponse() {
		response.setText(r);
	}
	
	public void setResponse(int i) {
		Card disprove = board.handleSuggestion(board.players.get(i).suggestion, board.players.get(i));
		board.seenCards.add(disprove);
		if (disprove != null) {
			this.r = disprove.toString();
		}
		
		updateResponse();
	}
}
