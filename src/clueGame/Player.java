package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	private String Name;
	private int row;
	private int column;
	private Color color;
	public ArrayList<Card> hand = new ArrayList<>();
	
	public Player(String name, int row, int column, Color color) {
		super();
		Name = name;
		this.row = row;
		this.column = column;
		this.color = color;
	}
	
	public Card disproveSuggestion(Solution suggestion) {
		//These are here to see if any of the cards are correct
		boolean cP = Board.getInstance().getSoln().person.equals(suggestion.person);
		boolean cW = Board.getInstance().getSoln().weapon.equals(suggestion.weapon);
		boolean cR = Board.getInstance().getSoln().room.equals(suggestion.room);
		
		if (cP && cW && cR) {
			Random ran = new Random();
			int choose = Math.abs(ran.nextInt())%3;
			
			switch (choose) {
			case 0:
				return new Card(suggestion.person, CardType.PERSON);
			case 1:
				return new Card(suggestion.weapon, CardType.WEAPON);
			case 2:
				return new Card(suggestion.room, CardType.ROOM);
			}
		}
		
		else if (cP || cW || cR) {
			//first check the combinations of them
			if (cP && cW) {
				Random ran = new Random();
				int choose = Math.abs(ran.nextInt())%2;
				switch (choose) {
				case 0:
					return new Card(suggestion.person, CardType.PERSON);
				case 1:
					return new Card(suggestion.weapon, CardType.WEAPON);
				}
			}
			else if (cP && cR) {
				Random ran = new Random();
				int choose = Math.abs(ran.nextInt())%2;
				switch (choose) {
				case 0:
					return new Card(suggestion.person, CardType.PERSON);
				case 1:
					return new Card(suggestion.room, CardType.ROOM);
				}
			}
			else if (cW && cR) {
				Random ran = new Random();
				int choose = Math.abs(ran.nextInt())%2;
				switch (choose) {
				case 0:
					return new Card(suggestion.weapon, CardType.WEAPON);
				case 1:
					return new Card(suggestion.room, CardType.ROOM);
				}
			}
			
			// Now check the individual ones
			if (cP) return new Card(suggestion.person, CardType.PERSON);
			if (cW) return new Card(suggestion.weapon, CardType.WEAPON);
			if (cR) return new Card(suggestion.room, CardType.ROOM);
		}
		
		return null;
		
	}

	// These are for testing purposes
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void giveCard(Card card) {
		hand.add(card);
	}
	
	
	

}
