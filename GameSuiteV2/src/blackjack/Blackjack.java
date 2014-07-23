package blackjack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author EM
 * This is the primary class for the blackjack package.
 * It generates a playing deck with 3 to 6 sets of 52 cards.
 * It creates blackjack player objects to keep track of hands.
 * 
 * Chronology
 * 
 * Player selects settings for new game
 * Player clicks start
 * Deck is generated according to settings
 * Players are generated according to settings
 * Cards are shuffled and dealt to players
 * Turn is pushed to first player
 * Player object executes actions and pushes turn to next player
 * So forth until bank, after bank's turn the round ends
 * Money is transferred
 * Hands are destroyed, new deck is generated, shuffled, and dealt, etc. 
 */
public class Blackjack {
	private ArrayList<Card> aryDeck = new ArrayList<Card>();
	private ArrayList<BjPlayer> aryPlayers = new ArrayList<BjPlayer>();
	private int whoTurn = -1;
		
	//Constructor
	public Blackjack() {
		aryDeck = genDeck(1); //For testing purposes 1 deck.
		aryPlayers.add(new BjPlayer("AI player one",9999,true));
		aryPlayers.add(new BjPlayer("Bank",9999,true));
		newRound();
		nextTurn(); //Starts game
	}
	
	public int getTurn() {
		return whoTurn;
	}
	
	public void setTurn(int turn) {
		this.whoTurn = turn;
	}
	
	public ArrayList<Card> getDeck() {
		return aryDeck;
	}
	
	public ArrayList<BjPlayer> getPlayers() {
		return aryPlayers;
	}
	
	public void addPlayer(String name, int bankRoll, boolean ai) {
		aryPlayers.add(new BjPlayer(name,bankRoll,ai));
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
		Collections.shuffle(tmpDeck);
		return tmpDeck;
	}
	
	public void nextTurn() {
		whoTurn++;
		BjPlayer player = aryPlayers.get(whoTurn);
		player.doTurn(this);
	}
	
	public void endTurn() {
		//End of turn cleanup
		whoTurn = -1;
		for (BjPlayer player: aryPlayers) {
			player.cleanUp();
		}
	}
	
	public Card drawCard() {
		Card card = aryDeck.get(0);
		aryDeck.remove(0);
		return card;
	}
	
	/**
	 * Shuffles the deck and deals the cards
	 */
	public void newRound() {
		for (int i = 0; i <= 3; i++) Collections.shuffle(aryDeck);
		for (BjPlayer player: aryPlayers) {
			ArrayList<Card> hand = new ArrayList<Card>();
			for (int j = 0; j < 2; j++) {
				hand.add(aryDeck.get(0));
				aryDeck.remove(0);
			}
			player.addHand(hand);
		}
	}
}
