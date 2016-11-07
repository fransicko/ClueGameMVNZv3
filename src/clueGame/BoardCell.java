package clueGame;

import java.awt.Graphics;

public class BoardCell {

	private int y;	// This represents the rows
	private int x;	// This represents the columns
	private String boardCellInitial;
	private int height = 20;
	private int width = 20;
	private int Xpixel;
	private int Ypixel;
	
	public BoardCell(int y, int x, String letter) {
		super();
		this.y = y;
		this.x = x;
		this.boardCellInitial = letter;
		
		this.Xpixel = x*width;
		this.Ypixel = y*height;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public boolean isDoorway() {
		
		if (boardCellInitial.length() == 2 && boardCellInitial.charAt(1) != 'N') {
			return true;
		}
		
		return false;
	}

	public DoorDirection getDoorDirection() {
		
		if (boardCellInitial.charAt(1) == 'U') {
			return DoorDirection.UP;
		}
		if (boardCellInitial.charAt(1) == 'D') {
			return DoorDirection.DOWN;
		}
		if (boardCellInitial.charAt(1) == 'L') {
			return DoorDirection.LEFT;
		}
		if (boardCellInitial.charAt(1) == 'R') {
			return DoorDirection.RIGHT;
		}
		
		return DoorDirection.NONE;
	}


	public char getInitial() {
		
		return boardCellInitial.charAt(0);
	}
	
	
	public String getWholeValue() {
		return boardCellInitial;
	}
	
	public char getSecondInitial() {
		
		return boardCellInitial.charAt(1);
	}
	
	public void draw(Graphics g) {
		g.drawRect(Xpixel, Ypixel, width, height);
	}

}
