package clueControlGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.junit.rules.DisableOnDebug;

import clueGame.Board;

public class clueGameGUI extends JFrame{
	private static Board board;
	private detectiveNotes notes = new detectiveNotes();
	
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		
	}
	
	
	
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createDetecItem());
		menu.add(createFileExitItem());
		
		return menu;
	}
	
	private JMenuItem createDetecItem() {
		JMenuItem item = new JMenuItem("Notes");
		class DialogListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				notes.setVisible(true);
				
			}
		}
		item.addActionListener(new DialogListener());
		
		return item;
	}

	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		
		return item;
	}

	public static void main(String[] args) {
		clueGameGUI frame = new clueGameGUI();
		frame.setVisible(true);
	}
}
