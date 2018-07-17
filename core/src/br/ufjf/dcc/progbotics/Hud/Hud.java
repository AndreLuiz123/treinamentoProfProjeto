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
    TextureRegionDrawable imagemComandoAndarUp, imagemComandoGirarDireitaUp, imagemComandoGirarEsquerdaUp, imagemComandoEsperarUp;
    TextureRegionDrawable imagemComandoAndarDown, imagemComandoGirarDireitaDown, imagemComandoGirarEsquerdaDown, imagemComandoEsperarDown;
    TextureRegionDrawable imagemAddComando;
    TextureRegionDrawable imagemLupaPlusUp, imagemLupaPlusDown, imagemLupaLessUp, imagemLupaLessDown;
    TextureRegionDrawable imagemMoveCameraCimaUp, imagemMoveCameraCimaDown, imagemMoveCameraEsquerdaUp, imagemMoveCameraEsquerdaDown, imagemMoveCameraBaixoUp, imagemMoveCameraBaixoDown, imagemMoveCameraDireitaUp, imagemMoveCameraDireitaDown;

    Button moveListaPersonagemEsquerda, moveListaPersonagemDireita;
    Button moveListaComandosEsquerda, moveListaComandosDireita;
    Button comandoAndar, comandoGirarDireita, comandoGirarEsquerda, comandoEsperar;
    Button lupaPlus, lupaLess;
    Button moveCameraCima, moveCameraBaixo, moveCameraDireita, moveCameraEsquerda;
    Image addComando;

    Label numeroPersonagem;

   // ArrayList<Image>

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
        sizeCorretoImagem(imagemEsquerdaUp, imagemEsquerdaDown, 40);
        moveListaPersonagemEsquerda = new ImageButton(imagemEsquerdaUp,imagemEsquerdaDown);

        imagemDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right.png"))));
        imagemDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right-down.png"))));
        sizeCorretoImagem(imagemDireitaUp, imagemDireitaDown, 40);
        moveListaPersonagemDireita = new ImageButton(imagemDireitaUp, imagemDireitaDown);
        moveListaPersonagemDireita.setPosition(moveListaPersonagemEsquerda.getX() + 2*moveListaPersonagemEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


        numeroPersonagem = new Label(String.format("%3d",enumeraPersonagem),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        numeroPersonagem.setPosition(moveListaPersonagemEsquerda.getX() + moveListaPersonagemEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());

        moveListaComandosEsquerda = new ImageButton(imagemEsquerdaUp,imagemEsquerdaDown);
        moveListaComandosEsquerda.setPosition(moveListaPersonagemDireita.getX() + moveListaPersonagemEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());

        moveListaComandosDireita = new ImageButton(imagemDireitaUp, imagemDireitaDown);
        moveListaComandosDireita.setPosition(Gdx.graphics.getWidth()/2 - moveListaPersonagemDireita.getWidth(), moveListaPersonagemEsquerda.getY());

        imagemComandoGirarDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/girarDireitaUp.png"))));
        imagemComandoGirarDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/girarDireitaDown.png"))));
        sizeCorretoImagem(imagemComandoGirarDireitaUp, imagemComandoGirarDireitaDown, 30);
        comandoGirarDireita = new ImageButton(imagemComandoGirarDireitaUp, imagemComandoGirarDireitaDown);
        comandoGirarDireita.setPosition(moveListaComandosEsquerda.getX() + moveListaComandosEsquerda.getWidth(), moveListaPersonagemEsquerda.getY() + 2*moveListaComandosEsquerda.getHeight());


        imagemComandoGirarEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/girarEsquerdaUp.png"))));
        imagemComandoGirarEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/girarEsquerdaDown.png"))));
        sizeCorretoImagem(imagemComandoGirarEsquerdaUp, imagemComandoGirarEsquerdaDown, 30);
        comandoGirarEsquerda = new ImageButton(imagemComandoGirarEsquerdaUp, imagemComandoGirarEsquerdaDown);
        comandoGirarEsquerda.setPosition(comandoGirarDireita.getX() + 2*comandoGirarDireita.getWidth(), moveListaPersonagemEsquerda.getY() + 2*moveListaComandosEsquerda.getHeight());

        imagemComandoAndarUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEmFrenteUp.png"))));
        imagemComandoAndarDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEmFrenteDown.png"))));
        sizeCorretoImagem(imagemComandoAndarUp, imagemComandoAndarDown, 30);
        comandoAndar = new ImageButton(imagemComandoAndarUp, imagemComandoAndarDown);
        comandoAndar.setPosition(comandoGirarEsquerda.getX() + 2*comandoGirarEsquerda.getWidth(), moveListaPersonagemEsquerda.getY() + 2*moveListaComandosEsquerda.getHeight());

        imagemComandoEsperarUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/esperarUp.png"))));
        imagemComandoEsperarDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/esperarDown.png"))));
        sizeCorretoImagem(imagemComandoEsperarUp, imagemComandoEsperarDown, 30);
        comandoEsperar = new ImageButton(imagemComandoEsperarUp, imagemComandoEsperarDown);
        comandoEsperar.setPosition(comandoAndar.getX() + 2*comandoAndar.getWidth(), moveListaPersonagemEsquerda.getY() + 2*moveListaComandosEsquerda.getHeight());

        imagemAddComando = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-plus-down.png"))));
        addComando = new Image(imagemAddComando);
        addComando.setPosition(moveListaComandosEsquerda.getX() + moveListaComandosEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());

        imagemLupaPlusUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaPlusUp.png"))));
        imagemLupaPlusDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaPlusDown.png"))));
        sizeCorretoImagem(imagemLupaPlusUp,imagemLupaPlusDown,50);
        lupaPlus = new ImageButton(imagemLupaPlusUp, imagemLupaPlusDown);
        lupaPlus.setPosition(getMoveListaPersonagemEsquerda().getX() + 2*lupaPlus.getWidth(),Gdx.graphics.getHeight()/2 - lupaPlus.getHeight());

        imagemLupaLessUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaLessUp.png"))));
        imagemLupaLessDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaLessDown.png"))));
        sizeCorretoImagem(imagemLupaLessUp,imagemLupaLessDown,50);
        lupaLess = new ImageButton(imagemLupaLessUp, imagemLupaLessDown);
        lupaLess.setPosition(getMoveListaPersonagemEsquerda().getX() + 0.8f*lupaLess.getWidth(),Gdx.graphics.getHeight()/2 - lupaPlus.getHeight());

        imagemMoveCameraCimaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraCimaUp.png"))));
        imagemMoveCameraCimaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraCimaDown.png"))));
        moveCameraCima = new ImageButton(imagemMoveCameraCimaUp, imagemMoveCameraCimaDown);
        moveCameraCima.setPosition(Gdx.graphics.getWidth() - 2*moveCameraCima.getWidth(), lupaPlus.getY());

        imagemMoveCameraBaixoUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraBaixoUp.png"))));
        imagemMoveCameraBaixoDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraBaixoDown.png"))));
        moveCameraBaixo = new ImageButton(imagemMoveCameraBaixoUp, imagemMoveCameraBaixoDown);
        moveCameraBaixo.setPosition(Gdx.graphics.getWidth() - 2*moveCameraCima.getWidth(), moveCameraCima.getY() - 2*moveCameraCima.getHeight());

        imagemMoveCameraEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraEsquerdaUp.png"))));
        imagemMoveCameraEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraEsquerdaDown.png"))));
        moveCameraEsquerda = new ImageButton(imagemMoveCameraEsquerdaUp, imagemMoveCameraEsquerdaDown);
        moveCameraEsquerda.setPosition(Gdx.graphics.getWidth() - 3*moveCameraCima.getWidth(), moveCameraCima.getY() - moveCameraCima.getHeight());

        imagemMoveCameraDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraDireitaUp.png"))));
        imagemMoveCameraDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraDireitaDown.png"))));
        moveCameraDireita = new ImageButton(imagemMoveCameraDireitaUp, imagemMoveCameraDireitaDown);
        moveCameraDireita.setPosition(Gdx.graphics.getWidth() - moveCameraCima.getWidth(), moveCameraCima.getY() - moveCameraCima.getHeight());



        //  moveListaPersonagemDireita.setStyle();


        stage.addActor(moveListaPersonagemEsquerda);
        stage.addActor(moveListaPersonagemDireita);
        stage.addActor(numeroPersonagem);
        stage.addActor(moveListaComandosDireita);
        stage.addActor(moveListaComandosEsquerda);
        stage.addActor(comandoGirarDireita);
        stage.addActor(comandoGirarEsquerda);
        stage.addActor(comandoAndar);
        stage.addActor(comandoEsperar);
        stage.addActor(addComando);
        stage.addActor(lupaPlus);
        stage.addActor(lupaLess);
        stage.addActor(moveCameraCima);
        stage.addActor(moveCameraBaixo);
        stage.addActor(moveCameraDireita);
        stage.addActor(moveCameraEsquerda);
    }


    public Button getMoveListaPersonagemEsquerda() {
        return moveListaPersonagemEsquerda;
    }

    public Button getMoveListaPersonagemDireita() {
        return moveListaPersonagemDireita;
    }

    public Button getComandoAndar() {return comandoAndar;}

    public Button getComandoGirarDireita() {return comandoGirarDireita;}

    public Button getComandoGirarEsquerda() {return comandoGirarEsquerda;}

    public Button getComandoEsperar() {return comandoEsperar;}

    public Button getLupaPlus() {return lupaPlus;}

    public Button getLupaLess() {return lupaLess;}

    public Button getMoveCameraCima() {return moveCameraCima;}

    public Button getMoveCameraBaixo() {return moveCameraBaixo;}

    public Button getMoveCameraDireita() {return moveCameraDireita;}

    public Button getMoveCameraEsquerda() {return moveCameraEsquerda;}

    public void atualizaComandosDoHeroi(Heroi player){

        for(int i = 0; i<player.comandos.size(); i++) {
            player.comandos.get(i);
        }

    }

    public void sizeCorretoImagem( TextureRegionDrawable imagem, TextureRegionDrawable imagem2, float size){
        imagem.setMinHeight(size);
        imagem.setMinWidth(size);
        imagem2.setMinHeight(size);
        imagem2.setMinWidth(size);
    }


    public void update(){

    }

    @Override
    public void dispose() {


    }
}
