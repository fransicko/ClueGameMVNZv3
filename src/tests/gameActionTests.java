package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.ComputerPlayer;

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
	
	// This will check that if the comp player is not near a door the nchose randomlly
	// If near a door go in it if not visited
	// If visited chose treat normally
	@Test
	public void selectTarget() {
		// We will check to
		ComputerPlayer a = board.getCompAt(0);
	}
	
	@Test
	public void checkAccusation() {
		
	}
	
	@Test
	public void disproveSuggestion() {
		fail("Not yet implemented");
	}
	
	@Test
	public void handleSuggestion() {
		fail("Not yet implemented");
	}
	
	@Test
	public void createSuggestion() {
		fail("Not yet implemented");
	}

}
