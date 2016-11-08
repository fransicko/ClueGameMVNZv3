package clueControlGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.Player;


public class detectiveNotes extends JPanel{
	private JTextField name;
	static Board board;
	
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueBoard.csv", "ClueLegend.txt");
		// set the file names for the player and weapon files
		board.setConfigFiles2("Players.txt", "weapons.txt");
		// Initialize will load BOTH config files 
		board.loadConfigFile();
	}

	
	public detectiveNotes() {
		setLayout(new GridLayout(1,2));
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		
		panel.add(createPeoplePanel());
		panel2.add(personGuessPanel());
		
		add(panel);
		add(panel2);
	}
	
	private JPanel createPeoplePanel() {
		JCheckBox person;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		
		for (Player i: board.players) {
			person = new JCheckBox(i.getName());
			panel.add(person);
		}
		
		return panel;
	}
	
	private JPanel personGuessPanel() {
		JPanel panel = new JPanel();
		JComboBox<String> personGuess = new JComboBox<String>();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
		
		for (Player i: board.players) {
			personGuess.addItem(i.getName());
		}
		
		panel.add(personGuess);
		return panel;
	}
	
	public static void main(String[] args) {
		setUp();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(600,300));
		
		detectiveNotes control = new detectiveNotes();
		frame.add(control, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}

}
