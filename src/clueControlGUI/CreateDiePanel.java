package clueControlGUI;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CreateDiePanel extends JPanel {
	private JTextArea die;
	private int roll;
	
	public CreateDiePanel() {
		//JPanel panel = new JPanel();
		setLayout(new GridLayout(1,2));
		//JLabel dieLabel = new JLabel("Roll");
		//name = new JTextField(5);
		//panel.add(name);
		setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		die = new JTextArea(1, 10);
		updateDie();

		add(die);
	}
	
	private void updateDie() {
		die.setText(String.valueOf(roll));
	}
	
	public void setRoll() {
		Random ran = new Random();
		this.roll = Math.abs(ran.nextInt(6)) + 1;
		updateDie();
	}
	
	
}
