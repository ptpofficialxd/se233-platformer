package se233.chapter4.model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se233.chapter4.Launcher;
import se233.chapter4.view.Platform;

public class Character extends Pane {
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private Image characterImg;
    private AnimatedSprite imageView;
    private int x, y;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int yVelocity = 0;
    int xVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 7;
    int yMaxVelocity = 13;
    boolean isFalling = true;
    boolean canJump = false;
    boolean isJumping = false;
    boolean isMovingRight = false;
    boolean isMovingLeft = false;

    Logger logger = LoggerFactory.getLogger(Character.class);

    public Character(int x, int y, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey, String img , int Xacc , int yAcc , int xMaxVel , int yMaxVel) {

        this.xAcceleration = Xacc ;
        this.yAcceleration = yAcc;
        this.xMaxVelocity = xMaxVel;
        this.yMaxVelocity = yMaxVel;

        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);

        System.out.println(img);
        this.characterImg = new Image(Launcher.class.getResourceAsStream(String.format("assets/%s.png",img)));
        this.imageView = new AnimatedSprite(characterImg, 4, 4, 1, offsetX, offsetY, 16, 32);

        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }

    public void repaint() {
        moveY();
        moveX();
    }

    public void jump() {
        if (canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }

    public void checkReachHighest() {
        if (isJumping && yVelocity <= 0) {
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }

    public void checkReachFloor() {
        if (isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT) {
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }

    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
        } else if (x + this.getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int) (this.getWidth());
        }
    }

    public void moveLeft() {
        this.isMovingLeft = true;
        this.isMovingRight = false;
    }

    public void moveRight() {
        this.isMovingLeft = false;
        this.isMovingRight = true;
    }

    public void stop() {
        this.isMovingLeft = false;
        this.isMovingRight = false;
    }

    public void moveX() {
        setTranslateX(x);

        if (isMovingLeft) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xMaxVelocity + xAcceleration;
            x = x - xVelocity;
        }
        if (isMovingRight) {
            xVelocity = xVelocity >= xMaxVelocity ? xMaxVelocity : xMaxVelocity + xAcceleration;
            x = x + xVelocity;
        }

    }

    public void moveY() {
        setTranslateY(y);
        if (isFalling) {
            yVelocity = yVelocity >= yMaxVelocity ? yMaxVelocity : yMaxVelocity + yAcceleration;
            y = y + yVelocity;
        } else if (isJumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity - yAcceleration;
            y = y - yVelocity;
        }
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }

    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}", x, y, xVelocity, yVelocity);
    }
}