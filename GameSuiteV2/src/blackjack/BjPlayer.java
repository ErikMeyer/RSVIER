package blackjack;

import java.util.ArrayList;

public class BjPlayer { //Possibly extends Player/Speler class from main menu package
	private String name;
	private ArrayList<ArrayList<Card>> aryHands;
	
	//Constructor
	public BjPlayer(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<ArrayList<Card>> getHands() {
		return aryHands;
	}
	
	public ArrayList<Card> getHand(int hand) {
		return aryHands.get(hand);
	}
	
	/**
	 * This method calculates the value of a given hand.
	 * However, if one the cards is an ace (11) it can be
	 * demoted to a 1 to prevent going bust.
	 * Cycle through the cards in a hand, check if the card
	 * is an ace and if the total is over 21, and if so change
	 * the ace value to 1 and update the total.
	 * 
	 * Second thought: perhaps we should not change the value
	 * in this get-method.
	 * @param hand
	 * @return
	 */
	public int getValue(int hand) {
		ArrayList<Card> aryHand = aryHands.get(hand);
		int total = 0;
		for (Card card: aryHand) {
			if (((total + card.getValue()) > 21) & (card.getValue() == 11)) {
				card.setValue(1);
			}
			total = total + card.getValue();
		}		
		return total;
	}
}
