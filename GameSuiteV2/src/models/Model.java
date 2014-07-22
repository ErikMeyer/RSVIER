package models;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JPanel;

public class Model extends Observable {

	private Players players;
	private JPanel activeViewState;
	private Player activePlayer;

	public Model() {
		this.players = new Players();
	}

	public void changeStateTo(JPanel viewstate) {
		this.activeViewState = viewstate;
		this.setChanged();
		this.notifyObservers();
	}

	public Player getActivePlayer() {
		return this.players.getActivePlayer();
	}

	public JPanel getActiveViewState() {
		return this.activeViewState;
	}

	public ArrayList<Player> getPlayerList() {
		return this.players.getArrayList();
	}

	public void updatePlayerWins() {
		this.players.incrementWins();
	}

}