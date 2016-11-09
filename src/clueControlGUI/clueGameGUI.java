package clueControlGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.junit.rules.DisableOnDebug;

import clueGame.Board;

public class clueGameGUI extends JFrame{
	private static Board board;
	private detectiveNotes notes = new detectiveNotes();
	
	public clueGameGUI() {
		//setLayout(new GridLayout(2,1));
		JPanel panel = new JPanel(); // Attempting to put the board on a panel
		JPanel panel2 = new JPanel();
		panel.setLayout(new GridLayout(1,2)); //Setting layout
		//panel.setSize(800, 800);
		setSize(750, 675);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = Board.getInstance();
		board.setConfigFiles("ClueBoard.csv", "ClueLegend.txt");
		// set the file names for the player and weapon files
		board.setConfigFiles2("Players.txt", "weapons.txt");
		// Initialize will load BOTH config files 
		board.loadConfigFile();
		
		add(board, BorderLayout.CENTER); //new
		add(myCardsPanel(), BorderLayout.EAST);//myCards call
		//add(panel);
		
		clueControlGUI control = new clueControlGUI();
		control.setSize(new Dimension(200, 750));
		add(control, BorderLayout.SOUTH);
		//add(panel2);
		
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
	
	private JPanel myCardsPanel() { //Need to get the size right
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "My Cards"));
		panel.setPreferredSize(new Dimension(200, 500));
		
		JPanel panelPeople = new JPanel();
		JPanel panelRooms = new JPanel();
		JPanel panelWeapons = new JPanel();
		
		panelPeople.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		panelRooms.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		panelWeapons.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		
		panel.add(panelPeople);
		panel.add(panelRooms);
		panel.add(panelWeapons);
		
		return panel;
	}

	public static void main(String[] args) {
		clueGameGUI frame = new clueGameGUI();
		frame.setVisible(true);
	}
}
