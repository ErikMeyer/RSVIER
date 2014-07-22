package memory;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import memory.gui.CardPanel;
import memory.gui.InfoPanel;
import memory.models.GameModel;

public class GamePanel extends JPanel implements iGameContract {

	private static final long serialVersionUID = -7076956782290128963L;

	private GameModel gameModel;

	public GamePanel() {

		this.add(new InfoPanel());
		this.add(new CardPanel());

		this.setSize(new Dimension(360, 480));
		this.setBackground(Color.YELLOW);

		this.initModels();
	}

	/*
	 * Wrap the GamePanel in a JFrame so that it can be run independently.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(720, 480);
		frame.setVisible(true);
		GamePanel game = new GamePanel();
		frame.getContentPane().add(game);
	}

	@Override
	public void addGameModelObserver(Observer obs) {
		this.gameModel.addObserver(obs);
	}

	/*
	 * Initialize the gameModel that will help us keep track of our game state.
	 * e.g. Whose turn it is, keeping score, etc.
	 */
	private void initModels() {
		this.gameModel = new GameModel();
	}

}
