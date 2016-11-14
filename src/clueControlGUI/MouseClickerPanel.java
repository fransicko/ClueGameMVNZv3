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

public class MouseClickerPanel extends JPanel implements MouseListener{
	private Board board = Board.getInstance();
	private ArrayList<targetBox> targets;
	private int roll;
	int mouseX;
	int mouseY;
	targetBox playerChoice = null;

	// variable used for singleton pattern
	private static MouseClickerPanel theInstance = new MouseClickerPanel();

	// ctor is private to ensure only one can be created
	private MouseClickerPanel() {
		addMouseListener(this);
	}

	// this method returns the only Board
	public static MouseClickerPanel getInstance() {
		return theInstance;
	}

	public MouseClickerPanel(int roll) {
		this.roll = roll;
		setPreferredSize(new Dimension(20, 20));


		//board.calcTargets(board.person.getColumn(), board.person.getRow(), roll);
		createBoxes(board.getTargets());
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
	
	public void reset() {
		roll = 0;
		targets = new ArrayList<>();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		for (targetBox i: targets) {
			if(i.containsClick(e.getX(), e.getY())) {
				playerChoice = new targetBox(i.getX(), i.getY());
				
				break;
			}
		}
			
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
