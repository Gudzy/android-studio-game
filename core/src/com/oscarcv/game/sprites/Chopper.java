package com.oscarcv.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Chopper {
    private Vector3 position;
    private Vector3 velocity;
    private Texture chpr;
    private TextureRegion chprRegion;
    private Rectangle bounds;

    private Sprite chopper;
    private Animation chopperAnimation;

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setPosition(float x, float y, float z) {
        position.add(x,y,z);
    }

    public void setVelocity(float x,float y,float z) {
        velocity.add(x,y,z);
    }

    public Chopper(int x, int y ) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(160, 150, 0);
        chpr = new Texture("birdanimation.png");
        chprRegion = new TextureRegion(chpr);
        chopper = new Sprite(chprRegion);
        chopperAnimation = new Animation(chopper, 3, 0.5f);
        bounds = new Rectangle(x, y, chpr.getWidth() / 3, chpr.getHeight());

    }

    public void update(float deltaTime){
        chopperAnimation.update(deltaTime);
        turn();
        position.add(velocity.x * deltaTime, velocity.y * deltaTime,0);
        bounds.setPosition(position.x, position.y);
    }

    public void turn(){
        if (position.y < 0){
            position.add(0,3f,0);
            velocity.add(0, -2 * velocity.y, 0);
        }
        if (position.y > 800 - chpr.getHeight()){
            position.add(0,-3f,0);
            velocity.add(0, -2 * velocity.y, 0);
        }
    }

    public void collide(Boolean right) {
        if(right) {
            position.add(-3f,0,0);
            velocity.add(-2 * velocity.x, 0, 0);

        } else{
            position.add(3f,0,0);
            velocity.add(-2 * velocity.x, 0, 0);
        }
    }

    public void reverse(){
        chopperAnimation.reverse();
    }

    public Vector3 getPosition() { return position; }
    public TextureRegion getChopper() { return chopperAnimation.getFrame(); }
    public Rectangle getBounds() { return bounds; }
}
