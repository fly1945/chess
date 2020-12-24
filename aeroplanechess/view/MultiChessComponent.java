package xyz.chengzi.aeroplanechess.view;
import java.io.Serializable;
import java.util.*;

import xyz.chengzi.aeroplanechess.controller.GameController;
public class MultiChessComponent extends MyChessComponent implements Serializable {
	public  static final long serialVersionUID = 4L;
	public MultiChessComponent(MyChessComponent chess) {
		super(chess.getColor(),chess.getMyLocation(),chess.getController(),chess.getDSC());
		chessList.clear();
		for(MyChessComponent mchess : chess.getChessList() ) {
			chessList.add(mchess);
		}
		super.chessType = 3;
		System.out.println(chessType);
		super.launched = true;
	}
	public void addChess(MyChessComponent chess) {
		for(MyChessComponent mchess : chess.getChessList()) {
			chessList.add(mchess);
		}
	}
	//重建重叠棋子
	public MultiChessComponent(List<MyChessComponent> chessList,boolean isVisible,GameController controller) {
		super(chessList.get(0).getColor(),chessList.get(0).getMyLocation(),controller,controller.getDSC());
		this.chessList.clear();
		for(MyChessComponent chess : chessList) {
			this.chessList.add(super.controller.getChessList().get(chess.getID()-1));
		}
		this.location = chessList.get(0).getMyLocation();
		System.out.println(this.location+"!");
		super.launched = true;
		super.chessType = 3;
		//System.out.println(controller.getMainFrame().getChessBoard());
		//controller.getMainFrame().addChess(this);\
		this.setIcon();
		this.setLocation(location);
		this.setVisible(isVisible);
		
	}
	@Override
	public void land() {//如果重叠棋子到终点了 就全部到终点了
		System.out.println("My color is "+color);
		this.setVisible(false);
		landed = true;
		movable = false;
		for(MyChessComponent chess : chessList) {
			chess.land();
			chess.setVisible(true);
		}
	}
	
	public List<MyChessComponent> getChessList(){
		return chessList;
	}
	
	
	
	
	@Override
	public void setIcon() {
		System.out.println(chessList.size());
		switch(color) {
		case 0:
			switch(chessList.size()) {
			case 2:
				this.setIcon(Pics.YELLOW2.getPic());
				System.out.println("!");
				break;
			case 3:
				this.setIcon(Pics.YELLOW3.getPic());
				break;
			case 4:
				this.setIcon(Pics.YELLOW4.getPic());
				break;
			}
			break;
		case 1:
			switch(chessList.size()) {
			case 2:
				this.setIcon(Pics.BLUE2.getPic());
				break;
			case 3:
				this.setIcon(Pics.BLUE3.getPic());
				break;
			case 4:
				this.setIcon(Pics.BLUE4.getPic());
				break;
			}
			break;
		case 2:
			switch(chessList.size()) {
			case 2:
				this.setIcon(Pics.RED2.getPic());
				break;
			case 3:
				this.setIcon(Pics.RED3.getPic());
				break;
			case 4:
				this.setIcon(Pics.RED4.getPic());
				break;
			}
			break;
		case 3:
			switch(chessList.size()) {
			case 2:
				this.setIcon(Pics.GREEN2.getPic());
				break;
			case 3:
				this.setIcon(Pics.GREEN3.getPic());
				break;
			case 4:
				this.setIcon(Pics.GREEN4.getPic());
				break;
			}
		}
	}
	@Override
	public void moveToHome() {
		this.setVisible(false);
		for(MyChessComponent chess : chessList) {
			chess.moveToHome();
		}
	}
	@Override
	public String toString() {
		String s1 = String.format("[ChessType = 3, Color = %d, ID = %d, location = %d", this.color,this.getID(),this.location);
		String s2 = "ChessList = ";
		for(MyChessComponent chess : chessList) {
			s2 = s2 + chess.toString()+",";
		}
		return s1 + s2 + "]";
		
	}
}
