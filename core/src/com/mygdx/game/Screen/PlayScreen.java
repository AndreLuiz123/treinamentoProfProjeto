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
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Sprites.Heroi;

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
    private Heroi player;
    private float posX, posX2, posY, posY2;
    private float vX, vY, xis, ypi;
    private TextureAtlas atlas;


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
        player = new Heroi(world, this);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/MyGdxGame.PPM, (rect.getY() + rect.getHeight()/2)/MyGdxGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/MyGdxGame.PPM, rect.getHeight()/2/MyGdxGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

        posX=player.b2body.getPosition().x;
        posY=player.b2body.getPosition().y;
        vX=0;
        vY=0;
        posX2=player.b2body.getPosition().x;
        posY2=player.b2body.getPosition().y;
        xis=0;
        ypi=0;

    }

    public TextureAtlas getAtlas(){
        return  atlas;
    }



    @Override
    public void show() {

    }

 /*   public void handleInput(){

        ACREDITO QUE ESTE MÉTODO NÃO É MAIS NECESSÁRIO. APESAR DISSO, MANTEREI ELE AQUI ATÉ O FINAL DO PROJETO CASO SUA IMPORTÂNCIA SEJA EVIDENCIADA

    }*/

    public void update(float dt){


     //   handleInput();
        world.step(dt,6,2);

        gameCam.update();

        renderer.setView(gameCam);

        player.update(dt);

    }






    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //player.draw(game.batch);
        //Só esta ai para indicar onde estão os objetos. Quando tudo estiver concluido, esta linha pode ser apagada.
        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
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

    }
}
