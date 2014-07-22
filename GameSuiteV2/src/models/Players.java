package models;

import java.util.ArrayList;

public class Players {

	private ArrayList<Player> players;
	private Player activePlayer;

	public Players() {
		// TODO: load a bunch of players (serialized I think) from disk
		this.players = new ArrayList<Player>();
		this.players.add(new Player("Mark"));
		this.players.add(new Player("Gerben"));
		this.players.add(new Player("Rogier"));
		this.players.add(new Player("Erik"));
		// ...

		this.activePlayer = this.players.get(0);

	}

	public void changeActivePlayerTo(Player player) {
		this.activePlayer = player;
	}

	public Player getActivePlayer() {
		return this.activePlayer;
	}

	public ArrayList<Player> getArrayList() {
		return this.players;
	}

	public void incrementWins() {
		this.activePlayer.incrementWins();
	}

}
