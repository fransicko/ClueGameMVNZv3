package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	private String Name;
	private int row;
	private int column;
	private Color color;
	public ArrayList<Card> hand = new ArrayList<>();
	public Board board = Board.getInstance();

	public Player(String name, int row, int column, Color color) {
		super();
		Name = name;
		this.row = row;
		this.column = column;
		this.color = color;
	}

	public Card disproveSuggestion(Solution suggestion) {
		//These are here to see if any of the cards are correct
		boolean cP = false;
		boolean cW = false;
		boolean cR = false;
		
		for (Card i: hand) {
			if (i.getName().equals(suggestion.person)) cP = true;
			else if (i.getName().equals(suggestion.room)) cR = true;
			else if (i.getName().equals(suggestion.weapon)) cW = true;
		}

		//This will be our dummy var
		Card ans = null;

		if (cP && cW && cR) {
			Random ran = new Random();
			int choose = Math.abs(ran.nextInt())%3;

			switch (choose) {
			case 0:
				ans = new Card(suggestion.person, CardType.PERSON);
				Board.getInstance().seenCards.add(new Card(ans));
				return ans;
			case 1:
				ans = new Card(suggestion.weapon, CardType.WEAPON);
				Board.getInstance().seenCards.add(new Card(ans));
				return ans;
			case 2:
				ans = new Card(suggestion.room, CardType.ROOM);
				Board.getInstance().seenCards.add(new Card(ans));
				return ans;
			}
		}

		else if (cP || cW || cR) {
			//first check the combinations of them
			if (cP && cW) {
				Random ran = new Random();
				int choose = Math.abs(ran.nextInt())%2;
				switch (choose) {
				case 0:
					ans = new Card(suggestion.person, CardType.PERSON);
					Board.getInstance().seenCards.add(new Card(ans));
					return ans;
				case 1:
					ans = new Card(suggestion.weapon, CardType.WEAPON);
					Board.getInstance().seenCards.add(new Card(ans));
					return ans;
				}
			}
			else if (cP && cR) {
				Random ran = new Random();
				int choose = Math.abs(ran.nextInt())%2;
				switch (choose) {
				case 0:
					ans = new Card(suggestion.person, CardType.PERSON);
					Board.getInstance().seenCards.add(new Card(ans));
					return ans;
				case 1:
					ans = new Card(suggestion.room, CardType.ROOM);
					Board.getInstance().seenCards.add(new Card(ans));
					return ans;
				}
			}
			else if (cW && cR) {
				Random ran = new Random();
				int choose = Math.abs(ran.nextInt())%2;
				switch (choose) {
				case 0:
					ans = new Card(suggestion.weapon, CardType.WEAPON);
					Board.getInstance().seenCards.add(new Card(ans));
					return ans;
				case 1:
					ans = new Card(suggestion.room, CardType.ROOM);
					Board.getInstance().seenCards.add(new Card(ans));
					return ans;
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

	public void draw(Graphics g) {
		
		g.setColor(color);
		g.fillRoundRect(column*20, row*20, 20, 20, 100, 100);
		g.drawRoundRect(column*20, row*20, 20, 20, 100, 100);
		
	}	


}
