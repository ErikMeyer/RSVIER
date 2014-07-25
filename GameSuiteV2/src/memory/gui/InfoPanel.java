package memory.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = 5349571156037303774L;

	public InfoPanel() {

		this.setLayout(new GridLayout(2, 0));
		this.setBackground(Color.CYAN);

		JLabel titelLabel = new JLabel("Memory!");
		titelLabel.setFont(new Font("Arial", 24, 24));
		JLabel subLabel = new JLabel("Vind de paren.");
		subLabel.setFont(new Font("Arial", 12, 12));

		this.add(titelLabel);
		this.add(subLabel);
	}
}
