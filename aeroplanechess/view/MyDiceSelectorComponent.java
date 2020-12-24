package xyz.chengzi.aeroplanechess.view;

import java.io.Serializable;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.listener.InputListener;
import xyz.chengzi.aeroplanechess.listener.Listenable;
import xyz.chengzi.aeroplanechess.util.Music_player;

import javax.swing.ButtonGroup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class MyDiceSelectorComponent extends JPanel implements Listenable<InputListener> , Serializable {
	
	private static final long serialVersionUID =9L;
	//对决判定
	private boolean isDuelRound = false;
	//对决数
	private List<Integer> duelDices = new ArrayList<Integer>();
	//单选按钮组
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<JRadioButton> radioButtonList = new ArrayList<JRadioButton>();
	//存储GameController传过来的数字 方便接着传输
	private int number1;
	private int number2;
	//实现Listenable
	private List<InputListener> listenerList = new ArrayList<InputListener>();
	//引用母controller
	private GameController controller;
	//按钮系列
	private JButton RollDice = new JButton("");
	public JLabel rollDiceLabel = new JLabel("掷骰子");
	
	private JRadioButton plus = new JRadioButton("N/A");
	private	JRadioButton minus = new JRadioButton("N/A");
	private	JRadioButton multiply = new JRadioButton("N/A");
	private JRadioButton divide = new JRadioButton("N/A");
	
	
	private JCheckBox cheatMode = new JCheckBox("\u4F5C\u5F0A\u6A21\u5F0F");
	//两个骰子
	private MyDicePiece dice1 = new MyDicePiece(18,10);
	private MyDicePiece dice2 = new MyDicePiece(118,10);
	
	private final JLabel radioButtonBack = new JLabel(Pics.RADIOBUTTONBACKGROUND.getPic());
	private final JLabel backGround = new JLabel("");
	
		
	/**
	 * Create the panel.
	 */
	public MyDiceSelectorComponent() {
		this.setOpaque(false);
		//位置 大小
		setBounds(980, 10, 200, 400);
		//按钮系列设置
		dice1.setBounds(18, 15, 64, 64);
		dice2.setBounds(118, 15, 64, 64);
		setLayout(null);
		
		rollDiceLabel.setForeground(Color.WHITE);
		rollDiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rollDiceLabel.setFont(new Font("幼圆", Font.BOLD, 28));
		rollDiceLabel.setBounds(10, 85, 165, 74);
		add(rollDiceLabel);
		add(dice1);
		add(dice2);
		RollDice.setFont(new Font("黑体", Font.PLAIN, 26));
		RollDice.setBorderPainted(false);
		RollDice.setFocusPainted(false);
		RollDice.setContentAreaFilled(false);
		RollDice.setIcon(Pics.BUTTON1.getPic());
		RollDice.setRolloverIcon(Pics.BUTTON2.getPic());
		RollDice.setPressedIcon(Pics.BUTTON3.getPic());
		RollDice.setBounds(15, 85, 165, 74);
		add(RollDice);
		

		
		
		buttonGroup.add(plus);
		plus.setHorizontalAlignment(SwingConstants.LEFT);
		plus.setFont(new Font("Segoe UI", Font.PLAIN, 37));
		plus.setBounds(10, 166, 100, 60);
		plus.setSelected(true);
		plus.setContentAreaFilled(false);
		add(plus);
		
		buttonGroup.add(minus);
		minus.setFont(new Font("Segoe UI", Font.PLAIN, 37));
		minus.setBounds(98, 166, 100, 60);
		minus.setContentAreaFilled(false);
		add(minus);
		
		buttonGroup.add(multiply);
		multiply.setFont(new Font("Segoe UI", Font.PLAIN, 37));
		multiply.setBounds(10, 220, 100, 60);
		multiply.setContentAreaFilled(false);
		add(multiply);
		
		buttonGroup.add(divide);
		divide.setFont(new Font("Segoe UI", Font.PLAIN, 37));
		divide.setBounds(98, 220, 100, 60);
		divide.setContentAreaFilled(false);
		add(divide);

		dice1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MyDicePiece clickedDice = (MyDicePiece)arg0.getSource();

				if(controller.getCheatModeState()) {
					Music_player.play_clic();
					if(clickedDice.equals(dice1)){
						controller.cheatSelectDice(1);
						number1 = controller.getOneDice(1);
						dice1.setDice(number1);
					}else if(clickedDice.equals(dice2)){
						controller.cheatSelectDice(2);
						number2 = controller.getOneDice(2);
						//System.out.println(number2);
						dice2.setDice(number2);
					}
					if (controller.hasSelectDices()) {
						int[] dices = controller.getDice();
						for (JRadioButton jr : radioButtonList) {
							jr.setEnabled(true);
						}
						plus.setText(dices[0] + dices[1] + "");
						minus.setText(Math.max(dices[0], dices[1]) - Math.min(dices[0], dices[1]) + "");
						multiply.setText(dices[0] * dices[1] + "");
						if (Math.max(dices[0], dices[1]) % Math.min(dices[0], dices[1]) == 0) {
							divide.setText(Math.max(dices[0], dices[1]) / Math.min(dices[0], dices[1]) + "");
						} else {
							divide.setText("N/A");
							divide.setEnabled(false);
						}

					}
				}
			}
		});
		dice2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MyDicePiece clickedDice = (MyDicePiece)arg0.getSource();
				if(controller.getCheatModeState()) {
					Music_player.play_clic();
					if(clickedDice.equals(dice1)){
						controller.cheatSelectDice(1);
						number1 = controller.getOneDice(1);
						dice1.setDice(number1);
					}else if(clickedDice.equals(dice2)){
						controller.cheatSelectDice(2);
						number2 = controller.getOneDice(2);
						//System.out.println(number2);
						dice2.setDice(number2);
					}
					if (controller.hasSelectDices()) {
						int[] dices = controller.getDice();
						for (JRadioButton jr : radioButtonList) {
							jr.setEnabled(true);
						}
						plus.setText(dices[0] + dices[1] + "");
						minus.setText(Math.max(dices[0], dices[1]) - Math.min(dices[0], dices[1]) + "");
						multiply.setText(dices[0] * dices[1] + "");
						if (Math.max(dices[0], dices[1]) % Math.min(dices[0], dices[1]) == 0) {
							divide.setText(Math.max(dices[0], dices[1]) / Math.min(dices[0], dices[1]) + "");
						} else {
							divide.setText("N/A");
							divide.setEnabled(false);
						} 
					}
					
				}
			}
		});
		
		radioButtonList.add(plus);
		radioButtonList.add(minus);
		radioButtonList.add(multiply);
		radioButtonList.add(divide);
		cheatMode.setFont(new Font("黑体", Font.PLAIN, 14));
		cheatMode.setContentAreaFilled(false);
		
		//作弊按钮相关
		cheatMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setCheatModeState(cheatMode.isSelected());
				RollDice.setEnabled(!cheatMode.isSelected());
			}
		});
		cheatMode.setBounds(100, 365, 88, 25);
		add(cheatMode);
		
		//跳过当前回合按
		radioButtonBack.setBounds(0, 166, 200, 120);
		
		add(radioButtonBack);
		backGround.setBounds(0, 0, 200, 400);
		backGround.setIcon(Pics.DSCBACKGROUND.getPic());
		add(backGround);
		
		
		for(JRadioButton jr : radioButtonList) {
			//jr.addActionListener(radioButtonListener);
			jr.setEnabled(false);
		}
		//按钮系列设置结束
		
		//动作 当按下掷骰子按钮时执行 1.让controller给出两个骰子随机数并用number1 number2接收 2.根据number1 number2修改两个骰子的图片、四个计算单选按钮的计算答案
		
		RollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				if (!isDuelRound) {//正常情况下
					for (InputListener listener : listenerList) {

						////System.out.println("rollDice");
						listener.rollDice();
						int[] dices = listener.getDice();
						number1 = dices[0];
						number2 = dices[1];
						dice1.setDice(number1);
						dice2.setDice(number2);
						for (JRadioButton jr : radioButtonList) {
							jr.setEnabled(true);
						}
						plus.setText(dices[0] + dices[1] + "");
						minus.setText(Math.max(dices[0], dices[1]) - Math.min(dices[0], dices[1]) + "");
						multiply.setText(dices[0] * dices[1] + "");
						if (Math.max(dices[0], dices[1]) % Math.min(dices[0], dices[1]) == 0) {
							divide.setText(Math.max(dices[0], dices[1]) / Math.min(dices[0], dices[1]) + "");
						} else {
							divide.setText("N/A");
							divide.setEnabled(false);
						}
						if (Math.max(dices[0], dices[1]) - Math.min(dices[0], dices[1]) > 0) {
							minus.setText(Math.max(dices[0], dices[1]) - Math.min(dices[0], dices[1]) + "");
						} else {
							minus.setText("N/A");
							minus.setEnabled(false);
						}

						if (Math.max(dices[0], dices[1]) * Math.min(dices[0], dices[1]) >12) {
							multiply.setText(12+"");
						}
					}
				}else {//如果在对局回合
					for(InputListener listener : listenerList) {
						listener.rollDice();
						int[] dices = listener.getDice();
						number1 = dices[0];
						number2 = dices[1];
						dice1.setDice(number1);
						dice2.setDice(number2);
						duelRollDiceNext();
					}
				}
			}
		});
		
	}
	
	public void enterDuel() {
		isDuelRound = true; 
		dice1.setDice(0);
		dice2.setDice(0);
		for(JRadioButton jr : radioButtonList) {
			jr.setEnabled(false);
			jr.setText("N/A");
		}
	}
	private boolean duelRollDiceNext() {
		controller.nextDuellist();
		if(duelDices.size() == 2) {
			controller.duelRolledDice(duelDices.get(0),duelDices.get(1));
			this.rollDiceLabel.setText("掷骰子");
			duelDices.clear();
			return true;
		}
		duelDices.add(number1+number2);
		if(duelDices.size() == 2) {
			this.rollDiceLabel.setText("结束对决");
		}
		return false;
		
	}
	public void exitDuel() {
		isDuelRound = false;
	}
	
	public void normalize() {
		//清空
		//System.out.println("DSC Normalize Start");
		dice1.setDice(0);
		dice2.setDice(0);
		for(JRadioButton jr : radioButtonList) {
			jr.setText("N/A");
			jr.setEnabled(false);
			jr.setSelected(false);
		}
		RollDice.setEnabled(true);
		//System.out.println("DSC Normalize End");
	}
	public void gameover() {
		dice1.setDice(0);
		dice2.setDice(0);
		for(JRadioButton jr : radioButtonList) {
			jr.setText("N/A");
			jr.setEnabled(false);
			jr.setSelected(false);
		}
		RollDice.setEnabled(false);
	}
	//是否选中了单选按钮
	public boolean ifSelectedDice() {
		for(JRadioButton k : radioButtonList) {
			if(k.isSelected()&&k.isEnabled()) {
				return true;
				}
			}
		return false;
		}
	// 当棋子被有效地点击了就以“D步数，骰子一号，骰子二号”的方式返回一个String交由棋子处理
	public int getCalculatedDice() {
		Integer intstep = 0;
		for(JRadioButton k : radioButtonList) {
			if(k.isSelected()) {
				//System.out.println(k.getText());
				if(!k.getText().equals("N/A")) {
				intstep = Integer.parseInt(k.getText());
				}
			}
		}
		return intstep;
		
	}

	//?废弃了
	public int getStep() {
		for(JRadioButton k : radioButtonList) {
			if(k.isSelected()&&!k.getText().equals("N/A")) {
				return Integer.parseInt(k.getText());
			}
		}
		return 0;
	}
    @Override
    public void registerListener(InputListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(InputListener listener) {
        listenerList.remove(listener);
    }
    //懒得listener了  直接引用controller
    public void setGC(GameController controller) {
    	this.controller = controller;
    }
}
