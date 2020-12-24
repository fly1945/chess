package xyz.chengzi.aeroplanechess.view;
import javax.swing.*;


import java.io.Serializable;

public class MyDicePiece extends JLabel implements Serializable {
	public  static final long serialVersionUID = 8L;
	private Integer diceFace;
	public MyDicePiece(Integer x,Integer y) {
		setBounds(x,y,64,64);
		setIcon(Pics.DEFAULTDICE.getPic());
	}
	public MyDicePiece() {
		this(0,0);
	}
	public void setDice(Integer i) {
		diceFace = i;
		setFace(diceFace);
		setVisible(true); 
	}
	private void setFace(Integer i) {
		switch(i) {
		case 1:
			setIcon(Pics.ONE.getPic());
			break;
		case 2:
			setIcon(Pics.TWO.getPic());
			break;
		case 3:
			setIcon(Pics.THREE.getPic());
			break;
		case 4:
			setIcon(Pics.FOUR.getPic());
			break;
		case 5:
			setIcon(Pics.FIVE.getPic());
			break;
		case 6:
			setIcon(Pics.SIX.getPic());
			break;
		default :
			setIcon(Pics.DEFAULTDICE.getPic());
		}
	}
}
