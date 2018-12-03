package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by markapptist on 2018-11-16.
 */

public class StoryScreen extends ScreenBeta {

    DialogBox text_box_1;
    String text_box_string_1,text_box_string_2,text_box_string_3,text_box_string_4,text_box_string_5,text_box_string_6;
    private int textCount;
    private ActorBeta playerProfile;
    private ActorBeta enemyProfile;
    Music storyMusic;

    public void initialize() {
        ActorBeta background = new ActorBeta(0, 0, mainStage);
        background.loadTexture("sprites/backgrounds/background0_59.png");
        background.setSize(WIDTH, HEIGHT);
        background.setOpacity(1);

        playerProfile = new ActorBeta(0,0,mainStage);
        playerProfile.loadTexture("sprites/rangers/fighter2/vegetto_profile.png");
        playerProfile.setSize(WIDTH/3, HEIGHT/2);
        playerProfile.setPosition(0,HEIGHT/2);

        enemyProfile = new ActorBeta(0,0,mainStage);
        enemyProfile.loadTexture("sprites/rangers/enemyfighter/gogetta_profile.png");
        enemyProfile.setSize(-WIDTH/3, HEIGHT/2);
        enemyProfile.setPosition(WIDTH,HEIGHT/2);

        storyMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/dialogue_music.mp3"));
        storyMusic.setVolume(0.5f);
        storyMusic.setPosition(2.5f);
        storyMusic.play();

        textCount = 0;

        text_box_1 = new DialogBox(0,0,mainStage);
        text_box_1.setDialogSize(WIDTH,HEIGHT/3);
        text_box_1.setFontScale(5.0f);
        text_box_1.alignCenter();
        text_box_1.setText("");
        text_box_1.setBackgroundColor(Color.WHITE);

        text_box_string_1 = "Hmm, another strong power level, who are you?";
        text_box_string_2 = "We are Gogeta, fusion of Goku and Vegetta.";
        text_box_string_3 = "What? Thats who we are. Although our name, is Vegetto.";
        text_box_string_4 = "Interesting, so you must be us, from another universe.";
        text_box_string_5 = "Oh boy, I can't wait to find out how strong you are.";
        text_box_string_6 = "Hmmmph, you'll see soon enough.";


        mainStage.addActor(text_box_1);
        mainStage.addActor(text_box_1.dialogLabel);
        mainStage.addActor(playerProfile);
        mainStage.addActor(enemyProfile);


    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        textCount++;
        return super.touchDown(screenX, screenY, pointer, button);

    }

    @Override
    public void update(float dt) {
        switch(textCount){

            case 0:
                text_box_1.setText(text_box_string_1);
                text_box_1.setFontColor(Color.BLACK);
                playerProfile.setOpacity(1);
                enemyProfile.setOpacity(0.5f);
                break;
            case 1:
                text_box_1.setText(text_box_string_2);
                text_box_1.setFontColor(Color.YELLOW);
                playerProfile.setOpacity(0.5f);
                enemyProfile.setOpacity(1);
                break;
            case 2:
                text_box_1.setText(text_box_string_3);
                text_box_1.setFontColor(Color.BLACK);
                playerProfile.setOpacity(1);
                enemyProfile.setOpacity(0.5f);

                break;
            case 3:
                text_box_1.setText(text_box_string_4);
                text_box_1.setFontColor(Color.YELLOW);
                playerProfile.setOpacity(0.5f);
                enemyProfile.setOpacity(1);
                break;
            case 4:
                text_box_1.setText(text_box_string_5);
                text_box_1.setFontColor(Color.BLACK);
                playerProfile.setOpacity(1);
                enemyProfile.setOpacity(0.5f);
                break;
            case 5:
                text_box_1.setText(text_box_string_6);
                text_box_1.setFontColor(Color.YELLOW);
                playerProfile.setOpacity(0.5f);
                enemyProfile.setOpacity(1);
                break;
            case 6:
                storyMusic.stop();
                MyGame.gameScreen = new GameScreen();
                MyGame.setActiveScreen(MyGame.gameScreen);

        }
    }
}
