package br.ufjf.dcc.progbotics.Hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

    TextureRegionDrawable imagemDireitaDown;
    TextureRegionDrawable imagemDireitaUp;
    TextureRegionDrawable imagemEsquerdaDown;
    TextureRegionDrawable imagemEsquerdaUp;

    Button moveListaPersonagemEsquerda, moveListaPersonagemDireita;
    Label numeroPersonagem;


    Skin mySkin;

    public Table table;

    public Hud(SpriteBatch sb){
        viewport = new FitViewport(ProgBoticsGame.V_WIDTH, ProgBoticsGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

        enumeraPersonagem = 0;


        mySkin = new Skin(Gdx.files.internal("orange/skin/uiskin.json"));




        imagemEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-left.png"))));
        imagemEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-left-down.png"))));
        moveListaPersonagemEsquerda = new ImageButton(imagemEsquerdaUp,imagemEsquerdaDown);
        moveListaPersonagemEsquerda.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
               enumeraPersonagem--;
             numeroPersonagem.setText(String.format("%3d",enumeraPersonagem));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });


        imagemDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right.png"))));
        imagemDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right-down.png"))));
        moveListaPersonagemDireita = new ImageButton(imagemDireitaUp, imagemDireitaDown);
        moveListaPersonagemDireita.setPosition(moveListaPersonagemEsquerda.getX() + 2*moveListaPersonagemEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());
        moveListaPersonagemDireita.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                enumeraPersonagem++;
                numeroPersonagem.setText(String.format("%3d",enumeraPersonagem));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });

        numeroPersonagem = new Label(String.format("%3d",enumeraPersonagem),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        numeroPersonagem.setPosition(moveListaPersonagemEsquerda.getX() + moveListaPersonagemEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


        //  moveListaPersonagemDireita.setStyle();


        stage.addActor(moveListaPersonagemEsquerda);
        stage.addActor(moveListaPersonagemDireita);
        stage.addActor(numeroPersonagem);
    }

    public void update(){

    }

    public void removeDepoisAddStage(){
        stage.clear();
        stage.addActor(moveListaPersonagemEsquerda);
        stage.addActor(moveListaPersonagemDireita);
        stage.addActor(numeroPersonagem);
    }




    @Override
    public void dispose() {


    }
}
