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

    public ArrayList<String> copiaPersonagem;

    public Table table;
    public

    Label cima;
    Label baixo;
    Label esquerda;
    Label direita;

    private   int i;


    public Hud(SpriteBatch sb, Heroi personagem){


        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

        baixo = new Label( "Down", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        cima = new Label( "Up", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        direita = new Label( "Right", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        esquerda = new Label( "Left", new Label.LabelStyle(new BitmapFont(), Color.WHITE));





        i=0;


    }

    public void update(Heroi personagem){



        System.out.println(i);

        if( personagem.comandos.size()>0){

            if(personagem.comandos.get(i-1)=="down") {
                table.add(new Label( "Down", new Label.LabelStyle(new BitmapFont(), Color.WHITE))).expandX().right().padTop(10);

                personagem.comandos.add(i-1,"D");
            }
            else
                if(personagem.comandos.get(i-1)=="up") {
                    table.add(new Label( "Up", new Label.LabelStyle(new BitmapFont(), Color.WHITE))).expandX().right();

                    personagem.comandos.add(i-1,"D");

                }
            else
                if(personagem.comandos.get(i-1)=="left"){
                    table.add(new Label( "Right", new Label.LabelStyle(new BitmapFont(), Color.WHITE))).expandX().right();

                    personagem.comandos.add(i-1,"D");

                }

            else
                if(personagem.comandos.get(i-1)=="right"){
                    table.add(new Label( "Right", new Label.LabelStyle(new BitmapFont(), Color.WHITE))).expandX().right();

                    personagem.comandos.add(i-1,"D");
                }

            stage.addActor(table);

        }





        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {

           table.clear();
            i=0;

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {

            i++;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

            i++;
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {

           i++;
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {

           i++;

        }




    }





}
