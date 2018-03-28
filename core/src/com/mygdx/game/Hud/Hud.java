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
    private ArrayList<String> comando  = new ArrayList<String>();
    private Label labelEnumeraPersonagem;
    private Label esq, dir, cim, bax;
    private Label espBranco;
    private ArrayList<Label> comandosEmTela  = new ArrayList<Label>();
    private int controlaComandoModificado=0, controlaComandoEmTelaSegundoBarraDeRolamento=0;

    public Table table;

    public Hud(SpriteBatch sb){
        viewport = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

        enumeraPersonagem = 1;


        comando.add("teste");
        comando.add("teste");
        comando.add("teste");
        comando.add("teste");
        comando.add("teste");



        labelEnumeraPersonagem = new Label(String.format("%02d", enumeraPersonagem),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        esq = new Label(String.format("left"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        dir = new Label(String.format("right"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        cim = new Label(String.format("up"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bax = new Label(String.format("down"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        espBranco = new Label(String.format("    "),new Label.LabelStyle(new BitmapFont(), Color.WHITE));



       for(int j=0; j<5; j++){
           comandosEmTela.add( new Label(String.format("%s",comando.get(j)),new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
       }

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
        for(int n=0; n<5; n++){
            table.add(comandosEmTela.get(n)).right();
            table.row();
        }

    }

    public void update(Heroi personagem){
        stage.addActor(table);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {

            for(int i=0; i<5; i++){
            comando.add(i,"   ");;
            }


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
            for(int n=0; n<5; n++){
                table.add(comandosEmTela.get(n)).right();
                table.row();
            }


            for(int n=0; n<5; n++){
                comandosEmTela.get(n).setText(String.format("%s", comando.get(n)));
            }

            controlaComandoModificado=0;

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {



        }

    }

    public void atualizaComandosDoHeroi(Heroi personagem) {


        for(int i=0; i<5; i++){
            comando.add(i,"   ");;
        }

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

        for(int n=0; n<5; n++){
            table.add(comandosEmTela.get(n)).right();
            table.row();
        }


        for(int n=0; n<5; n++){
            comandosEmTela.get(n).setText(String.format("%s", comando.get(n)));
        }



            if(personagem.comandos.size()>0){
                for(controlaComandoModificado=0; controlaComandoModificado<personagem.comandos.size(); controlaComandoModificado++) {
                    if (controlaComandoModificado < 5) {
                        comandosEmTela.get(controlaComandoModificado).setText(String.format("%s", personagem.comandos.get(controlaComandoModificado + controlaComandoEmTelaSegundoBarraDeRolamento)));
                        System.out.println(controlaComandoModificado);
                    }

                }
            }


    }

    public void barraDeRolamento(int sentido, Heroi personagem){


        controlaComandoEmTelaSegundoBarraDeRolamento+=sentido;

        System.out.println(controlaComandoEmTelaSegundoBarraDeRolamento);


        for (int i = 0; i < 5; i++)
            comandosEmTela.get(i).setText(String.format("%s", personagem.comandos.get(i + controlaComandoEmTelaSegundoBarraDeRolamento)));



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
