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
	//Ԥ������
	public void appear(Integer step) {
		//System.out.println("appearִ��");


		mapMove(step,true);
		this.setVisible(true);
		
	}
	//����Ԥ������
	public void disappear() {
		this.setVisible(false);
	}
	@Override
	public String toString() {
		return String.format("[ChessType = 2, Color = %d, ChessID = %d]",this.color,this.getID());
	}
	
	//��ͼ�ƶ�����
	@Override
		public int mapMove(int step,boolean canJump) {
			//System.out.println("���� "+location);
			boolean RT = false;
			if(launched) {
					while (step > 0) {
						step--;
						if (location >= 200) {
							//System.out.print("������ͼ��");
							//�����㵽���ͼ
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
							//���յ�ǰ��
							//������յ��˻��в����ͻ�ͷ ��һ���ߵ�ʱ����������
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
							//System.out.print("�ڴ��ͼ������һ��");
							//ת�� ת���Ϊ��49 ��10 ��23 ��36
							//���ͼ������
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
			//������յ��˾��趨����
			//System.out.println((this instanceof PredictChess) ? "PredictChess":"NormalChess");
			if((location == 106 ||location == 113 || location == 120 || location == 127)&&!(this instanceof PredictChess)) {
				land();
			}
			//�ж�����ĵص��Ƿ�����������
			switch(controller.hasMetOtherChess(this)) {
			case 0:
				//û��
				//System.out.println("û��������������");

				//����
				if(isAtFlyPoint()) {
					flyMove();
				}
				//��Ծ
				if(canJump&&isAtJumpPoint()) {
					jumpMove();
					
				}
				setLocation(location);
				break;
			case 1:
				//�����Լ�������
				setLocation(location);
				if (!(this instanceof PredictChess)) {
					controller.createMultiChess(this);
					//System.out.println("�����Լ�������");
				}
				break;
			case 2:
				//�����������:
				setLocation(location);
				if (!(this instanceof PredictChess)) {
					controller.startDuel(this);
				}
				//System.out.println("����������ҵ�����");
				break;
			}
			return location;
		}
		//�ж��Ƿ�����Ծ����
	@Override
	public void flyMove() {//�ڷ��к�ʱ����������������ͬʱִ����Ծ�͵���/��ս
		//controller.flyPass(this.color);
		mapMove(12,false);
	}
}
