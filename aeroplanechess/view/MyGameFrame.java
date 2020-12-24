package xyz.chengzi.aeroplanechess.view;
import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.listener.GameStateListener;
import xyz.chengzi.aeroplanechess.model.MyPlayer;

import java.io.Serializable;

import javax.swing.*;
public class MyGameFrame extends JFrame implements GameStateListener, Serializable {
	public  static final long serialVersionUID = 10L;
    private MyChessBoardComponent CBC = new MyChessBoardComponent();
    private MyDiceSelectorComponent DSC;
    private MenuPanel menuPanel;
	public MyGameFrame(GameController controller) {
		this.setTitle("∑…––∆Â");
		getContentPane().setLayout(null);
		setBounds(100, 100, 1200, 790);
		JLabel background = new JLabel(Pics.BACKGROUND.getPic());
		background.setBounds(-10,-22,1200,790);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CBC.setLayout(null);
        menuPanel = new MenuPanel(controller);
         DSC = controller.getDSC();
         //Õº∆¨∏≤∏«À≥–Ú£∫‘§≤‚∆Â◊”>∆Â◊”>∆Â≈Ã
        getContentPane().add(DSC);
        getContentPane().add(controller.getStatusBar());
        for(MyPlayer player : controller.getPlayerList()) {
        	for(MyChessComponent chess : player.getChessList()) {
        		CBC.PredictChessPanel.add(chess.getPredictChess());
        	}
        }
        for(MyPlayer player : controller.getPlayerList()) {
        	for(MyChessComponent chess : player.getChessList()) {
        		CBC.add(chess);
        	}
        }
        getContentPane().add(CBC);
        getContentPane().add(menuPanel);
        //getContentPane().add(controller.getLog());
		this.getContentPane().add(background);
	}
    public void reget(GameController controller) {
        this.setTitle("∑…––∆Â");
        controller.setMainFrame(this);
        getContentPane().setLayout(null);
        setBounds(100, 100, 1200, 790);
        JLabel background = new JLabel(Pics.BACKGROUND.getPic());
        background.setBounds(-10,-22,1200,790);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CBC.setLayout(null);
        menuPanel = new MenuPanel(controller);
        DSC = controller.getDSC();
        //Õº∆¨∏≤∏«À≥–Ú£∫‘§≤‚∆Â◊”>∆Â◊”>∆Â≈Ã
        getContentPane().add(DSC);
        getContentPane().add(controller.getStatusBar());
        for(MyPlayer player : controller.getPlayerList()) {
            for(MyChessComponent chess : player.getChessList()) {
                CBC.PredictChessPanel.add(chess.getPredictChess());
            }
        }
        for(MyPlayer player : controller.getPlayerList()) {
            for(MyChessComponent chess : player.getChessList()) {
                CBC.add(chess);
            }
        }
        getContentPane().add(CBC);
        getContentPane().add(menuPanel);
        //getContentPane().add(controller.getLog());
        this.getContentPane().add(background);
    }

    public MyDiceSelectorComponent getDSC() {
        return DSC;
    }

    public void initialize() {
	}

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public MyChessBoardComponent getChessBoard() {
		return CBC;
	}
    public void addChess(MyChessComponent chess) {
    	this.CBC.add(chess);
    }
    @Override
    public void onPlayerStartRound(int player) {
    }

    @Override
    public void onPlayerEndRound(int player) {

    }
}