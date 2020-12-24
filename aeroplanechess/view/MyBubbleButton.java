package xyz.chengzi.aeroplanechess.view;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.*;
public class MyBubbleButton extends JButton implements Serializable {
	public  static final long serialVersionUID = 5L;
	public MyBubbleButton() {
		super();
		init();
	}
	public MyBubbleButton(String string) {
		super(string);
		init();
		
	}
	public MyBubbleButton(ImageIcon icon) {
		super(icon);
		init();
	}
	private void init() {
		this.setFont(new Font("ºÚÌå", Font.PLAIN, 26));
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setIcon(Pics.BUTTON1.getPic());
		this.setRolloverIcon(Pics.BUTTON2.getPic());
		this.setPressedIcon(Pics.BUTTON3.getPic());
		this.setBounds(0, 0, 165, 74);
	}
}
