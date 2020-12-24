package xyz.chengzi.aeroplanechess.controller;

import xyz.chengzi.aeroplanechess.listener.GameStateListener;
import xyz.chengzi.aeroplanechess.listener.InputListener;
import xyz.chengzi.aeroplanechess.listener.Listenable;
import xyz.chengzi.aeroplanechess.listener.StepListener;
import xyz.chengzi.aeroplanechess.model.MyChessBoardLocation;
import xyz.chengzi.aeroplanechess.model.MyPlayer;
import xyz.chengzi.aeroplanechess.util.Music_player;
import xyz.chengzi.aeroplanechess.util.RandomUtil;

import xyz.chengzi.aeroplanechess.view.GameoverFrame;
import xyz.chengzi.aeroplanechess.view.MultiChessComponent;
import xyz.chengzi.aeroplanechess.view.MyChessComponent;
import xyz.chengzi.aeroplanechess.view.MyDiceSelectorComponent;
import xyz.chengzi.aeroplanechess.view.MyGameFrame;
import xyz.chengzi.aeroplanechess.view.PredictChess;
import xyz.chengzi.aeroplanechess.view.StatusBar;
import xyz.chengzi.aeroplanechess.view.TextLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.SSLEngineResult.Status;
//��Ϸ���̿���
public class GameController implements InputListener, StepListener, Listenable<GameStateListener> , Serializable {
	public  static final long serialVersionUID = 2L;
	private TextLog textLog = new TextLog();
	private final List<GameStateListener> listenerList = new ArrayList<>();
	//�������ӵ�����
	private Integer rolledNumber1 = null;
	private Integer rolledNumber2 = null;
	private Integer winnerCount;
	private Integer currentPlayer;
	private Integer smallTurnCount;
	private List<MyPlayer> playerList = new ArrayList<MyPlayer>();
	private List<MyChessComponent> chessList = new ArrayList<MyChessComponent>();
	private List<MyChessComponent> movedChessList = new ArrayList<MyChessComponent>();
	private MyDiceSelectorComponent DSC;
	private StatusBar statusBar;

	private boolean isCheatMode;
	//�Ծ��غ����
	private MyPlayer duellist1,duellist2;
	private MyChessComponent duelChess1,duelChess2;
	private boolean isDuelRound = false;
	private int[] duelDices = new int[2];

	private MyGameFrame mainFrame;
	private GameoverFrame gameover;
	public GameController() {
		//��ʼ����Ϸ
		initializeGame();
	}
	public void out(int i){
		System.out.println(i);
	}
	public MyGameFrame getMainFrame() {
		return mainFrame;
	}
	public List<MyPlayer> getPlayerList(){
		return playerList;
	}
	public MyDiceSelectorComponent getDSC() {
		return DSC;
	}
	public StatusBar getStatusBar() {
		return statusBar;
	}
	public TextLog getLog() {
		return textLog;
	}
	public Integer getCurrentPlayer() {
		return currentPlayer;
	}
	public boolean ifRolledDice() {
		return !(rolledNumber1 == null );
	}
	public MyPlayer getPlayerForNow() {
		return playerList.get(currentPlayer);
	}
	@Override
	public int[] getDice() {
		return new int[] {rolledNumber1,rolledNumber2};
	}
	public Integer getOneDice(Integer n) {
		switch(n) {
			case 1:
				return rolledNumber1;
			case 2:
				return rolledNumber2;
			default:
				return -1;
		}
	}
	public void reset(GameController in){

		setCurrentPlayer(in.getCurrentPlayer());

		setWinnerCount(in.getWinnerCount());

		//��ȡ�������б� �Ծ������б�����ݴ����������б�
		for(int i = 0;i<32;i++) {
			chessList.get(i).reCreate(in.getChessList().get(i));
		}
		//����chessList���ص����� �ӽ���
		if(in.getChessList().size()>31) {
			for(int i = 32;i<in.getChessList().size();i++) {
				if(in.getChessList().get(i).getChessType() == 3) {
					System.out.println(in.getChessList().get(i).isVisible());
					MultiChessComponent multiChess = new MultiChessComponent(in.getChessList().get(i).chessList,in.getChessList().get(i).isVisible(),this);
					System.out.println(multiChess.isVisible());
					mainFrame.getChessBoard().PredictChessPanel.add(multiChess);
					mainFrame.getChessBoard().PredictChessPanel.add(multiChess.getPredictChess());
					multiChess.setLocation(multiChess.location);

				}
			}
		}

		setIsCheatMode(in.getCheatModeState());

		setSmallTurnCount(in.getSmallTurnCount());

		statusBar.moveArrow(this.getCurrentPlayer());
		statusBar.clearDuellist();
		statusBar.reCreate(in.statusBar);
		DSC.normalize();
		for(MyChessComponent chess : chessList) {
			this.setMovable(chess);
		}

	}
	public void plusLandedCount() {
		this.getPlayerForNow().landedCount++;
		if(this.getPlayerForNow().landedCount == 4) {
			setWinner(this.getPlayerForNow());
			this.getPlayerForNow().hasWon = true;
		}
	}


