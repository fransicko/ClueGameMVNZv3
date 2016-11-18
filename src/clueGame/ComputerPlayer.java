package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import clueControlGUI.CreateGuessPanel;
import clueControlGUI.CreateGuessResultPanel;

public class ComputerPlayer extends Player{
	// This will be used to keep track of rooms visited
	private BoardCell visited = new BoardCell(0, 0,"nothing");
	public CreateGuessPanel guess = CreateGuessPanel.getInstance();
	public CreateGuessResultPanel response = CreateGuessResultPanel.getInstance();

	public ComputerPlayer(String name, int row, int column, Color color) {
		super(name, row, column, color);
		// TODO Auto-generated constructor stub
	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		for (BoardCell i: targets) {
			//(visited.getX() == i.getX() && visited.getY() == i.getY())
			if (i.isDoorway() && !(visited.getX() == i.getX() && visited.getY() == i.getY() && visited.getWholeValue().equals(i.getWholeValue()))) {
				visited = new BoardCell(i.getY(), i.getX(), i.getWholeValue());
				return i;
			}
		}

		Random ran = new Random();
		Iterator<BoardCell> itr = targets.iterator();
		int location = Math.abs(ran.nextInt(targets.size()));

		int i = 0;
		while (itr.hasNext()) {
			BoardCell loc = itr.next();
			if (i == location) {
				return loc;
			}
			++i;

		}

		return null;
	}

	public void makeAccusation() {
		Random ran = new Random();
		int room = Math.abs(ran.nextInt())%Board.getInstance().roomCards.size();
		int ppl = Math.abs(ran.nextInt())%Board.getInstance().personCards.size();
		int weapon = Math.abs(ran.nextInt())%Board.getInstance().weaponCards.size();

		Solution compSoln = new Solution(Board.getInstance().personCards.get(ppl).getName(), Board.getInstance().roomCards.get(room).getName(), Board.getInstance().weaponCards.get(weapon).getName());
		Board.getInstance().checkAccusation(compSoln);
	}

	public void createSuggestion() {
		Random ran = new Random();
		String room = board.legendMap.get(Board.getInstance().getCellAt(getRow(), getColumn()).getInitial());
		ArrayList<Card> people = new ArrayList<>();
		for (Card i : Board.getInstance().personCards) {
			if (!Board.getInstance().seenCards.contains(i)) {
				people.add(i);
			}
		}
		ArrayList<Card> weapon = new ArrayList<>();
		for (Card i : Board.getInstance().weaponCards) {
			if (!Board.getInstance().seenCards.contains(i)) {
				weapon.add(i);
			}
		}

		int selection1 = Math.abs(ran.nextInt())%people.size();
		int selection2 = Math.abs(ran.nextInt())%weapon.size();

		suggestion = new Solution(people.get(selection1).getName(), room, weapon.get(selection2).getName());

	}

	public void compMove(int k) {
		board.calcTargets(getRow(), getColumn(), k);
		BoardCell move = pickLocation(board.getTargets());

		setColumn(move.getX());
		setRow(move.getY());
		
		if (board.getCellAt(getRow(), getColumn()).isDoorway()) makeSuggestion();
		board.repaint();

	}
	
	public void makeSuggestion() {
		createSuggestion();
		
		int index = 0;
		for (int i = 0; i < board.players.size(); ++i) {
			if (board.players.get(i).getName().equals(getName())) {
				index = i;
				break;
			}
		}
		// This will pull a random player so the current room
		for (int i = 0; i < 6; ++i) {
			if (board.players.get(i).getName().equals(suggestion.getPerson())) {
				board.players.get(i).setRow(getRow());
				board.players.get(i).setColumn(getColumn());
				
				if (!board.players.get(i).getName().equals("Mr. Smith")) {
					board.comp.get(i-1).setVisited(getRow(), getColumn());
				}
				guess.setGuess(suggestion);
				response.setResponse(index);
				
				break;
			}
		}
		
		
	}
	
	public void setVisited(int i, int j) {
		visited = new BoardCell(i, j, board.getCellAt(i, j).getWholeValue());
	}

}
