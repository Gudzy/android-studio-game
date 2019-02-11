package com.oscarcv.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Racket {
    private Texture racket;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private  Vector3 velocity;
    private Rectangle bounds;

    public Texture getRacket() {
        return racket;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Racket(int x, int y) {
        racket = new Texture("racket.png");
        position = new Vector3(x,y,0);
        bounds = new Rectangle(position.x, position.y, racket.getWidth(), racket.getHeight());
        velocity = new Vector3(0,0,0);
    }

    public void jump(){
        velocity.y =330;
    }

    public void update(float deltaTime) {
        if(position.y > 0)
            velocity.add(0, GRAVITY,0);
        bounds.setPosition(position.x, position.y);
        position.add(velocity.x * deltaTime, velocity.y * deltaTime,0);
        if(position.y < 0){
            position.y = 0;
        }
    }
    public boolean collides(Rectangle player) {
        return player.overlaps(bounds);
    }
}
