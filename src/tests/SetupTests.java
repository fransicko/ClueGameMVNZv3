package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
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
		ComputerPlayer comp1 = board.getComp(0);
		assertEquals("Ms. Proudmoore", comp1.getName());
		assertEquals(9, comp1.getRow());
		assertEquals(0, comp1.getColumn());
		assertEquals(Color.blue, comp1.getColor());
		
		//Check the last person
		ComputerPlayer comp2 = board.getComp(4);
		assertEquals("Mr. Hellscream", comp2.getName());
		assertEquals(6, comp2.getRow());
		assertEquals(6, comp2.getColumn());
		assertEquals(Color.red, comp2.getColor());
	}
	
	// This will chack to make sure we have a full deck.
	@Test
	public void FullDeck() {
		
	}

}
