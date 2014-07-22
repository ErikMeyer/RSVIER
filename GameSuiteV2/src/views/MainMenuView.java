package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import models.Model;

/**
 * View
 */
public class MainMenuView extends JPanel {

	private class listModulesButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			MainMenuView.this.changeStateTo(new ListModulesView(MainMenuView.this.model));
		}
	}

	private class quitButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class userButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			MainMenuView.this.changeStateTo(new ListUsersView(MainMenuView.this.model));
		}
	}

	private Model model;

	public MainMenuView(Model model) {

		this.model = model;

		JButton listModulesButton = new JButton("Play a Game!");
		listModulesButton.addActionListener(new listModulesButtonHandler());
		this.add(listModulesButton);

		JButton userButton = new JButton("User Management");
		userButton.addActionListener(new userButtonHandler());
		this.add(userButton);

		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new quitButtonHandler());
		this.add(quitButton);

	}

	public <E> void changeStateTo(E view) {
		this.model.changeStateTo((JPanel) view);
	}

}