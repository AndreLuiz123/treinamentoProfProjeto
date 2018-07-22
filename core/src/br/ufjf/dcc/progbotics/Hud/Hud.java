package br.ufjf.dcc.progbotics.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import br.ufjf.dcc.progbotics.ProgBoticsGame;
import br.ufjf.dcc.progbotics.Sprites.Heroi;

import java.util.ArrayList;

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
    private Image baixoIMG, cimaIMG, direitaIMG, esquerdaIMG, alavancaIMG, lixoIMG, nextLevelIMG;
    private ArrayList<Label> comandosEmTela  = new ArrayList<Label>();
    private int controlaComandoModificado=0, controlaComandoEmTelaSegundoBarraDeRolamento=0;

    public Table table;

    public Hud(SpriteBatch sb){
        viewport = new FitViewport(ProgBoticsGame.V_WIDTH, ProgBoticsGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

        enumeraPersonagem = 1;


        comando.add(" ");
        comando.add(" ");
        comando.add(" ");
        comando.add(" ");
        comando.add(" ");



        labelEnumeraPersonagem = new Label(String.format("%02d", enumeraPersonagem),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        esq = new Label(String.format("left"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        dir = new Label(String.format("right"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        cim = new Label(String.format("up"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bax = new Label(String.format("down"),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        espBranco = new Label(String.format("    "),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        baixoIMG = new Image(new Texture(Gdx.files.absolute("setinea.png")));
        direitaIMG = new Image(new Texture(Gdx.files.absolute("setinea.png")));
        cimaIMG = new Image(new Texture(Gdx.files.absolute("setinea.png")));
        esquerdaIMG = new Image(new Texture(Gdx.files.absolute("setinea.png")));
        alavancaIMG = new Image(new Texture(Gdx.files.absolute("alavancas.png")));
        lixoIMG = new Image(new Texture(Gdx.files.absolute("lixo.png")));
        nextLevelIMG = new Image(new Texture(Gdx.files.absolute("acionar.png")));





       for(int j=0; j<5; j++){
           comandosEmTela.add( new Label(String.format("%s",comando.get(j)),new Label.LabelStyle(new BitmapFont(), Color.WHITE)));
       }

        table.setDebug(true);

        table.add(esquerdaIMG).expandX().right();
        table.add(lixoIMG);
        table.add(espBranco).right();
        table.add(labelEnumeraPersonagem).right();
        table.row();
        table.add(direitaIMG).right();
        table.row();
        table.add(cimaIMG).right();
        table.row();
        table.add(baixoIMG).right();
        table.row();
        table.add(alavancaIMG).right();
        //table.add(espBranco).right();
        //table.add(baixoIMG).right();


        table.row();
        for(int n=0; n<5; n++){
            table.add(comandosEmTela.get(n)).right();
            table.row();
        }

    }

    public void update(Heroi personagem){
        stage.addActor(table);

        baixoIMG.setSize(20,20);
        baixoIMG.setRotation(180);
        baixoIMG.setPosition(370, 150);
        cimaIMG.setSize(20,20);
        cimaIMG.setPosition(350, 150);
        direitaIMG.setSize(20,20);
        direitaIMG.setRotation(270);
        direitaIMG.setPosition(350,190);
        esquerdaIMG.setSize(20,20);
        esquerdaIMG.setRotation(90);
        esquerdaIMG.setPosition(370, 190);
        orientaAlavancaOption(alavancaIMG,110);
        lixoIMG.setSize(20,20);
        lixoIMG.setPosition(320,190);

        if (Gdx.input.justTouched()&& Gdx.input.getX()>960 && Gdx.input.getX()<1019
                && Gdx.input.getY()>0 && Gdx.input.getY()<53) {

            for(int i=0; i<5; i++){
            comando.add(i,"   ");
            }

            baixoIMG.setSize(20,20);
            baixoIMG.setRotation(180);
            baixoIMG.setPosition(370, 150);
            cimaIMG.setSize(20,20);
            cimaIMG.setPosition(350, 150);
            direitaIMG.setSize(20,20);
            direitaIMG.setRotation(270);
            direitaIMG.setPosition(350,190);
            esquerdaIMG.setSize(20,20);
            esquerdaIMG.setRotation(90);
            esquerdaIMG.setPosition(370, 190);
            table.row();
            table.add(alavancaIMG).right();
            lixoIMG.setSize(20,20);
            lixoIMG.setPosition(320,190);


            table.clear();




            table.add(esquerdaIMG).expandX().right();
            table.add(lixoIMG);
            table.add(espBranco).right();
            table.add(labelEnumeraPersonagem).right();
            table.row();
            table.add(direitaIMG).right();
            table.row();
            table.add(cimaIMG).right();
            table.row();
            table.add(baixoIMG).right();
            table.row();
            table.add(espBranco).right();
            table.row();
            table.row();
            table.add(alavancaIMG).right();

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

            System.out.println(baixoIMG.getY());


        }

    }

    public void atualizaComandosDoHeroi(Heroi personagem) {


        for(int i=0; i<5; i++){
            comando.add(i,"   ");;
        }



        baixoIMG.setSize(20,20);
        baixoIMG.setRotation(180);
        baixoIMG.setPosition(370, 150);
        cimaIMG.setSize(20,20);
        cimaIMG.setPosition(350, 150);
        direitaIMG.setSize(20,20);
        direitaIMG.setRotation(270);
        direitaIMG.setPosition(350,190);
        esquerdaIMG.setSize(20,20);
        esquerdaIMG.setRotation(90);
        esquerdaIMG.setPosition(370, 190);
        orientaAlavancaOption(alavancaIMG,110);
        lixoIMG.setSize(20,20);
        lixoIMG.setPosition(320,190);


        table.clear();

        table.add(esquerdaIMG).expandX().right();
        table.add(lixoIMG);
        table.add(espBranco).right();
        table.add(labelEnumeraPersonagem).right();
        table.row();
        table.add(direitaIMG).right();
        table.row();
        table.add(cimaIMG).right();
        table.row();
        table.add(baixoIMG).right();
        table.row();
        table.add(espBranco).right();
        table.row();
        table.row();
        table.add(alavancaIMG).right();

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

                    }

                }
            }


    }

    public void barraDeRolamento(int sentido, Heroi personagem){


        controlaComandoEmTelaSegundoBarraDeRolamento+=sentido;




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

    public void orientaSeta(Image setaIMG, int rotacao, int posicaoY){

        setaIMG.setSize(20,20);
        setaIMG.setRotation(rotacao);
        setaIMG.setPosition(370,posicaoY);

    }

    public void orientaAlavancaOption(Image setaIMG, int posicaoY){

        setaIMG.setSize(50,20);
        setaIMG.setPosition(350,posicaoY);

    }

    public void proximoNivel(){

        table.add(nextLevelIMG);
        nextLevelIMG.setSize(90,90);
        nextLevelIMG.setPosition(100,300);

    }




    @Override
    public void dispose() {


    }
}
