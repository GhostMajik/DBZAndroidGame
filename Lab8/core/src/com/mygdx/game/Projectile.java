package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by user on 11/24/2018.
 */

public class Projectile extends ActorBeta{

    Animation<TextureRegion> startUp;
    Animation<TextureRegion> moving;
    Animation<TextureRegion> death;

    Projectile(){
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
        setAcceleration(300);
    }
}
