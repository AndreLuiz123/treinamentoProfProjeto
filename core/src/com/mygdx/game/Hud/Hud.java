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


    private int enumeraPersonagem;
    private String comando1,comando2,comando3,comando4,comando5;
    private Label labelEnumeraPersonagem;
    private Label esq, dir, cim, bax;
    private Label espBranco, comandosTela1, comandosTela2, comandosTela3, comandosTela4, comandosTela5;

    public Table table;

    public Hud(SpriteBatch sb){
        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

        enumeraPersonagem = 1;


        comando1 = "teste";
        comando2 = "teste";
        comando3 = "teste";
        comando4 = "teste";
        comando5 = "teste";

        labelEnumeraPersonagem = new Label(String.format("%02d", enumeraPersonagem),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        esq = new Label(String.format("left"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        dir = new Label(String.format("right"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        cim = new Label(String.format("up"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bax = new Label(String.format("down"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        espBranco = new Label(String.format("    "),new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        comandosTela1 =  new Label(String.format("%s",comando1),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        comandosTela2 =  new Label(String.format("%s",comando2),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        comandosTela3 =  new Label(String.format("%s",comando3),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        comandosTela4 =  new Label(String.format("%s",comando4),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        comandosTela5 =  new Label(String.format("%s",comando5),new Label.LabelStyle(new BitmapFont(), Color.WHITE));

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
        table.add(comandosTela1).right();
        table.row();
        table.add(comandosTela2).right();
        table.row();
        table.add(comandosTela3).right();
        table.row();
        table.add(comandosTela4).right();
        table.row();
        table.add(comandosTela5).right();

    }

    public void update(Heroi personagem){
        stage.addActor(table);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            comando1="  ";
            comando2= "  ";
            comando3="  ";
            comando4= "   ";
            comando5= "   ";
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
            table.add(comandosTela1).right();
            table.row();
            table.add(comandosTela2).right();
            table.row();
            table.add(comandosTela3).right();
            table.row();
            table.add(comandosTela4).right();
            table.row();
            table.add(comandosTela5).right();


            comandosTela5.setText(String.format("%s", comando1));
            comandosTela4.setText(String.format("%s", comando1));
            comandosTela3.setText(String.format("%s", comando1));
            comandosTela2.setText(String.format("%s", comando1));
            comandosTela1.setText(String.format("%s", comando1));
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
