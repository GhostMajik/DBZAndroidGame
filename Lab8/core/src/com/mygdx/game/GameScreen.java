package com.mygdx.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.Timer;

/**
 * Created by markapptist on 2018-11-12.
 */

public class GameScreen extends ScreenBeta {

    BlueRanger blueRanger;
    EnemyCharacter enemyCharacter;
    PlayerKiBlast kiBlast;
    EnemyKiBlast enemyKiBlast;
    PlayerKiSuper playerKiSuper;

    Touchpad touchpad;

    Skin skin;
    Skin uiSkin;

    ActorBeta foreground;
    ActorBeta background;


    Label timerLabel;
    Music stageMusic;
    Sound kiAttackSound;
    Sound punchSound;
    int timer;

    @Override
    public void initialize() {

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("sprites/backgrounds/background0_59.png");
        foreground.setSize(WIDTH, HEIGHT);

        background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);

        stageMusic =  Gdx.audio.newMusic(Gdx.files.internal("sounds/TrunksMusic.mp3"));
        stageMusic.setVolume(0.25f);
        stageMusic.setLooping(true);
        stageMusic.play();

        kiAttackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/kiblast.mp3"));
        kiAttackSound.setVolume(1,0.4f);

        punchSound = Gdx.audio.newSound(Gdx.files.internal("sounds/punchSFX.mp3"));
        punchSound.setVolume(2,0.25f);

        skin = new Skin(Gdx.files.internal("skins/pixthulhu/skin/pixthulhu-ui.json"));
        uiSkin = new Skin(Gdx.files.internal("skins/arcade/skin/arcade-ui.json"));


        uiStage.addActor(gameTableContainer);
        //Touchpad
        touchpad = new Touchpad(40.0f, skin, "default");
        touchpad.setPosition(WIDTH / 5, HEIGHT / 3);
        touchpad.setResetOnTouchUp(true);
        touchpad.getColor().a = 1.0f;

        timer = 10800;
        timerLabel = new Label("Timer:" + timer,uiSkin);
        timerLabel.setPosition(Gdx.graphics.getWidth()/2 - timerLabel.getWidth()- 150, Gdx.graphics.getHeight() - 100);
        timerLabel.setFontScale(4.0f);
        mainStage.addActor(timerLabel);


        gameUiTable.add(touchpad).width(touchpad.getWidth() * 1.5f).height(touchpad.getHeight() * 1.5f).padRight(800).padTop(600);

        Button aButton = new Button(uiSkin, "red");
        Button bButton = new Button(uiSkin, "blue");
        aButton.getColor().a = 0.7f;
        bButton.getColor().a = 0.7f;

        gameUiTable.padRight(50).add(aButton).width(aButton.getWidth() * 2.0f).height(aButton.getHeight() * 2.0f).bottom().padRight(120);
        gameUiTable.add(bButton).width(bButton.getWidth() * 2.0f).height(bButton.getHeight() * 2.0f).bottom().padBottom(120);


        aButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(!blueRanger.isAttacking) {
                    com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                        @Override
                        public void run() {
                            blueRanger.isAttacking = false;
                        }

                    }, blueRanger.basicAttack.getAnimationDuration());
                    blueRanger.setAnimation(blueRanger.basicAttack);
                    blueRanger.isAttacking = true;
                    punchSound.play();
                    kiBlast = new PlayerKiBlast();
                    kiBlast.isAlive = true;
                    kiBlast.setAnimation(kiBlast.moving);
                    kiBlast.setPosition(blueRanger.getX() + kiBlast.getWidth() + blueRanger.getWidth() + 50,blueRanger.getY());
                    mainStage.addActor(kiBlast);
                    kiAttackSound.play();

                }
                return false;

            }
        });

        bButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                if(!blueRanger.isAttacking) {
                    com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                        @Override
                        public void run() {
                            blueRanger.isAttacking = false;
                        }

                    }, blueRanger.superAttack.getAnimationDuration());
                    blueRanger.setAnimation(blueRanger.superAttack);
                    blueRanger.isAttacking = true;
                    playerKiSuper = new PlayerKiSuper();
                    playerKiSuper.isAlive = true;
                    playerKiSuper.setAnimation(playerKiSuper.moving);
                    playerKiSuper.setPosition(blueRanger.getX() + playerKiSuper.getWidth() + blueRanger.getWidth() + 50,blueRanger.getY());
                    mainStage.addActor(playerKiSuper);
                    kiAttackSound.play();
                }
            }
        });

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float deltaX = ((Touchpad) actor).getKnobPercentX();
                float deltaY = ((Touchpad) actor).getKnobPercentY();

                Gdx.app.log("Delta X", "" + deltaX);
                Gdx.app.log("Delta Y", "" + deltaY);
            }
        });

        //CREATE BLUE RANGER
        blueRanger = new BlueRanger();
        blueRanger.setPosition(WIDTH / 2, HEIGHT / 3);

        enemyCharacter = new EnemyCharacter();
        enemyCharacter.setPosition(WIDTH - WIDTH /4, HEIGHT/3);

        mainStage.addActor(blueRanger);
        mainStage.addActor(enemyCharacter);

    }

    @Override
    public void update(float dt) {
        blueRanger.act(dt);
        touchpad.act(dt);

        timer -= dt;
        timerLabel.setText("Timer:" + timer / 60);
        Gdx.app.log("TIMER: ", Integer.toString(timer));
        if(timer <= 0){
            stageMusic.stop();
            if(blueRanger.health >= enemyCharacter.health) {
                MyGame.menuScreen = new MenuScreen();
                MyGame.setActiveScreen(MyGame.menuScreen);
            }
            //temp reload game if player loses
            else if(blueRanger.health < enemyCharacter.health){
                MyGame.gameScreen = new GameScreen();
                MyGame.setActiveScreen(MyGame.gameScreen);
            }
        }
        if(touchpad.getKnobPercentX() > 0.4 && touchpad.getKnobPercentX() < 1 && !blueRanger.isAttacking) {
            Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
            blueRanger.setAnimation(blueRanger.moveForward);
                blueRanger.moveBy(5,0);

        }

        if(touchpad.getKnobPercentY() > 0.4 && touchpad.getKnobPercentY() < 1 && !blueRanger.isAttacking) {
            Gdx.app.log("Delta Y", "Knob Y is " + touchpad.getKnobPercentX());
            blueRanger.setAnimation(blueRanger.moveVertical);
            blueRanger.moveBy(0,5);

        }
        if(touchpad.getKnobPercentX() > -1 && touchpad.getKnobPercentX() < -0.4 && !blueRanger.isAttacking) {
            Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
            blueRanger.setAnimation(blueRanger.moveBackward);
            blueRanger.moveBy(-5,0);

        }

        if(touchpad.getKnobPercentY() > -1 && touchpad.getKnobPercentY() < -0.4 && !blueRanger.isAttacking) {
            Gdx.app.log("Delta Y", "Knob Y is " + touchpad.getKnobPercentX());
            blueRanger.moveBy(0,-5);

        }

        if(touchpad.getKnobPercentX() > -0.4 && touchpad.getKnobPercentX() < 0.4 && touchpad.getKnobPercentY() > -0.4 && touchpad.getKnobPercentY() < 0.4 && !blueRanger.isAttacking) {
            Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
            blueRanger.setAnimation(blueRanger.idle);
        }

        moveAI();

        blueRanger.boundToWorld();
        enemyCharacter.boundToWorld();
    }

    //TODO, add more random logic to the AI, add decision for when projectile is WithinDistance X to move or stay, randomize decision to counter super attack with super, move or idle.
    void moveAI(){
        //redo this super attack if statement if given time
      /*  if(enemyCharacter.getY() == blueRanger.getY()){
            if(!enemyCharacter.isAttacking) {
                enemyCharacter.isAttacking = true;
                enemyCharacter.setAnimation(enemyCharacter.superAttack);
                com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                    @Override
                    public void run() {
                        enemyCharacter.isAttacking = false;
                    }
                }, 5f);

            }
        }*/
        if(enemyCharacter.isWithinDistance( 300 + blueRanger.getWidth(),blueRanger)){
            enemyCharacter.moveBy(2,0);
            enemyCharacter.setAnimation(enemyCharacter.moveBackward);
        }
        //can expand this to have a better attack targeting approach for the AI, should be closer on Y before attacking
        else if(enemyCharacter.isWithinDistance(WIDTH/3 + blueRanger.getWidth(), blueRanger) || enemyCharacter.isWithinDistance( -WIDTH/3 - blueRanger.getWidth(), blueRanger)){
            if(blueRanger.getY() > enemyCharacter.getY() + 50){
                enemyCharacter.moveBy(0,5);
                enemyCharacter.setAnimation(enemyCharacter.moveVertical);
            }
            if(blueRanger.getY() < enemyCharacter.getY() - 50){
                enemyCharacter.moveBy(0,-5);
                enemyCharacter.setAnimation(enemyCharacter.moveForward);
               // enemyCharacter.setAnimation(enemyCharacter.moveVertical);
            }
            else if(!enemyCharacter.isAttacking) {
                enemyCharacter.isAttacking = true;
                enemyCharacter.setAnimation(enemyCharacter.basicAttack);
                //spawn ki blast
                enemyKiBlast = new EnemyKiBlast();
                enemyKiBlast.isAlive = true;
                enemyKiBlast.setAnimation(enemyKiBlast.moving);

                enemyKiBlast.setPosition(enemyCharacter.getX(), enemyCharacter.getY());
                mainStage.addActor(enemyKiBlast);
                //delay between attack, should randomzie delay for better AI gameplay
                com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                    @Override
                    public void run() {
                        enemyCharacter.isAttacking = false;
                    }
                },1.5f);
            }
        }
        //can fill this out with a more complex if statements or ai states to enchance AI movement
        else if(enemyCharacter.isWithinDistance(WIDTH, blueRanger) || enemyCharacter.isWithinDistance(-WIDTH, blueRanger)){
            if(blueRanger.getY() > enemyCharacter.getY()){
                enemyCharacter.moveBy(0,10);
                enemyCharacter.setAnimation(enemyCharacter.moveVertical);
            }
            if(blueRanger.getY() < enemyCharacter.getY()){
                enemyCharacter.moveBy(0,-10);
                enemyCharacter.setAnimation(enemyCharacter.moveVertical);
            }
            if(blueRanger.getX() < enemyCharacter.getX() && enemyCharacter.getX() > WIDTH/2){
                enemyCharacter.moveBy(-2,0);
                enemyCharacter.setAnimation(enemyCharacter.moveForward);
            }

            if(blueRanger.getX() > enemyCharacter.getX()){
                enemyCharacter.moveBy(2,0);
                enemyCharacter.setAnimation(enemyCharacter.moveBackward);
            }
        }
        else if(!enemyCharacter.isWithinDistance(800,blueRanger)){
            enemyCharacter.setAnimation(enemyCharacter.idle);
        }
    }


}
