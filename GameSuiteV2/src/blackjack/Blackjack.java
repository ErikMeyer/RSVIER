package blackjack;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import memory.Memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;


public class Blackjack extends JPanel {
	BlackjackModel bjm = new BlackjackModel();
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Blackjack() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblBlackjackHasBeen = new JLabel("Blackjack has been loaded.");
		lblBlackjackHasBeen.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblBlackjackHasBeen, BorderLayout.CENTER);

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);

		Blackjack game = new Blackjack();
		frame.add(game);
	}
	
	private class BlackjackModel {
		private ArrayList<Card> aryDeck = new ArrayList<Card>();
		private ArrayList<Player> aryPlayers = new ArrayList<Player>();
		private int whoTurn = -1;
			
		//Constructor
		public BlackjackModel() {
			aryDeck = genDeck(1); //For testing purposes 1 deck.
			addPlayer("AI player one",9999,true);
			addPlayer("Bank",9999,true);
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
		
		public ArrayList<Player> getPlayers() {
			return aryPlayers;
		}
		
		public void addPlayer(String name, int bankRoll, boolean ai) {
			aryPlayers.add(new Player(name,bankRoll,ai));
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
			Player player = aryPlayers.get(whoTurn);
			player.doTurn(this,0);
		}
		
		public void endTurn() {
			//End of turn cleanup
			whoTurn = -1;
			for (Player player: aryPlayers) {
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
			for (Player player: aryPlayers) {
				ArrayList<Card> hand = new ArrayList<Card>();
				for (int j = 0; j < 2; j++) {
					hand.add(aryDeck.get(0));
					aryDeck.remove(0);
				}
				player.addHand(hand);
			}
		}
	}
	
	private class Player { //Possibly extends Player/Speler class from main menu package
		private String name;
		private boolean ai;
		private ArrayList<ArrayList<Card>> aryHands = new ArrayList<ArrayList<Card>>();
		private int bankRoll;
		private boolean once = true;
		
		//Constructor
		public Player(String name, int bankRoll, boolean ai) {
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
		
		public int getValue(ArrayList<Card> hand) {
			int total = 0;
			for (Card card: hand) {
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
		 * Check if hand value is equal or over 17 (if so stand, else hit)
		 * 
		 * @param master
		 */
		public void doTurn(BlackjackModel master, int handIndex) {
			boolean didSplit = false;
			ArrayList<Card> hand = getHand(handIndex);
			
			if (once==true) {
			getHand(0).clear();
			getHand(0).add(new Card("AH",11));
			getHand(0).add(new Card("AC",11));
			this.once=false;
			}
			
			System.out.println("Turn "+master.getTurn());
			System.out.println("My name is "+name);
			for (ArrayList<Card> tmpHand: getHands()) {
				System.out.println("Hand "+getHands().indexOf(tmpHand));
				for (Card tmpCard: tmpHand) {
					System.out.println("Card "+tmpCard.getName());
				}
			}
			
			//Check for split
			if (	(hand.size()==2)&
						(hand.get(0).getValue()==11)&
						(hand.get(1).getValue()==11)) {
				while (	(hand.size()==2)&
						(hand.get(0).getValue()==11)&
						(hand.get(1).getValue()==11)) {
					
				System.out.println("Split baby!");
				//Actions
				ArrayList<Card> newHand = new ArrayList<Card>();
				newHand.add(hand.get(1)); 		//Add the second card of the current hand to a new hand
				newHand.add(master.drawCard()); //Add a new second card to the new hand
				hand.remove(1); 				//Remove the second card from the current hand
				hand.add(master.drawCard()); 	//Add a new second card to the current hand
				addHand(newHand);				//Add the new hand to aryHands
				didSplit = true;				//Remember that we split
				for (ArrayList<Card> tmpHand: getHands()) {
					System.out.println("Hand "+getHands().indexOf(tmpHand)+", value = "+getValue(tmpHand));
					for (Card tmpCard: tmpHand) {
						System.out.println("Card "+tmpCard.getName());
					}
				}	
			}}
			
			//Check for blackjack
			else if (getValue(hand)==21) {
				System.out.println("Blackjack baby!");
				//Actions
				//Requires no action until evaluation?
			}
			
			//Check for hit
			else if (getValue(hand)<17) {
				while (getValue(hand)<17) {
					System.out.println("Hand ("+getHands().indexOf(hand)+") value ("+getValue(hand)+") is less than 17: Hit.");
					//Actions
					hand.add(master.drawCard());
					System.out.println("I got a "+hand.get(hand.size()-1).getName()+" ("+hand.get(hand.size()-1).getValue()+"). Hand value is now "+getValue(hand)+".");
					if (getValue(hand)>21) {
						System.out.println("I'm sorry, you're bust.");
						//Actions
					}
				}
			}
			
			//Check for stand
			else if (	(getValue(hand)>16)&
						(getValue(hand)<21)) {
				System.out.println("Hand value ("+getValue(hand)+") is between 16 and 21: Stand.");
				//Actions
				//Requires no action until evaluation?
			} 
				
			/**
			 * If we are not in the last hand for this player,
			 * that means there's been a split, and we should redo this hand.
			 * 
			 * Wrap-up is not working correctly.
			 
			if (didSplit==true) {
				didSplit = false;
				doTurn(master,handIndex);
			}
			else if (handIndex<getHands().size()-1) {
			while (handIndex<getHands().size()-1) {
				handIndex++;
				doTurn(master,handIndex);
			}}
			//If this was the last hand, and we are the bank, end the turn.
			if (name.equals("Bank")) {
				master.endTurn();
			}
			//If this was the last hand, and we are not the bank, proceed to the next player.
			else {
				master.nextTurn();
			}
			
			*/
			 
		}
		
		public void cleanUp() {
			aryHands = new ArrayList<ArrayList<Card>>(); //Removes any hands (and cards in those hands)
		}
	}
	
	private class Card {
		private String name;
		private int value;
		
		
		//Constructor
		public Card(String name, int value) {
			this.name = name;
			this.value = value;
		}
		
		public String getName() {
			return name;
		}
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int newValue) {
			this.value = newValue;
		}
	}

}
