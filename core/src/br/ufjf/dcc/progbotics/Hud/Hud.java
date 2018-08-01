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

    TextureRegionDrawable imagemDireitaDown;
    TextureRegionDrawable imagemDireitaUp;
    TextureRegionDrawable imagemEsquerdaDown;
    TextureRegionDrawable imagemEsquerdaUp;
    TextureRegionDrawable imagemComandoAndarCimaUp,   imagemComandoAndarDireitaUp,   imagemComandoAndarEsquerdaUp,   imagemComandoAndarBaixoUp,  imagemComandoEsperarUp;
    TextureRegionDrawable imagemComandoAndarCimaDown, imagemComandoAndarDireitaDown, imagemComandoAndarEsquerdaDown, imagemComandoAndarBaixoDown,imagemComandoEsperarDown;
    TextureRegionDrawable imagemAddComando;
    TextureRegionDrawable imagemLupaPlusUp, imagemLupaPlusDown, imagemLupaLessUp, imagemLupaLessDown;
    TextureRegionDrawable imagemMoveCameraCimaUp, imagemMoveCameraCimaDown, imagemMoveCameraEsquerdaUp, imagemMoveCameraEsquerdaDown, imagemMoveCameraBaixoUp, imagemMoveCameraBaixoDown, imagemMoveCameraDireitaUp, imagemMoveCameraDireitaDown;
    TextureRegionDrawable pbBlue, pbRed, pbYellow;
    TextureRegionDrawable imagemRestartUp, imagemRestartDown;
    TextureRegionDrawable imagemApagaComandoUp, imagemApagaComandoDown;
    Image progBotBlue, progBotRed, progBotYellow;


    Button moveListaPersonagemEsquerda, moveListaPersonagemDireita;
    public Button moveListaComandosEsquerda, moveListaComandosDireita;
    Button comandoAndarCima;
    Button comandoAndarDireita;
    Button comandoAndarEsquerda;
    Button comandoAndarBaixo;
    public Button comandoEsperar;
    Button lupaPlus, lupaLess;
    Button moveCameraCima, moveCameraBaixo, moveCameraDireita, moveCameraEsquerda;
    public Button restartLevel;
    public ArrayList<Button> apagaComando;
    Image personagem;
    ArrayList<Image> comandos;
    ArrayList<Label> numeracaoComando;

    Label numeroPersonagem;

    // ArrayList<Image>

    Skin mySkin;

    public int controlaComandoEmTela;

    public Table table;

    public Hud(SpriteBatch sb) {
        viewport = new FitViewport(ProgBoticsGame.V_WIDTH, ProgBoticsGame.V_HEIGHT);
        stage = new Stage(viewport, sb);

        table = new Table();
        table.top();
        table.setFillParent(true);
        table.setDebug(true);

        enumeraPersonagem = 0;
        controlaComandoEmTela = 0;


        mySkin = new Skin(Gdx.files.internal("orange/skin/uiskin.json"));

        imagemEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-left.png"))));
        imagemEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-left-down.png"))));
        sizeCorretoImagem(imagemEsquerdaUp, imagemEsquerdaDown, 50);

        imagemDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right.png"))));
        imagemDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right-down.png"))));
        sizeCorretoImagem(imagemDireitaUp, imagemDireitaDown, 50);

        moveListaPersonagemEsquerda = new ImageButton(imagemEsquerdaUp, imagemEsquerdaDown);
        //  moveListaPersonagemEsquerda.setPosition(0, 2*moveListaPersonagemEsquerda.getHeight());

        moveListaPersonagemDireita = new ImageButton(imagemDireitaUp, imagemDireitaDown);
        //  moveListaPersonagemDireita.setPosition(moveListaPersonagemEsquerda.getX() + 2*moveListaPersonagemEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


        numeroPersonagem = new Label(String.format("%3d", enumeraPersonagem), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //    numeroPersonagem.setPosition(moveListaPersonagemEsquerda.getX() + moveListaPersonagemEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


        moveListaComandosEsquerda = new ImageButton(imagemEsquerdaUp, imagemEsquerdaDown);
        //   moveListaComandosEsquerda.setPosition(0, 0);


        table.setDebug(true);

        moveListaComandosDireita = new ImageButton(imagemDireitaUp, imagemDireitaDown);
        //  moveListaComandosDireita.setPosition(Gdx.graphics.getWidth()/2 - moveListaPersonagemDireita.getWidth(), 0);


        imagemComandoAndarDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirDireitaUp.png"))));
        imagemComandoAndarDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirDireitaDown.png"))));
        sizeCorretoImagem(imagemComandoAndarDireitaUp, imagemComandoAndarDireitaDown, 50);
        comandoAndarDireita = new ImageButton(imagemComandoAndarDireitaUp, imagemComandoAndarDireitaDown);
        //   comandoGirarDireita.setPosition(moveListaPersonagemDireita.getX() + moveListaComandosEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


        imagemComandoAndarEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEsquerdaUp.png"))));
        imagemComandoAndarEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEsquerdaDown.png"))));
        sizeCorretoImagem(imagemComandoAndarEsquerdaUp, imagemComandoAndarEsquerdaDown, 50);
        comandoAndarEsquerda = new ImageButton(imagemComandoAndarEsquerdaUp, imagemComandoAndarEsquerdaDown);
        //comandoGirarEsquerda.setPosition(comandoGirarDireita.getX() + comandoGirarDireita.getWidth(), moveListaPersonagemEsquerda.getY());

        imagemComandoAndarCimaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEmFrenteUp.png"))));
        imagemComandoAndarCimaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEmFrenteDown.png"))));
        sizeCorretoImagem(imagemComandoAndarCimaUp, imagemComandoAndarCimaDown, 50);
        comandoAndarCima = new ImageButton(imagemComandoAndarCimaUp, imagemComandoAndarCimaDown);
        //  comandoAndar.setPosition(comandoGirarEsquerda.getX() + comandoGirarEsquerda.getWidth(),  moveListaPersonagemEsquerda.getY());

        imagemComandoAndarBaixoUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirBaixoUp.png"))));
        imagemComandoAndarBaixoDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirBaixoDown.png"))));
        sizeCorretoImagem(imagemComandoAndarBaixoUp, imagemComandoAndarBaixoDown, 50);
        comandoAndarBaixo = new ImageButton(imagemComandoAndarBaixoUp, imagemComandoAndarBaixoDown);
        //comandoEsperar.setPosition(comandoAndar.getX() + comandoAndar.getWidth(),  moveListaPersonagemEsquerda.getY());

        imagemComandoEsperarUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/esperarUp.png"))));
        imagemComandoEsperarDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/esperarDown.png"))));
        sizeCorretoImagem(imagemComandoEsperarUp, imagemComandoEsperarDown, 50);
        comandoEsperar = new ImageButton(imagemComandoEsperarUp, imagemComandoEsperarDown);

        imagemAddComando = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/espVazioComando.png"))));
        pbBlue = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/BlueProgBot.png"))));
        pbRed = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/RedProgBot.png"))));
        pbYellow = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/YellowProgBot.png"))));
        progBotBlue = new Image(pbRed);
        personagem = new Image(pbRed);
        personagem.setSize(50, 50);
        //  personagem.setPosition(moveListaComandosEsquerda.getX() + moveListaComandosEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


        imagemLupaPlusUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaPlusUp.png"))));
        imagemLupaPlusDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaPlusDown.png"))));
        sizeCorretoImagem(imagemLupaPlusUp, imagemLupaPlusDown, 50);
        lupaPlus = new ImageButton(imagemLupaPlusUp, imagemLupaPlusDown);
        //  lupaPlus.setPosition(getMoveListaPersonagemEsquerda().getX() + 2*lupaPlus.getWidth(),Gdx.graphics.getHeight()/2 - lupaPlus.getHeight());

        imagemLupaLessUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaLessUp.png"))));
        imagemLupaLessDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/lupaLessDown.png"))));
        sizeCorretoImagem(imagemLupaLessUp, imagemLupaLessDown, 50);
        lupaLess = new ImageButton(imagemLupaLessUp, imagemLupaLessDown);
        //  lupaLess.setPosition(getMoveListaPersonagemEsquerda().getX() + 0.8f*lupaLess.getWidth(),Gdx.graphics.getHeight()/2 - lupaPlus.getHeight());

        imagemMoveCameraCimaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraCimaUp.png"))));
        imagemMoveCameraCimaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraCimaDown.png"))));
        moveCameraCima = new ImageButton(imagemMoveCameraCimaUp, imagemMoveCameraCimaDown);
        //  moveCameraCima.setPosition(Gdx.graphics.getWidth() - moveCameraCima.getWidth(), lupaPlus.getY());

        imagemMoveCameraBaixoUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraBaixoUp.png"))));
        imagemMoveCameraBaixoDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraBaixoDown.png"))));
        moveCameraBaixo = new ImageButton(imagemMoveCameraBaixoUp, imagemMoveCameraBaixoDown);
        //   moveCameraBaixo.setPosition(Gdx.graphics.getWidth() - moveCameraCima.getWidth(), moveCameraCima.getY() - 2*moveCameraCima.getHeight());

        imagemMoveCameraEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraEsquerdaUp.png"))));
        imagemMoveCameraEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraEsquerdaDown.png"))));
        moveCameraEsquerda = new ImageButton(imagemMoveCameraEsquerdaUp, imagemMoveCameraEsquerdaDown);
        //   moveCameraEsquerda.setPosition(Gdx.graphics.getWidth() - 3*moveCameraCima.getWidth(), moveCameraCima.getY() - moveCameraCima.getHeight());

        imagemMoveCameraDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraDireitaUp.png"))));
        imagemMoveCameraDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraDireitaDown.png"))));
        sizeCorretoImagem(imagemMoveCameraDireitaUp, imagemMoveCameraDireitaDown, 50);
        moveCameraDireita = new ImageButton(imagemMoveCameraDireitaUp, imagemMoveCameraDireitaDown);
        //   moveCameraDireita.setPosition(Gdx.graphics.getWidth()/2 - moveCameraCima.getWidth(), lupaPlus.getY());

        imagemRestartUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/girarDireitaUp.png"))));
        imagemRestartDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/girarDireitaDown.png"))));
        sizeCorretoImagem(imagemRestartUp, imagemRestartDown, 50);
        restartLevel = new ImageButton(imagemRestartUp, imagemRestartDown);

        imagemApagaComandoUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/close-button.png"))));
        imagemApagaComandoDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/close-button-down.png"))));



        comandos = new ArrayList<Image>();
        apagaComando = new ArrayList<Button>();
        numeracaoComando = new ArrayList<Label>();
        //  moveListaPersonagemDireita.setStyle();

        table.add(lupaLess).expandY().top();
        table.add(lupaPlus).top();
        table.add();
        table.add();
        table.add();
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
            comandos.add(new Image(imagemAddComando));
            apagaComando.add(new ImageButton(imagemApagaComandoUp, imagemApagaComandoDown));
            numeracaoComando.add(new Label(String.format("%02d",controlaComandoEmTela+i), new Label.LabelStyle(new BitmapFont(), Color.PURPLE)));
            //  comandos.get(i).setSize(50,50);
            stack.add(comandos.get(i));
            stack.add(numeracaoComando.get(i));
            stack.add(apagaComando.get(i));
            table.add(stack).expandX().fill(true);


            //   comandos.get(i).setPosition(moveListaComandosEsquerda.getX() + (i+1)*comandos.get(i).getWidth(), moveListaComandosEsquerda.getY());
            //    stage.addActor(comandos.get(i));

        }
        table.add(moveListaComandosDireita).expandX();


        // table.top();


        stage.addActor(table);

 /*      table.addActor(numeroPersonagem);
        table.addActor(moveListaComandosDireita);
        table.addActor(moveListaComandosEsquerda);
        table.addActor(comandoGirarDireita);
        table.addActor(comandoGirarEsquerda);
        table.addActor(comandoAndar);
        table.addActor(comandoEsperar);
        table.addActor(personagem);
        table.addActor(lupaPlus);
        table.addActor(lupaLess);
        table.addActor(moveCameraCima);
        table.addActor(moveCameraBaixo);
        table.addActor(moveCameraDireita);
        table.addActor(moveCameraEsquerda);
*/


      /*  stage.addActor(moveListaPersonagemEsquerda);
        stage.addActor(moveListaPersonagemDireita);
        stage.addActor(numeroPersonagem);
        stage.addActor(moveListaComandosDireita);
        stage.addActor(moveListaComandosEsquerda);
        stage.addActor(comandoGirarDireita);
        stage.addActor(comandoGirarEsquerda);
        stage.addActor(comandoAndar);
        stage.addActor(comandoEsperar);
        stage.addActor(personagem);
        stage.addActor(lupaPlus);
        stage.addActor(lupaLess);
        stage.addActor(moveCameraCima);
        stage.addActor(moveCameraBaixo);
        stage.addActor(moveCameraDireita);
        stage.addActor(moveCameraEsquerda);*/


    }


    public Button getMoveListaPersonagemEsquerda() {
        return moveListaPersonagemEsquerda;
    }

    public Button getMoveListaPersonagemDireita() {
        return moveListaPersonagemDireita;
    }

    public Button getComandoAndar() {
        return comandoAndarCima;
    }

    public Button getComandoGirarDireita() {
        return comandoAndarDireita;
    }

    public Button getComandoGirarEsquerda() {
        return comandoAndarEsquerda;
    }

    public Button getComandoEsperar() {
        return comandoAndarBaixo;
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

    public ArrayList<Image> getComandos() {
        return comandos;
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

    public TextureRegionDrawable getImagemComandoAndarUp() {
        return imagemComandoAndarCimaUp;
    }

    public TextureRegionDrawable getImagemComandoGirarDireitaUp() {
        return imagemComandoAndarDireitaUp;
    }

    public TextureRegionDrawable getImagemComandoGirarEsquerdaUp() {
        return imagemComandoAndarEsquerdaUp;
    }

    public TextureRegionDrawable getImagemComandoEsperarUp() {
        return imagemComandoAndarBaixoUp;
    }

    public TextureRegionDrawable getImagemComandoAndarDown() {
        return imagemComandoAndarCimaDown;
    }

    public TextureRegionDrawable getImagemComandoGirarDireitaDown() {
        return imagemComandoAndarDireitaDown;
    }

    public TextureRegionDrawable getImagemComandoGirarEsquerdaDown() {
        return imagemComandoAndarEsquerdaDown;
    }

    public TextureRegionDrawable getImagemComandoEsperarDown() {
        return imagemComandoAndarBaixoDown;
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

    public Image getPersonagem() {
        return personagem;
    }

    public void atualizaComandosDoHeroi(Heroi player) {


        for (int i = 0; i < 5; i++) {
            if (controlaComandoEmTela + i < player.comandos.size()) {
                int c = controlaComandoEmTela + i;
                if (player.comandos.get(c) == Heroi.COMMAND_DOWN) {
                    this.comandos.get(i).setDrawable(getImagemComandoEsperarUp());
                    //getImage()
                    //setDrawable();
                } else if (player.comandos.get(c) == Heroi.COMMAND_LEFT) {
                    this.comandos.get(i).setDrawable(getImagemComandoGirarEsquerdaUp());

                } else if (player.comandos.get(c) == Heroi.COMMAND_RIGHT) {
                    this.comandos.get(i).setDrawable(getImagemComandoGirarDireitaUp());

                } else if (player.comandos.get(c) == Heroi.COMMAND_UP) {
                    this.comandos.get(i).setDrawable(getImagemComandoAndarUp());
                } else if (player.comandos.get(c) == Heroi.COMMAND_WAIT) {
                    this.comandos.get(i).setDrawable(imagemComandoEsperarUp);
                }
            } else {
                this.comandos.get(i).setDrawable(getImagemAddComando());
            }

            numeracaoComando.get(i).setText(String.format("%02d",controlaComandoEmTela+i+1));
        }


    }




        /*
        if(player.comandos.size()>0) {

            if (player.comandos.size() <= 5) {


            for (i = 0; i < player.comandos.size(); i++) {
                if (player.comandos.get(i) == Heroi.COMMAND_DOWN) {


                    this.comandos.get(i).setDrawable(getImagemComandoEsperarUp());
                            //getImage()
                            //setDrawable();



                } else {
                    if (player.comandos.get(i) == Heroi.COMMAND_LEFT) {
                        this.comandos.get(i).setDrawable(getImagemComandoGirarEsquerdaUp());

                    } else {
                        if (player.comandos.get(i) == Heroi.COMMAND_RIGHT) {
                            this.comandos.get(i).setDrawable(getImagemComandoGirarDireitaUp());

                        } else {
                            if (player.comandos.get(i) == Heroi.COMMAND_UP) {
                                this.comandos.get(i).setDrawable(getImagemComandoAndarUp());

                            }
                        }
                    }
                }
            }

            for (i = player.comandos.size(); i < 5; i++) {
                if(i+controlaComandoEmTela >= this.comandos.size())
                this.comandos.get(i+controlaComandoEmTela).setDrawable(getImagemAddComando());
            }
        }else{

                for (i = 0; i < 5; i++) {
                    if (player.comandos.get(i+controlaComandoEmTela) == Heroi.COMMAND_DOWN) {
                        this.comandos.get(i).setDrawable(getImagemComandoEsperarUp());

                    } else {
                        if (player.comandos.get(i+controlaComandoEmTela) == Heroi.COMMAND_LEFT) {
                            this.comandos.get(i).setDrawable(getImagemComandoGirarEsquerdaUp());

                        } else {
                            if (player.comandos.get(i+controlaComandoEmTela) == Heroi.COMMAND_RIGHT) {
                                this.comandos.get(i).setDrawable(getImagemComandoGirarDireitaUp());

                            } else {
                                if (player.comandos.get(i+controlaComandoEmTela) == Heroi.COMMAND_UP) {
                                    this.comandos.get(i).setDrawable(getImagemComandoAndarUp());

                                }
                            }
                        }
                    }
                }

            }

        }else{
            for(i=0; i<5; i++){
                this.comandos.get(i).setDrawable(getImagemAddComando());
            }
        }
*/

    public void sizeCorretoImagem(TextureRegionDrawable imagem, TextureRegionDrawable imagem2, float size) {
        imagem.setMinHeight(size);
        imagem.setMinWidth(size);
        imagem2.setMinHeight(size);
        imagem2.setMinWidth(size);
    }


    public void update() {

    }

    @Override
    public void dispose() {


    }
}
