package clueGame;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	// This will be used to keep track of rooms visited
	private Set<BoardCell> visited = new HashSet<>();

	public ComputerPlayer(String name, int row, int column, Color color) {
		super(name, row, column, color);
		// TODO Auto-generated constructor stub
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		for (BoardCell i: targets) {
			if (i.isDoorway() && !visited.contains(i)) {
				visited.add(i);
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
		
	}
	
	public void createSuggestion() {
		
	}

}
