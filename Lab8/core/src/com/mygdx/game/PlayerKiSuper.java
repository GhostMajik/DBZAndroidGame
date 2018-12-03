package com.mygdx.game;

/**
 * Created by user on 11/27/2018.
 */

public class PlayerKiSuper extends Projectile {

    public boolean isAlive;
    PlayerKiSuper(){
        isAlive = true;
        String[] superKi = {"sprites/superKiBlast1.png","sprites/superKiBlast2.png","sprites/kiblast1.png","sprites/superKiBlast3.png"};
        //      String[] deathBlast = {""};

        moving = loadAnimationFromFiles(superKi,0.25f,true);
//        death = loadAnimationFromFiles(deathBlast, 0.5f, true);
        this.setBoundaryRectangle();
        setScale(5.0f);
        setMaxSpeed(200);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        setAcceleration(200);
        accelerateAtAngle(0);
       if(!isAlive){
            remove();
        }
    }

}
