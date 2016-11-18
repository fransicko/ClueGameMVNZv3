package clueGame;

public class Card {
	private String cardName;
	private CardType type;
	
	public Card(String cardName, CardType type) {
		super();
		this.cardName = cardName;
		this.type = type;
	}

	public Card(Card ans) {
		// TODO Auto-generated constructor stub
		this.cardName = ans.getName();
		this.type = ans.getType();
	}

	public boolean equals() {
		// Made it false because it wont let it be null
		return false;
	}
	
	public String getName() {
		return cardName;
	}
	
	public CardType getType() {
		return type;
	}

	@Override
	public String toString() {
		return cardName;
	}

}
