package tests;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;
import junit.framework.TestCase;

//@Before method to set up your IntBoard.
//Methods to test the creation of adjacency lists, including:
//top left corner (i.e., location [0][0])
//bottom right corner (i.e., location [3][3])
//a right edge (e.g., location [1][3])
//a left edge (e.g., location [3][0])
//second column middle of grid (e.g., location [1][1])
//second from last column, middle of grid(e.g., location [2][2]).
//Note: the "second" type tests are for catching off-by-one errors.
//Create at least six methods to test target creation (see examples below)
//This class must work with a 4x4 grid (i.e., not the full game board)
//should be in package tests


public class IntBoardTests extends TestCase {
	
	@Before
	@Test
	public void testAdjacency00()
	{
		BoardCell cell = IntBoard.getCell(0,0);
		Set<BoardCell> testList = IntBoard.getAdjList(cell);
		assertTrue(testList.contains(IntBoard.getCell(1, 0)));
		assertTrue(testList.contains(IntBoard.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency13()
	{
		BoardCell cell = IntBoard.getCell(1,3);
		Set<BoardCell> testList = IntBoard.getAdjList(cell);
		assertTrue(testList.contains(IntBoard.getCell(1, 2)));
		assertTrue(testList.contains(IntBoard.getCell(2, 3)));
		assertTrue(testList.contains(IntBoard.getCell(0, 3)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency30()
	{
		BoardCell cell = IntBoard.getCell(3,0);
		Set<BoardCell> testList = IntBoard.getAdjList(cell);
		assertTrue(testList.contains(IntBoard.getCell(3, 1)));
		assertTrue(testList.contains(IntBoard.getCell(2, 0)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency33()
	{
		BoardCell cell = IntBoard.getCell(3,3);
		Set<BoardCell> testList = IntBoard.getAdjList(cell);
		assertTrue(testList.contains(IntBoard.getCell(2, 3)));
		assertTrue(testList.contains(IntBoard.getCell(3, 2)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency11()
	{
		BoardCell cell = IntBoard.getCell(1,1);
		Set<BoardCell> testList = IntBoard.getAdjList(cell);
		assertTrue(testList.contains(IntBoard.getCell(1, 0)));
		assertTrue(testList.contains(IntBoard.getCell(0, 1)));
		assertTrue(testList.contains(IntBoard.getCell(2, 1)));
		assertTrue(testList.contains(IntBoard.getCell(1, 2)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency22()
	{
		BoardCell cell = IntBoard.getCell(2,2);
		Set<BoardCell> testList = IntBoard.getAdjList(cell);
		assertTrue(testList.contains(IntBoard.getCell(2, 3)));
		assertTrue(testList.contains(IntBoard.getCell(3, 2)));
		assertTrue(testList.contains(IntBoard.getCell(1, 2)));
		assertTrue(testList.contains(IntBoard.getCell(2, 1)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testTargets0_3()
	{
		BoardCell cell = IntBoard.getCell(0, 0);
		IntBoard.calcTargets(cell, 3);
		Set targets = IntBoard.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(IntBoard.getCell(3, 0)));
		assertTrue(targets.contains(IntBoard.getCell(2, 1)));
		assertTrue(targets.contains(IntBoard.getCell(0, 1)));
		assertTrue(targets.contains(IntBoard.getCell(1, 2)));
		assertTrue(targets.contains(IntBoard.getCell(0, 3)));
		assertTrue(targets.contains(IntBoard.getCell(1, 0)));
	}
	
	@Test
	public void testTargets3_0()
	{
		BoardCell cell = IntBoard.getCell(0, 0);
		IntBoard.calcTargets(cell, 3);
		Set targets = IntBoard.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(IntBoard.getCell(3, 0)));
		assertTrue(targets.contains(IntBoard.getCell(2, 1)));
		assertTrue(targets.contains(IntBoard.getCell(0, 1)));
		assertTrue(targets.contains(IntBoard.getCell(1, 2)));
		assertTrue(targets.contains(IntBoard.getCell(0, 3)));
		assertTrue(targets.contains(IntBoard.getCell(1, 0)));
	}
	
	@Test
	public void testTargets1_3()
	{
		BoardCell cell = IntBoard.getCell(0, 0);
		IntBoard.calcTargets(cell, 3);
		Set targets = IntBoard.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(IntBoard.getCell(3, 0)));
		assertTrue(targets.contains(IntBoard.getCell(2, 1)));
		assertTrue(targets.contains(IntBoard.getCell(0, 1)));
		assertTrue(targets.contains(IntBoard.getCell(1, 2)));
		assertTrue(targets.contains(IntBoard.getCell(0, 3)));
		assertTrue(targets.contains(IntBoard.getCell(1, 0)));
	}

}
