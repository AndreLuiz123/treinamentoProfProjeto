package br.ufjf.dcc.progbotics.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc.progbotics.Hud.Hud;
import br.ufjf.dcc.progbotics.ProgBoticsGame;
import br.ufjf.dcc.progbotics.Sprites.Heroi;

/**
 * Created by Andre Luiz on 15/07/2018.
 */

public class ScreenTestes implements Screen {

    public Hud hud;

    private ProgBoticsGame game;
    private ArrayList<String> personagens;

    public ScreenTestes(ProgBoticsGame game){


        /*personagens.add("ProgBot1");
        personagens.add("ProgBot2");
        personagens.add("ProgBot3");
        personagens.add("ProgBot4");*/

        this.hud = new Hud(game.batch);
        this.game = game;

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(hud.stage);
    }

    @Override
    public void render(float delta) {
        hud.stage.draw();
        hud.update();
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