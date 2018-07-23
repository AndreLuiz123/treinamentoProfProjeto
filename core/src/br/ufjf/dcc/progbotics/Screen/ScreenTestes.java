package br.ufjf.dcc.progbotics.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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


        personagens = new ArrayList<String>();

        personagens.add("ProgBot1");
        personagens.add("ProgBot2");
        personagens.add("ProgBot3");
        personagens.add("ProgBot4");

        this.hud = new Hud(game.batch);
        this.game = game;

        hud.getMoveListaPersonagemDireita().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //enumeraPersonagem++;
               // numeroPersonagem.setText(String.format("%3d",enumeraPersonagem));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });

        hud.getMoveListaPersonagemEsquerda().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //enumeraPersonagem--;
                //numeroPersonagem.setText(String.format("%3d",enumeraPersonagem));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });



    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(hud.stage);
    }

    public void handleInput(){


    }

    public void update(float dt){
        handleInput();

    }

    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        hud.stage.act();
        hud.stage.draw();
        hud.table.setDebug(true);
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
