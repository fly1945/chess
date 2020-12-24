package xyz.chengzi.aeroplanechess.view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.listener.Listenable;
import xyz.chengzi.aeroplanechess.listener.StepListener;
import xyz.chengzi.aeroplanechess.model.MyChessBoardLocation;
import xyz.chengzi.aeroplanechess.util.Music_player;
public class MyChessComponent extends JLabel implements Listenable<StepListener>, Serializable {
	public  static final long serialVersionUID = 7L;
	protected  int chessType;
	//棋子类型 1为普通棋子 2为预测棋子 3为重叠棋子
	private static int chessCnt = 0;
	//棋子Label 直接扔到MyGameFrame上
	private final int chessID;
	private List<StepListener> listenerList = new ArrayList<StepListener>();
	//是否允许点击boolean
	protected boolean movable = false;
	//是否到达终点boolean
	protected boolean landed = false;
	//坐标
	public int location;
	//家位置
	public int homeLocation;
	//图像？
	//颜色
	protected final int  color;
	//是否起飞了
	protected boolean launched = false;
	//预测棋子
	private PredictChess predictChess;
	protected GameController controller;
	private MyDiceSelectorComponent DSC;
	public List<MyChessComponent> chessList = new ArrayList<MyChessComponent>(); 
	//标准棋子建立函数
	public MyChessComponent(int color,int location,GameController controller,MyDiceSelectorComponent DSC) {
		predictChess = new PredictChess(color,controller,DSC);
		chessType = 1;
		chessCnt++;
		chessID = chessCnt;
		controller.registerChess(this);
		//System.out.println(chessID);
		this.location = location;
		this.DSC = DSC;
		this.homeLocation = location;
		this.color = color;
		this.controller = controller;
		this.setIcon();
		super.setBounds(0,0,50,50);
		chessList.add(this);
		setIcon();
		setLocation(location);
		//对所有实际棋子添加点击处理
		addMouseListener(
		         new MouseAdapter() {
		        	 public void mousePressed(MouseEvent E) {
						getPredictChess().disappear();
						Music_player.play_clic();
						if(movable) {
							pressed();
						}
		        	 }
		 			@Override
					public void mouseEntered(MouseEvent e) {
		 				//System.out.println(chessID+" "+ movable);
		 				//System.out.println(DSC.getStep()!=0);
		 				controller.setMovable((MyChessComponent)e.getSource());
		 				if (movable&&!landed&&launched&&DSC.getStep()!=0) {
							getPredictChess().location = getMyLocation();
							getPredictChess().appear(DSC.getStep());
						}
					}
					@Override
					public void mouseExited(MouseEvent e) {
						getPredictChess().setLocation(location);
						getPredictChess().disappear();
					}
		         
		         });
	}
	//预测棋子的创建接口
	public MyChessComponent(int color,int location,GameController controller,MyDiceSelectorComponent listener,String forPredictChess) {
		chessCnt++;
		chessType = 2;
		chessID = chessCnt;
		//System.out.println(chessID);
		controller.registerChess(this);
		this.location = location;
		this.color = color;
		this.controller = controller;
		setLocation(location);
		registerListener(controller);
	}
	//重置棋子的创建接口
	public void reCreate(MyChessComponent otherChess) {
		this.location = otherChess.location;
		this.movable = otherChess.movable;
		this.landed = otherChess.landed;
		this.launched = otherChess.launched;
		this.setVisible(otherChess.isVisible());
		this.setLocation(location);
		if(landed) {
			this.land();
		}
		System.out.println(this);
	}

	private void pressed() {
		controller.stepped(this);
	}
	
	
	
	
	
	
	
	
	
    @Override
    public void registerListener(StepListener listener) {
        listenerList.add(listener);
    }


	
	
