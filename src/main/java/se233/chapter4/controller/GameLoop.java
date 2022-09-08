package se233.chapter4.controller;

import se233.chapter4.model.Character;
import se233.chapter4.view.Platform;

public class GameLoop implements Runnable {

    private Platform platform ;
    private int frameRate ;
    private float interval ;
    private boolean running ;

    public GameLoop(Platform platform){
        this.platform = platform ;
        this.frameRate = 10 ;
        this.interval = 1000.0f / frameRate ;
        this.running = true ;
    }

    private void update(Character character){
        if(platform.getKeys().isPressed(character.getLeftKey())){
            character.setScaleX(-1);
            character.moveLeft() ;
            character.trace();
        }

        if(platform.getKeys().isPressed(character.getRightKey())){
            character.setScaleX(1);
            character.moveRight() ;
            character.trace();
        }

        if(!platform.getKeys().isPressed(character.getLeftKey()) && !platform.getKeys().isPressed(character.getRightKey())){
            character.stop() ;

        }

        if(platform.getKeys().isPressed(character.getLeftKey()) || platform.getKeys().isPressed(character.getRightKey())) {
            character.getImageView().tick();
        }

        if(platform.getKeys().isPressed(character.getUpKey())) {
            character.jump();
        }
    }
    @Override
    public void run() {
        while (running){
            float time = System.currentTimeMillis();
            update(platform.getPlayer1());
            update(platform.getPlayer2());
            time = System.currentTimeMillis() - time ;
            if(time < interval){
                try{
                    Thread.sleep((long)(interval-time));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }else{
                try{
                    Thread.sleep((long)(interval - (interval%time)));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }
    }
}