package xyz.chengzi.aeroplanechess.view;

import javax.swing.JPanel;

import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.util.FileController;
import xyz.chengzi.aeroplanechess.util.Music_player;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MenuPanel extends JPanel implements Serializable {
	public  static final long serialVersionUID = 3L;
	JButton btnExitButton = new JButton(Pics.QUIT.getPic());
	JButton btnRestartButton = new JButton(Pics.RESTART.getPic());
	JButton btnSaveButton = new JButton(Pics.SAVE.getPic());
	JButton btnBegin_music_Button = new JButton(Pics.BEGIN_MUSIC.getPic());
	JButton btn_acoustics_Button = new JButton(Pics.ACOUSTIC_MUSIC.getPic());
	GameController controller;
	/**
	 * Create the panel.
	 */
	public MenuPanel(GameController controller) {
		this.setOpaque(false);
		this.controller = controller;
		setLayout(null);
		this.setBounds(880,650,299,100);
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.getMainFrame().dispose();
	            GameController controller = new GameController();
	            StartMenu startMenu = new StartMenu(controller);
	            startMenu.setVisible(true);
				//System.exit(0);
			}
		});
		btnExitButton.setBounds(232, 25, 55, 55);
		btnRestartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.restartGame();
			}
		});
		btnRestartButton.setBounds(173,25,55,55);
		btnSaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileController.saveGame(controller);
			}
		});
		btnSaveButton.setBounds(116, 25, 55, 55);
///////////////
		btnBegin_music_Button.setBounds(56, 25, 55, 55);
		btnBegin_music_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music_player.setBackGround_open_if();
			}
		});
		//存档按钮应当只在正常回合能按



		btn_acoustics_Button.setBounds(0, 25, 55, 55);
		btn_acoustics_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Music_player.setAcoustics_open_if();
			}
		});
		//存档按钮应当只在正常回合能按


		//存档按钮应当只在正常回合能按
		add(btnSaveButton);
		add(btnExitButton);
		add(btnRestartButton);
		add(btnBegin_music_Button);
		add(btn_acoustics_Button);
	}
}
