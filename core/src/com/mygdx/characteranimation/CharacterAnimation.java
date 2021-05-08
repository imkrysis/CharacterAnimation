package com.mygdx.characteranimation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Rectangle;

public class CharacterAnimation implements Screen {

	CharacterGame game;

	OrthographicCamera camera;
	SpriteBatch batch;
	Rectangle character;
	Texture background;

	public CharacterAnimation(CharacterGame game) {

		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.WIDTH, game.HEIGHT);

		background = new Texture(Gdx.files.internal("background.png"));

		character = new Rectangle();

	}

	@Override
	public void show() {

	}

	@Override
	public void render (float delta) {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		if(character.x < 0) character.x = 0;
		if(character.x > 800 - character.width) character.x = 800 - character.width;
		if(character.y < 0) character.y = 0;
		if(character.y > 480 - character.height) character.y = 480 - character.height;

		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.end();

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
	public void dispose () {

		batch.dispose();

	}

}