	public void initializeGame() {
		//����DSC
		statusBar = new StatusBar(this);
		DSC = new MyDiceSelectorComponent();
		DSC.registerListener(this);
		DSC.setGC(this);
		//�������
		chessList.clear();
		playerList.clear();
		playerList.add(new MyPlayer(0,DSC,this));
		playerList.add(new MyPlayer(1,DSC,this));
		playerList.add(new MyPlayer(2,DSC,this));
		playerList.add(new MyPlayer(3,DSC,this));
		currentPlayer = 0;
		smallTurnCount = 0;
		//�����������趨�ƶ����
		//tellCurrentPlayer();
		statusBar.moveArrow(0);
		isCheatMode = false;
		winnerCount = 0;
		mainFrame = new MyGameFrame(this);
	}
	public void restartGame() {
		System.out.println("restart game");
		//System.out.println("RESTART");
		for(MyChessComponent chess : chessList) {
			this.setMovable(chess);
			if(chess.getChessType() == 1) {
				chess.moveToHome();
			}else {
				chess.setVisible(false);
			}
		}
		currentPlayer = 0;
		statusBar.moveArrow(0);
		DSC.exitDuel();
		statusBar.clearDuellist();
		winnerCount = 0;
		DSC.normalize();
	}
	//��ǰ��غϽ��� �غϽ�����һ�����
	public void nextBigRound() {
		movedChessList.clear();
		rolledNumber1 = null;
		rolledNumber2 = null;
		smallTurnCount = 0;
		/*for(MyChessComponent chess : getPlayerForNow().getChessList()) {
			chess.getPredictChess().setVisible(false);
		}*/
		currentPlayer = (currentPlayer + 1) % 4;
		while(playerList.get(currentPlayer).hasWon()) {
			currentPlayer = (currentPlayer + 1) % 4;
		}
		tellCurrentPlayer();
		statusBar.moveArrow(currentPlayer);
		DSC.normalize();
	}


	//ִ�е�ǰ��ҵ���һ��С�غ�
	public void nextSmallRound() {
		for(MyChessComponent chess : getPlayerForNow().getChessList()) {
			chess.getPredictChess().setVisible(false);
		}
		tellCurrentPlayer();
		statusBar.moveArrow(currentPlayer);
		smallTurnCount++;
		rolledNumber1 = null;
		rolledNumber2 = null;
		DSC.normalize();
	}


	//������һ����С�غϻ��Ǵ�غ� ����1ΪС�غ� ����0Ϊ��غ� ����-1Ϊ�Ծ��غ� ����-2��ʾ��Ϸ����
	private Integer determineRoundProcedure() {
		if(winnerCount == 3) {
			System.out.println("GameOver!");
			gameover.setVisible(true);
			return -2;
		}
		if(isDuelRound) {
			return -1;
		}
		if(rolledNumber1+rolledNumber2>=10&&smallTurnCount<2) {
			//System.out.println("Next Small Round");
			nextSmallRound();
			return 1;
		}else {
			nextBigRound();
			return 0;
		}
	}


	//StepListener
	@Override
	public String stepped(MyChessComponent chess) {
		//System.out.println("do step procedure");
		if(chess.isMovable()) {
			chess.move(DSC.getCalculatedDice(), rolledNumber1, rolledNumber2);
			movedChessList.add(chess);
			determineRoundProcedure();
		}
		return "C";
	}
	//�趨�����Ƿ���Ա����
	public boolean setMovable(MyChessComponent chessFocused) {
		//System.out.print("!");
		if(isDuelRound) {
			return false;
		}
		if(chessFocused.isLanded()) {
			chessFocused.setMovable(false);
			return false;
		}
		if(!chessFocused.isLanded()&&chessFocused.getColor() == this.getCurrentPlayer()) {
			if(chessFocused.getMyLocation()<300&&hasSelectDices()&&DSC.ifSelectedDice()&&hasSelectDices()) {
				chessFocused.setMovable(true);
				return true;
			}
			if(hasSelectDices()&&(rolledNumber1==6||rolledNumber2 == 6)) {
				chessFocused.setMovable(true);
				return true;
			}
		}
		chessFocused.setMovable(false);
		return false;
	}

