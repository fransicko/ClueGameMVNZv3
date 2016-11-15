package clueControlGUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.HumanPlayer;

public class MouseClickerPanel implements MouseListener{
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
