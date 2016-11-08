package clueControlGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clueGame.Board;

public class clueGameGUI extends JFrame{
	private static Board board;
	
	public clueGameGUI() {
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = Board.getInstance();
		board.setConfigFiles("ClueBoard.csv", "ClueLegend.txt");
		// set the file names for the player and weapon files
		board.setConfigFiles2("Players.txt", "weapons.txt");
		// Initialize will load BOTH config files 
		board.loadConfigFile();
		
		add(board, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		clueGameGUI frame = new clueGameGUI();
		frame.setVisible(true);
	}
}
