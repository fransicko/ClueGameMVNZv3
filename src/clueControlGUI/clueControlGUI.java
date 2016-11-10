package clueControlGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;


public class clueControlGUI extends JPanel{
	private Board board = Board.getInstance();
	private JTextField name;
	private String whosTurn = "";
	
	public clueControlGUI() {
		setLayout(new GridLayout(2,1));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.add(createWhoseTurn());
		//add(panel);
		panel.add(createNextPlayerButtonPanel());
		//add(panel);
		panel.add(createMakeAccusationButtonPanel());
		add(panel);
		JPanel panel2= new JPanel();
		panel2.setLayout(new GridLayout(1, 3));
		panel2.add(createDiePanel());
		add(panel2);
		panel2.add(createGuessPanel());
		add(panel2);
		panel2.add(createGuessResultPanel());
		add(panel2);
		
	}
	
	private JPanel createDiePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		JLabel dieLabel = new JLabel("Roll");
		name = new JTextField(5);
		panel.add(dieLabel);
		panel.add(name);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		
		return panel;
		
	}
	
	private JPanel createGuessPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		//JLabel guessLabel = new JLabel("Guess");
		name = new JTextField(5);
		//panel.add(guessLabel);
		panel.add(name);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		return panel;
	}
	
	private JPanel createNextPlayerButtonPanel() {
		JButton nextPlayer = new JButton("Next player");
		JPanel panel = new JPanel(new BorderLayout());
		nextPlayer.addActionListener(new NextPlayerListener());
		
		panel.add(nextPlayer);
		return panel;
	}
	
	class NextPlayerListener implements ActionListener {
		private int turn = 0;
		public void actionPerformed(ActionEvent e) {
			setWhosTurn(board.players.get(turn).getName());
			++turn;
			
			if (turn == 6) turn = 0;
		}
	}
	
	private JPanel createMakeAccusationButtonPanel() {
		JButton accusation = new JButton("Make an Accusation");
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(accusation);
		return panel;
	}
	
	private JPanel createGuessResultPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		//JLabel guessLabel = new JLabel("Response");
		name = new JTextField(5);
		//panel.add(guessLabel);
		panel.add(name);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return panel;
	}
	
	private JPanel createWhoseTurn() {
		JPanel panel = new JPanel();
		panel.setLayout((new GridLayout(2,1)));
		JLabel guessLabel = new JLabel("Whose turn?");
		//name = new JTextField(5);
		panel.add(guessLabel);
		//panel.add(name);
		JTextArea people = new JTextArea(1, 10);
		people.setText(whosTurn);
		panel.add(people);
		
		return panel;
	}
	
	public void setWhosTurn(String whosTurn) {
		this.whosTurn = whosTurn;
	}
	
}
