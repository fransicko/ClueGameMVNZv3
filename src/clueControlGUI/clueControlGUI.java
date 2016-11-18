package clueControlGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.Card;
import clueGame.ComputerPlayer;
import clueGame.Solution;


public class clueControlGUI extends JPanel{
	private Board board = Board.getInstance();
	private JTextField name;
	private CreateWhoseTurn turn;
	private CreateDiePanel die;
	private CreateGuessPanel guess;
	MouseClickerPanel accusationPanel = new MouseClickerPanel();
	JComboBox<String> personDropDownAccusation = new JComboBox<String>();
	JComboBox<String> roomDropDownAccusation = new JComboBox<String>();
	JComboBox<String> weaponDropDownAccusation = new JComboBox<String>();
	private static int next = 0;
	private CreateGuessResultPanel response;



	public clueControlGUI() {
		turn = new CreateWhoseTurn();
		die = new CreateDiePanel();
		guess = CreateGuessPanel.getInstance();
		response = CreateGuessResultPanel.getInstance();

		setLayout(new GridLayout(2,1));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.add(turn);
		//add(panel);
		panel.add(createNextPlayerButtonPanel());
		//add(panel);
		panel.add(createMakeAccusationButtonPanel());
		add(panel);
		JPanel panel2= new JPanel();
		panel2.setLayout(new GridLayout(1, 3));
		panel2.add(die);
		add(panel2);
		panel2.add(guess);
		add(panel2);
		panel2.add(response);
		add(panel2);

	}

	private JPanel createNextPlayerButtonPanel() {
		JButton nextPlayer = new JButton("Next player");
		JPanel panel = new JPanel(new BorderLayout());
		nextPlayer.addActionListener(new NextPlayerListener());

		panel.add(nextPlayer);
		return panel;
	}

	class NextPlayerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (board.nextTurn) {
				turn.setWhosTurn(board.players.get(next).getName());
				die.setRoll();
				if (next == 0) {
					board.setTurn(false);
					board.person.makeMove(die.getRoll());
				}
				else {
					if (board.comp.get(next-1).hasAccusation == true) {
						board.comp.get(next-1).makeAccusation(); //Computer making accusation, it should be right if their last suggestion had no disproval
					}
					else {
					 board.comp.get(next-1).compMove(die.getRoll());
					}
				}

				++next;
				if (next == 6) next = 0;
			}

			else {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "You must chose a location", "Play your turn", JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}

	class MakeAccusationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (next == 1) {
				personDropDownAccusation = new JComboBox<String>();
				roomDropDownAccusation = new JComboBox<String>();
				weaponDropDownAccusation = new JComboBox<String>();
				accusationPanel = new MouseClickerPanel();
				//Display the accusation panel
				accusationPanel.setTitle("Make an Accusation");
				accusationPanel.setSize(350, 225);
				accusationPanel.setLayout(new GridLayout(1,2));

				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(4, 1));


				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout(4, 1));

				//Add Your Room, Weapons, People messageDialog
				JLabel yourRoomGuess = new JLabel("Room");
				JLabel personGuess = new JLabel("Person");
				JLabel weaponGuess = new JLabel("Weapon");
				panel.add(yourRoomGuess);
				panel.add(personGuess);
				panel.add(weaponGuess);

				//Add room drop down

				for (Card j: board.roomCards) {
					roomDropDownAccusation.addItem(j.getName());
				}

				panel2.add(roomDropDownAccusation);

				//Add person drop down

				for (Card j: board.personCards) {
					if (j.getName() != board.person.getName()) {
						personDropDownAccusation.addItem(j.getName());
					}
				}


				panel2.add(personDropDownAccusation);

				//Add weapon drop down

				for (Card j: board.weaponCards) {
					weaponDropDownAccusation.addItem(j.getName());
				}

				panel2.add(weaponDropDownAccusation);

				//Need to Add submit button to panel1
				JButton submitAccusationPanel = new JButton("Submit");
				submitAccusationPanel.addActionListener(new SubmitAccusationListener());
				panel.add(submitAccusationPanel);

				//Cancel button for panel2
				JButton cancelAccusationPanel = new JButton("Cancel");
				cancelAccusationPanel.addActionListener(new CancelAccusationListener());
				panel2.add(cancelAccusationPanel);


				accusationPanel.add(panel);
				accusationPanel.add(panel2);
				accusationPanel.setVisible(true);
			}
			
			else {
				JFrame frame = new JFrame();
				JOptionPane wrong = new JOptionPane();
				wrong.showMessageDialog(frame, "It is not your turn!");
			}

		}

	}

	private JPanel createMakeAccusationButtonPanel() {
		JButton accusation = new JButton("Make an Accusation");
		JPanel panel = new JPanel(new BorderLayout());
		accusation.addActionListener(new MakeAccusationListener());
		panel.add(accusation);
		return panel;
	}

/*	private JPanel createGuessResultPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		name = new JTextField(5);
		panel.add(name);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return panel;
	}*/

	class CancelAccusationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			accusationPanel.dispose();
		}
	}

	class SubmitAccusationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
				String personAccuse = personDropDownAccusation.getSelectedItem().toString();
				String weaponAccuse = weaponDropDownAccusation.getSelectedItem().toString();
				String roomAccuse = roomDropDownAccusation.getSelectedItem().toString();
				
				Solution Accusation = new Solution(personAccuse, roomAccuse, weaponAccuse);
				
				if (board.checkAccusation(Accusation) == true) {
					//Player wins game
					
					JFrame frame = new JFrame();
					JOptionPane wrong = new JOptionPane();
					wrong.showMessageDialog(frame, "Congradulations! You Won!");
					System.exit(0);
				}
				else {
					JFrame frame = new JFrame();
					JOptionPane wrong = new JOptionPane();
					wrong.showMessageDialog(frame, "Incorrect!");
					
				}
				
				board.setTurn(true);
				board.person.finishMove();
				
				accusationPanel.dispose();

		}
	}



}
