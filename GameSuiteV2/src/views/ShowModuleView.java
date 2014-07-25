package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import memory.iGameContract;
import models.Model;

public class ShowModuleView extends JPanel {

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ShowModuleView.this.changeStateTo(new MainMenuView(ShowModuleView.this.model));
		}
	}

	private class ModuleObserver implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			System.out.println("A module reported a win!");
			ShowModuleView.this.model.updatePlayerWins();
		}
	}

	private Model model;

	public <E extends iGameContract> ShowModuleView(Model model, E e) {

		this.model = model;
		E module = e;

		// tell this module we want to observe it's model.
		((iGameContract) e).addGameModelObserver(new ModuleObserver());

		// add module to JFrame, assuming it's a JPanel
		this.add((JPanel) module);

		JButton mainMenuButton = new JButton("Back to Main Menu");
		mainMenuButton.addActionListener(new ButtonHandler());
		this.add(mainMenuButton);
	}

	public <E> void changeStateTo(E jpanel) {
		this.model.changeStateTo((JPanel) jpanel);
	}

}
