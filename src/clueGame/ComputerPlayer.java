package clueGame;

import java.awt.Color;
import java.util.Set;

public class ComputerPlayer extends Player{
	// This will be used to keep track of 

	public ComputerPlayer(String name, int row, int column, Color color) {
		super(name, row, column, color);
		// TODO Auto-generated constructor stub
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		for (BoardCell i: targets) {
			if (i.isDoorway()) {
				return i;
			}
		}
		
		
		return null;
	}
	
	public void makeAccusation() {
		
	}
	
	public void createSuggestion() {
		
	}

}
