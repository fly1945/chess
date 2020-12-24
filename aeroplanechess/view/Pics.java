package xyz.chengzi.aeroplanechess.view;

import javax.swing.ImageIcon;
import java.io.Serializable;

public enum Pics implements Serializable {

	ONE("src/images/dice-six-faces-one.png"),TWO("src/images/dice-six-faces-two.png"),THREE("src/images/dice-six-faces-three.png"),FOUR("src/images/dice-six-faces-four.png"),FIVE("src/images/dice-six-faces-five.png"),SIX("src/images/dice-six-faces-six.png"),DEFAULTDICE("src/images/perspective-dice-six-faces-five.png"), 
	REDPLANE("src/images/ChessRed.png"),BLUEPLANE("src/images/ChessBlue.png"),GREENPLANE("src/images/ChessGreen.png"),YELLOWPLANE("src/images/ChessYellow.png"),
	TEST("src/images/testChess.png"),RESTART("src/images/clockwise-rotation.png"),QUIT("src/images/exit-door.png"),SAVE("src/images/save.png"),ROLLDICE("src/images/rolling-dices.png"),
	CHESSBOARD("src/images/chessBoard.png"),
	NEXTPLANEBLUE("src/images/NextJet-Blue.png"),NEXTPLANEGREEN("src/images/NextJet-Green.png"),NEXTPLANEYELLOW("src/images/NextJet-Yellow.png"),NEXTPLANERED("src/images/NextJet-Red.png"),
	LANDEDPLANE("src/images/chess-queen (11).png"),LANDEDBLUE("src/images/LandedBlue.png"),LANDEDGREEN("src/images/LandedGreen.png"),LANDEDYELLOW("src/images/LandedYellow.png"),LANDEDRED("src/images/LandedRed.png"),
	BLUEPILOT("src/images/BluePilot.png"),REDPILOT("src/images/RedPilot.png"),YELLOWPILOT("src/images/YellowPilot.png"),GREENPILOT("src/images/GreenPilot.png"),
	ARROW("src/images/plain-arrow.png"),
	RED2("src/images/MultiChess/Red2.png"),RED3("src/images/MultiChess/Red3.png"),RED4("src/images/MultiChess/Red4.png"),
	BLUE2("src/images/MultiChess/Blue2.png"),BLUE3("src/images/MultiChess/Blue3.png"),BLUE4("src/images/MultiChess/Blue4.png"),
	GREEN2("src/images/MultiChess/Green2.png"),GREEN3("src/images/MultiChess/Green3.png"),GREEN4("src/images/MultiChess/Green4.png"),
	YELLOW2("src/images/MultiChess/Yellow2.png"),YELLOW3("src/images/MultiChess/Yellow3.png"),YELLOW4("src/images/MultiChess/Yellow4.png"),
	ROCKET("src/images/rocket.png"),SWORDS("src/images/swords.png"),
	FIRST("src/images/GoldMedal.png"),SECOND("src/images/SilverMedal.png"),THIRD("src/images/CopporMedal.png"),FORTH("src/images/IronMedal.png"),
	BACKGROUND("src/images/background.png"),
	BUTTON1("src/images/Button1.png"),BUTTON2("src/images/Button2.png"),BUTTON3("src/images/Button3.png"),
	RADIOBUTTON1("src/images/radioButton1.png"),RADIOBUTTON2("src/images/radioButton2.png"),RADIOBUTTON3("src/images/radioButton3.png"),RADIOBUTTON4("src/images/radioButton4.png"),
	RADIOBUTTONBACKGROUND("src/images/radioButtons.png"),DSCBACKGROUND("src/images/DSCBackground.png"),STATUSBACKGROUND("src/images/StatusBackground.png"),
	TITLE("src/images/title.png"),
	BEGIN_MUSIC("src/images/begin_music_pic.png"),
	ACOUSTIC_MUSIC("src/images/acoustic_music_pic.png")
	;
	public  static final long serialVersionUID = 11L;
	
	final private ImageIcon icon;
	private Pics(String target) {
		this.icon = new ImageIcon(target);
	}
	public ImageIcon getPic() {
		return this.icon;
	}
}
