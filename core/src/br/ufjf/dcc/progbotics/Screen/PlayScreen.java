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

/**
 * Created by Andre Luiz on 15/11/2017.
 */

public class PlayScreen implements Screen {

    public static String[] LEVEL_NAMES = {"ProgBotsLevel1.tmx", "ProgBotsLevel2.tmx"};
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
    private boolean bul;


    public PlayScreen(ProgBoticsGame game) {

        atlas = new TextureAtlas("teste.pack");

        this.game = game;
        gameCam = new OrthographicCamera(0,0);
        gamePort = new FitViewport(ProgBoticsGame.V_WIDTH / ProgBoticsGame.PPM, ProgBoticsGame.V_HEIGHT / ProgBoticsGame.PPM, gameCam);

        constantBackground = new Texture("backgroundProgBotics.png");


        this.setLevel(this.level);
    }

    public void setLevel(Integer level) {
        this.level = level;
       // setActivePlayer(1);
        activePlayer = 0;
        switch (this.level) {
            case 0:
                mapLoader = new TmxMapLoader();
                map = mapLoader.load(LEVEL_NAMES[this.level]);
                world = new World(new Vector2(0, 0), true);
                players = new ArrayList<Heroi>();
                players.add(new Heroi(world, this, "heroi1"));
                players.add(new Heroi(world, this, "heroi2"));
                players.add(new Heroi(world, this, "heroi3"));
                players.get(0).b2body.setTransform(85 / ProgBoticsGame.PPM, 31 / ProgBoticsGame.PPM, 0);
                players.get(1).b2body.setTransform(115 / ProgBoticsGame.PPM, 31 / ProgBoticsGame.PPM, 0);
                players.get(2).b2body.setTransform(145 / ProgBoticsGame.PPM, 31 / ProgBoticsGame.PPM, 0);

                alavancas = new ArrayList<Alavanca>();

                alavancas.add(new Alavanca(world, this, players.get(0)));
                alavancas.add(new Alavanca(world, this, players.get(1)));
                alavancas.add(new Alavanca(world, this, players.get(2)));
                alavancas.get(0).b2body.setTransform(85 / ProgBoticsGame.PPM, 100 / ProgBoticsGame.PPM, 0);
                alavancas.get(1).b2body.setTransform(145 / ProgBoticsGame.PPM, 100 / ProgBoticsGame.PPM, 0);
                alavancas.get(2).b2body.setTransform(115 / ProgBoticsGame.PPM, 100 / ProgBoticsGame.PPM, 0);
                new B2WorldCreator(world, map);
                world.setContactListener(new WorldContactListener(players, alavancas));

                break;
            case 1:
                mapLoader = new TmxMapLoader();
                map = mapLoader.load(LEVEL_NAMES[this.level]);
                world = new World(new Vector2(0, 0), true);
                players = new ArrayList<Heroi>();
                players.add(new Heroi(world, this, "heroi1"));
                players.add(new Heroi(world, this, "heroi2"));
                players.add(new Heroi(world, this, "heroi3"));
                players.get(0).b2body.setTransform(130 / ProgBoticsGame.PPM, 90 / ProgBoticsGame.PPM, 0);
                players.get(1).b2body.setTransform(160 / ProgBoticsGame.PPM, 90 / ProgBoticsGame.PPM, 0);
                players.get(2).b2body.setTransform(180 / ProgBoticsGame.PPM, 90 / ProgBoticsGame.PPM, 0);

                alavancas = new ArrayList<Alavanca>();

                alavancas.add(new Alavanca(world, this, players.get(0)));
                alavancas.add(new Alavanca(world, this, players.get(1)));
                alavancas.add(new Alavanca(world, this, players.get(2)));
                alavancas.get(0).b2body.setTransform(35 / ProgBoticsGame.PPM, 90 / ProgBoticsGame.PPM, 0);
                alavancas.get(1).b2body.setTransform(95 / ProgBoticsGame.PPM, 90 / ProgBoticsGame.PPM, 0);
                alavancas.get(2).b2body.setTransform(65 / ProgBoticsGame.PPM, 90 / ProgBoticsGame.PPM, 0);
                new B2WorldCreator(world, map);
                world.setContactListener(new WorldContactListener(players, alavancas));
                break;
            case 2:


            default:
        }

        renderer = new OrthogonalTiledMapRenderer(map, 1 / ProgBoticsGame.PPM);
        gameCam.position.set(1.0500001f,0.55000037f,0.0f);
        gameCam.zoom = 0.54999983f;

        b2dr = new Box2DDebugRenderer();


        bul = false;


        hud = new Hud(game.batch);

        handleInput();


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
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
          /*  hud.atualizaEnumeracaoPersonagem(false, true);
            setActivePlayer(getActivePlayer() - 1);
            hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));*/
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
          /*  hud.atualizaEnumeracaoPersonagem(true, false);
            setActivePlayer(getActivePlayer() + 1);
            hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));*/

        }



            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                players.get(getActivePlayer()).comandoAtual = 0;
                players.get(getActivePlayer()).pode = false;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                players.get(getActivePlayer()).rodaComando();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.O)){
                players.get(getActivePlayer()).pode = true;
            }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            /*for (Heroi player : players) {
                if (!player.comandos.isEmpty()) {
                    player.comandoAtual = 0;
                    player.pode = true;
                }
            }*/
            gameCam.translate(0.05f, 0, 0);
            System.out.println("zoom:"+gameCam.zoom);
            System.out.println("gameCam position:"+gameCam.position);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            /*for (Heroi player : players) {
                if (!player.comandos.isEmpty()) {
                    player.comandoAtual = 0;
                    player.pode = true;
                }
            }*/
            gameCam.translate(-0.05f, 0, 0);
            System.out.println("zoom:"+gameCam.zoom);
            System.out.println("gameCam position:"+gameCam.position);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            /*for (Heroi player : players) {
                if (!player.comandos.isEmpty()) {
                    player.comandoAtual = 0;
                    player.pode = true;
                }
            }*/
            gameCam.translate(0, -0.05f, 0);
            System.out.println("zoom:"+gameCam.zoom);
            System.out.println("gameCam position:"+gameCam.position);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            /*for (Heroi player : players) {
                if (!player.comandos.isEmpty()) {
                    player.comandoAtual = 0;
                    player.pode = true;
                }
            }*/
            gameCam.translate(0, 0.05f, 0);
            System.out.println("zoom:"+gameCam.zoom);
            System.out.println("gameCam position:"+gameCam.position);
        }


        hud.getMoveListaPersonagemEsquerda().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setActivePlayer(getActivePlayer() - 1);
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
                    default:
                }
             //   hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });

        hud.getMoveListaPersonagemDireita().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setActivePlayer(getActivePlayer() + 1);
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
                    default:
                }
             //   hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });



        hud.getMoveCameraDireita().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                for (Heroi player : players) {
                    if (!player.comandos.isEmpty()) {
                        player.comandoAtual = 0;
                        player.pode = true;
                    }
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
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });

        hud.getComandoAndar().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_UP);
               //  hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });

        hud.getComandoGirarDireita().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_RIGHT);
               //  hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });

        hud.getComandoGirarEsquerda().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_LEFT);
              //  hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });

        hud.getComandoEsperar().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_DOWN);
          //       hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
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
        if (alavancas.get(0).isLigada() && alavancas.get(1).isLigada() && alavancas.get(2).isLigada()) {
            //game.setScreen(new PlayScreen2(game));
            System.out.println("Passou para fase :"+this.level+1);
            this.setLevel(this.level+1);
        }


    }



    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //players.get(0).draw(game.batch);
        //Só esta ai para indicar onde estão os objetos. Quando tudo estiver concluido, esta linha pode ser apagada.
        b2dr.render(world, gameCam.combined);


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
      //  game.batch.draw(constantBackground,0,0);
        for (Alavanca alavanca : alavancas) {
            alavanca.draw(game.batch);
        }
        for (Heroi player : players) {
            player.draw(game.batch);
        }

        game.batch.end();

        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
