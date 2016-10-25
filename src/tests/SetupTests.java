package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;

public class SetupTests {
	// Constants that I will use to test whether the file was loaded correctly
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 26;

	// NOTE: I made Board static because I only want to set it up one 
	// time (using @BeforeClass), no need to do setup before each test.
	private static Board board;

	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueBoard.csv", "ClueLegend.txt");
		// set the file names for the player and weapon files
		board.setConfigFiles2("Players.txt", "weapons.txt");
		// Initialize will load BOTH config files 
		board.loadConfigFile();
	}
	
	// This will check to see if the people were loaded in correctly
	@Test
	public void PeopleLoaded() {
		//check that the person is there
		HumanPlayer person = board.getPerson();
		assertEquals("Mr. Smith", person.getName());
		assertEquals(0, person.getRow());
		assertEquals(6, person.getColumn());
		assertEquals(Color.black, person.getColor());
		
		//Check the first person
		ComputerPlayer comp1 = board.getCompAt(0);
		assertEquals("Ms. Proudmoore", comp1.getName());
		assertEquals(9, comp1.getRow());
		assertEquals(0, comp1.getColumn());
		assertEquals(Color.blue, comp1.getColor());
		
		//Check the last person
		ComputerPlayer comp2 = board.getCompAt(4);
		assertEquals("Mr. Hellscream", comp2.getName());
		assertEquals(6, comp2.getRow());
		assertEquals(6, comp2.getColumn());
		assertEquals(Color.red, comp2.getColor());
	}
	
	// This will chack to make sure we have a full deck.
	@Test
	public void FullDeck() {
		int size = board.deckSize();
		assertEquals(21, size);
		
		int room = 0;
		int weapon = 0;
		int person = 0;
		ArrayList<Card> checkDeck = board.getDeck();
		
		// We will run throught the array of cards to make sure that we have the correct number of cards of each type
		for (Card i: checkDeck) {
			if (i.getType() == CardType.PERSON) ++person;
			else if (i.getType() == CardType.WEAPON) ++weapon;
			else if (i.getType() == CardType.ROOM) ++room;
		}
		
		assertEquals(6, person);
		assertEquals(6, weapon);
		assertEquals(9, room);
		
		//Test to make sure that the cards are loaded correctly
		assertTrue(checkDeck.get(12).getName().equals("Mr. Thrall"));
		assertTrue(checkDeck.get(12).getType() == CardType.PERSON);
		assertTrue(checkDeck.get(15).getName().equals("DoomHammer"));
		assertTrue(checkDeck.get(15).getType() == CardType.WEAPON);
		assertTrue(checkDeck.get(0).getName().equals("Bathroom"));
		assertTrue(checkDeck.get(0).getType() == CardType.ROOM);
	}
	
	@Test
	public void DealtCards() {
		HumanPlayer a = board.getPerson();
		ComputerPlayer b = board.getCompAt(0);
		ComputerPlayer c = board.getCompAt(4);
		
		//Check to make sure that these people have the correct corresponding # of cards 
		assertEquals(4, a.hand.size());
		assertEquals(4, b.hand.size());
		assertEquals(3, c.hand.size());
		
		// Check to make sure that each person has a unique card and checks to make sure that there are no duplicates
		// How does this check duplicates? Before the deck is dealt, there are 21 unique cards in the deck. Once all the cards are,
		// dealt, the deck will be empty and each players will have from 3-4 cards, each of which are unique. When the test is run, all
		// cards from the players are put back into a testing deck that will check if the total number of cards is 21. Since the 
		// cards are unique since the the dealing of the cards, and putting into the testing deck, if the number of cards is 21, then
		// there will not be any duplicates.
		int checkDupicats = a.hand.size();
		ArrayList<ComputerPlayer> d = new ArrayList<>(board.getComp());
		for (ComputerPlayer i: d) {
			checkDupicats += i.hand.size();
		}
		
		assertEquals(21, checkDupicats);
	}

}
