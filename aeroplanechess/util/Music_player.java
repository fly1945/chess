package xyz.chengzi.aeroplanechess.util;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;


public class Music_player{
    static boolean backGround_open_if=true;
    static boolean acoustics_open_if=true;
    static AudioStream backGround_au;
    static  FileInputStream backGround;
    public static void setBackGround_open_if() {
        if(backGround_open_if){
            stop_begin_music();
            backGround_open_if=false;
        }else {
            play_begin_music();
            backGround_open_if=true;
        }
    }

    public static void setAcoustics_open_if() {
        if(acoustics_open_if){

            acoustics_open_if=false;
        }else {
            acoustics_open_if=true;
        }
        System.out.println("acoustics_open_if"+acoustics_open_if);
    }

    public static void main(String[] args) {
        play_dice_row();
    }
    private static Boolean check_acoustics_open_if(){
        if(acoustics_open_if){
            return true;
        }
        return false;
    }
    public static void play_clic(){
        if(!check_acoustics_open_if()){
            return;
        }
        try{
            FileInputStream file=new FileInputStream(Music_location.clic.getMusic_location());
            AudioStream as=new AudioStream(file);
            AudioPlayer.player.start(as);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void play_dice_row(){
        if(!check_acoustics_open_if()){
            return;
        }
        try{
            FileInputStream file=new FileInputStream(Music_location.dice_row.getMusic_location());
            AudioStream as=new AudioStream(file);
            AudioPlayer.player.start(as);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void play_fight_start(){
        if(!check_acoustics_open_if()){
            return;
        }
        try{
            FileInputStream file=new FileInputStream(Music_location.fight_start.getMusic_location());
            AudioStream as=new AudioStream(file);
            AudioPlayer.player.start(as);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void stop_begin_music(){
        if(!check_acoustics_open_if()){
            return;
        }
        try{
            AudioPlayer.player.stop(backGround_au);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void play_begin_music(){
        if(!check_acoustics_open_if()){
            return;
        }
        try{
            backGround=new FileInputStream(Music_location.begin_music.getMusic_location());
            backGround_au=new AudioStream(backGround);
            AudioPlayer.player.start(backGround_au);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void play_fly_bomb(){
        if(!check_acoustics_open_if()){
            return;
        }
        try{
            FileInputStream file=new FileInputStream(Music_location.fly_bomb.getMusic_location());
            AudioStream as=new AudioStream(file);
            AudioPlayer.player.start(as);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void play_change_view(){
        if(!check_acoustics_open_if()){
            return;
        }
        try{
            FileInputStream file=new FileInputStream(Music_location.change_view.getMusic_location());
            AudioStream as=new AudioStream(file);
            AudioPlayer.player.start(as);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
