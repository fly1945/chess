package xyz.chengzi.aeroplanechess.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Font;
import java.io.Serializable;

public class TextLog extends JPanel implements Serializable {
	public  static final long serialVersionUID = 16L;
	/**
	 * Create the panel.
	 */
	private JTextArea textPane = new JTextArea();
	public TextLog() {
		setLayout(null);
		setBounds(800,450,400,300);
		textPane.setEditable(false);
		textPane.setFont(new Font("ºÚÌå", Font.PLAIN, 26));
		textPane.setBounds(0, 0, 400, 300);
		add(textPane);

	}
	public void setText(String input) {
		textPane.setText(input);
	}
}
