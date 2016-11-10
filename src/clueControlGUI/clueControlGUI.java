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
	private CreateWhoseTurn turn;
	private CreateDiePanel die;
	
	
	public clueControlGUI() {
		turn = new CreateWhoseTurn();
		die = new CreateDiePanel();
		
		setLayout(new GridLayout(2,1));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.add(turn);
		//add(panel);
		panel.add(createNextPlayerButtonPanel());
		//add(panel);
		panel.add(createMakeAccusationButtonPanel());
		add(panel);
		JPanel panel2= new JPanel();
		panel2.setLayout(new GridLayout(1, 3));
		panel2.add(die);
		add(panel2);
		panel2.add(new CreateGuessPanel());
		add(panel2);
		panel2.add(createGuessResultPanel());
		add(panel2);
		
	}
	
	private JPanel createNextPlayerButtonPanel() {
		JButton nextPlayer = new JButton("Next player");
		JPanel panel = new JPanel(new BorderLayout());
		nextPlayer.addActionListener(new NextPlayerListener());
		
		panel.add(nextPlayer);
		return panel;
	}
	
	class NextPlayerListener implements ActionListener {
		private int next = 0;
		public void actionPerformed(ActionEvent e) {
			turn.setWhosTurn(board.players.get(next).getName());
			die.setRoll();
			
			++next;
			
			if (next == 6) next = 0;
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
	
	
}
