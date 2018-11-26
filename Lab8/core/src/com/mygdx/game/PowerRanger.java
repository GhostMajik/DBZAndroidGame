package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by markapptist on 2018-11-12.
 */

public class PowerRanger extends ActorBeta {

    Animation<TextureRegion> idle;
    Animation<TextureRegion> moveForward;
    Animation<TextureRegion> moveBackward;
    Animation<TextureRegion> moveVertical;
    Animation<TextureRegion> basicAttack;
    Animation<TextureRegion> superAttack;
    Animation<TextureRegion> powerUp;
    int health;

    PowerRanger() {

    }

    @Override
    public void act(float dt) {
        super.act(dt);

     //   setAcceleration(900);
     //   accelerateAtAngle(270);
      //  applyPhysics(dt);
    }
}
