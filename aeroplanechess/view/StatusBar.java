package xyz.chengzi.aeroplanechess.view;

import javax.swing.JPanel;

import xyz.chengzi.aeroplanechess.controller.GameController;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.io.Serializable;
import java.util.*;
public class StatusBar extends JPanel implements Serializable {
	public  static final long serialVersionUID = 15L;
	private GameController controller;
	private JLabel arrow;
	private JLabel statusIcon0 = new JLabel(Pics.SWORDS.getPic());
	private JLabel statusIcon1 = new JLabel(Pics.SWORDS.getPic());
	private JLabel statusIcon2 = new JLabel(Pics.SWORDS.getPic());
	private JLabel statusIcon3 = new JLabel(Pics.SWORDS.getPic());
	private int a,b;
	private List<JLabel> statusIcons = new ArrayList<JLabel>();
	private List<Icon> icons = new ArrayList<Icon>();
	/**
	 * Create the panel.
	 */
	public StatusBar(GameController controller) {

		this.setOpaque(false);
		statusIcon0.setBounds(100, 10, 70, 70);
		statusIcon1.setBounds(100, 10, 70, 70);
		statusIcon2.setBounds(100, 10, 70, 70);
		statusIcon3.setBounds(100, 10, 70, 70);
		statusIcons.add(statusIcon0);
		statusIcons.add(statusIcon1);
		statusIcons.add(statusIcon2);
		statusIcons.add(statusIcon3);
		statusIcon0.setVisible(false);
		statusIcon1.setVisible(false);
		statusIcon2.setVisible(false);
		statusIcon3.setVisible(false);
		this.controller = controller;
		setLayout(null);
		setBounds(760, 10, 230, 400);
		
		arrow = new JLabel(Pics.ARROW.getPic());
		arrow.setBounds(3, 40, 25, 25);
		add(arrow);
		
		JPanel status_0 = new JPanel();
		status_0.setBounds(30, 0, 200, 100);
		status_0.setOpaque(false);
		add(status_0);
		status_0.setLayout(null);
		
		JLabel Icon0 = new JLabel(Pics.YELLOWPILOT.getPic());
		Icon0.setBounds(10, 10, 80, 80);
		status_0.add(Icon0);
		
		
		JPanel status_1 = new JPanel();
		status_1.setBounds(30, 100, 200, 100);
		status_1.setOpaque(false);
		add(status_1);
		status_1.setLayout(null);
		
		JLabel Icon1 = new JLabel(Pics.BLUEPILOT.getPic());
		Icon1.setBounds(10, 10, 80, 80);
		status_1.add(Icon1);


		JPanel status_2 = new JPanel();
		status_2.setBounds(30, 200, 200, 100);
		status_2.setOpaque(false);
		add(status_2);
		status_2.setLayout(null);
		
		JLabel Icon2 = new JLabel(Pics.REDPILOT.getPic());
		Icon2.setBounds(10, 10, 80, 80);
		status_2.add(Icon2);
		

		JPanel status_3 = new JPanel();
		status_3.setBounds(30, 300, 200, 100);
		status_3.setOpaque(false);
		add(status_3);
		status_3.setLayout(null);
		
		JLabel Icon3 = new JLabel(Pics.GREENPILOT.getPic());
		Icon3.setBounds(10, 10, 80, 80);
		status_3.add(Icon3);
		
		status_0.add(statusIcon0);
		status_1.add(statusIcon1);
		status_2.add(statusIcon2);
		status_3.add(statusIcon3);
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 230, 400);
		background.setIcon(Pics.STATUSBACKGROUND.getPic());
		add(background);
	}
	public void moveArrow() {
		arrow.setBounds(3,30+controller.getCurrentPlayer()*100,35,35);
	}
	public void moveArrow(int n) {
		arrow.setBounds(3,30+100*n,35,35);
	}
	public void setDuellist(int a,int b) {
		this.a = a;
		this.b = b;
		for(int i = 0;i<4;i++) {
			if(i == a || i == b) {
				statusIcons.get(i).setVisible(true);
			}
		}
	}
	public void clearDuellist() {
		for(int i = 0;i<4;i++) {
			if(i == a || i == b) {
				statusIcons.get(i).setVisible(false);
			}
		}
	}
	public void reCreate(StatusBar other) {
		for(int i = 0;i<4;i++) {
			this.statusIcons.get(i).setIcon(other.statusIcons.get(i).getIcon());
			this.statusIcons.get(i).setVisible(other.statusIcons.get(i).isVisible());
		}
	}
	public void setWinner(int color,int count) {
		ImageIcon tempIcon;
		switch(count) {
		case 1:
			tempIcon = Pics.FIRST.getPic();
			break;
		case 2:
			tempIcon = Pics.SECOND.getPic();
			break;
		case 3:
			tempIcon = Pics.THIRD.getPic();
			break;
		case 4:
			tempIcon = Pics.FORTH.getPic();
			break;	
		default:
			tempIcon = new ImageIcon();
		}
		for(int i = 0;i<4;i++) {
			if (i == color) {
				statusIcons.get(i).setIcon(tempIcon);
				statusIcons.get(i).setVisible(true);
			}
		}
		//setIconList();
	}
	/*public void setIconList() {
		for(int i = 0;i<4;i++) {
			Icon temp = this.icons.get(i);
			temp = this.statusIcons.get(i).getIcon();
		}
	}*/
}
