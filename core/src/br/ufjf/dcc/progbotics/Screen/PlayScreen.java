package br.ufjf.dcc.progbotics.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//import com.sun.org.apache.xpath.internal.operations.String;

import br.ufjf.dcc.progbotics.Hud.Hud;
import br.ufjf.dcc.progbotics.ProgBoticsGame;

import br.ufjf.dcc.progbotics.Sprites.Alavanca;
import br.ufjf.dcc.progbotics.Sprites.Heroi;
import br.ufjf.dcc.progbotics.Tools.B2WorldCreator;
import br.ufjf.dcc.progbotics.Tools.WorldContactListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Andre Luiz on 15/11/2017.
 */

public class PlayScreen implements Screen {

    public static String[] LEVEL_NAMES = {"ProgBotsLevel2.tmx", "ProgBotsLevel2b.tmx","ProgBotsLevel3b.tmx", "ProgBotsLevel4.tmx","ProgBotsLevel5.tmx","ProgBotsLevel6.tmx","ProgBotsLevel7.tmx","ProgBotsLevel8.tmx","ProgBotsLevel9.tmx"};
    private ProgBoticsGame game;
    private Texture texture, constantBackground;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private List<Heroi> players;
    private Rectangle rect;
    private List<Alavanca> alavancas;
    private Integer level = 0;
    private int activePlayer = 0;
    private TextureAtlas atlas;
    private Hud hud;
    private boolean comandosEmExecucao=false;
    public ArrayList<Integer> comandosUtilizados;
    int i=0;

    public PlayScreen(ProgBoticsGame game) {

        atlas = new TextureAtlas("teste.pack");

        this.game = game;
        gameCam = new OrthographicCamera(0,0);
        gamePort = new FitViewport(ProgBoticsGame.V_WIDTH / ProgBoticsGame.PPM, ProgBoticsGame.V_HEIGHT / ProgBoticsGame.PPM, gameCam);

        constantBackground = new Texture("backgroundProgBotics.png");

        this.setLevel(this.level);

        b2dr = new Box2DDebugRenderer();

        hud = new Hud(game.batch);

        handleInput();

        /*Comandos da variavel comandosUtilizados:
        * comandosUtilizados.get(0) <- numero de vezes que o comando  moveListaPersonagensEsquerda é utilizado
        * comandosUtilizados.get(1) <- numero de vezes que o comando  moveListaPersonagensDireita é utilizado
        * comandosUtilizados.get(2) <- numero de vezes que o comando  moveListaComandosEsquerda é utilizado
        * comandosUtilizados.get(3) <- numero de vezes que o comando  moveListaComandosDireita é utilizado
        * comandosUtilizados.get(4) <- numero de vezes que o comando  executarComandos é utilizado
        * comandosUtilizados.get(5) <- numero de vezes que o comando  lupaPlus é utilizado
        * comandosUtilizados.get(6) <- numero de vezes que o comando  lupaLess é utilizado
        * comandosUtilizados.get(7) <- numero de vezes que o comando  esperar é utilizado
        * comandosUtilizados.get(8) <- numero de vezes que o comando  andarParaCima é utilizado
        * comandosUtilizados.get(9) <- numero de vezes que o comando  andarParaDireita é utilizado
        * comandosUtilizados.get(10) <- numero de vezes que o comando andarParaEsquerda é utilizado
        * comandosUtilizados.get(11) <- numero de vezes que o comando andarParaBaixo é utilizado
        * comandosUtilizados.get(12) <- numero de vezes que o comando apagarComando é utilizado
        * comandosUtilizados.get(13) <- numero de vezes que o comando restartLevel é utilizado
        * */
        comandosUtilizados = new ArrayList<Integer>();
        for(int i=0; i<14; i++)
        comandosUtilizados.add(0);


    }

