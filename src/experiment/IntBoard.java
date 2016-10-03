package experiment;

import java.util.*;

public class IntBoard {
	private int startCell;
	private int pathLength;
	
	public IntBoard(){
		super();
	}

	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private BoardCell[][] grid;
	Set<BoardCell> visited;
	Set<BoardCell> targets;
	
	public int calcAdjacencies() {
		return 0;
	}
	
	public static int calcTargets(BoardCell cell, int pathLength) {
		//findAllTargets();	
		return 0;
	}
	
	public static HashSet getTargets(){
		return null;	
	}
	
	public static HashSet getAdjList(BoardCell cell){
		return null;
	}

	public static BoardCell getCell(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
