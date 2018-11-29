package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;

/**
 * Created by markapptist on 2018-11-12.
 */

public class MenuScreen extends ScreenBeta {

    TextButton startButton;
    TextButton helpButton;
    TextButton exitButton;

    Label label;
    Music menuMusic;

    FireParticle fire;


    @Override
    public void initialize() {

        uiTable.background(skin.getDrawable("window-c"));
        uiTable.setColor(0,0,0,0.6f);

        uiStage.addActor(tableContainer);
        ActorBeta backGround = new ActorBeta(0,0,mainStage);
        backGround.loadTexture("sprites/backgrounds/dbztitlescreen.png");
        backGround.setSize(WIDTH,HEIGHT);
        backGround.setOpacity(1);

        startButton = new TextButton("Start", skin.get(("default"), TextButton.TextButtonStyle.class));
        startButton.setOrigin(Align.center);
        startButton.setTransform(true);
        startButton.setScale(3);

        helpButton = new TextButton("Help", skin.get(("default"), TextButton.TextButtonStyle.class));
        helpButton.setOrigin(Align.center);
        helpButton.setTransform(true);
        helpButton.setScale(3);

        exitButton = new TextButton("Exit", skin.get(("default"), TextButton.TextButtonStyle.class));
        exitButton.setOrigin(Align.center);
        exitButton.setTransform(true);
        exitButton.setScale(3);

        setUpButtons();

        label = new Label("LABEL", labelStyle);
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/menuTheme.mp3"));
        menuMusic.setVolume(0.25f);
        menuMusic.setLooping(true);
        menuMusic.play();
        //Add to TABLE

        uiTable.row().padTop(HEIGHT /20).padBottom(HEIGHT / 20);
        uiTable.add(startButton).size(startButton.getWidth(), startButton.getHeight()).expandX();


        /**PARTICLE EFFECTS**/
        fire = new FireParticle();
        fire.centerAtActor(startButton);
        fire.start();
        fire.setPosition(WIDTH / 2, HEIGHT / 2);
        fire.setScale(2.0f);

        mainStage.addActor(fire);
    }

    public void setUpButtons() {

        startButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                menuMusic.stop();
                MyGame.storyScreen = new StoryScreen();
                MyGame.setActiveScreen(MyGame.storyScreen);

            }
        });
    }

    @Override
    public void render(float delta) {super.render(delta);}

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void update(float dt) {fire.act(dt);}
}
