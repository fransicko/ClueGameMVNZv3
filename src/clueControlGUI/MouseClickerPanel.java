package clueControlGUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JPanel;

import clueGame.Board;
import clueGame.BoardCell;

public class MouseClickerPanel extends JPanel {
	private Board board = Board.getInstance();
	private ArrayList<targetBox> targets;
	private int roll;
	
	public MouseClickerPanel(int roll) {
		this.roll = roll;
		setPreferredSize(new Dimension(20, 20));
		
		
		board.calcTargets(board.person.getColumn(), board.person.getRow(), roll);
		createBoxes(board.getTargets());
	}
	
	private void createBoxes(Set<BoardCell> targs) {
			targets = new ArrayList<>();
			
			for (BoardCell i: targs) {
				targets.add(new targetBox(i.getX(), i.getY()));
			}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (targetBox i: targets) {
			i.draw(g);
		}
	}
	
	

}
