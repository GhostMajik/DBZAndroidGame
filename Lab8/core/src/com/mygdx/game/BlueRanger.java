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

        String[] idleString = {"sprites/rangers/fighter2/vegetto_idle.png", "sprites/rangers/fighter2/vegetto_idle2.png"};

        String[] moveString = {"sprites/rangers/fighter2/vegetto_moving.png"};
        String[] moveBackString = {"sprites/rangers/fighter2/vegetto_backward.png"};

        String[] moveVerticalString = {"sprites/rangers/fighter2/vegetto_teleport1.png","sprites/rangers/fighter2/vegetto_teleport2.png"};

        String[] attackString = {"sprites/rangers/fighter2/vegetto_attack0.png","sprites/rangers/fighter2/vegetto_attack2.png","sprites/rangers/fighter2/vegetto_attack3.png"};

        String[] specialAttackString = {"sprites/rangers/fighter2/vegetto_super2.png","sprites/rangers/fighter2/vegetto_super1.png","sprites/rangers/fighter2/vegetto_super1.png","sprites/rangers/fighter2/vegetto_super2.png"};

        isAttacking = false;

        idle = loadAnimationFromFiles(idleString, 0.5f, true);
        moveForward = loadAnimationFromFiles(moveString,0.25f,true);
        moveBackward = loadAnimationFromFiles(moveBackString, 0.25f, true);
        moveVertical = loadAnimationFromFiles(moveVerticalString, 0.25f, true);
        basicAttack = loadAnimationFromFiles(attackString, 0.25f, false);
        superAttack = loadAnimationFromFiles(specialAttackString, 0.25f, false);
        setOrigin(getX()/2,getY()/2);
        this.setBoundaryRectangle();

        setScale(5.0f);

        setMaxSpeed(900);

    }


    @Override
    public void act(float dt) {
        super.act(dt);

        setAcceleration(900);
    }

}
