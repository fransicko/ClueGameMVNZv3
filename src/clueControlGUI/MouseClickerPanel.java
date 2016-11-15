package clueControlGUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.HumanPlayer;

public class MouseClickerPanel extends JDialog implements MouseListener{
	private Board board = Board.getInstance();
	
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
				//Display the guess panel - Player is inside a room
				MouseClickerPanel guessPanel = new MouseClickerPanel();
				guessPanel.setTitle("Make a Guess");
				guessPanel.setSize(700, 450);
				guessPanel.setLayout(new GridLayout(1,2));
				
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3, 1));
				
				
				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout(3, 1));
				
				
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

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}



}
