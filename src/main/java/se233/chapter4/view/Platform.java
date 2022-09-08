package se233.chapter4.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import se233.chapter4.Launcher;
import se233.chapter4.model.Character;
import se233.chapter4.model.Keys;

public class Platform extends Pane {
    public static final  int WIDTH = 800 ;
    private Keys keys ;
    public static final int HEIGHT = 400 ;
    public static final int GROUND = 300 ;
    private Image platformImg ;
    private Character player1 ;
    private Character player2 ;

    public Platform() {
        this.keys = new Keys() ;
        platformImg = new Image(Launcher.class.getResourceAsStream("assets/Background.png")) ;
        ImageView backgroundImg = new ImageView(platformImg) ;
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        player1 = new Character(30,30,0,0, KeyCode.A , KeyCode.D,KeyCode.W, "MarioSheet",1,1,7,17) ;
        player2 = new Character(0,30,0,48, KeyCode.LEFT , KeyCode.RIGHT,KeyCode.UP, "MegamanSheet",1,1,5,19) ;

        this.getChildren().addAll(backgroundImg ,player1, player2);
    }

    public Character getPlayer1()  {
        return  this.player1 ;
    }
    public Character getPlayer2() { return  this.player2 ;}
    public Keys getKeys() {
        return keys ;
    }

}