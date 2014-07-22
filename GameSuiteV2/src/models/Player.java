package models;

public class Player {

	private String name;
	private int memoryWins;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public int getWins() {
		return this.memoryWins;
	}

	public void incrementWins() {
		// TODO: Get Games from an enum or something
		// ScoreCard type maybe?
		this.memoryWins++;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}