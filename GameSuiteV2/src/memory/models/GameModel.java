package memory.models;

import java.util.Observable;

public class GameModel extends Observable {

	private int clickcount;

	public GameModel() {

	}

	public void clicked() {
		if (this.clickcount == 0) {
			// flip()
		} else {
			// check match
			// reset
		}
	}

}
