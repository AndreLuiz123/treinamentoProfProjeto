package br.ufjf.dcc.progbotics;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.ufjf.dcc.progbotics.Screen.PlayScreen;

public class ProgBoticsGame extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		PlayScreen playscreen = new PlayScreen(this);
		playscreen.setLevel(0);
		setScreen(playscreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
