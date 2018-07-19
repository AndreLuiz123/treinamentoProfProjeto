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
    TextureRegionDrawable pbBlue, pbRed, pbYellow;
    Image progBotBlue, progBotRed, progBotYellow;


    Button moveListaPersonagemEsquerda, moveListaPersonagemDireita;
    Button moveListaComandosEsquerda, moveListaComandosDireita;
    Button comandoAndar, comandoGirarDireita, comandoGirarEsquerda, comandoEsperar;
    Button lupaPlus, lupaLess;
    Button moveCameraCima, moveCameraBaixo, moveCameraDireita, moveCameraEsquerda;
    Image personagem;
    ArrayList<Image> comandos;

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
        sizeCorretoImagem(imagemEsquerdaUp, imagemEsquerdaDown, 50);

        imagemDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right.png"))));
        imagemDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-right-down.png"))));
        sizeCorretoImagem(imagemDireitaUp, imagemDireitaDown, 50);

        moveListaPersonagemEsquerda = new ImageButton(imagemEsquerdaUp,imagemEsquerdaDown);
        moveListaPersonagemEsquerda.setPosition(0, 2*moveListaPersonagemEsquerda.getHeight());

        moveListaPersonagemDireita = new ImageButton(imagemDireitaUp, imagemDireitaDown);
        moveListaPersonagemDireita.setPosition(moveListaPersonagemEsquerda.getX() + 2*moveListaPersonagemEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


        numeroPersonagem = new Label(String.format("%3d",enumeraPersonagem),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        numeroPersonagem.setPosition(moveListaPersonagemEsquerda.getX() + moveListaPersonagemEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


        moveListaComandosEsquerda = new ImageButton(imagemEsquerdaUp,imagemEsquerdaDown);
        moveListaComandosEsquerda.setPosition(0, 0);

        moveListaComandosDireita = new ImageButton(imagemDireitaUp, imagemDireitaDown);
        moveListaComandosDireita.setPosition(Gdx.graphics.getWidth()/2 - moveListaPersonagemDireita.getWidth(), 0);

        imagemComandoGirarDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirDireitaUp.png"))));
        imagemComandoGirarDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirDireitaDown.png"))));
        sizeCorretoImagem(imagemComandoGirarDireitaUp, imagemComandoGirarDireitaDown, 50);
        comandoGirarDireita = new ImageButton(imagemComandoGirarDireitaUp, imagemComandoGirarDireitaDown);
        comandoGirarDireita.setPosition(moveListaPersonagemDireita.getX() + moveListaComandosEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


        imagemComandoGirarEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEsquerdaUp.png"))));
        imagemComandoGirarEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEsquerdaDown.png"))));
        sizeCorretoImagem(imagemComandoGirarEsquerdaUp, imagemComandoGirarEsquerdaDown, 50);
        comandoGirarEsquerda = new ImageButton(imagemComandoGirarEsquerdaUp, imagemComandoGirarEsquerdaDown);
        comandoGirarEsquerda.setPosition(comandoGirarDireita.getX() + comandoGirarDireita.getWidth(), moveListaPersonagemEsquerda.getY());

        imagemComandoAndarUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEmFrenteUp.png"))));
        imagemComandoAndarDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirEmFrenteDown.png"))));
        sizeCorretoImagem(imagemComandoAndarUp, imagemComandoAndarDown, 50);
        comandoAndar = new ImageButton(imagemComandoAndarUp, imagemComandoAndarDown);
        comandoAndar.setPosition(comandoGirarEsquerda.getX() + comandoGirarEsquerda.getWidth(),  moveListaPersonagemEsquerda.getY());

        imagemComandoEsperarUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirBaixoUp.png"))));
        imagemComandoEsperarDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/seguirBaixoDown.png"))));
        sizeCorretoImagem(imagemComandoEsperarUp, imagemComandoEsperarDown, 50);
        comandoEsperar = new ImageButton(imagemComandoEsperarUp, imagemComandoEsperarDown);
        comandoEsperar.setPosition(comandoAndar.getX() + comandoAndar.getWidth(),  moveListaPersonagemEsquerda.getY());

        imagemAddComando = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/espVazioComando.png"))));
        pbBlue = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/BlueProgBot.png"))));
        pbRed = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/RedProgBot.png"))));
        pbYellow = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/YellowProgBot.png"))));
        progBotBlue = new Image(pbRed);
        personagem = new Image(pbRed);
        personagem.setSize(50,50);
        personagem.setPosition(moveListaComandosEsquerda.getX() + moveListaComandosEsquerda.getWidth(), moveListaPersonagemEsquerda.getY());


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
        moveCameraCima.setPosition(Gdx.graphics.getWidth() - moveCameraCima.getWidth(), lupaPlus.getY());

        imagemMoveCameraBaixoUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraBaixoUp.png"))));
        imagemMoveCameraBaixoDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraBaixoDown.png"))));
        moveCameraBaixo = new ImageButton(imagemMoveCameraBaixoUp, imagemMoveCameraBaixoDown);
        moveCameraBaixo.setPosition(Gdx.graphics.getWidth() - moveCameraCima.getWidth(), moveCameraCima.getY() - 2*moveCameraCima.getHeight());

        imagemMoveCameraEsquerdaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraEsquerdaUp.png"))));
        imagemMoveCameraEsquerdaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraEsquerdaDown.png"))));
        moveCameraEsquerda = new ImageButton(imagemMoveCameraEsquerdaUp, imagemMoveCameraEsquerdaDown);
        moveCameraEsquerda.setPosition(Gdx.graphics.getWidth() - 3*moveCameraCima.getWidth(), moveCameraCima.getY() - moveCameraCima.getHeight());

        imagemMoveCameraDireitaUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraDireitaUp.png"))));
        imagemMoveCameraDireitaDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/movimentaCameraDireitaDown.png"))));
        sizeCorretoImagem(imagemMoveCameraDireitaUp,imagemMoveCameraDireitaDown,50);
        moveCameraDireita = new ImageButton(imagemMoveCameraDireitaUp, imagemMoveCameraDireitaDown);
        moveCameraDireita.setPosition(Gdx.graphics.getWidth()/2 - moveCameraCima.getWidth(), lupaPlus.getY());

        comandos = new ArrayList<Image>();

        for(int i=0; i<5; i++) {
            comandos.add(new Image(imagemAddComando));
            comandos.get(i).setSize(50,50);
            comandos.get(i).setPosition(moveListaComandosEsquerda.getX() + (i+1)*comandos.get(i).getWidth(), moveListaComandosEsquerda.getY());
            stage.addActor(comandos.get(i));
        }


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
        stage.addActor(personagem);
        stage.addActor(lupaPlus);
        stage.addActor(lupaLess);
        stage.addActor(moveCameraCima);
        stage.addActor(moveCameraBaixo);
        stage.addActor(moveCameraDireita);
        stage.addActor(moveCameraEsquerda);

    }


    public Button getMoveListaPersonagemEsquerda() {return moveListaPersonagemEsquerda;}

    public Button getMoveListaPersonagemDireita() {return moveListaPersonagemDireita;}

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

    public ArrayList<Image> getComandos() {return comandos;}

    public TextureRegionDrawable getImagemDireitaDown() {return imagemDireitaDown;}

    public TextureRegionDrawable getImagemDireitaUp() {return imagemDireitaUp;}

    public TextureRegionDrawable getImagemEsquerdaDown() {return imagemEsquerdaDown;}

    public TextureRegionDrawable getImagemEsquerdaUp() {return imagemEsquerdaUp;}

    public TextureRegionDrawable getImagemComandoAndarUp() {return imagemComandoAndarUp;}

    public TextureRegionDrawable getImagemComandoGirarDireitaUp() {return imagemComandoGirarDireitaUp;}

    public TextureRegionDrawable getImagemComandoGirarEsquerdaUp() {return imagemComandoGirarEsquerdaUp;}

    public TextureRegionDrawable getImagemComandoEsperarUp() {return imagemComandoEsperarUp;}

    public TextureRegionDrawable getImagemComandoAndarDown() {return imagemComandoAndarDown;}

    public TextureRegionDrawable getImagemComandoGirarDireitaDown() {return imagemComandoGirarDireitaDown;}

    public TextureRegionDrawable getImagemComandoGirarEsquerdaDown() {return imagemComandoGirarEsquerdaDown;}

    public TextureRegionDrawable getImagemComandoEsperarDown() {return imagemComandoEsperarDown;}

    public TextureRegionDrawable getImagemAddComando() {return imagemAddComando;}

    public TextureRegionDrawable getImagemLupaPlusUp() {return imagemLupaPlusUp;}

    public TextureRegionDrawable getImagemLupaPlusDown() {return imagemLupaPlusDown;}

    public TextureRegionDrawable getImagemLupaLessUp() {return imagemLupaLessUp;}

    public TextureRegionDrawable getImagemLupaLessDown() {return imagemLupaLessDown;}

    public TextureRegionDrawable getImagemMoveCameraCimaUp() {return imagemMoveCameraCimaUp;}

    public TextureRegionDrawable getImagemMoveCameraCimaDown() {return imagemMoveCameraCimaDown;}

    public TextureRegionDrawable getImagemMoveCameraEsquerdaUp() {return imagemMoveCameraEsquerdaUp;}

    public TextureRegionDrawable getImagemMoveCameraEsquerdaDown() {return imagemMoveCameraEsquerdaDown;}

    public TextureRegionDrawable getImagemMoveCameraBaixoUp() {return imagemMoveCameraBaixoUp;}

    public TextureRegionDrawable getImagemMoveCameraBaixoDown() {return imagemMoveCameraBaixoDown;}

    public TextureRegionDrawable getImagemMoveCameraDireitaUp() {return imagemMoveCameraDireitaUp;}

    public TextureRegionDrawable getImagemMoveCameraDireitaDown() {return imagemMoveCameraDireitaDown;}

    public TextureRegionDrawable getPbBlue() {return pbBlue;}

    public TextureRegionDrawable getPbRed() {return pbRed;}

    public TextureRegionDrawable getPbYellow() {return pbYellow;}

    public Image getPersonagem() {return personagem;}

    public void atualizaComandosDoHeroi(Heroi player){

        if(player.comandos.size()<=5 && player.comandos.size()>0){
            for(int i = 0; i<player.comandos.size(); i++) {
                if(player.comandos.get(i) == Heroi.COMMAND_DOWN){
                    this.comandos.get(i).setDrawable(getImagemComandoAndarDown());
                    System.out.println("TESTE");
                }else{
                    if(player.comandos.get(i) == Heroi.COMMAND_LEFT){
                        this.comandos.get(i).setDrawable(getImagemComandoGirarEsquerdaUp());
                        System.out.println(this.comandos.get(i));
                    }else{
                        if(player.comandos.get(i) == Heroi.COMMAND_RIGHT){
                            this.comandos.get(i).setDrawable(getImagemComandoGirarDireitaUp());
                            System.out.println(player.comandos.get(i));
                        }else{
                            if(player.comandos.get(i) == Heroi.COMMAND_UP){
                                this.comandos.get(i).setDrawable(getImagemComandoAndarUp());
                                System.out.println(player.comandos.get(i));
                            }else{

                                    this.comandos.get(i).setDrawable(imagemAddComando);
                                    System.out.println("VAO VE");

                            }
                        }
                    }
                }
            }
            System.out.println(player.comandos.get(0));
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
