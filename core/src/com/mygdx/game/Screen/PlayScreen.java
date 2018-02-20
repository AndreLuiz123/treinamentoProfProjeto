package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Hud.Hud;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Sprites.Heroi;
import com.mygdx.game.Tools.B2WorldCreator;
import com.mygdx.game.Tools.WorldContactListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andre Luiz on 15/11/2017.
 */

public class PlayScreen implements Screen{

    private MyGdxGame game;
    private Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private List<Heroi> players;
    private int activePlayer = 0;
    private TextureAtlas atlas;
    private Hud hud;
    private boolean bul;

    public PlayScreen(MyGdxGame game ) {

        atlas = new TextureAtlas("teste.pack");

        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MyGdxGame.V_WIDTH/MyGdxGame.PPM,MyGdxGame.V_HEIGHT/MyGdxGame.PPM,gameCam);



        mapLoader = new TmxMapLoader();
        map = mapLoader.load("sem título.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/MyGdxGame.PPM);
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2,0);
        world = new World(new Vector2(0,0), true);
        players = new ArrayList<Heroi>();
        players.add(new Heroi(world, this));
        players.add(new Heroi(world, this));
        players.add(new Heroi(world, this));
        players.get(1).b2body.setTransform(100 / MyGdxGame.PPM, 100 / MyGdxGame.PPM, 0);
        players.get(2).b2body.setTransform(200 / MyGdxGame.PPM, 100 / MyGdxGame.PPM, 0);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);



        world.setContactListener(new WorldContactListener());

        bul=false;

        hud = new Hud(game.batch);

    }

    public TextureAtlas getAtlas(){
        return  atlas;
    }

    public MyGdxGame getGame(){ return game;}



    @Override
    public void show() {

    }

    public int getActivePlayer(){
        return activePlayer;
    }
    public void setActivePlayer(int ap){
        if(ap < 0 || ap > players.size() - 1) return;
        activePlayer = ap;
    }

    public void handleInput(){
            if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
                setActivePlayer(getActivePlayer()-1);
                hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
                setActivePlayer(getActivePlayer()+1);
                hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                players.get(getActivePlayer()).comandoAtual = 0;
                players.get(getActivePlayer()).pode = false;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                players.get(getActivePlayer()).comandos.clear();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
                players.get(getActivePlayer()).rodaComando();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.O)){
                players.get(getActivePlayer()).pode = true;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.L)){
                for (Heroi player: players) {
                    player.comandoAtual = 0;
                    player.pode = true;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_UP);
                hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_DOWN);
                hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }


            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_RIGHT);
                hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));
            }


            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                players.get(getActivePlayer()).colocaComandos(Heroi.COMMAND_LEFT);
                hud.atualizaComandosDoHeroi(players.get(getActivePlayer()));

            }
    }

    public void update(float dt){


        handleInput();


        hud.update(players.get(0));

        world.step(dt,6,2);

        gameCam.update();

        renderer.setView(gameCam);

        for (Heroi player : players) {
            player.update(dt);
        }
    }






    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //players.get(0).draw(game.batch);
        //Só esta ai para indicar onde estão os objetos. Quando tudo estiver concluido, esta linha pode ser apagada.
        b2dr.render(world, gameCam.combined);


        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        /*for (Heroi player : players) {
            player.draw(game.batch);
        }*/
        game.batch.end();
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
}
