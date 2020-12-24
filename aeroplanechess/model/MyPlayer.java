package xyz.chengzi.aeroplanechess.model;
import java.io.Serializable;
import java.util.*;
import javax.swing.*;

import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.view.MyChessComponent;
import xyz.chengzi.aeroplanechess.view.MyDiceSelectorComponent;
public class MyPlayer implements  Serializable  {
	//玩家颜色
	public int landedCount = 0;
	private int color;
	private GameController controller;
	public boolean hasWon = false;
	public boolean isAFK = false;
	//玩家棋子列表
	private List<MyChessComponent> chessList = new ArrayList<MyChessComponent>();
	public MyPlayer(int color,MyDiceSelectorComponent DSC,GameController controller) {
		this.controller = controller;
		this.color = color;
		switch(color) {
		
		}
		for(int i = 0;i<4;i++) {
		chessList.add(new MyChessComponent(color,color*4+300+i,controller,DSC)); 
		}
	}
	public int getColor() {
		return color;
	}
	public List<MyChessComponent> getChessList(){
		return chessList;
	}
	public int getLandedCount() {
		return landedCount;
	}
	public void plusLandedCount() {
		landedCount++;
		if(landedCount == 4) {
			controller.setWinner(this);
			hasWon = true;
		}
	}
	public void clear() {
		landedCount = 0;
	}
	public boolean hasWon() {
		return hasWon;
	}
}
