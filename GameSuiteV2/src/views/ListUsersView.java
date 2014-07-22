package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import models.Model;
import models.Player;

public class ListUsersView extends JPanel {

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ListUsersView.this.doAction();
		}
	}

	private Model model;

	public ListUsersView(Model model) {
		this.model = model;

		this.add(new JLabel("Logged in as: " + this.model.getActivePlayer().getName()));

		JTextArea textarea = new JTextArea();
		String tmp = "Memory wins: \r\n-----------------------------\r\n";
		for (Player player : this.model.getPlayerList()) {
			tmp += player.getName() + " " + player.getWins() + "\r\n";
		}

		textarea.setText(tmp);
		this.add(textarea);

		JButton mainMenuButton = new JButton("Back to Main Menu");
		mainMenuButton.addActionListener(new ButtonHandler());
		this.add(mainMenuButton);

	}

	public void doAction() {
		this.model.changeStateTo(new MainMenuView(this.model));
	}
}