	//地图移动入口
	public void move(int step,int dice1,int dice2) {
		if(landed) {
		}
			//如果降落了就啥都不做
		else if(!launched) {
			//如果没有起飞
			if(dice1 == 6 || dice2 == 6) {
				//如果扔到了6就起飞
				//System.out.println("Launched");
				location  = 200+(location -300)/4;
				launched = true;
			}
			setLocation(location);
		}else {
			//在大地图上走
			mapMove(step,true);
			}
	}
	
	
	
	
	
	
	
	
	//地图移动规则
	public int mapMove(int step,boolean canJump) {
		//System.out.println("现在 "+location);
		boolean RT = false;
		Timer timer = new Timer() ;
		  timer. scheduleAtFixedRate(new TimerTask() {
	            @Override
	            public void run() {
	            	
	            }
	        }, 2000, step);
				while (step > 0) {
					step--;
					if (location >= 200) {
						//System.out.print("进入大地图了");
						//出发点到大地图
						switch (location) {
						case 200:
							location = 0;
							break;
						case 201:
							location = 13;
							break;
						case 202:
							location = 26;
							break;
						default:
							location = 39;
						}
					} else if (location >= 100) {
					//	//System.out.println(location);
						//在终点前走
						//如果到终点了还有步数就回头 下一次走的时候还是正着走
						if(location == 106 ||location == 113 || location == 120 || location == 127) {
							RT = true;
						}
						if(location == 100 ||location == 107 || location == 114 || location == 121) {
							RT = false;
						}
						////System.out.println(RT);
						if(RT) {
							location -=1;
						}else {
							location +=1;
						}
					} else {
						//System.out.print("在大地图上走了一步");
						//转向 转向点为黄49 蓝10 红23 绿36
						//大地图正常走
						location += 1;
						if (location == 52) {
							location = 0;
						}
						switch(color) {
						case 0:
							if(location == 49) {
								location = 100;
							}
							break;
						case 1 :
							if(location == 10) {	
								location = 107;
							}
							break;
						case 2 :
							if(location == 23) {
								location = 114;
							}
							break;
						case 3 :
							if(location == 36) {
							location = 121;
							}
						}
					} 
				}
		//System.out.println("Moved to "+location);
		//如果到终点了就设定降落
		//System.out.println((this instanceof PredictChess) ? "PredictChess":"NormalChess");
		if((location == 106 ||location == 113 || location == 120 || location == 127)&&!(this instanceof PredictChess)) {
			land();
		}
		//判定到达的地点是否有其它棋子
		switch(controller.hasMetOtherChess(this)) {
		case 0:
			//没有
			//System.out.println("没有遇到其他棋子");

			//飞行
			if(isAtFlyPoint()) {
				flyMove();
			}
			//跳跃
			if(canJump&&isAtJumpPoint()) {
				jumpMove();
				
			}
			setLocation(location);
			break;
		case 1:
			//遇到自己的棋子
			setLocation(location);
			if (!(this instanceof PredictChess)) {
				controller.createMultiChess(this);
				//System.out.println("遇到自己的棋子");
			}
			break;
		case 2:
			//遇到其他玩家:
			setLocation(location);
			if (!(this instanceof PredictChess)) {
				controller.startDuel(this);
			}
			//System.out.println("遇到其他玩家的棋子");
			break;
		}
		return location;
	}
	//判定是否在跳跃点上
	public boolean isAtJumpPoint() {
		if (location<100) {
			switch (color) {
			case 0:
				if (location % 4 == 1) {
					return true;
				}
				break;
			case 1:
				if (location % 4 == 2) {
					return true;
				}
				break;
			case 2:
				if (location % 4 == 3) {
					return true;
				}
				break;
			case 3:
				if (location % 4 == 0) {
					return true;
				}
			}
		}
		return false;
	}
	//跳跃移动 并且不能跳第二次
	public void jumpMove() {
		mapMove(4,false); 
		
	}
	//是否落在起飞点上
	public boolean isAtFlyPoint() {
		switch(color) {
		case 0:
			if(location == 17) {
				return true;
			}
			break;
		case 1:
			if(location == 30) {
				return true;
			}
			break;
		case 2:
			if(location == 43) {
				return true;
			}
			break;
		case 3:
			if(location == 4) {
				return true;
			}
			break;
		}
		return false;
	}
	//飞跃移动 并且不能跳跃
	public void flyMove() {//在飞行后时与其它棋子相遇会同时执行跳跃和叠棋/对战
		controller.flyPass(this.color);
		mapMove(12,false);
	}
	//降落
	public void land() {
		//System.out.println("My color is "+color);
		location =homeLocation;
		landed = true;
		switch(color) {
		case 0 :
			this.setIcon(Pics.LANDEDYELLOW.getPic());
			break;
		case 1:
			this.setIcon(Pics.LANDEDBLUE.getPic());
			break;
		case 2:
			
			this.setIcon(Pics.LANDEDRED.getPic());
			break;
		case 3:
			this.setIcon(Pics.LANDEDGREEN.getPic());
		}
		setLocation(location);
		movable = false;
		controller.getPlayerList().get(this.color).plusLandedCount();
	}
	public void moveToHome() {
    	Music_player.play_fly_bomb();
		this.setVisible(true);
		this.setMyLocation(homeLocation);
		this.setIcon();
		this.setLocation(location);
		launched = false;
		landed = false;
	}
//	public void 
	public void setLocation(int location) {
		int[] temp = MyChessBoardLocation.getLocation(location);
		setLocation(temp[0],temp[1]);
	}
	public void setIcon() {
		switch(color) {
		case 0 ://黄绿红蓝
			this.setIcon(Pics.YELLOWPLANE.getPic());
			break;
		case 1 :
			this.setIcon(Pics.BLUEPLANE.getPic());
			break;
		case 2:
			this.setIcon(Pics.REDPLANE.getPic());
			break;
		default :
			this.setIcon(Pics.GREENPLANE.getPic());
		}
	}

