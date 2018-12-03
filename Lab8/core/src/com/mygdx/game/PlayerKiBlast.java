package com.mygdx.game;

/**
 * Created by user on 11/24/2018.
 */

public class PlayerKiBlast extends Projectile {

    public boolean isAlive;
    PlayerKiBlast(){
        isAlive = true;
        String[] kiBlast = {"sprites/kiblast0.png","sprites/kiblast2.png","sprites/kiblast1.png","sprites/kiblast3.png"};
  //      String[] deathBlast = {""};

        moving = loadAnimationFromFiles(kiBlast,0.25f,true);
//        death = loadAnimationFromFiles(deathBlast, 0.5f, true);
        this.setBoundaryRectangle();
        setScale(5.0f);
        setMaxSpeed(300);


    }



    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(100);
        accelerateAtAngle(0);
        if(!isAlive){
           remove();
        }
    }
}
