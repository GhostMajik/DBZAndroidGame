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
        setScale(5.0f);
        setMaxSpeed(500);


    }

    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(300);
        accelerateAtAngle(0);
       /* if(!isAlive){
            setAnimation(death);
            setAcceleration(0);
            setSpeed(0);
        }*/
    }
}
