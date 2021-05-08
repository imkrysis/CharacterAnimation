package com.mygdx.characteranimation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CharacterGame extends Game {

    public static final int WIDTH = 800, HEIGHT = 480;

    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {

        batch = new SpriteBatch();
        font = new BitmapFont();

        this.setScreen(new MenuScreen(this));
    }
}
