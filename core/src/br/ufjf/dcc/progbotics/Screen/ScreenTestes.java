package br.ufjf.dcc.progbotics.Screen;

import com.badlogic.gdx.Screen;

import br.ufjf.dcc.progbotics.Hud.Hud;
import br.ufjf.dcc.progbotics.ProgBoticsGame;

/**
 * Created by Andre Luiz on 15/07/2018.
 */

public class ScreenTestes implements Screen {

    public Hud hud;

    private ProgBoticsGame game;

    public ScreenTestes(ProgBoticsGame game){

        this.hud = new Hud(game.batch);

        this.game = game;

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        hud.stage.draw();


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
