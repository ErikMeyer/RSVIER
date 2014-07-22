package memory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Memory extends JPanel {

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Memory.this.clickcounter++;
			Card card = (Card) e.getSource();
			card.setEnabled(false);
			card.setText(card.getValue());
			if (((Card) e.getSource()).value.equals("X")) {
				Memory.this.tempcounter++;
			}
			if (Memory.this.clickcounter == 2) {
				if (Memory.this.tempcounter == 2) {
					JOptionPane.showMessageDialog(Memory.this, "Gewonnen!", "blabla",
							JOptionPane.WARNING_MESSAGE);
					// Memory.this.model.updatePlayerWins();
				} else {
					JOptionPane.showMessageDialog(Memory.this, "Verloren!", "blabla",
							JOptionPane.WARNING_MESSAGE);
				}
				Memory.this.resetGame();
			}
		}
	}

	private class Card extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1232041766522234980L;
		public String value;

		public Card(String value) {
			this.value = value;
			this.setText("...");
		}

		public String getValue() {
			return this.value;
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5165955045093485207L;

	private Card kaart1, kaart2, kaart3;
	private int tempcounter, clickcounter;

	public Memory() {

		this.setSize(new Dimension(360, 480));
		this.setBackground(Color.YELLOW);

		JLabel titelLabel = new JLabel("Memory!");
		titelLabel.setFont(new Font("Arial", 24, 24));

		JLabel subLabel = new JLabel("Vind het paar X'en!");
		subLabel.setFont(new Font("Arial", 12, 12));

		JPanel instructiePanel = new JPanel(new GridLayout(2, 0));
		instructiePanel.add(titelLabel);
		instructiePanel.add(subLabel);
		this.add(instructiePanel);

		this.kaart1 = new Card("X");
		this.kaart1.addActionListener(new ButtonHandler());
		this.add(this.kaart1);

		this.kaart2 = new Card("O");
		this.kaart2.addActionListener(new ButtonHandler());
		this.add(this.kaart2);

		this.kaart3 = new Card("X");
		this.kaart3.addActionListener(new ButtonHandler());
		this.add(this.kaart3);
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);

		Memory game = new Memory();
		frame.add(game);

	}

	public void resetGame() {
		this.tempcounter = 0;
		this.clickcounter = 0;
		this.kaart1.setText("...");
		this.kaart2.setText("...");
		this.kaart3.setText("...");
		this.kaart1.setEnabled(true);
		this.kaart2.setEnabled(true);
		this.kaart3.setEnabled(true);
	}

}
