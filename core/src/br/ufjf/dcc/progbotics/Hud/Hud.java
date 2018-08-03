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
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
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
    private ArrayList<String> comando = new ArrayList<String>();
    private Label labelEnumeraPersonagem;
    private Label esq, dir, cim, bax;
    private Label espBranco;
    private Image baixoIMG, cimaIMG, direitaIMG, esquerdaIMG, alavancaIMG, lixoIMG, nextLevelIMG;
    private ArrayList<Label> comandosEmTela = new ArrayList<Label>();
    private int controlaComandoModificado = 0, controlaComandoEmTelaSegundoBarraDeRolamento = 0;

    private TextureRegionDrawable imagemDireitaDown;
    private TextureRegionDrawable imagemDireitaUp;
    private TextureRegionDrawable imagemEsquerdaDown;
    private TextureRegionDrawable imagemEsquerdaUp;
    private TextureRegionDrawable imagemComandoAndarCimaUp,   imagemComandoAndarDireitaUp,   imagemComandoAndarEsquerdaUp,   imagemComandoAndarBaixoUp,  imagemComandoEsperarUp;
    private TextureRegionDrawable imagemComandoAndarCimaDown, imagemComandoAndarDireitaDown, imagemComandoAndarEsquerdaDown, imagemComandoAndarBaixoDown,imagemComandoEsperarDown;
    private TextureRegionDrawable imagemAddComando;
    private TextureRegionDrawable imagemLupaPlusUp, imagemLupaPlusDown, imagemLupaLessUp, imagemLupaLessDown;
    private TextureRegionDrawable imagemMoveCameraCimaUp, imagemMoveCameraCimaDown, imagemMoveCameraEsquerdaUp, imagemMoveCameraEsquerdaDown, imagemMoveCameraBaixoUp, imagemMoveCameraBaixoDown, imagemMoveCameraDireitaUp, imagemMoveCameraDireitaDown;
    private TextureRegionDrawable pbBlue;
    private TextureRegionDrawable pbRed;
    private TextureRegionDrawable pbYellow;
    private TextureRegionDrawable pbGreen;
    private TextureRegionDrawable imagemRestartUp, imagemRestartDown;
    private TextureRegionDrawable imagemApagaComandoUp, imagemApagaComandoDown;
    private Image progBotBlue, progBotRed, progBotYellow;


    private Button moveListaPersonagemEsquerda, moveListaPersonagemDireita;
    private Button moveListaComandosEsquerda, moveListaComandosDireita;
    private Button comandoAndarCima;
    private Button comandoAndarDireita;
    private Button comandoAndarEsquerda;
    private Button comandoAndarBaixo;
    private Button comandoEsperar;
    private Button lupaPlus, lupaLess;
    private Button moveCameraCima, moveCameraBaixo, moveCameraDireita, moveCameraEsquerda;
    private Button restartLevel;
    private ArrayList<Button> apagaComando;
    private Image personagem;
    private ArrayList<Image> comandos;
    private ArrayList<Label> numeracaoComando;

    private Label numeroPersonagem;
    public Label timerJogo;
    private Skin mySkin;

    public int controlaComandoEmTela;

    public Table table;

    public int timer;
    public float timeCount;

    public Hud(SpriteBatch sb) {
        viewport = new FitViewport(ProgBoticsGame.V_WIDTH, ProgBoticsGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);

        enumeraPersonagem = 0;
        controlaComandoEmTela = 0;

        timer = 10;
        timeCount = 0;

        mySkin = new Skin(Gdx.files.internal("orange/skin/uiskin.json"));

        imagemEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-left.png"))));
        imagemEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-left-down.png"))));
        sizeCorretoImagem(imagemEsquerdaUp, imagemEsquerdaDown, 50);

        imagemDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right.png"))));
        imagemDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right-down.png"))));
        sizeCorretoImagem(imagemDireitaUp, imagemDireitaDown, 50);

        moveListaPersonagemEsquerda = new ImageButton(imagemEsquerdaUp, imagemEsquerdaDown);

        moveListaPersonagemDireita = new ImageButton(imagemDireitaUp, imagemDireitaDown);

        numeroPersonagem = new Label(String.format("%3d", enumeraPersonagem), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timerJogo =  new Label(String.format("%2d", timer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timerJogo.setFontScale(3);


        moveListaComandosEsquerda = new ImageButton(imagemEsquerdaUp, imagemEsquerdaDown);

        moveListaComandosDireita = new ImageButton(imagemDireitaUp, imagemDireitaDown);

        imagemComandoAndarDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirDireitaUp.png"))));
        imagemComandoAndarDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirDireitaDown.png"))));
        sizeCorretoImagem(imagemComandoAndarDireitaUp, imagemComandoAndarDireitaDown, 50);
        comandoAndarDireita = new ImageButton(imagemComandoAndarDireitaUp, imagemComandoAndarDireitaDown);

        imagemComandoAndarEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEsquerdaUp.png"))));
        imagemComandoAndarEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEsquerdaDown.png"))));
        sizeCorretoImagem(imagemComandoAndarEsquerdaUp, imagemComandoAndarEsquerdaDown, 50);
        comandoAndarEsquerda = new ImageButton(imagemComandoAndarEsquerdaUp, imagemComandoAndarEsquerdaDown);

        imagemComandoAndarCimaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEmFrenteUp.png"))));
        imagemComandoAndarCimaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEmFrenteDown.png"))));
        sizeCorretoImagem(imagemComandoAndarCimaUp, imagemComandoAndarCimaDown, 50);
        comandoAndarCima = new ImageButton(imagemComandoAndarCimaUp, imagemComandoAndarCimaDown);

        imagemComandoAndarBaixoUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirBaixoUp.png"))));
        imagemComandoAndarBaixoDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirBaixoDown.png"))));
        sizeCorretoImagem(imagemComandoAndarBaixoUp, imagemComandoAndarBaixoDown, 50);
        comandoAndarBaixo = new ImageButton(imagemComandoAndarBaixoUp, imagemComandoAndarBaixoDown);

        imagemComandoEsperarUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/esperarUp.png"))));
        imagemComandoEsperarDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/esperarDown.png"))));
        sizeCorretoImagem(imagemComandoEsperarUp, imagemComandoEsperarDown, 50);
        comandoEsperar = new ImageButton(imagemComandoEsperarUp, imagemComandoEsperarDown);

        imagemAddComando = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/espVazioComando.png"))));
        pbBlue = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/BlueProgBot.png"))));
        pbRed = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/RedProgBot.png"))));
        pbYellow = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/YellowProgBot.png"))));
        pbGreen = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/GreenProgBot.png"))));
        progBotBlue = new Image(pbRed);
        personagem = new Image(pbRed);
        personagem.setSize(50, 50);


        imagemLupaPlusUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaPlusUp.png"))));
        imagemLupaPlusDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaPlusDown.png"))));
        sizeCorretoImagem(imagemLupaPlusUp, imagemLupaPlusDown, 50);
        lupaPlus = new ImageButton(imagemLupaPlusUp, imagemLupaPlusDown);

        imagemLupaLessUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaLessUp.png"))));
        imagemLupaLessDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaLessDown.png"))));
        sizeCorretoImagem(imagemLupaLessUp, imagemLupaLessDown, 50);
        lupaLess = new ImageButton(imagemLupaLessUp, imagemLupaLessDown);

        imagemMoveCameraCimaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraCimaUp.png"))));
        imagemMoveCameraCimaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraCimaDown.png"))));
        moveCameraCima = new ImageButton(imagemMoveCameraCimaUp, imagemMoveCameraCimaDown);

        imagemMoveCameraBaixoUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraBaixoUp.png"))));
        imagemMoveCameraBaixoDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraBaixoDown.png"))));
        moveCameraBaixo = new ImageButton(imagemMoveCameraBaixoUp, imagemMoveCameraBaixoDown);

        imagemMoveCameraEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraEsquerdaUp.png"))));
        imagemMoveCameraEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraEsquerdaDown.png"))));
        moveCameraEsquerda = new ImageButton(imagemMoveCameraEsquerdaUp, imagemMoveCameraEsquerdaDown);

        imagemMoveCameraDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraDireitaUp.png"))));
        imagemMoveCameraDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraDireitaDown.png"))));
        sizeCorretoImagem(imagemMoveCameraDireitaUp, imagemMoveCameraDireitaDown, 50);
        moveCameraDireita = new ImageButton(imagemMoveCameraDireitaUp, imagemMoveCameraDireitaDown);

        imagemRestartUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/girarDireitaUp.png"))));
        imagemRestartDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/girarDireitaDown.png"))));
        sizeCorretoImagem(imagemRestartUp, imagemRestartDown, 50);
        restartLevel = new ImageButton(imagemRestartUp, imagemRestartDown);

        imagemApagaComandoUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/imagemVazia.png"))));
        imagemApagaComandoDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/close-button-down.png"))));

        comandos = new ArrayList<Image>();
        apagaComando = new ArrayList<Button>();
        numeracaoComando = new ArrayList<Label>();

        table.add(lupaLess).expandY().top();
        table.add(lupaPlus).top();
        table.add();
        table.add();
        table.add(timerJogo).top();
        table.add();
        table.add(restartLevel).top();
        table.row();
        table.add(moveListaPersonagemEsquerda).expandX();
        table.add(personagem).expandX().fill(true);
        table.add(moveListaPersonagemDireita).expandX();
        table.add();
        table.add();
        table.add();
        table.add(moveCameraDireita);
        table.row();
        table.bottom();
        table.add().expandX();
        table.add(comandoEsperar).expandX();
        table.add(comandoAndarDireita).expandX();
        table.add(comandoAndarEsquerda).expandX();
        table.add(comandoAndarCima).expandX();
        table.add(comandoAndarBaixo).expandX();
        table.add().expandX();
        table.row();
        table.add(moveListaComandosEsquerda).expandX();
        for (int i = 0; i < 5; i++) {
            Stack stack = new Stack();
            Table table2 = new Table();
            table2.setDebug(true);

            comandos.add(new Image(imagemAddComando));
            apagaComando.add(new ImageButton(imagemApagaComandoUp, imagemApagaComandoDown));
            numeracaoComando.add(new Label(String.format("%02d",controlaComandoEmTela+i), new Label.LabelStyle(new BitmapFont(), Color.PURPLE)));

            table2.add(numeracaoComando.get(i)).expandY().top().expandX().left();
            stack.add(comandos.get(i));
            stack.add(table2);
            stack.add(apagaComando.get(i));
            table.add(stack).expandX().fill(true);
        }
        table.add(moveListaComandosDireita).expandX();

        stage.addActor(table);

    }

    public void atualizaComandosDoHeroi(Heroi player) {

        for (int i = 0; i < 5; i++) {
            if (controlaComandoEmTela + i < player.comandos.size()) {
                int c = controlaComandoEmTela + i;
                if (player.comandos.get(c) == Heroi.COMMAND_DOWN) {
                    this.comandos.get(i).setDrawable(getImagemComandoAndarBaixoUp());
                    //getImage()
                    //setDrawable();
                } else if (player.comandos.get(c) == Heroi.COMMAND_LEFT) {
                    this.comandos.get(i).setDrawable(getImagemComandoAndarEsquerdaUp());

                } else if (player.comandos.get(c) == Heroi.COMMAND_RIGHT) {
                    this.comandos.get(i).setDrawable(getImagemComandoAndarDireitaUp());

                } else if (player.comandos.get(c) == Heroi.COMMAND_UP) {
                    this.comandos.get(i).setDrawable(getImagemComandoAndarCimaUp());
                } else if (player.comandos.get(c) == Heroi.COMMAND_WAIT) {
                    this.comandos.get(i).setDrawable(imagemComandoEsperarUp);
                }
            } else {
                this.comandos.get(i).setDrawable(getImagemAddComando());
            }
            numeracaoComando.get(i).setText(String.format("%02d",controlaComandoEmTela+i+1));
        }
    }


    public void sizeCorretoImagem(TextureRegionDrawable imagem, TextureRegionDrawable imagem2, float size) {
        imagem.setMinHeight(size);
        imagem.setMinWidth(size);
        imagem2.setMinHeight(size);
        imagem2.setMinWidth(size);
    }

    public Viewport getViewport() {
        return viewport;
    }

    public int getEnumeraPersonagem() {
        return enumeraPersonagem;
    }

    public ArrayList<String> getComando() {
        return comando;
    }

    public Label getLabelEnumeraPersonagem() {
        return labelEnumeraPersonagem;
    }

    public Label getEsq() {
        return esq;
    }

    public Label getDir() {
        return dir;
    }

    public Label getCim() {
        return cim;
    }

    public Label getBax() {
        return bax;
    }

    public Label getEspBranco() {
        return espBranco;
    }

    public Image getBaixoIMG() {
        return baixoIMG;
    }

    public Image getCimaIMG() {
        return cimaIMG;
    }

    public Image getDireitaIMG() {
        return direitaIMG;
    }

    public Image getEsquerdaIMG() {
        return esquerdaIMG;
    }

    public Image getAlavancaIMG() {
        return alavancaIMG;
    }

    public Image getLixoIMG() {
        return lixoIMG;
    }

    public Image getNextLevelIMG() {
        return nextLevelIMG;
    }

    public ArrayList<Label> getComandosEmTela() {
        return comandosEmTela;
    }

    public int getControlaComandoModificado() {
        return controlaComandoModificado;
    }

    public int getControlaComandoEmTelaSegundoBarraDeRolamento() {
        return controlaComandoEmTelaSegundoBarraDeRolamento;
    }

    public TextureRegionDrawable getImagemDireitaDown() {
        return imagemDireitaDown;
    }

    public TextureRegionDrawable getImagemDireitaUp() {
        return imagemDireitaUp;
    }

    public TextureRegionDrawable getImagemEsquerdaDown() {
        return imagemEsquerdaDown;
    }

    public TextureRegionDrawable getImagemEsquerdaUp() {
        return imagemEsquerdaUp;
    }

    public TextureRegionDrawable getImagemComandoAndarCimaUp() {
        return imagemComandoAndarCimaUp;
    }

    public TextureRegionDrawable getImagemComandoAndarDireitaUp() {
        return imagemComandoAndarDireitaUp;
    }

    public TextureRegionDrawable getImagemComandoAndarEsquerdaUp() {
        return imagemComandoAndarEsquerdaUp;
    }

    public TextureRegionDrawable getImagemComandoAndarBaixoUp() {
        return imagemComandoAndarBaixoUp;
    }

    public TextureRegionDrawable getImagemComandoEsperarUp() {
        return imagemComandoEsperarUp;
    }

    public TextureRegionDrawable getImagemComandoAndarCimaDown() {
        return imagemComandoAndarCimaDown;
    }

    public TextureRegionDrawable getImagemComandoAndarDireitaDown() {
        return imagemComandoAndarDireitaDown;
    }

    public TextureRegionDrawable getImagemComandoAndarEsquerdaDown() {
        return imagemComandoAndarEsquerdaDown;
    }

    public TextureRegionDrawable getImagemComandoAndarBaixoDown() {
        return imagemComandoAndarBaixoDown;
    }

    public TextureRegionDrawable getImagemComandoEsperarDown() {
        return imagemComandoEsperarDown;
    }

    public TextureRegionDrawable getImagemAddComando() {
        return imagemAddComando;
    }

    public TextureRegionDrawable getImagemLupaPlusUp() {
        return imagemLupaPlusUp;
    }

    public TextureRegionDrawable getImagemLupaPlusDown() {
        return imagemLupaPlusDown;
    }

    public TextureRegionDrawable getImagemLupaLessUp() {
        return imagemLupaLessUp;
    }

    public TextureRegionDrawable getImagemLupaLessDown() {
        return imagemLupaLessDown;
    }

    public TextureRegionDrawable getImagemMoveCameraCimaUp() {
        return imagemMoveCameraCimaUp;
    }

    public TextureRegionDrawable getImagemMoveCameraCimaDown() {
        return imagemMoveCameraCimaDown;
    }

    public TextureRegionDrawable getImagemMoveCameraEsquerdaUp() {
        return imagemMoveCameraEsquerdaUp;
    }

    public TextureRegionDrawable getImagemMoveCameraEsquerdaDown() {
        return imagemMoveCameraEsquerdaDown;
    }

    public TextureRegionDrawable getImagemMoveCameraBaixoUp() {
        return imagemMoveCameraBaixoUp;
    }

    public TextureRegionDrawable getImagemMoveCameraBaixoDown() {
        return imagemMoveCameraBaixoDown;
    }

    public TextureRegionDrawable getImagemMoveCameraDireitaUp() {
        return imagemMoveCameraDireitaUp;
    }

    public TextureRegionDrawable getImagemMoveCameraDireitaDown() {
        return imagemMoveCameraDireitaDown;
    }

    public TextureRegionDrawable getPbBlue() {
        return pbBlue;
    }

    public TextureRegionDrawable getPbRed() {
        return pbRed;
    }

    public TextureRegionDrawable getPbYellow() {
        return pbYellow;
    }

    public TextureRegionDrawable getPbGreen() {
        return pbGreen;
    }

    public TextureRegionDrawable getImagemRestartUp() {
        return imagemRestartUp;
    }

    public TextureRegionDrawable getImagemRestartDown() {
        return imagemRestartDown;
    }

    public TextureRegionDrawable getImagemApagaComandoUp() {
        return imagemApagaComandoUp;
    }

    public TextureRegionDrawable getImagemApagaComandoDown() {
        return imagemApagaComandoDown;
    }

    public Image getProgBotBlue() {
        return progBotBlue;
    }

    public Image getProgBotRed() {
        return progBotRed;
    }

    public Image getProgBotYellow() {
        return progBotYellow;
    }

    public Button getMoveListaPersonagemEsquerda() {
        return moveListaPersonagemEsquerda;
    }

    public Button getMoveListaPersonagemDireita() {
        return moveListaPersonagemDireita;
    }

    public Button getMoveListaComandosEsquerda() {
        return moveListaComandosEsquerda;
    }

    public Button getMoveListaComandosDireita() {
        return moveListaComandosDireita;
    }

    public Button getComandoAndarCima() {
        return comandoAndarCima;
    }

    public Button getComandoAndarDireita() {
        return comandoAndarDireita;
    }

    public Button getComandoAndarEsquerda() {
        return comandoAndarEsquerda;
    }

    public Button getComandoAndarBaixo() {
        return comandoAndarBaixo;
    }

    public Button getComandoEsperar() {
        return comandoEsperar;
    }

    public Button getLupaPlus() {
        return lupaPlus;
    }

    public Button getLupaLess() {
        return lupaLess;
    }

    public Button getMoveCameraCima() {
        return moveCameraCima;
    }

    public Button getMoveCameraBaixo() {
        return moveCameraBaixo;
    }

    public Button getMoveCameraDireita() {
        return moveCameraDireita;
    }

    public Button getMoveCameraEsquerda() {
        return moveCameraEsquerda;
    }

    public Button getRestartLevel() {
        return restartLevel;
    }

    public ArrayList<Button> getApagaComando() {
        return apagaComando;
    }

    public ArrayList<Image> getComandos() {
        return comandos;
    }

    public ArrayList<Label> getNumeracaoComando() {
        return numeracaoComando;
    }

    public Label getNumeroPersonagem() {
        return numeroPersonagem;
    }

    public Skin getMySkin() {
        return mySkin;
    }

    public Image getPersonagem() {
        return personagem;
    }

    public void contaTempo(float dt){

        timeCount+=dt;

        if(timer!=0)
        if(timeCount>1){
            timer--;
            timeCount=0;
            timerJogo.setText(String.format("%2d", timer));
        }
    }



    public void update() {


    }

    @Override
    public void dispose() {


    }
}
