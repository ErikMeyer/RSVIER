package vieropeenrij;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VierOpEenRij extends JPanel {

	private class xKnop extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6348509778437474448L;

		public xKnop() {
			this.setText("...");
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					xKnop knop = (xKnop) e.getSource();
					knop.setText("O");
					knop.setEnabled(false);

					VierOpEenRij.this.clickcounter++;

					if (VierOpEenRij.this.clickcounter == 4) {
						JOptionPane.showMessageDialog(xKnop.this, "4 op een rij!");
						VierOpEenRij.this.resetGamePanel();
					}
				}
			});
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 546273507812627781L;

	private xKnop knop1, knop2, knop3, knop4;
	private int clickcounter;

	public VierOpEenRij() {
		this.setSize(new Dimension(1420, 240));
		this.setBackground(Color.BLUE);

		JLabel titelLabel = new JLabel("Vier Op Een Rij!");
		titelLabel.setFont(new Font("Arial", 24, 24));

		JLabel subLabel = new JLabel("Plaats 4 X'en naast elkaar.");
		subLabel.setFont(new Font("Arial", 12, 12));

		JPanel instructiePanel = new JPanel(new GridLayout(2, 0));
		instructiePanel.add(titelLabel);
		instructiePanel.add(subLabel);

		JPanel gamePanel = new JPanel();

		this.knop1 = new xKnop();
		this.knop2 = new xKnop();
		this.knop3 = new xKnop();
		this.knop4 = new xKnop();

		gamePanel.add(this.knop1);
		gamePanel.add(this.knop2);
		gamePanel.add(this.knop3);
		gamePanel.add(this.knop4);

		this.add(instructiePanel);
		this.add(gamePanel);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);

		VierOpEenRij game = new VierOpEenRij();
		frame.add(game);
	}

	public void resetGamePanel() {
		this.knop1.setText("...");
		this.knop2.setText("...");
		this.knop3.setText("...");
		this.knop4.setText("...");
		this.knop1.setEnabled(true);
		this.knop2.setEnabled(true);
		this.knop3.setEnabled(true);
		this.knop4.setEnabled(true);
		this.clickcounter = 0;
	}

}
