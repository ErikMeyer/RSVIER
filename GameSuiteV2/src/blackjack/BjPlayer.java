package blackjack;

import java.util.ArrayList;

/**
 * @author EM
 *
 */
/**
 * @author EM
 *
 */
public class BjPlayer { //Possibly extends Player/Speler class from main menu package
	private String name;
	private boolean ai;
	private ArrayList<ArrayList<Card>> aryHands = new ArrayList<ArrayList<Card>>();
	private int bankRoll;
	
	//Constructor
	public BjPlayer(String name, int bankRoll, boolean ai) {
		this.name = name;
		this.ai = ai;
		this.bankRoll = bankRoll;
	}
	
	public int getBankroll() {
		return bankRoll;
	}
	
	public boolean getAI() {
		return ai;
	}
	
	public void setAI(boolean ai) {
		this.ai = ai;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<ArrayList<Card>> getHands() {
		return aryHands;
	}
	
	public ArrayList<Card> getHand(int hand) {
		return aryHands.get(hand);
	}
	
	public void addHand(ArrayList<Card> hand) {
		aryHands.add(hand);
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
		int total = 0;
		for (Card card: aryHands.get(hand)) {
			total = total + card.getValue();
		}
		return total;
	}
	
	/**
	 * This method executes all the actions in the player's turn.
	 * It contains the AI.
	 * 
	 * Actions:
	 * Check for blackjack
	 * Check for split-options
	 * Check if hand value is equal or over 17 (if so pass, else call)
	 * 
	 * @param master
	 */
	public void doTurn(Blackjack master) {
		
		getHand(0).clear();
		getHand(0).add(new Card("AH",11));
		getHand(0).add(new Card("AC",11));
		
		for (ArrayList<Card> hand: aryHands) {
			System.out.println("Turn "+master.getTurn());
			System.out.println("My name is "+name);
			System.out.println("Hand "+aryHands.indexOf(hand));
			System.out.println("My first card is "+getHand(0).get(0).getName());
			System.out.println("My second card is "+getHand(0).get(1).getName());
			System.out.println("Total value comes to "+getValue(0));
			
			//Check for blackjack
			/*
			if (getValue(hand.get(aryHands.indexOf(hand)).getValue())==21) {
				System.out.println("Blackjack baby!");
				//Actions
			}
			*/
			//Check for split
			if ((hand.get(0).getValue()==11) & (hand.get(1).getValue()==11)) {
				System.out.println("I have two aces, so I'm going to split");
				
				ArrayList<Card> newHand = new ArrayList<Card>();
				newHand.add(hand.get(1)); //Add the second card of the current hand to a new hand
				newHand.add(master.drawCard()); //Add a new second card to the new hand
				hand.remove(1); //Remove the second card from the current hand
				hand.add(master.drawCard()); //Add a new second card to the current hand
				//addHand(newHand); //Add the new hand  <== Problematic!
			}
		}
		
		
		
		
		if (name.equals("Bank")) {
			master.endTurn();
		}
		else {
			master.nextTurn();
		}
		 
	}
	
	public void cleanUp() {
		aryHands = new ArrayList<ArrayList<Card>>(); //Removes any hands (and cards in those hands)
	}
}
