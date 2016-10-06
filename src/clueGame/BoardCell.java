package clueGame;

public class BoardCell {

	private int y;
	private int x;
	
	public BoardCell(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public boolean isDoorway() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getDoorDirection() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getInitial() {
		// TODO Auto-generated method stub
		return null;
	}


}
