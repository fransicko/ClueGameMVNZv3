package clueGame;

import java.awt.Color;
import java.util.Set;

import clueControlGUI.MouseClickerPanel;

public class HumanPlayer extends Player{
	private Board board = Board.getInstance();
	private MouseClickerPanel click = new MouseClickerPanel();

	public HumanPlayer(String name, int row, int column, Color color) {
		super(name, row, column, color);
	}
	
	public void makeMove(int roll) {
		board.calcTargets(getRow(), getColumn(), roll);
		Set<BoardCell> targs = board.getTargets();
		
		for (BoardCell i: targs) {
			i.setCanMove(Color.cyan);
		}
		board.repaint();
		
		board.addMouseListener(click);
		
	}
	
	public void finishMove() {
		board.removeMouseListener(click);
		for (BoardCell i: board.getTargets()) {
			
			i.setCanMove(Color.yellow);
		}
		
		board.repaint();
	}
	
	public String getTileInitial() {
		return board.getInitial(row, column);;
	}
}
