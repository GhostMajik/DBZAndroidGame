package com.mygdx.game;

/**
 * Created by user on 11/24/2018.
 */

public class EnemyCharacter extends  PowerRanger {

    public boolean isAttacking;
    EnemyCharacter(){

        health = 1;

        String[] idleString = {"sprites/rangers/enemyfighter/gogetta_idle.png", "sprites/rangers/enemyfighter/gogetta_idle2.png"};

        String[] moveString = {"sprites/rangers/enemyfighter/gogetta_forward.png","sprites/rangers/enemyfighter/gogetta_forward2.png"};
        String[] moveBackString = {"sprites/rangers/enemyfighter/gogetta_backward.png"};

        String[] moveVerticalString = {"sprites/rangers/enemyfighter/gogetta_teleport1.png","sprites/rangers/enemyfighter/gogetta_teleport2.png"};

        String[] attackString = {"sprites/rangers/enemyfighter/gogetta_attack1.png","sprites/rangers/enemyfighter/gogetta_attack2.png"};

        String[] specialAttackString = {"sprites/rangers/enemyfighter/gogetta_super1.png","sprites/rangers/enemyfighter/gogetta_super2.png"};

        isAttacking = false;

        idle = loadAnimationFromFiles(idleString, 0.5f, true);
        moveForward = loadAnimationFromFiles(moveString,0.5f,true);
        moveBackward = loadAnimationFromFiles(moveBackString, 0.25f, true);
        moveVertical = loadAnimationFromFiles(moveVerticalString, 0.25f, true);
        basicAttack = loadAnimationFromFiles(attackString, 0.25f, false);
        superAttack = loadAnimationFromFiles(specialAttackString, 0.25f, false);
        setOrigin(getX()/2,getY()/2);
        this.setBoundaryRectangle();

        setScale(4.0f);
        setMaxSpeed(500);

    }

    @Override
    public void act(float dt) {
        super.act(dt);

        setAcceleration(900);
    }
}
