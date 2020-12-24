package xyz.chengzi.aeroplanechess;

import xyz.chengzi.aeroplanechess.controller.GameController;
import xyz.chengzi.aeroplanechess.util.Music_player;
import xyz.chengzi.aeroplanechess.view.GameoverFrame;
import xyz.chengzi.aeroplanechess.view.MyDiceSelectorComponent;
import xyz.chengzi.aeroplanechess.view.MyGameFrame;
import xyz.chengzi.aeroplanechess.view.StartMenu;
import javax.swing.*;
public class AeroplaneChess {//Ö÷ÓÎÏ·³ÌÐò
    public static void main(String[] args) {
        System.setProperty("sun.java2d.win.uiScaleX", "96dpi");
        System.setProperty("sun.java2d.win.uiScaleY", "96dpi");
        SwingUtilities.invokeLater(() -> {
            GameController controller = new GameController();
            StartMenu startMenu = new StartMenu(controller);
            startMenu.setVisible(true);
            Music_player.play_begin_music();
        });
    }
}