package br.ufjf.dcc.progbotics;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.ufjf.dcc.progbotics.Screen.MenuScreen;
import br.ufjf.dcc.progbotics.Screen.PlayScreen;
import br.ufjf.dcc.progbotics.Screen.ScreenTestes;

public class ProgBoticsGame extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 600;
	public static final String TITLE = "ProgBotics";
	public static final float PPM = 100;
	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		PlayScreen playscreen = new PlayScreen(this);
		MenuScreen menu = new MenuScreen(this);
		//Screen playscreen = new ScreenTestes(this);
		//playscreen.setLevel(1);
		setScreen(menu);
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
