package com.mygdx.game;

/**
 * Created by user on 11/24/2018.
 */

public class EnemyKiBlast extends Projectile {

     boolean isAlive;
    EnemyKiBlast(){
        isAlive = true;
        String[] kiBlast = {"sprites/evilKi0.png","sprites/evilKi1.png","sprites/evilKi2.png","sprites/evilKi3.png"};
        //      String[] deathBlast = {""};

        moving = loadAnimationFromFiles(kiBlast,0.25f,true);
//        death = loadAnimationFromFiles(deathBlast, 0.5f, true);
        setScale(2.0f);
        setMaxSpeed(500);
    }



    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(300);
        accelerateAtAngle(180);
       /* if(!isAlive){
            setAnimation(death);
            setAcceleration(0);
            setSpeed(0);
        }*/
    }
}
