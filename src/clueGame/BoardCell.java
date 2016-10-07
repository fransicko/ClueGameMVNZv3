package clueGame;

public class BoardCell {

	private int y;
	private int x;
	private String boardCellInitial;

	
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

	
	public void setInitial(String string){
		boardCellInitial = string;
	}


	public String getInitial() {
		// TODO Auto-generated method stub
		return boardCellInitial;
	}

}
