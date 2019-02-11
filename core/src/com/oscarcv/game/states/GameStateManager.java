package com.oscarcv.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public final class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    //pop and instantly push
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float deltaTime){
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
