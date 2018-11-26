package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by markapptist on 2018-11-12.
 */

public class BlueRanger extends PowerRanger {

    boolean isAttacking;
    BlueRanger() {

        health = 100;

        String[] idleString = {"sprites/rangers/blue/BlueRanger_0.png", "sprites/rangers/blue/BlueRanger_1.png",
                "sprites/rangers/blue/BlueRanger_2.png", "sprites/rangers/blue/BlueRanger_3.png"};

        String[] moveString = {"sprites/rangers/blue/BlueRanger_11.png", "sprites/rangers/blue/BlueRanger_12.png",
                "sprites/rangers/blue/BlueRanger_13.png", "sprites/rangers/blue/BlueRanger_14.png","sprites/rangers/blue/BlueRanger_15.png", "sprites/rangers/blue/BlueRanger_16.png",
                "sprites/rangers/blue/BlueRanger_17.png", "sprites/rangers/blue/BlueRanger_18.png"};
        String[] moveBackString = {"sprites/rangers/blue/BlueRanger_22.png", "sprites/rangers/blue/BlueRanger_21.png",
                "sprites/rangers/blue/BlueRanger_20.png", "sprites/rangers/blue/BlueRanger_19.png"};

        String[] moveVerticalString = {"sprites/rangers/blue/BlueRanger_130.png"};

        String[] attackString = {"sprites/rangers/blue/BlueRanger_273.png", "sprites/rangers/blue/BlueRanger_274.png",
                "sprites/rangers/blue/BlueRanger_275.png"};

        String[] specialAttackString = {"sprites/rangers/blue/BlueRanger_184.png", "sprites/rangers/blue/BlueRanger_185.png",
                "sprites/rangers/blue/BlueRanger_186.png","sprites/rangers/blue/BlueRanger_188.png", "sprites/rangers/blue/BlueRanger_189.png"};

        isAttacking = false;

        idle = loadAnimationFromFiles(idleString, 0.5f, true);
        moveForward = loadAnimationFromFiles(moveString,0.25f,true);
        moveBackward = loadAnimationFromFiles(moveBackString, 0.25f, true);
        moveVertical = loadAnimationFromFiles(moveVerticalString, 0.25f, true);
        basicAttack = loadAnimationFromFiles(attackString, 0.25f, true);
        superAttack = loadAnimationFromFiles(specialAttackString, 0.25f, true);
        this.setBoundaryRectangle();

        setScale(4.0f);

        setMaxSpeed(900);

    }


    @Override
    public void act(float dt) {
        super.act(dt);

        setAcceleration(900);
    }

}
