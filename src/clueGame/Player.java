package clueGame;

import java.awt.Color;
import java.util.ArrayList;

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