    public void setLevel(Integer level) {
        this.level = level;
        //setActivePlayer(0);
        activePlayer = 0;
                mapLoader = new TmxMapLoader();
                map = mapLoader.load(LEVEL_NAMES[this.level]);
                world = new World(new Vector2(0, 0), true);
                players = new ArrayList<Heroi>();
                alavancas = new ArrayList<Alavanca>();
                new B2WorldCreator(world, map, players,alavancas, this);
                world.setContactListener(new WorldContactListener(players, alavancas));
                renderer = new OrthogonalTiledMapRenderer(map, 1 / ProgBoticsGame.PPM);
                gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/4,0.0f);
                comandosEmExecucao = false;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public ProgBoticsGame getGame() {
        return game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(hud.stage);
    }

    public int getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(int ap) {
        if (ap < 0 || ap > players.size() - 1) return;
        activePlayer = ap;
    }

    public void handleInput() {

        hud.getMoveListaPersonagemEsquerda().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                setActivePlayer(getActivePlayer() - 1);
                System.out.println(getActivePlayer());
                switch (getActivePlayer()){
                    case 0:
                        hud.getPersonagem().setDrawable(hud.getPbRed());
                        break;
                    case 1:
                        hud.getPersonagem().setDrawable(hud.getPbBlue());
                        break;
                    case 2:
                        hud.getPersonagem().setDrawable(hud.getPbYellow());
                        break;
                    case 3:
                        hud.getPersonagem().setDrawable(hud.getPbGreen());
                        break;
                    default:
                }
                hud.controlaComandoEmTela=0;
                comandosUtilizados.add(0,comandosUtilizados.get(0)+1);

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        for(int i=0; i<5; i++) {
            final Integer xx = i;
            hud.getApagaComando().get(i).addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println(hud.controlaComandoEmTela+" - "+xx);
                    if(!comandosEmExecucao)
                    if(hud.controlaComandoEmTela+xx< players.get(activePlayer).comandos.size()) {
                        players.get(activePlayer).comandos.remove(hud.controlaComandoEmTela + xx);
                        comandosUtilizados.add(1, comandosUtilizados.get(12) + 1);
                    }
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
        }

        hud.getMoveListaPersonagemDireita().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setActivePlayer(getActivePlayer() + 1);
                System.out.println(getActivePlayer());
                switch (getActivePlayer()){
                    case 0:
                        hud.getPersonagem().setDrawable(hud.getPbRed());
                        break;
                    case 1:
                        hud.getPersonagem().setDrawable(hud.getPbBlue());
                        break;
                    case 2:
                        hud.getPersonagem().setDrawable(hud.getPbYellow());
                        break;
                    case 3:
                        hud.getPersonagem().setDrawable(hud.getPbGreen());
                        break;
                    default:
                }
                hud.controlaComandoEmTela=0;
                comandosUtilizados.add(1, comandosUtilizados.get(1) + 1);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        hud.getMoveListaComandosEsquerda().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                if( hud.controlaComandoEmTela!=0){
                    hud.controlaComandoEmTela--;
                }
                System.out.println(hud.controlaComandoEmTela);
                System.out.println(players.get(activePlayer).comandos.size());
                comandosUtilizados.add(1, comandosUtilizados.get(2) + 1);

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        hud.getMoveListaComandosDireita().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                  hud.controlaComandoEmTela++;

                System.out.println(hud.controlaComandoEmTela);
                System.out.println(players.get(activePlayer).comandos.size());
                comandosUtilizados.add(1, comandosUtilizados.get(3) + 1);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        hud.getMoveCameraDireita().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(!comandosEmExecucao) {
                    for (Heroi player : players) {
                        if (!player.comandos.isEmpty()) {
                            player.comandoAtual = 0;
                            player.pode = true;
                        }
                    }
                    comandosEmExecucao = true;
                    System.out.println(comandosEmExecucao);
                    comandosUtilizados.add(1, comandosUtilizados.get(4) + 1);
                }
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        hud.getLupaPlus().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameCam.zoom-=0.05;
                System.out.println("zoom:"+gameCam.zoom);
                System.out.println("gameCam position:"+gameCam.position);
                comandosUtilizados.add(1, comandosUtilizados.get(5) + 1);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });



        hud.getLupaLess().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                gameCam.zoom+=0.05;
                System.out.println("zoom:"+gameCam.zoom);
                System.out.println("gameCam position:"+gameCam.position);
                comandosUtilizados.add(1, comandosUtilizados.get(6) + 1);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });




            hud.getComandoEsperar().addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(!comandosEmExecucao) {
                        players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_WAIT);
                        if (players.get(activePlayer).comandos.size() > 5 && hud.controlaComandoEmTela + 5 != players.get(activePlayer).comandos.size()) {
                            hud.controlaComandoEmTela++;
                            comandosUtilizados.add(1, comandosUtilizados.get(7) + 1);
                        }
                    }
                    //  hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });


            hud.getComandoAndarCima().addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(!comandosEmExecucao) {
                        players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_UP);
                        if (players.get(activePlayer).comandos.size() > 5 && hud.controlaComandoEmTela + 5 != players.get(activePlayer).comandos.size()) {
                            hud.controlaComandoEmTela++;
                            comandosUtilizados.add(1, comandosUtilizados.get(8) + 1);
                        }
                    }
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

            hud.getComandoAndarDireita().addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(!comandosEmExecucao) {
                        players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_RIGHT);
                        if (players.get(activePlayer).comandos.size() > 5 && hud.controlaComandoEmTela + 5 != players.get(activePlayer).comandos.size()) {
                            hud.controlaComandoEmTela++;
                            comandosUtilizados.add(1, comandosUtilizados.get(9) + 1);
                        }
                    }
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

            hud.getComandoAndarEsquerda().addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(!comandosEmExecucao) {
                        players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_LEFT);
                        if (players.get(activePlayer).comandos.size() > 5 && hud.controlaComandoEmTela + 5 != players.get(activePlayer).comandos.size()) {
                            hud.controlaComandoEmTela++;
                            comandosUtilizados.add(1, comandosUtilizados.get(10) + 1);
                        }
                    }
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });

            hud.getComandoAndarBaixo().addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(!comandosEmExecucao) {
                        players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_DOWN);
                        if (players.get(activePlayer).comandos.size() > 5 && hud.controlaComandoEmTela + 5 != players.get(activePlayer).comandos.size()) {
                            hud.controlaComandoEmTela++;
                            comandosUtilizados.add(1, comandosUtilizados.get(11) + 1);
                        }
                    }
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                    return true;
                }
            });



        hud.getRestartLevel().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setLevel(level);
                hud.controlaComandoEmTela=0;
                activePlayer=0;
                hud.getPersonagem().setDrawable(hud.getPbRed());
                comandosUtilizados.add(1, comandosUtilizados.get(13) + 1);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });



    }

    public void update(float dt) {

        hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
        world.step(dt, 6, 2);
        gameCam.update();
        renderer.setView(gameCam);

        for (Heroi player : players) {
            player.update(dt);
        }
        for (Alavanca alavanca : alavancas) {
            alavanca.update(dt);
        }

        if (alavancasLigadas()) {
            //game.setScreen(new PlayScreen2(game));
            System.out.println("Passou para fase :"+this.level+1);
            this.setLevel(this.level+1);
            hud.getPersonagem().setDrawable(hud.getPbRed());
        }
    }

    public boolean alavancasLigadas(){

        for(int i=0; i<alavancas.size(); i++){
            if(!alavancas.get(i).isLigada())
            return false;
        }

        return true;
    }



    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //Só esta ai para indicar onde estão os objetos. Quando tudo estiver concluido, esta linha pode ser apagada.
        //b2dr.render(world, gameCam.combined);


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
        for (Heroi player : players) {
            player.draw(game.batch);
        }
        game.batch.end();

        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height);
        hud.stage.getViewport().update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }

    public World getWorld() {
        return world;
    }

}
