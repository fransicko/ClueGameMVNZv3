package clueControlGUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.HumanPlayer;

public class MouseClickerPanel extends JPanel implements MouseListener{
	private Board board = Board.getInstance();
	private ArrayList<targetBox> targets;
	//private int roll;
	int mouseX;
	int mouseY;
	targetBox playerChoice = null;
	
	public MouseClickerPanel() {
		setPreferredSize(new Dimension(20, 20));


		//board.calcTargets(board.person.getColumn(), board.person.getRow(), roll);
		createBoxes(board.getTargets());
		addMouseListener(this);
	}

	private void createBoxes(Set<BoardCell> targs) {
		targets = new ArrayList<>();

		for (BoardCell i: targs) {
			targets.add(new targetBox(i.getX(), i.getY()));
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (targetBox i: targets) {
			i.draw(g);
		}
	}
	
	//Added the next couple functions
	//playerchoice is based on if one of the targetboxes in the arraylist have a mouse click
	//I retrieve the humanPLayer variable called person from board and update the X and Y position
	//I created a setter in board to set a new humanPLayer, it retained the name and color, just a new position
	//What Im stuck on is actually updating the dot on the screen, i believe it is an issue with addmouselistener(this)
	//Feel free to modify
	@Override
	public void mouseClicked(MouseEvent e) {
		for (targetBox i: targets) {
			if(i.containsClick(e.getX(), e.getY())) {
				playerChoice = new targetBox(i.getX(), i.getY());
				HumanPlayer currentPlayer = board.getPerson();
				HumanPlayer player = new HumanPlayer (currentPlayer.getName(), playerChoice.getX(), playerChoice.getY(), currentPlayer.getColor());
				board.setPerson(player);
				break;
			}
		}
			
		//This is for displaying the error message if the click was not in a targetbox, needs more work
		if (playerChoice == null) {
			JOptionPane invalidTarget = new JOptionPane();
			//invalidTarget.showMessageDialog(frame, "Invalid Target");

			//frame.setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}



}
