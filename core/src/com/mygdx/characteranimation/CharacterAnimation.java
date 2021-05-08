package com.mygdx.characteranimation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Rectangle;

public class CharacterAnimation implements Screen {

	CharacterGame game;

	OrthographicCamera camera;
	SpriteBatch batch;
	Rectangle character;

	Texture characterSheet;
	Texture background;

	Animation<TextureRegion> walkDownAnimation;
	Animation<TextureRegion> walkLeftAnimation;
	Animation<TextureRegion> walkRightAnimation;
	Animation<TextureRegion> walkUpAnimation;

	float stateTime;

	static final int SHEET_COLS = 12, SHEET_ROWS = 4;

	public CharacterAnimation(CharacterGame game) {

		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.WIDTH, game.HEIGHT);

		characterSheet = new Texture(Gdx.files.internal("character.png"));
		background = new Texture(Gdx.files.internal("background.png"));

		TextureRegion[][] tmp = TextureRegion.split(characterSheet,
				characterSheet.getWidth() / SHEET_COLS,
				characterSheet.getHeight() / SHEET_ROWS);

		TextureRegion[] walkDownFrames = new TextureRegion[SHEET_COLS];
		TextureRegion[] walkLeftFrames = new TextureRegion[SHEET_COLS];
		TextureRegion[] walkRightFrames = new TextureRegion[SHEET_COLS];
		TextureRegion[] walkUpFrames = new TextureRegion[SHEET_COLS];

		int index = 0;

		for (int i = 0; i < SHEET_ROWS; i++) {

			index = 0;

			for (int j = 0; j < SHEET_COLS; j++) {

				if (i == 0)
					walkDownFrames[index++] = tmp[i][j];
				if (i == 1)
					walkLeftFrames[index++] = tmp[i][j];
				if (i == 2)
					walkRightFrames[index++] = tmp[i][j];
				if (i == 3)
					walkUpFrames[index++] = tmp[i][j];

			}

		}

		// Initialize the Animation with the frame interval and array of frames
		walkDownAnimation = new Animation<TextureRegion>(0.076f, walkDownFrames);
		walkLeftAnimation = new Animation<TextureRegion>(0.076f, walkLeftFrames);
		walkRightAnimation = new Animation<TextureRegion>(0.076f, walkRightFrames);
		walkUpAnimation = new Animation<TextureRegion>(0.076f, walkUpFrames);

		// Crear el personatge
		character = new Rectangle();
		character.width = characterSheet.getWidth() / SHEET_COLS;
		character.height = characterSheet.getHeight() / SHEET_ROWS;
		character.x = 800/2;
		character.y = 64/2;


		stateTime = 0f;

	}

	@Override
	public void show() {

	}

	@Override
	public void render (float delta) {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateTime += Gdx.graphics.getDeltaTime();

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		TextureRegion currentFrame = null;

		TextureRegion currentDownFrame = walkDownAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentRightFrame = walkRightAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentUpFrame = walkUpAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentLeftFrame = walkLeftAnimation.getKeyFrame(stateTime, true);

		// Get keyboard arrows pressed info
		boolean pressedDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);
		boolean pressedLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
		boolean pressedRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		boolean pressedUp = Gdx.input.isKeyPressed(Input.Keys.UP);

		if (pressedDown) {

			currentFrame = walkDownAnimation.getKeyFrame(stateTime, true);
			character.y -= 200 * Gdx.graphics.getDeltaTime();

		} else if (pressedLeft) {

			currentFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
			character.x -= 200 * Gdx.graphics.getDeltaTime();

		} else if (pressedRight) {

			currentFrame = walkRightAnimation.getKeyFrame(stateTime, true);
			character.x += 200 * Gdx.graphics.getDeltaTime();

		} else if (pressedUp) {

			currentFrame = walkUpAnimation.getKeyFrame(stateTime, true);
			character.y += 200 * Gdx.graphics.getDeltaTime();

		} else {

			currentFrame = walkDownAnimation.getKeyFrame(0);
		}

		if(character.x < 0) character.x = 0;
		if(character.x > 800 - character.width) character.x = 800 - character.width;
		if(character.y < 0) character.y = 0;
		if(character.y > 480 - character.height) character.y = 480 - character.height;

		game.batch.begin();
		game.batch.draw(background, 0, 0);
		game.batch.draw(currentFrame, character.x, character.y);
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
