package com.oscarcv.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.oscarcv.game.ChopperBounce;
import com.oscarcv.game.sprites.Animation;
import com.badlogic.gdx.graphics.Color;
import com.oscarcv.game.sprites.Chopper;
import com.oscarcv.game.sprites.Racket;
import static java.awt.Color.*;


public class GameState extends State{
    private Texture background;
    private Chopper chopper;
    private Racket racketLeft, racketRight;
    private BitmapFont font;
    private Integer leftScore, rightScore;
    private String winner;
    private Boolean turn;

    public GameState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, ChopperBounce.WIDTH, ChopperBounce.HEIGHT);
        background = new Texture("citybackground.jpg");
        chopper = new Chopper(240,300);
        racketLeft = new Racket(10, 300);
        racketRight = new Racket(420, 300);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        leftScore = 0;
        rightScore = 0;
        winner = "";
        turn = false;

    }

    @Override
    protected void handleInput() {
        //Checks if a player has won - if not the next touch on the screen should start a new game
            if(Gdx.input.justTouched() && rightScore < 3 && leftScore < 3){
                if(Gdx.input.getX() <= 240){
                    racketLeft.jump();
                } else {
                    racketRight.jump();
                }
            } else if(Gdx.input.justTouched() && (rightScore == 3 || leftScore == 3)){

                gsm.set(new GameState(gsm));
        }
    }

    public void declareWinner(){
        if(leftScore >= 3){
            winner = "Player 1 wins! Touch the screen to play again";
        }
        if(rightScore >= 3) {
            winner = "Player 2 wins! Touch the screen to play again";
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        declareWinner();
        if(leftScore < 3 || rightScore < 3){
            chopper.update(deltaTime);
        }
        racketLeft.update(deltaTime);
        racketRight.update(deltaTime);
        if(racketLeft.collides(chopper.getBounds())){
            chopper.collide(false);
            turn();
        }
        if(racketRight.collides(chopper.getBounds())){
            chopper.collide(true);
            turn();
        }
        if (chopper.getPosition().x < 0){
            chopper.setPosition(3f,0,0);
            chopper.setVelocity(-2 * chopper.getVelocity().x, 0, 0);
            if(rightScore < 3 && leftScore < 3){
                rightScore += 1;
            }
            turn();
        }
        if (chopper.getPosition().x > 480 - 40){
            chopper.setPosition(-3f,0,0);
            chopper.setVelocity(-2 * chopper.getVelocity().x, 0, 0);
            if(leftScore < 3 && rightScore < 3){
                leftScore += 1;
            }
            turn();

        }
    }
    public void turn(){
        turn = false;
        if(!turn){
            chopper.reverse();
            turn= true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0,ChopperBounce.WIDTH, ChopperBounce.HEIGHT);
        sb.draw(chopper.getChopper(), chopper.getPosition().x, chopper.getPosition().y);
        sb.draw(racketLeft.getRacket(), racketLeft.getPosition().x, racketLeft.getPosition().y);
        sb.draw(racketRight.getRacket(), racketRight.getPosition().x, racketRight.getPosition().y);
        font.draw(sb,winner, 90, 700);
        font.draw(sb, leftScore.toString(), 20,750);
        font.draw(sb, rightScore.toString(), 450,750);
        font.draw(sb,"Player 1", 10,20);
        font.draw(sb, "Player 2", 410,20);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
