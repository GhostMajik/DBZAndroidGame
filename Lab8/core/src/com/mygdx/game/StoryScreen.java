package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by markapptist on 2018-11-16.
 */

public class StoryScreen extends ScreenBeta {

    Scene scene;
    ActorBeta continueKey;
    DialogBox text_box_1;
    String text_box_1_string;

    public void initialize() {
        ActorBeta background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("sprites/backgrounds/background0_59.png");
        background.setSize(WIDTH, HEIGHT);
        background.setOpacity(1);

        text_box_1 = new DialogBox(0,0,mainStage){
            @Override
            public void setText(String text) {
                super.dialogLabel = new Label("",skin, "default");
                super.setText("TEXT_BOX_1_DIALOGBOX");

            }
        };
       // text_box_1.setText("TEXT_BOX_1_DIALOGBOX");


        mainStage.addActor(text_box_1);


    }

    @Override
    public void update(float dt) {

    }
}
