package com.mygdx.characteranimation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MenuScreen implements Screen {

    CharacterGame game;

    Texture background = new Texture(Gdx.files.internal("background.png"));

    GlyphLayout layout;

    OrthographicCamera camera;

    public MenuScreen(final CharacterGame game) {

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(background, 0, 0);

        layout = new GlyphLayout(new BitmapFont(), "Click on the screen to start the game...");

        game.font.draw(game.batch, layout, Gdx.graphics.getWidth() / 2 - (layout.width / 2),
                Gdx.graphics.getHeight() / 2 - (layout.height / 2));

        game.batch.end();

        if (Gdx.input.isTouched()) {

            game.setScreen(new CharacterAnimation(game));
            dispose();

        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
