package xyz.chengzi.aeroplanechess.util;

public enum Music_location {
    clic("src/music/clic2.wav"),
    fly_bomb("src/music/fly_bomb.wav"),
    change_view("src/music/change_view.wav"),
    dice_row("src/music/dice_row.wav"),
    fly_pass("src/music/fly_pass.wav"),
    begin_music("src/music/begin_music.wav"),
    fight_start("src/music/fight_start.wav");
    private Music_location(String location) {
        this.locate = location;
    }

    private String locate;
    public String getMusic_location() {
        return this.locate;
    }
}