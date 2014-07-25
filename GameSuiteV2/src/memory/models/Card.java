package memory.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Card extends JButton {

	private class CardButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Card.this.clicked();
		}
	}

	private static final long serialVersionUID = -3496267835833227911L;

	private boolean upsideDown = true;
	private int value = 0;

	public Card(int value) {
		this.value = value;
		this.addActionListener(new CardButtonHandler());
		this.flip();
		this.flip();
	}

	private void clicked() {
		System.out.println("Flipping card with value: " + this.getValue());
		this.flip();
	}

	public boolean compareTo(Card card) {
		if (this.getValue() == card.getValue()) {
			return true;
		}
		return false;
	}

	private void flip() {
		if (this.upsideDown) {
			this.setText(String.valueOf(this.getValue()));
			this.upsideDown = false;
		} else {
			this.setText("...");
			this.upsideDown = true;
		}
	}

	public int getValue() {
		return this.value;
	}

}
