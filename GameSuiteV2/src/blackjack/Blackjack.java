package blackjack;

import java.util.ArrayList;

/**
 * @author EM
 * This is the primary class for the blackjack package.
 * It generates a playing deck with 3 to 6 sets of 52 cards.
 * It creates blackjack player objects to keep track of hands.
 */
public class Blackjack {
	public ArrayList<Card> aryDeck; //Make private after testing.
	
	//Constructor
	public Blackjack() {
		aryDeck = genDeck(1); //For testing purposes 1 deck.
	}
	
	//First generates one normal deck (52), 
	//then adds more decks if required, 
	//then shuffles the full deck.
	//H = Hearts, S = Spades, C = Clubs, D = Diamonds
	public ArrayList<Card> genDeck(int decks) {
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		
		String suits[] = {"H","S","C","D"};
		String names[] = {"A","K","Q","J","10","9","8","7","6","5","4","3","2"};
		int values [] = {11,10,10,10,10,9,8,7,6,5,4,3,2};
		
		for (int deck = 0; deck < decks; deck++) {
			for (int suit = 0; suit < suits.length; suit++) {
				for (int name = 0; name < names.length; name++) {
					tmpDeck.add(new Card(""+names[name]+suits[suit],values[name]));
				}
			}
		}
		return tmpDeck;
	}
}