	public void setChessType(int chessType) {
		this.chessType = chessType;
	}

	public static void setChessCnt(int chessCnt) {
		MyChessComponent.chessCnt = chessCnt;
	}

	public void setListenerList(List<StepListener> listenerList) {
		this.listenerList = listenerList;
	}

	public void setLanded(boolean landed) {
		this.landed = landed;
	}

	public void setHomeLocation(int homeLocation) {
		this.homeLocation = homeLocation;
	}

	public void setLaunched(boolean launched) {
		this.launched = launched;
	}

	public void setPredictChess(PredictChess predictChess) {
		this.predictChess = predictChess;
	}

	public void setController(GameController controller) {
		this.controller = controller;
	}

	public void setDSC(MyDiceSelectorComponent DSC) {
		this.DSC = DSC;
	}

	public void setChessList(List<MyChessComponent> chessList) {
		this.chessList = chessList;
	}

	@Override
    public void unregisterListener(StepListener listener) {
        listenerList.remove(listener);
    }
	
   
	public int getMyLocation() {
		return location;
	}
	public int getColor() {
		return color;
	}
	public void setMovable(boolean able) {
		this.movable = able;
	}
	public boolean isMovable() {
		return movable;
	}
	public boolean isLaunched() {
		return launched;
	}
	public boolean isLanded() {
		return landed;
	}
	public PredictChess getPredictChess() {
		return predictChess;
	}
	public void setLaunched() {
		this.launched = true;
	}
	public int getID() {
		return this.chessID;
	}
	public GameController getController() {
		return controller;
	}
	public MyDiceSelectorComponent getDSC() {
		return DSC;
	}
	public int getChessType() {
		return chessType;
	}
	public List<MyChessComponent> getChessList(){
		return chessList;
	}
	public void setMyLocation(Integer location) {
		this.location = location;
	}
	@Override
	public String toString() {//棋子类型-颜色-ID-坐标-是否降落了-是否起飞了
		return String.format("[ChessType = 1, Color = %d, ID = %d, location = %d, landed = %d, launched = %d]", color,chessID,location,(landed?1:0),(launched?1:0));
	}
	
}
