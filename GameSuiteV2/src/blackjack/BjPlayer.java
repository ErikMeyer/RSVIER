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
	
	public int getValue(int hand) {
		int valueOne = aryHands.get(hand).get(0).getValue(); 
		int valueTwo = aryHands.get(hand).get(1).getValue();
		int total = valueOne + valueTwo;
		for (int i = 0; i < 2; i++) { //Bezig
			if (valueOne==11 & total > 21) {
				valueOne = 1;
				total = valueOne + valueTwo;
			}
		}
		return 0; //Change
	}
}
