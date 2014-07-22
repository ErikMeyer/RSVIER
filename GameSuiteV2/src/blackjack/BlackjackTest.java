package blackjack;


public class BlackjackTest {

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		System.out.println("There are " + blackjack.aryDeck.size() + " cards in the deck, they are:");
		for (int i = 0; i < blackjack.aryDeck.size(); i++) {
			System.out.println(blackjack.aryDeck.get(i).getName());
		}
		
		
	}

}
