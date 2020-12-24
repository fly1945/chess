package xyz.chengzi.aeroplanechess.view;

import xyz.chengzi.aeroplanechess.AeroplaneChess;
import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.util.FileController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class StartMenu extends JFrame implements Serializable {
	public  static final long serialVersionUID = 14L;
	private JPanel contentPane;
	private MyGameFrame GF;
	private final JLabel titleLabel = new JLabel(Pics.TITLE.getPic());
	private StartMenu myself;
	/**
	 * Create the frame.
	 */
	public StartMenu(GameController controller) {
		myself = this;
		this.setTitle("开始界面");
		this.GF = controller.getMainFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 790);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		titleLabel.setBounds(313, 143, 600, 200);
		contentPane.add(titleLabel);
		
		JPanel startPanel = new JPanel();
		startPanel.setBounds(200, 550, 165, 74);
		contentPane.add(startPanel);
		startPanel.setLayout(null);
		startPanel.setOpaque(false);
		
		JLabel lblNewLabel = new JLabel("START");
		lblNewLabel.setBounds(0, 0, 165, 74);
		startPanel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("幼圆", Font.BOLD, 30));
		
		JButton btnStart = new MyBubbleButton();
		btnStart.setBounds(0, 0, 165, 74);
		startPanel.add(btnStart);
		//btnStart.setVisible(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GF.setVisible(true);
				dispose();
			}
		});
		
		JPanel resumePanel = new JPanel();
		resumePanel.setBounds(520, 550, 165, 74);
		contentPane.add(resumePanel);
		resumePanel.setLayout(null);
		resumePanel.setOpaque(false);
		
		JLabel lblResume = new JLabel("RESUME");
		lblResume.setBounds(0, 0, 165, 74);
		resumePanel.add(lblResume);
		lblResume.setHorizontalAlignment(SwingConstants.CENTER);
		lblResume.setForeground(Color.WHITE);
		lblResume.setFont(new Font("幼圆", Font.BOLD, 30));
		
		JButton btnResume = new MyBubbleButton();
		btnResume.setBounds(0, 0, 165, 74);
		resumePanel.add(btnResume);
		btnResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileController.loadSave(controller, myself);
 			}
		});

		JPanel exitPanel = new JPanel();
		exitPanel.setBounds(830, 550, 165, 74);
		contentPane.add(exitPanel);
		exitPanel.setLayout(null);
		exitPanel.setOpaque(false);
		
		JLabel lblResume_1 = new JLabel("EXIT");
		lblResume_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblResume_1.setForeground(Color.WHITE);
		lblResume_1.setFont(new Font("幼圆", Font.BOLD, 30));
		lblResume_1.setBounds(0, 0, 165, 74);
		exitPanel.add(lblResume_1);
		
		JButton btnExit = new MyBubbleButton();
		btnExit.setBounds(0, 0, 165, 74);
		exitPanel.add(btnExit);
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel background = new JLabel(Pics.BACKGROUND.getPic());
		background.setBounds(-10,-22,1200,790);
		getContentPane().add(background);
	}
}
