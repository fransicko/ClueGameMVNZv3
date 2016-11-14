package clueGame;

import java.awt.Color;
import java.awt.Graphics;

public class BoardCell {

	private int y;	// This represents the rows
	private int x;	// This represents the columns
	private String boardCellInitial;
	private int height = 20;
	private int width = 20;
	private int Xpixel;
	private int Ypixel;
	private Color canMove;
	
	public BoardCell(int y, int x, String letter) {
		super();
		this.y = y;
		this.x = x;
		this.boardCellInitial = letter;
		
		this.Xpixel = x*width;
		this.Ypixel = y*height;
		canMove = Color.yellow;
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
		char initail = getInitial();
		Board board = Board.getInstance();
		
		if (initail == 'W') {
			// These two lines fill in the rectangle with the given color
			g.setColor(canMove);
			g.fillRect(Xpixel, Ypixel, width, height);
			canMove = Color.yellow;
			
			// These two lines create the black border around the rectangle
			g.setColor(Color.black);
			g.drawRect(Xpixel, Ypixel, width, height);
		}
		else if (initail == 'X') {
			g.setColor(Color.red);
			g.fillRect(Xpixel, Ypixel, width, height);
		}
		else {
			if (canMove == Color.cyan) {
				g.setColor(canMove);
				canMove = Color.yellow;
			}
			else g.setColor(Color.gray);
			
			g.fillRect(Xpixel, Ypixel, width, height);
			
			if (boardCellInitial.length() == 2) {
				g.setColor(Color.blue);
				
				
				if (getSecondInitial() == 'R') {
					
					g.fillRect(Xpixel+width-5, Ypixel, 5, height);
					g.drawRect(Xpixel+width-5, Ypixel, 5, height);
				}
				else if (getSecondInitial() == 'L') {
					
					g.fillRect(Xpixel, Ypixel, 5, height);
					g.drawRect(Xpixel, Ypixel, 5, height);
				}
				else if (getSecondInitial() == 'U') {
					
					g.fillRect(Xpixel, Ypixel, width, 5);
					g.drawRect(Xpixel, Ypixel, width, 5);
				}
				else if (getSecondInitial() == 'D') {
					
					g.fillRect(Xpixel, Ypixel+height-5, width, 5);
					g.drawRect(Xpixel, Ypixel+height-5, width, 5);
				}
				
				if (getSecondInitial() == 'N') {
					String name = board.getLegendAt(getInitial());
					g.drawString(name, Xpixel, Ypixel);
				}
			}
		}
		//These two lines create the black border around the rectangle
		//g.setColor(Color.black);
		//g.drawRect(Xpixel, Ypixel, width, height);
	}

	public void setCanMove(Color canMove) {
		this.canMove = canMove;
	}
	

}
