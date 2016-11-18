package clueControlGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clueControlGUI.clueControlGUI.NextPlayerListener;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

public class MouseClickerPanel extends JDialog implements MouseListener{
	private Board board = Board.getInstance();
	public JComboBox<String> weaponDropDownGuess = new JComboBox<String>();
	public JComboBox<String> personDropDownGuess = new JComboBox<String>();
	public MouseClickerPanel guessPanel;
	public CreateGuessPanel guess = CreateGuessPanel.getInstance();
	public CreateGuessResultPanel response = CreateGuessResultPanel.getInstance();
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Set<BoardCell> targets = board.getTargets();
		Point i = e.getPoint();
		if(targets.contains(board.getCellAt((int) i.getY()/20, (int) i.getX()/20))) {
			board.person.setColumn((int) i.getX()/20);
			board.person.setRow((int) i.getY()/20);
			
			board.setTurn(true);
			board.person.finishMove();
			
			if(board.person.getTileInitial().length() == 2) {
				
				weaponDropDownGuess = new JComboBox<String>();
				personDropDownGuess = new JComboBox<String>();
				guessPanel = new MouseClickerPanel();
				
				//Display the guess panel - Player is inside a room
				guessPanel.setTitle("Make a Guess");
				guessPanel.setSize(350, 225);
				guessPanel.setLayout(new GridLayout(1,2));
				
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(4, 1));
				
				
				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout(4, 1));
				
				//Add Your Room, Weapons, People messageDialog
				JLabel yourRoomGuess = new JLabel("Your Room");
				JLabel personGuess = new JLabel("Person");
				JLabel weaponGuess = new JLabel("Weapon");
				panel.add(yourRoomGuess);
				panel.add(personGuess);
				panel.add(weaponGuess);
				
				//Add current room as guessRoom
				JLabel roomGuess = new JLabel(board.legendMap.get(board.person.getTileInitial().charAt(0)));
				panel2.add(roomGuess);
				
				//For the person drop down menu on guess panel
				
				for (Card j: board.personCards) {
					if (j.getName() != board.person.getName()) {
						personDropDownGuess.addItem(j.getName());
					}
				}
				
				
				panel2.add(personDropDownGuess);
				
				//For the weapon drop down menu on guess panel
				
				for (Card j: board.weaponCards) {
					weaponDropDownGuess.addItem(j.getName());
				}
				
				panel2.add(weaponDropDownGuess);
				
				//Need to Add submit button to panel1
				JButton submitGuessPanel = new JButton("Submit");
				submitGuessPanel.addActionListener(new SubmitGuessListener());
				panel.add(submitGuessPanel);
				
				//Cancel button for panel2
				JButton cancelGuessPanel = new JButton("Cancel");
				cancelGuessPanel.addActionListener(new CancelGuessListener());
				panel2.add(cancelGuessPanel);
				
				
				guessPanel.add(panel);
				guessPanel.add(panel2);
				guessPanel.setVisible(true);
				
				
			}

		}
		else {
			JFrame frame = new JFrame();
			JOptionPane wrong = new JOptionPane();
			wrong.showMessageDialog(frame, "Invalid position", "Try again", JOptionPane.INFORMATION_MESSAGE);

		}
	}
	
	class SubmitGuessListener implements ActionListener {

		Solution playerSug;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String personSug = personDropDownGuess.getSelectedItem().toString();
			String weaponSug = weaponDropDownGuess.getSelectedItem().toString();
			String roomSug = board.legendMap.get(board.person.getTileInitial().charAt(0));
			//System.out.println(personSug);
			
			board.person.setSuggestion(new Solution(personSug, roomSug, weaponSug));
			//System.out.println(board.person.suggestion);
			
			for (int i = 0; i < 6; ++i) {
				if (board.players.get(i).getName().equals(board.person.suggestion.getPerson())) {
					board.players.get(i).setRow(board.person.getRow());
					board.players.get(i).setColumn(board.person.getColumn());
					break;
				}
			}
			
			board.repaint();
			
			guess.setGuess(board.person.suggestion);
			response.setResponse();
			guessPanel.dispose();
			
		}
		

	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}



}



class CancelGuessListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "You must make a suggestion", "Play your turn", JOptionPane.INFORMATION_MESSAGE);
	}
}

