package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

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
			System.out.println("TEST");
		}
	}

	private Model model;

	public <E> ShowModuleView(Model model, E e) {
		this.model = model;

		E module = e;

		this.add((JPanel) module);

		JButton mainMenuButton = new JButton("Back to Main Menu");
		mainMenuButton.addActionListener(new ButtonHandler());
		this.add(mainMenuButton);
	}

	public <E> void changeStateTo(E jpanel) {
		this.model.changeStateTo((JPanel) jpanel);
	}

}
