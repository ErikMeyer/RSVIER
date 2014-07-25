package memory.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import memory.models.Card;

public class CardPanel extends JPanel {

	private static final long serialVersionUID = 7347714630525947748L;
	private ArrayList<Card> cards;

	public CardPanel() {
		this.setLayout(new FlowLayout());

		// Populate table
		this.cards = new ArrayList<Card>();
		for (int i = 0; i < 5; i++) {
			this.cards.add(new Card(i));
			this.cards.add(new Card(i));
		}

		// Shuffle table
		this.shuffleCards();

		// Show cards on the table
		for (Card card : this.cards) {
			this.add(card);
			card.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

				}

			});
		}

	}

	private void shuffleCards() {

	}

}
