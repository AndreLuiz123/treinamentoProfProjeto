package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.ufjf.dcc.progbotics.ProgBoticsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ProgBoticsGame(), config);

		config.width = ProgBoticsGame.V_WIDTH;
		config.height = ProgBoticsGame.V_HEIGHT;
		config.title = ProgBoticsGame.TITLE;
	}
}
