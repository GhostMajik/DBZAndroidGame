package com.mygdx.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

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

    ActorBeta gogetaProfile;
    ActorBeta vegettoProfile;

    ActorBeta vegetto_backgroundHP;
    ActorBeta vegetto_hpBar;

    ActorBeta gogeta_backgroundHP;
    ActorBeta gogeta_hpBar;


    Label timerLabel;
    Label gameOverLabel;

    TextButton replayButton;
    TextButton menuButton;

    Music stageMusic;
    Sound kiAttackSound;
    Sound punchSound;
    Sound hitSound;
    int timer;
    float gogetaHP,vegettoHP;
    boolean canDamagePlayer, canDamageAI;
    String winningPlayer;

    @Override
    public void initialize() {

        canDamagePlayer = true;
        canDamageAI = true;

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);
        skin = new Skin(Gdx.files.internal("skins/pixthulhu/skin/pixthulhu-ui.json"));
        uiSkin = new Skin(Gdx.files.internal("skins/arcade/skin/arcade-ui.json"));

        foreground = new ActorBeta(0, 0, mainStage);
        foreground.loadTexture("sprites/backgrounds/background0_59.png");
        foreground.setSize(WIDTH, HEIGHT);

        background = new ActorBeta(900, 300, mainStage);
        background.loadTexture("sprites/backgrounds/background0_20.png");
        background.setScale(2.0f);
        winningPlayer = "1";

        gameOverLabel = new Label("Game Over" + "\n" + winningPlayer + " WINS!", uiSkin);
        gameOverLabel.setAlignment(Align.center);
        gameOverLabel.setPosition(WIDTH/2 - gameOverLabel.getWidth() + 100,HEIGHT/2 + 200);
        gameOverLabel.setFontScale(3.0f);
        gameOverLabel.setVisible(false);

        replayButton = new TextButton("Replay", skin.get(("default"), TextButton.TextButtonStyle.class));
        replayButton.setOrigin(Align.center);
        replayButton.setPosition(WIDTH/2 - replayButton.getWidth() + 112.5f, HEIGHT/2 - 100);
        replayButton.setScale(3);
        replayButton.setVisible(false);
        replayButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                stageMusic.stop();
                MyGame.gameScreen = new GameScreen();
                MyGame.setActiveScreen(MyGame.gameScreen);

            }
        });

        menuButton = new TextButton("Menu", skin.get(("default"), TextButton.TextButtonStyle.class));
        menuButton.setOrigin(Align.center);
        menuButton.setPosition(WIDTH/2 - menuButton.getWidth() + 100,HEIGHT/4);
        menuButton.setScale(3);
        menuButton.setVisible(false);
        menuButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                stageMusic.stop();
                MyGame.menuScreen = new MenuScreen();
                MyGame.setActiveScreen(MyGame.menuScreen);

            }
        });

        mainStage.addActor(gameOverLabel);
        mainStage.addActor(replayButton);
        mainStage.addActor(menuButton);

        gogetaHP = 1.0f;
        vegettoHP = 1.0f;

        gogetaProfile = new ActorBeta(0,0,mainStage);
        gogetaProfile.loadTexture("sprites/rangers/enemyfighter/gogetta_profile.png");
        gogetaProfile.setScale(3.0f);
        gogetaProfile.setPosition(WIDTH - 75, HEIGHT - 100);

        gogeta_backgroundHP = new ActorBeta(0,0, mainStage);
        gogeta_backgroundHP.loadTexture("sprites/hpBarBG.png");
        gogeta_backgroundHP.setOrigin(gogeta_backgroundHP.getX()/2,gogeta_backgroundHP.getY()/2);
        gogeta_backgroundHP.setPosition(WIDTH/2 + 250, HEIGHT- 100);
        gogeta_backgroundHP.setScale(1.5f,1.0f);

        gogeta_hpBar = new ActorBeta(0,0, mainStage);
        gogeta_hpBar.loadTexture("sprites/hpBar.png");
        gogeta_hpBar.setOrigin(gogeta_hpBar.getX()/2,gogeta_hpBar.getY()/2);
        gogeta_hpBar.setPosition(WIDTH/2 + 250, HEIGHT- 100);
        gogeta_hpBar.setScale(1.5f * gogetaHP,1.0f);

        vegettoProfile = new ActorBeta(0,0,mainStage);
        vegettoProfile.loadTexture("sprites/rangers/fighter2/vegetto_profile.png");
        vegettoProfile.setScale(4.0f);
        vegettoProfile.setPosition(50, HEIGHT - 100);

        vegetto_backgroundHP = new ActorBeta(0,0, mainStage);
        vegetto_backgroundHP.loadTexture("sprites/hpBarBG.png");
        vegetto_backgroundHP.setOrigin(vegetto_backgroundHP.getX()/2,vegetto_backgroundHP.getY()/2);
        vegetto_backgroundHP.setPosition(150, HEIGHT - 100);
        vegetto_backgroundHP.setScale(1.5f,1.0f);

        vegetto_hpBar = new ActorBeta(0,0, mainStage);
        vegetto_hpBar.loadTexture("sprites/hpBar.png");
        vegetto_hpBar.setOrigin(vegetto_hpBar.getX()/2,vegetto_hpBar.getY()/2);
        vegetto_hpBar.setPosition(150, HEIGHT - 100);
        vegetto_hpBar.setScale(1.5f * vegettoHP,1.0f);

        mainStage.addActor(gogetaProfile);
        mainStage.addActor(vegettoProfile);
        mainStage.addActor(vegetto_hpBar);
        mainStage.addActor(gogeta_hpBar);

        stageMusic =  Gdx.audio.newMusic(Gdx.files.internal("sounds/TrunksMusic.mp3"));
        stageMusic.setVolume(0.15f);
        stageMusic.setLooping(true);
        stageMusic.play();

        kiAttackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/kiblast.mp3"));
        kiAttackSound.setVolume(1,0.25f);

        punchSound = Gdx.audio.newSound(Gdx.files.internal("sounds/punchSFX.mp3"));
        punchSound.setVolume(2,0.5f);

        hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hitSound.mp3"));
        hitSound.setVolume(3,0.5f);


        uiStage.addActor(gameTableContainer);
        //Touchpad
        touchpad = new Touchpad(40.0f, skin, "default");
        touchpad.setPosition(WIDTH / 5, HEIGHT / 3);
        touchpad.setResetOnTouchUp(true);
        touchpad.getColor().a = 1.0f;

        timer = 10800;
        timerLabel = new Label("Timer:" + timer,uiSkin);
        timerLabel.setPosition(Gdx.graphics.getWidth()/2 - timerLabel.getWidth()-25, Gdx.graphics.getHeight() - 100);
        timerLabel.setFontScale(3.0f);
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
                if(!blueRanger.isAttacking && isPaused == false) {
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
                    kiBlast.setOrigin(kiBlast.getX()/2,kiBlast.getY()/2);
                    kiBlast.isAlive = true;
                    kiBlast.setAnimation(kiBlast.moving);
                    kiBlast.setPosition(blueRanger.getX() + kiBlast.getWidth() + blueRanger.getWidth() + 50,blueRanger.getY());
                    mainStage.addActor(kiBlast);
                    punchSound.play();

                }
                return false;

            }
        });

        bButton.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);

                if(!blueRanger.isAttacking && isPaused == false) {
                    vegettoHP -= 0.25f;
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
                    playerKiSuper.setOrigin(playerKiSuper.getX()/2,playerKiSuper.getY()/2);
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

        //CREATE FIGHTERS
        blueRanger = new BlueRanger();
        blueRanger.setPosition(WIDTH / 4, HEIGHT / 3);

        enemyCharacter = new EnemyCharacter();
        enemyCharacter.setPosition(WIDTH - WIDTH /4, HEIGHT/3);

        mainStage.addActor(blueRanger);
        mainStage.addActor(enemyCharacter);

    }

    @Override
    public void update(float dt) {
        blueRanger.act(dt);
        touchpad.act(dt);
        mainStage.act();
        vegetto_hpBar.setScale(1.5f * vegettoHP,1.0f);
        gogeta_hpBar.setScale(1.5f * gogetaHP,1.0f);

        for(Actor actors : mainStage.getActors()){
            if(actors.getX() > WIDTH && actors.getX() < 0 - actors.getWidth()){
                actors.remove();
            }
        }

        timer -= dt;
        timerLabel.setText("Timer:" + timer / 60);
        Gdx.app.log("TIMER: ", Integer.toString(timer));
        if(timer <= 0){
            stageMusic.stop();
            if(vegettoHP >= gogetaHP) {
                winningPlayer = "Vegetto";
                gameOverLabel.setText("Game Over" + "\n" + winningPlayer + " WINS!");
                gameOverLabel.setVisible(true);
                menuButton.setVisible(true);
                replayButton.setVisible(true);
            }
            //temp reload game if player loses
            else if(vegettoHP < gogetaHP){
                winningPlayer = "Gogeta";
                gameOverLabel.setText("Game Over" + "\n" + winningPlayer + " WINS!");
                gameOverLabel.setVisible(true);
                menuButton.setVisible(true);
                replayButton.setVisible(true);
            }
        }
        //load game over screen here
        if(vegettoHP <= 0) {
            vegettoHP = 0;
            stageMusic.stop();
            winningPlayer = "Gogeta";
            this.isPaused = true;
            gameOverLabel.setText("Game Over" + "\n" + winningPlayer + " WINS!");
            gameOverLabel.setVisible(true);
            menuButton.setVisible(true);
            replayButton.setVisible(true);
        }
        if(gogetaHP <= 0) {
            gogetaHP = 0;
            stageMusic.stop();
            winningPlayer = "Vegetto";
            this.isPaused = true;
            gameOverLabel.setText("Game Over" + "\n" + winningPlayer + " WINS!");
            gameOverLabel.setVisible(true);
            menuButton.setVisible(true);
            replayButton.setVisible(true);
        }
        if(timer > 0 && vegettoHP > 0 && gogetaHP > 0) {
            if (touchpad.getKnobPercentX() > 0.4 && touchpad.getKnobPercentX() < 1 && !blueRanger.isAttacking) {
                Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
                blueRanger.setAnimation(blueRanger.moveForward);
                blueRanger.moveBy(5, 0);

            }

            if (touchpad.getKnobPercentY() > 0.4 && touchpad.getKnobPercentY() < 1 && !blueRanger.isAttacking) {
                Gdx.app.log("Delta Y", "Knob Y is " + touchpad.getKnobPercentX());
                blueRanger.setAnimation(blueRanger.moveVertical);
                blueRanger.moveBy(0, 5);

            }
            if (touchpad.getKnobPercentX() > -1 && touchpad.getKnobPercentX() < -0.4 && !blueRanger.isAttacking) {
                Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
                blueRanger.setAnimation(blueRanger.moveBackward);
                blueRanger.moveBy(-5, 0);

            }

            if (touchpad.getKnobPercentY() > -1 && touchpad.getKnobPercentY() < -0.4 && !blueRanger.isAttacking) {
                Gdx.app.log("Delta Y", "Knob Y is " + touchpad.getKnobPercentX());
                blueRanger.moveBy(0, -5);

            }

            if (touchpad.getKnobPercentX() > -0.4 && touchpad.getKnobPercentX() < 0.4 && touchpad.getKnobPercentY() > -0.4 && touchpad.getKnobPercentY() < 0.4 && !blueRanger.isAttacking) {
                Gdx.app.log("Delta X", "Knob X is " + touchpad.getKnobPercentX());
                blueRanger.setAnimation(blueRanger.idle);
            }


            MoveAI();
            // CheckKiHit();
            if (kiBlast != null) {
                if (kiBlast.overlaps(enemyCharacter)) {
                    kiBlast.preventOverlap(enemyCharacter);
                    kiBlast.remove();
                    kiBlast.isAlive = false;
                    if (canDamageAI) {
                        canDamageAI = false;
                        gogetaHP -= 0.1f;
                        hitSound.play();
                        Gdx.app.log("ENEMY HIT:", "HP" + Float.toString(gogetaHP));
                        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                            @Override
                            public void run() {
                                canDamageAI = true;
                            }
                        }, 0.75f);
                    }
                }
                if(enemyKiBlast != null) {
                    if (kiBlast.overlaps(enemyKiBlast)) {
                        kiBlast.preventOverlap(enemyKiBlast);
                        kiBlast.remove();
                        kiBlast.isAlive = false;
                        enemyKiBlast.remove();
                        enemyKiBlast.isAlive = false;
                        //spawn explosion here from projectile death
                    }
                }
            }
            //enemy normal attack
            if (enemyKiBlast != null) {
                if (enemyKiBlast.overlaps(blueRanger)) {
                    enemyKiBlast.preventOverlap(blueRanger);
                    enemyKiBlast.remove();
                    if (canDamagePlayer) {
                        canDamagePlayer = false;
                        vegettoHP -= 0.1f;
                        Gdx.app.log("Ranger HIT:", "HIT" + Float.toString(vegettoHP));
                        hitSound.play();
                        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                            @Override
                            public void run() {
                                canDamagePlayer = true;
                            }
                        }, 0.75f);
                    }
                }
                if (kiBlast != null && enemyKiBlast.overlaps(kiBlast)) {
                    enemyKiBlast.preventOverlap(kiBlast);
                    enemyKiBlast.remove();
                    kiBlast.remove();
                }
            }
            //player super attack
            if (playerKiSuper != null) {
                if (playerKiSuper.overlaps(enemyCharacter)) {
                    playerKiSuper.preventOverlap(enemyCharacter);
                    playerKiSuper.remove();
                    playerKiSuper.isAlive = false;
                    if (canDamageAI) {
                        canDamageAI = false;
                        gogetaHP -= 0.5f;
                        hitSound.play();
                        Gdx.app.log("ENEMY HIT:", "HP" + Float.toString(gogetaHP));
                        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                            @Override
                            public void run() {
                                canDamageAI = true;
                            }
                        }, 0.75f);
                    }
                }
                if(enemyKiBlast != null) {
                    if (playerKiSuper.overlaps(enemyKiBlast)) {
                        // playerKiSuper.preventOverlap(enemyKiBlast);
                        enemyKiBlast.remove();
                    }
                }
            }
        }

        blueRanger.boundToWorld();
        enemyCharacter.boundToWorld();
    }

    //not sure if this caused buggy collision, should of used vector list to check each projectiles collisions against other objects
    void CheckKiHit(){
        //player normal attack
        if(kiBlast != null){
            if(kiBlast.overlaps(enemyCharacter)){
                kiBlast.preventOverlap(enemyCharacter);
                kiBlast.remove();
                kiBlast.isAlive = false;
                enemyCharacter.health = enemyCharacter.health - 10;
                Gdx.app.log("Ranger:", "HIT" + Integer.toString(enemyCharacter.health));
                //play hit sound
            }
            if(kiBlast.overlaps(enemyKiBlast)){
                kiBlast.preventOverlap(enemyKiBlast);
                kiBlast.remove();
                kiBlast.isAlive = false;
                enemyKiBlast.remove();
                enemyKiBlast.isAlive = false;
                //spawn explosion here
            }
        }
        //enemy normal attack
        if(enemyKiBlast != null){
            if(enemyKiBlast.overlaps(blueRanger)){
                enemyKiBlast.preventOverlap(blueRanger);
                enemyKiBlast.remove();
                blueRanger.health = blueRanger.health - 10;
                Gdx.app.log("Ranger:", "HIT" + Integer.toString(blueRanger.health));
                //play hit sound
            }
        }
        //player super attack
       if(playerKiSuper != null){
            if(playerKiSuper.overlaps(enemyCharacter)){
                playerKiSuper.preventOverlap(enemyCharacter);
                playerKiSuper.remove();
                enemyCharacter.health -= 100;
                //play hit sound
            }
            if(playerKiSuper.overlaps(enemyKiBlast)){
                playerKiSuper.preventOverlap(enemyKiBlast);
                enemyKiBlast.remove();
            }
        }
    }

    //TODO, add more random logic to the AI, add decision for when projectile is WithinDistance X to move or stay, randomize decision to counter super attack with super, move or idle.
    void MoveAI(){
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
            enemyCharacter.moveBy(5,0);
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
                enemyKiBlast.setOrigin(enemyKiBlast.getX()/2,enemyKiBlast.getY()/2);
                enemyKiBlast.isAlive = true;
                enemyKiBlast.setAnimation(enemyKiBlast.moving);
                punchSound.play();

                enemyKiBlast.setPosition(enemyCharacter.getX(), enemyCharacter.getY());
                mainStage.addActor(enemyKiBlast);
                //delay between attack, should randomzie delay for better AI gameplay
                com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
                    @Override
                    public void run() {
                        enemyCharacter.isAttacking = false;
                    }
                },2.5f);
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
