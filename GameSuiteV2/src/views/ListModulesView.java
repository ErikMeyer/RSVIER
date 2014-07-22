package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import memory.Memory;
import models.Model;

public class ListModulesView extends JPanel {

	private class ButtonHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ListModulesView.this.changeStateTo(new ShowModuleView(ListModulesView.this.model,
					new Memory()));
		}
	}

	private class ButtonHandler2 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(ListModulesView.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				// TODO: check of het wel een jar file is, of het een manifest heeft, etc.
				try {

					// Use the main class as specified in the jar's manifest
					JarFile jarfile = new JarFile(file);
					Manifest manifest = jarfile.getManifest();
					Attributes attrs = manifest.getMainAttributes();
					String clazzName = attrs.getValue("Main-Class");

					ClassLoader clazzloader = URLClassLoader.newInstance(new URL[] {
						file.toURI().toURL()
					}, this.getClass().getClassLoader());

					Class module = Class.forName(clazzName, true, clazzloader);

					ListModulesView.this.changeStateTo(new ShowModuleView(
							ListModulesView.this.model, module.newInstance()));

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
	}

	private Model model;

	public ListModulesView(Model model) {
		this.model = model;

		this.add(new JLabel("Installed games:"));
		JButton gameButtonMemory = new JButton("Memory");
		gameButtonMemory.addActionListener(new ButtonHandler());
		this.add(gameButtonMemory);

		JButton gameButtonLoad = new JButton("Load Game from Disk");
		gameButtonLoad.addActionListener(new ButtonHandler2());
		this.add(gameButtonLoad);
	}

	public <E> void changeStateTo(E jpanel) {
		this.model.changeStateTo((JPanel) jpanel);
	}
}
