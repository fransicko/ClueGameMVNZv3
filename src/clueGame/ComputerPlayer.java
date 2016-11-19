package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import clueControlGUI.CreateGuessPanel;
import clueControlGUI.CreateGuessResultPanel;

public class ComputerPlayer extends Player{
	// This will be used to keep track of rooms visited
	private BoardCell visited = new BoardCell(0, 0,"nothing");
	public CreateGuessPanel guess = CreateGuessPanel.getInstance();
	public CreateGuessResultPanel response = CreateGuessResultPanel.getInstance();
	public Boolean compWin = false;

	public ComputerPlayer(String name, int row, int column, Color color) {
		super(name, row, column, color);
		// TODO Auto-generated constructor stub
	}

	public BoardCell pickLocation(Set<BoardCell> targets) {
		for (BoardCell i: targets) {
			if (i.isDoorway() && !visited.getWholeValue().equals(i.getWholeValue())) {
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


	public void createSuggestion() {
		Random ran = new Random();
		String room = board.legendMap.get(Board.getInstance().getCellAt(getRow(), getColumn()).getInitial());
		ArrayList<Card> people = new ArrayList<>(Board.getInstance().personCards);
		ArrayList<Card> weapon = new ArrayList<>(Board.getInstance().weaponCards);
		
		for (Card i: Board.getInstance().seenCards) {
			if (i.getType() == CardType.PERSON) {
				for (Card j: people) {
					if (j.getName().equals(i.getName())) {
						people.remove(j);
						break;
					}
				}
				
			}
		}
		
		for (Card i: Board.getInstance().seenCards) {
			if (i.getType() == CardType.WEAPON) {
				for (Card j: weapon) {
					if (j.getName().equals(i.getName())) {
						weapon.remove(j);
						break;
					}
				}
				
			}
		}

		int selection1 = Math.abs(ran.nextInt())%people.size();
		int selection2 = Math.abs(ran.nextInt())%weapon.size();

		suggestion = new Solution(people.get(selection1).getName(), room, weapon.get(selection2).getName());

	}

	public void compMove(int k) {
		if (hasAccusation) {
			accuse();
		}
		else {
			board.calcTargets(getRow(), getColumn(), k);
			BoardCell move = pickLocation(board.getTargets());

			setColumn(move.getX());
			setRow(move.getY());

			if (board.getCellAt(getRow(), getColumn()).isDoorway()) makeSuggestion();
			board.repaint();
		}



	}

	public void accuse() {
		if (board.checkAccusation(suggestion) == true) {
			//Player wins game

			JFrame frame = new JFrame();
			JOptionPane wrong = new JOptionPane();
			wrong.showMessageDialog(frame, "Congradulations " + getName() + ", it was " + suggestion.toString());
			System.exit(0);
		}
		else {
			JFrame frame = new JFrame();
			JOptionPane wrong = new JOptionPane();
			wrong.showMessageDialog(frame, getName() + " guessed incorrectly with " + suggestion.toString());
			hasAccusation = false;
			compWin = false;

		}

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
