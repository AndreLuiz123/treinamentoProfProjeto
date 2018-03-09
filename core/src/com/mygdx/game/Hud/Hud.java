package com.mygdx.game.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Sprites.Heroi;

import java.util.ArrayList;

import jdk.nashorn.internal.ir.Labels;

/**
 * Created by Andre Luiz on 19/01/2018.
 */

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private int enumeraPersonagem, comandosTela=0;
    private Label labelEnumeraPersonagem;
    private Label esq, dir, cim, bax;
    private Label espBranco;

    public Table table;

    public Hud(SpriteBatch sb){
        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

        enumeraPersonagem = 1;


        labelEnumeraPersonagem = new Label(String.format("%02d", enumeraPersonagem),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        esq = new Label(String.format("left"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        dir = new Label(String.format("right"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        cim = new Label(String.format("up"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bax = new Label(String.format("down"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        espBranco = new Label(String.format("    "),new Label.LabelStyle(new BitmapFont(), Color.WHITE));



      //  table.setDebug(true);

        table.add(esq).expandX().right();
        table.add(espBranco).right();
        table.add(labelEnumeraPersonagem).right();
        table.row();
        table.add(dir).right();
        table.row();
        table.add(cim).right();
        table.row();
        table.add(bax).right();
        table.row();
        table.add(espBranco).right();
        table.row();


    }

    public void update(Heroi personagem){
        stage.addActor(table);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            table.clear();
            table.add(esq).expandX().right();
            table.add(espBranco).right();
            table.add(labelEnumeraPersonagem).right();
            table.row();
            table.add(dir).right();
            table.row();
            table.add(cim).right();
            table.row();
            table.add(bax).right();
            table.row();
            table.add(espBranco).right();
            table.row();

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {


        }

    }

    public void atualizaComandosDoHeroi(Heroi personagem) {
        table.clear();

        table.add(esq).expandX().right();
        table.add(espBranco).right();
        table.add(labelEnumeraPersonagem).right();
        table.row();
        table.add(dir).right();
        table.row();
        table.add(cim).right();
        table.row();
        table.add(bax).right();
        table.row();
        table.add(espBranco).right();
        table.row();


        for (String comando: personagem.comandos) {
            table.add(new Label(comando, new Label.LabelStyle(new BitmapFont(), Color.GOLD))).expandX().right();
            table.row();

        }
    }

    public  void atualizaEnumeracaoPersonagem(boolean mais, boolean menos){

        if(mais){
            if(enumeraPersonagem!=3) {
                enumeraPersonagem++;
                labelEnumeraPersonagem.setText(String.format("%02d", enumeraPersonagem));
            }
        }else{
            if(menos) {
                if(enumeraPersonagem!=1) {
                    enumeraPersonagem--;
                    labelEnumeraPersonagem.setText(String.format("%02d", enumeraPersonagem));
                }
            }
        }

    }

    public void indicaMovimentoAtual(){



    }





    @Override
    public void dispose() {


    }
}
