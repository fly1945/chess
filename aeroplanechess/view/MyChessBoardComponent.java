package xyz.chengzi.aeroplanechess.view;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import java.io.Serializable;

public class MyChessBoardComponent extends JPanel implements Serializable {
	public  static final long serialVersionUID = 6L;
	//∆Â≈ÃPanel
	public JPanel PredictChessPanel = new JPanel();
	public MyChessBoardComponent() {
		this.setOpaque(false);
		setBounds(0,0,753,753);
		setLayout(null);
		
		PredictChessPanel.setBounds(0, 0, 753, 753);
		add(PredictChessPanel);
		PredictChessPanel.setLayout(null);
		PredictChessPanel.setBackground(null);
		PredictChessPanel.setOpaque(false);
		/*
		JLabel chessBoard = new JLabel();
		chessBoard.setBounds(0, 0, 753, 753);
		chessBoard.setIcon(Pics.CHESSBOARD.getPic());
		add(chessBoard);*/
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Pics.CHESSBOARD.getPic().getImage(), 0, 0,this.getWidth(), this.getHeight(), this);
	}
}
