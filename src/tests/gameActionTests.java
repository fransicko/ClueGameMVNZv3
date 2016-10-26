package tests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
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
		boolean loc = false;
		ComputerPlayer test = new ComputerPlayer("test1", 0, 0, Color.gray);
		board.calcTargets(selected, 6);
		BoardCell selectedT = test.pickLocation(board.getTargets());
		
		//The point of this is to make sure that we land on a different location other than the doors
		for (int i = 0; i < 50; ++i) {
			if (selectedT != board.getCellAt(4, 5) && selectedT != board.getCellAt(1, 8)) {
				loc = true;
				break;
			}
			board.calcTargets(selectedT, 6);
			selectedT = test.pickLocation(board.getTargets());
		}
		
		assertTrue(loc);
		
		
	}
	
	//This will test the accusation made by the player, which is you
	@Test
	public void checkAccusation() {
		fail("Not yet implemented");
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
