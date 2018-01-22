package com.mygdx.game.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Sprites.Heroi;

import java.util.ArrayList;

import jdk.nashorn.internal.ir.Labels;

/**
 * Created by Andre Luiz on 19/01/2018.
 */

public class Hud {
    public Stage stage;
    private Viewport viewport;


    public Table table;

    public Hud(SpriteBatch sb){


        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

    }

    public void update(Heroi personagem){
        stage.addActor(table);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
           table.clear();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {

            atualizaComandosDoHeroi(personagem);
        }






    }

    private void atualizaComandosDoHeroi(Heroi personagem) {
        table.clear();
        for (String comando: personagem.comandos) {
            table.add(new Label(comando, new Label.LabelStyle(new BitmapFont(), Color.WHITE))).expandX().right();
            table.row();
        }
    }


}
