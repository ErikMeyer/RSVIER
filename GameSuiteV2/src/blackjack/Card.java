package blackjack;

/**
 * @author Erik Meyer
 *
 */
public class Card {
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
}
