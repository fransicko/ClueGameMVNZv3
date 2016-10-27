package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Solution;

public class gameActionTests {
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

	// This will check that if the comp player is not near a door then chose randomlly
	@Test
	public void selectRandomTarget() {
		ComputerPlayer a = new ComputerPlayer("test", 24, 0, Color.gray);
		board.calcTargets(24, 0, 2);

		boolean loc_22_0 = false;
		boolean loc_23_1 = false;
		boolean loc_24_2 = false;

		for (int i = 0; i < 100; ++i) {
			BoardCell selected = a.pickLocation(board.getTargets());

			if (selected == board.getCellAt(22, 0)) {
				loc_22_0 = true;
			}
			else if (selected == board.getCellAt(23, 1)) {
				loc_23_1 = true;
			}
			else if (selected == board.getCellAt(24, 2)) {
				loc_24_2 = true;
			}
			else {
				fail("Its getting a wrong value");
			}
		}

		assertTrue(loc_22_0);
		assertTrue(loc_23_1);
		assertTrue(loc_24_2);
	}

	//Test that only a door is selected if it is an option and has not been visited
	@Test
	public void selectRoomTarget() {
		ComputerPlayer a = new ComputerPlayer("test", 9, 0, Color.gray);
		board.calcTargets(9, 0, 2);
		BoardCell selectedA = a.pickLocation(board.getTargets());

		ComputerPlayer b = new ComputerPlayer("test1", 0, 0, Color.gray);
		board.calcTargets(23, 3, 2);
		BoardCell selectedB = b.pickLocation(board.getTargets());

		assertEquals(board.getCellAt(10, 0), selectedA);
		assertEquals(board.getCellAt(21, 3), selectedB);
	}

	//Test to make sure that if we visited a room than we don't automatically visit again when moving
	@Test
	public void selectNotVisitedRoomTarget() {
		// This will be the initial set up
		ComputerPlayer a = new ComputerPlayer("test", 9, 0, Color.gray);
		board.calcTargets(2, 7, 4);
		BoardCell selected = a.pickLocation(board.getTargets());

		int loc_1_8 = 0;
		int loc_4_5 = 0;

		if (selected == board.getCellAt(1, 8)) {
			loc_1_8 += 1;
		}
		else if (selected == board.getCellAt(4, 5)) {
			loc_4_5 += 1;
		}

		// This will move us out of the door and into the next door
		// The point is to make sure that we visited both doors then we will do a random one after
		board.calcTargets(selected, 6);
		selected = a.pickLocation(board.getTargets());
		if (selected == board.getCellAt(1, 8)) {
			loc_1_8 += 1;
		}
		else if (selected == board.getCellAt(4, 5)) {
			loc_4_5 += 1;
		}

		// Make sure they are visited after two runs
		assertEquals(1, loc_1_8);
		assertEquals(1, loc_4_5);

		// we will go through a loop to make sure that its getting more than one spot
		ComputerPlayer test = new ComputerPlayer("test1", 2, 7, Color.gray);
		board.calcTargets(2, 7, 2);
		BoardCell selectedT = test.pickLocation(board.getTargets());
		assertEquals(selectedT, board.getCellAt(1, 8));

		// selected is now the north-east door
		board.calcTargets(selectedT, 1); 
		selectedT = test.pickLocation(board.getTargets()); //we are now out of the room

		board.calcTargets(selectedT, 4); 
		selectedT = test.pickLocation(board.getTargets());
		// We should not be in the room
		// NOTE: for this test it will fail for certain times
		assertNotEquals(selectedT, board.getCellAt(1, 8));


	}

	//This will test the accusation made by the player
	@Test
	public void checkAccusation() {
		Solution correct = new Solution(board.getSoln().person, board.getSoln().room, board.getSoln().weapon);
		assertTrue(board.checkAccusation(correct));

		//Check for wrong person
		String p = "";
		for (Card i: board.personCards) {
			if (i.getName() != board.getSoln().person) {
				p = i.getName();
				break;
			}
		}
		Solution wrongP = new Solution(p, board.getSoln().room, board.getSoln().weapon);
		assertFalse(board.checkAccusation(wrongP));

		//Check wrong weapon
		String w = "";
		for (Card i: board.weaponCards) {
			if (i.getName() != board.getSoln().weapon) {
				w = i.getName();
				break;
			}
		}
		Solution wrongW = new Solution(board.getSoln().person, board.getSoln().room, w);
		assertFalse(board.checkAccusation(wrongW));

		//Check for wrong room
		String r = "";
		for (Card i: board.roomCards) {
			if (i.getName() != board.getSoln().room) {
				r = i.getName();
				break;
			}
		}
		Solution wrongR = new Solution(board.getSoln().person, r, board.getSoln().weapon);
		assertFalse(board.checkAccusation(wrongR));

	}

