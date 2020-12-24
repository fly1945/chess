package xyz.chengzi.aeroplanechess.view;

import xyz.chengzi.aeroplanechess.controller.GameController;

import java.io.Serializable;

public class PredictChess extends MyChessComponent implements Serializable {
	public  static final long serialVersionUID = 12L;
	public PredictChess(Integer color,GameController controller,MyDiceSelectorComponent DSC) {
		super(color,0,controller,DSC,"HELLO");
		super.setLaunched();
		setBounds(0,0,55,55);
		setVisible(false);
		switch(color) {
		case 0 :
			this.setIcon(Pics.NEXTPLANEYELLOW.getPic());
			break;
		case 1 : 
			this.setIcon(Pics.NEXTPLANEBLUE.getPic());
			break;
		case 2 :
			this.setIcon(Pics.NEXTPLANERED.getPic());
			break;
		default :
			this.setIcon(Pics.NEXTPLANEGREEN.getPic());
		}
	}
	//预测棋子
	public void appear(Integer step) {
		//System.out.println("appear执行");


		mapMove(step,true);
		this.setVisible(true);
		
	}
	//消灭预测棋子
	public void disappear() {
		this.setVisible(false);
	}
	@Override
	public String toString() {
		return String.format("[ChessType = 2, Color = %d, ChessID = %d]",this.color,this.getID());
	}
	
	//地图移动规则
	@Override
		public int mapMove(int step,boolean canJump) {
			//System.out.println("现在 "+location);
			boolean RT = false;
			if(launched) {
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
	@Override
	public void flyMove() {//在飞行后时与其它棋子相遇会同时执行跳跃和叠棋/对战
		//controller.flyPass(this.color);
		mapMove(12,false);
	}
}
