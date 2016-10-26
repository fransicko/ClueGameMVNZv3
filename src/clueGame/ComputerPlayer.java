package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	// This will be used to keep track of rooms visited
	private BoardCell visited = new BoardCell(0,0,"nothing");

	public ComputerPlayer(String name, int row, int column, Color color) {
		super(name, row, column, color);
		// TODO Auto-generated constructor stub
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		for (BoardCell i: targets) {
			if (i.isDoorway() && !(visited.getX() == i.getX() && visited.getY() == i.getY())) {
				visited = new BoardCell(i.getX(), i.getY(), i.getWholeValue());
				return i;
			}
		}
		
		Random ran = new Random();
		Iterator<BoardCell> itr = targets.iterator();
		int location = Math.abs(ran.nextInt())%targets.size();
		
		int i = 0;
		while (itr.hasNext()) {
			BoardCell loc = itr.next();
			if (i == location) {
				return loc;
			}
			++i;
			
		}
		
		return null;
	}
	
	public void makeAccusation() {
		Random ran = new Random();
		int room = Math.abs(ran.nextInt())%Board.getInstance().roomCards.size();
		int ppl = Math.abs(ran.nextInt())%Board.getInstance().personCards.size();
		int weapon = Math.abs(ran.nextInt())%Board.getInstance().weaponCards.size();
		
		Solution compSoln = new Solution(Board.getInstance().personCards.get(ppl).getName(), Board.getInstance().roomCards.get(room).getName(), Board.getInstance().weaponCards.get(weapon).getName());
		Board.getInstance().checkAccusation(compSoln);
	}
	
	public void createSuggestion() {
		
	}

}
