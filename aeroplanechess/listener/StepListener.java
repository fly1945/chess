package xyz.chengzi.aeroplanechess.listener;

import xyz.chengzi.aeroplanechess.view.MyChessComponent;

public interface StepListener extends Listener{
	String stepped(MyChessComponent chess);
}