	private void skipToNextBigTurn() {//���ĸ����Ӷ����ܶ����ߵ����γ�����ʱʱ����skip
		Integer cnt = 0;
		for(MyChessComponent chess : this.getPlayerForNow().getChessList()) {
			cnt+= (chess.location>=300) ? 1:0;
		}//���û��AFK���ڲ��ڵ����غ�
		if(!this.getPlayerForNow().isAFK&&!(this.smallTurnCount==2&&rolledNumber1+rolledNumber2 >= 10)&&(cnt < 4 ||(rolledNumber1 == null || rolledNumber2 == null)||(rolledNumber1 == 6 || rolledNumber2 == 6))) {
		}else {
			DSC.rollDiceLabel.setText("�޷��ƶ�");
			Timer skipTimer = new Timer();
			skipTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					nextBigRound();
					DSC.rollDiceLabel.setText("������");
				}
			}, 500);
		}
	
	}
	//�ж�һ�������Ƿ������������ص�
	//����0��ʾû�� ����1��ʾ�����Լ������� ����2��ʾ����������ҵ�����
	public Integer hasMetOtherChess(MyChessComponent chess) {
		for(MyChessComponent otherChess : chessList ) {
			if(!otherChess.equals(chess)) {
				if(otherChess.getColor() == chess.getColor()&&MyChessBoardLocation.compare(otherChess.getMyLocation(), chess.getMyLocation())&&!(otherChess.getChessType()==2)&&otherChess.isVisible()) {
					return 1;
				}
				else if(otherChess.getColor() != chess.getColor() &&MyChessBoardLocation.compare(otherChess.getMyLocation(), chess.getMyLocation())&&!(otherChess.getChessType()==2)&&otherChess.isVisible()) {
					return 2;
				}
			}
		}
		return 0;
	}


	//�����ص�����
	public void createMultiChess(MyChessComponent chess) {
		//System.out.println(chess.getChessType());
		if(chess.getChessType() == 3) {
			chess = (MultiChessComponent)chess;
		}
		MultiChessComponent multiChess = new MultiChessComponent(chess);
		chess.setVisible(false);
		MyChessComponent otherChess = getMetChess(chess);
		multiChess.addChess(otherChess);
		otherChess.setVisible(false);
		mainFrame.getChessBoard().add(multiChess);
		mainFrame.getChessBoard().PredictChessPanel.add(multiChess.getPredictChess());
		multiChess.setIcon();
		multiChess.setVisible(true);
	}


	//Ѱ���뵱ǰ�����ص������� ���������ص������ӵĹ���
	public MyChessComponent getMetChess(MyChessComponent thisChess) {
		for(MyChessComponent otherChess : chessList) {
			if(otherChess.getID()!=thisChess.getID()&&MyChessBoardLocation.compare(otherChess.getMyLocation(), thisChess.getMyLocation())&&!(otherChess.getChessType()==2)&&otherChess.isVisible()) {
				//System.out.println("GotCha!"+otherChess.getID() );
				return otherChess;
			}
		}
		//System.out.println("NONONONOONO");
		return null;
	}


	//�Ծ����
	//Ѱ�ҶԾ�����
	//��ʼ�Ծ�
	public void startDuel(MyChessComponent duelChess1) {//ץס�Ծ������ӺͶԾ��Ľ�ɫ
		this.duelChess1 = duelChess1;
		duelChess2 = getMetChess(duelChess1);
		duelChess1.setLocation(duelChess1.getX()+10, duelChess1.getY()-10);
		duelChess2.setLocation(duelChess2.getX()-10, duelChess2.getY()+10);
		isDuelRound = true;
		duellist1 = playerList.get(duelChess1.getColor());
		duellist2 = playerList.get(duelChess2.getColor());
		statusBar.setDuellist(duellist1.getColor(), duellist2.getColor());
		rolledNumber1 = null;
		rolledNumber2 = null;
		DSC.enterDuel();
		statusBar.moveArrow(duellist1.getColor());
		Music_player.play_fight_start();

	}
	public void nextDuellist() {
		statusBar.moveArrow(duellist2.getColor());
	}
	//DSC�Ǳ������rollDice
	public void flyPass(int color) {
		int location = 0;
		switch(color) {
		case 0:
			location = 117;
			break;
		case 1:
			location = 124;
			break;
		case 2:
			location = 103;
			break;
		case 3:
			location = 110;
			break;
		}
		for(MyChessComponent chess :chessList) {
			if(chess.getMyLocation() == location) {
				chess.moveToHome();
			}
			
		}
	}
	public void duelRolledDice(Integer a,Integer b) {
		this.duelDices = new int[] {a,b};
		if(a>=b) {//duelChess1ʤ��
			if(duelChess2.getChessList().size()>2) {//����Ǵ���2���ص����� ����һ�����ӻؼ�
				duelChess2.getChessList().get(0).moveToHome();
				duelChess2.getChessList().remove(0);
				duelChess2.setIcon();
				startDuel(duelChess1);
			}else if(duelChess2.getChessList().size()==2) {//����ǵ��ڶ������� �����ص��������Ρ���һ�����ӻؼҡ���һ�����ӳ������ص����ӵ�λ�ü����Ծ�
				duelChess2.setVisible(false);
				duelChess2.getChessList().get(0).moveToHome();
				duelChess2.getChessList().remove(0);
				duelChess2.getChessList().get(0).setMyLocation(duelChess2.location);//��������ǲ�֪��Ϊʲôlocation���ܺ����趨
				duelChess2.getChessList().get(0).setVisible(true);
				startDuel(duelChess1);//�ظ��Ծ�
			}else {
				duelChess2.moveToHome();
				exitDuel(true);//�����Ծ� ��ǰ���ʤ��
				duelChess1.setLocation(duelChess1.getMyLocation());
			}
		}else {
			if(duelChess1.getChessList().size()>2) {//����Ǵ���2���ص����� ����һ�����ӻؼ�
				duelChess1.getChessList().get(0).moveToHome();
				duelChess1.getChessList().remove(0);
				duelChess1.setIcon();
				startDuel(duelChess1);
			}else if(duelChess1.getChessList().size()==2) {//����ǵ��ڶ������� �����ص��������Ρ���һ�����ӻؼҡ���һ�����ӳ������ص����ӵ�λ�ü����Ծ�
				duelChess1.setVisible(false);
				duelChess1.getChessList().get(0).moveToHome();
				duelChess1.getChessList().remove(0);
				duelChess1.getChessList().get(0).setMyLocation(duelChess1.location);
				duelChess1.getChessList().get(0).setVisible(true);
				startDuel(duelChess1);//�ظ��Ծ�
			}else {
				duelChess1.moveToHome();
				exitDuel(false);//�����Ծ� �������ʤ��
				duelChess2.setLocation(duelChess2.getMyLocation());
			}
		}
	}
	//�����Ծ�
	public void exitDuel(boolean hasWon) {
		isDuelRound = false;
		DSC.exitDuel();
		statusBar.clearDuellist();
		if(hasWon) {//Ӯ�˽���һ��С�غ� ���˸���һ����
			this.nextSmallRound();
		}else {
			this.nextBigRound();
		}
	}
	//�ı�������
	public void tellCurrentPlayer() {
		textLog.setText("��ǰ����ǣ�"+currentPlayer+"\n");
	}

	//������ ר����ͨ�ƶ�
	@Override
	public void rollDice() {
		//System.out.println("GameController.rollDice");
		if(rolledNumber1 == null||rolledNumber2 == null||isDuelRound) {
			Music_player.play_dice_row();
//			try {
//				Thread.currentThread().sleep(500);
//			}catch(Exception e) {
//
//			}
			rolledNumber1 = RandomUtil.nextInt(1, 6);
			rolledNumber2 = RandomUtil.nextInt(1, 6);
			//System.out.println(rolledNumber1+" "+rolledNumber2);
			for(MyChessComponent chess : this.getPlayerForNow().getChessList()) {
				setMovable(chess);
			}
		}
		skipToNextBigTurn();
	}

	//����ģʽѡ����
	public void cheatSelectDice(Integer n) {
		switch(n) {
			case 1:
				if(rolledNumber1 == null) {
					rolledNumber1 = 1;
				}else {
					if(rolledNumber1 == 6) {
						rolledNumber1 = 1;
					}else {
						rolledNumber1 += 1;
					}
				}
				break;
			case 2:
				if(rolledNumber2 == null) {
					rolledNumber2 = 1;
				}else {
					if(rolledNumber2 == 6) {
						rolledNumber2 = 1;
					}else {
						rolledNumber2 += 1;
					}
				}
		}
		for(MyChessComponent chess : this.getPlayerForNow().getChessList()) {
			setMovable(chess);
		}

	}

	public boolean hasSelectDices() {
		return (rolledNumber1 != null && rolledNumber2 != null);
	}

	public boolean getCheatModeState() {
		return isCheatMode;
	}
	public void setCheatModeState(boolean state) {
		isCheatMode = state;
	}
	public void setIsCheatMode(boolean isCheatMode){
		this.isCheatMode=isCheatMode;
	}
	public void setTextLog(TextLog textLog) {
		this.textLog = textLog;
	}

	public void setRolledNumber1(Integer rolledNumber1) {
		this.rolledNumber1 = rolledNumber1;
	}

	public void setRolledNumber2(Integer rolledNumber2) {
		this.rolledNumber2 = rolledNumber2;
	}

	public void setWinnerCount(Integer winnerCount) {
		this.winnerCount = winnerCount;
	}

	public void setCurrentPlayer(Integer currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setSmallTurnCount(Integer smallTurnCount) {
		this.smallTurnCount = smallTurnCount;
	}

	public void setPlayerList(List<MyPlayer> playerList) {
		this.playerList = playerList;
	}

	public void setChessList(List<MyChessComponent> chessList) {
		this.chessList = chessList;
	}

	public void setMovedChessList(List<MyChessComponent> movedChessList) {
		this.movedChessList = movedChessList;
	}

	public void setDSC(MyDiceSelectorComponent DSC) {
		this.DSC = DSC;
	}

	public void setStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}

	public void setCheatMode(boolean cheatMode) {
		isCheatMode = cheatMode;
	}

	public void setDuellist1(MyPlayer duellist1) {
		this.duellist1 = duellist1;
	}

	public void setDuellist2(MyPlayer duellist2) {
		this.duellist2 = duellist2;
	}

	public void setDuelChess1(MyChessComponent duelChess1) {
		this.duelChess1 = duelChess1;
	}

	public void setDuelChess2(MyChessComponent duelChess2) {
		this.duelChess2 = duelChess2;
	}

	public void setDuelRound(boolean duelRound) {
		isDuelRound = duelRound;
	}

	public void setDuelDices(int[] duelDices) {
		this.duelDices = duelDices;
	}

	public void setGameover(GameoverFrame gameover) {
		this.gameover = gameover;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public TextLog getTextLog() {
		return textLog;
	}

	public List<GameStateListener> getListenerList() {
		return listenerList;
	}

	public Integer getRolledNumber1() {
		return rolledNumber1;
	}

	public Integer getRolledNumber2() {
		return rolledNumber2;
	}

	public Integer getWinnerCount() {
		return winnerCount;
	}

	public Integer getSmallTurnCount() {
		return smallTurnCount;
	}

	public List<MyChessComponent> getChessList() {
		return chessList;
	}

	public List<MyChessComponent> getMovedChessList() {
		return movedChessList;
	}

	public boolean isCheatMode() {
		return isCheatMode;
	}

	public MyPlayer getDuellist1() {
		return duellist1;
	}

	public MyPlayer getDuellist2() {
		return duellist2;
	}

	public MyChessComponent getDuelChess1() {
		return duelChess1;
	}

	public MyChessComponent getDuelChess2() {
		return duelChess2;
	}

	public boolean isDuelRound() {
		return isDuelRound;
	}

	public int[] getDuelDices() {
		return duelDices;
	}

	public GameoverFrame getGameover() {
		return gameover;
	}

	public void setWinner(MyPlayer player) {
		winnerCount++;
		statusBar.setWinner(player.getColor(), winnerCount);
	}

	public void setMainFrame(MyGameFrame frame) {
		this.mainFrame = frame;
	}
	public void setGameoverFrame(GameoverFrame gameover) {
		this.gameover = gameover;
	}

	public void registerChess(MyChessComponent chess) {
		chessList.add(chess);
	}
	@Override
	public void registerListener(GameStateListener listener) {
		listenerList.add(listener);
	}

	@Override
	public void unregisterListener(GameStateListener listener) {
		listenerList.remove(listener);
	}
}