	@Test
	public void disproveSuggestion() {
		ComputerPlayer test = new ComputerPlayer("Testing", 0, 0, Color.black);
		Solution correct = new Solution(board.getSoln().person, board.getSoln().room, board.getSoln().weapon);
		//Check for one wrong card
		String p = "";
		for (Card i: board.personCards) {
			if (i.getName() != board.getSoln().person) {
				p = i.getName();
				break;
			}
		}
		//Check wrong weapon
		String w = "";
		for (Card i: board.weaponCards) {
			if (i.getName() != board.getSoln().weapon) {
				w = i.getName();
				break;
			}
		}
		//Check for wrong room
		String r = "";
		for (Card i: board.roomCards) {
			if (i.getName() != board.getSoln().room) {
				r = i.getName();
				break;
			}
		}
		
		test.hand = new ArrayList<Card>();
		test.hand.add(new Card(board.getSoln().room, CardType.ROOM));

		//Check with only one correct value
		Solution wrongPW = new Solution(p, board.getSoln().room, w);
		Card correctR = test.disproveSuggestion(wrongPW);
		assertEquals(board.getSoln().room, correctR.getName());

		//Check with two correct values
		Solution wrongP = new Solution(p, board.getSoln().room, board.getSoln().weapon);
		test.hand.add(new Card(board.getSoln().weapon, CardType.WEAPON));
		// These will be used to check that we return atleast one of them
		boolean cR = false;
		boolean cW = false;

		for (int i = 0; i < 10; ++i) {
			Card correctRW = test.disproveSuggestion(wrongP);
			if (correctRW.getName().equals(board.getSoln().room)) {
				cR = true;
			}
			else if (correctRW.getName().equals(board.getSoln().weapon)) {
				cW = true;
			}
		}

		assertTrue(cR);
		assertTrue(cW);

		//Now we check if it is null
		Solution wrongPWR = new Solution(p, r, w);
		Card correctNULL = test.disproveSuggestion(wrongPWR);
		assertNull(correctNULL);
	}

	@Test
	public void createSuggestion() {
		ComputerPlayer a = new ComputerPlayer("test", 21, 3, Color.gray);
		ArrayList<Card> p = new ArrayList<>(board.personCards);
		ArrayList<Card> w = new ArrayList<>(board.weaponCards);
		Random ran = new Random();
		
		// Check to make sure that the suggested room is the current room
		a.createSuggestion();
		assertEquals(board.getCellAt(21, 3).getWholeValue(), a.suggestion.room);
		
		// Check with only one person and weapon
		// Check an array with one person
		board.personCards = new ArrayList<>();
		board.personCards.add(p.get(1));
		board.weaponCards = new ArrayList<>();
		board.weaponCards.add(w.get(0));
		
		a.createSuggestion();
		assertEquals("Ms. Proudmoore", a.suggestion.person);
		assertEquals("DoomHammer", a.suggestion.weapon);
		
		// check with multiple people
		// Because of the random selection,
		boolean isPerson = false;
		board.personCards.add(p.get(2));
		board.personCards.add(p.get(3));
		
		
		for(int i = 0; i < 100; i++) {
			a.createSuggestion();
			if(a.suggestion.person.equals("Mr. Wrynn")) {
				break;
			}
		}
		assertEquals("Mr. Wrynn", a.suggestion.person);
		
		// check with multiple weapons
		boolean isWeapon = false;
		board.weaponCards.add(w.get(2));
		board.weaponCards.add(w.get(3));
		
		for(int i = 0; i < 100; i++) {
			a.createSuggestion();
			if(a.suggestion.weapon.equals("Atiesh")) {
				break;
			}
		}
		assertEquals("Atiesh", a.suggestion.weapon);
	}
	
	@Test
	public void handleSuggestion() {
		String suggester = "Test";
		// At the start the suggestion is correct but will change later
		Solution suggestion = new Solution(board.getSoln().person, board.getSoln().room, board.getSoln().weapon);;
		// This will test to make sure that no one has a card to disprove
		// We will make everyones hand empty
		Board.getInstance().person.hand = new ArrayList<Card>();
		for (ComputerPlayer i: Board.getInstance().comp) {
			i.hand = new ArrayList<Card>();
		}
		Card noOne = Board.getInstance().handleSuggestion(suggestion, suggester);
		assertNull(noOne);
		
		// Only the accusing player has the cards
		Board.getInstance().comp.add(new ComputerPlayer("test", 0, 0, Color.black));
		Board.getInstance().comp.get(5).hand.add(new Card(board.getSoln().person, CardType.PERSON));
		Card accus = Board.getInstance().handleSuggestion(suggestion, "test");
		assertNull(accus);
		
		//only human can disprove
		Board.getInstance().person.hand.add(new Card(board.getSoln().room, CardType.ROOM));
		Card human1 = Board.getInstance().handleSuggestion(suggestion, "test");
		assertTrue(board.getSoln().room.equals(human1.getName()));
		
		//Now the human is the accuser
		//We are re-using the values that we have already laid out but we are getting ride of test's hand
		Board.getInstance().comp.get(5).hand = new ArrayList<Card>();
		Card human2 = Board.getInstance().handleSuggestion(suggestion, Board.getInstance().person.getName());
		assertNull(human2);
		
		// Two computers can disprove but only the first one can disprove
		Board.getInstance().person.hand = new ArrayList<Card>();
		Board.getInstance().comp.get(1).hand.add(new Card(board.getSoln().person, CardType.PERSON));
		Board.getInstance().comp.get(2).hand.add(new Card(board.getSoln().room, CardType.ROOM));
		Card comp0 = Board.getInstance().handleSuggestion(suggestion, Board.getInstance().comp.get(0).getName());
		assertTrue(board.getSoln().person.equals(comp0.getName()));
		
		// Player then computer can disprove but only computer will disprove
		Board.getInstance().person.hand.add((new Card(board.getSoln().weapon, CardType.WEAPON)));
		Card comp1 = Board.getInstance().handleSuggestion(suggestion, Board.getInstance().comp.get(0).getName());
		assertTrue(board.getSoln().person.equals(comp1.getName()));
		
	}

}
