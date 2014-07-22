package controllers;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Model;
import views.MainMenuView;

public class Controller {

	private class ModelObserver implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			Controller.this.changePanel(((Model) o).getActiveViewState());
		}
	}

	private Model model;
	private JFrame frame;
	private JPanel panel;

	public Controller() {

		this.model = new Model();
		this.model.addObserver(new ModelObserver());

		this.frame = new JFrame();
		this.frame.setSize(720, 480);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panel = new MainMenuView(this.model);
		this.changePanel(this.panel);

		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);

	}

	public <E> void changePanel(E jpanel) {
		this.panel = (JPanel) jpanel;
		this.frame.getContentPane().removeAll();
		this.frame.add(this.panel);
		this.frame.revalidate();
		this.frame.repaint();
	}
}