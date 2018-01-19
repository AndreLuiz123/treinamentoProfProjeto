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

import jdk.nashorn.internal.ir.Labels;

/**
 * Created by Andre Luiz on 19/01/2018.
 */

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private String comandos = "ofnwfe";
    public Table table;
    public

    Label cima;
    Label baixo;
    Label esquerda;
    Label direita;

    public Hud(){

        stage.addActor(table);
    }

    public Hud(SpriteBatch sb){


        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

         table = new Table();
        table.top();
        table.setFillParent(true);

        baixo = new Label( "Down", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        cima = new Label( "Up", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        direita = new Label( "Right", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        esquerda = new Label( "Left", new Label.LabelStyle(new BitmapFont(), Color.WHITE));





        table.add(baixo).expandX().padTop(10).right();
        table.row();
        table.add(cima).expandX().right();
        table.row();
        table.add(esquerda).expandX().right();
        table.row();
        table.add(direita).expandX().right();

        stage.addActor(table);
    }






}
