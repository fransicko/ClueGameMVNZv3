package clueControlGUI;

import javax.swing.JOptionPane;

import clueGame.Board;

public class ClueGame {
	private static Board board;
	
	public static void main(String[] args) {
		board = Board.getInstance();
		board.setConfigFiles("ClueBoard.csv", "ClueLegend.txt");
		// set the file names for the player and weapon files
		board.setConfigFiles2("Players.txt", "weapons.txt");
		// Initialize will load BOTH config files 
		board.loadConfigFile();
		
		clueGameGUI frame = new clueGameGUI();

		JOptionPane welcome = new JOptionPane();
		welcome.showMessageDialog(frame, "You are " + board.person.getName() + ", press Next Player to begin play", "Welcome to Clue", JOptionPane.INFORMATION_MESSAGE);

		frame.setVisible(true);
	}
}
