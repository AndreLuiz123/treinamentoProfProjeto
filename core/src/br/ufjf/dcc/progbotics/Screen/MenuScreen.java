package br.ufjf.dcc.progbotics.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import br.ufjf.dcc.progbotics.ProgBoticsGame;

/**
 * Created by Andre Luiz on 24/07/2018.
 */

public class MenuScreen implements Screen {

    private ProgBoticsGame game;
    private Viewport gamePort;
    private Stage stage;


    Table table;

    Skin skin;
    Button jogar;
    Button sairJogo;

    public MenuScreen(final ProgBoticsGame game){

        this.game = game;

        gamePort = new FitViewport(ProgBoticsGame.V_WIDTH, ProgBoticsGame.V_HEIGHT);
        stage = new Stage(gamePort, game.batch);

        table = new Table();
        table.top();
        table.setFillParent(true);

        skin = new Skin(Gdx.files.internal("orange/skin/uiskin.json"));


        jogar = new TextButton("Jogar",skin);
        jogar.setSize(Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/10);
        jogar.setPosition(((Gdx.graphics.getWidth()/2)-jogar.getWidth()/2),Gdx.graphics.getHeight()/2-jogar.getHeight()/10);
        jogar.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                PlayScreen playscreen = new PlayScreen(game);
                game.setScreen(playscreen);

                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        sairJogo = new TextButton("Sair",skin);
        sairJogo.setSize(Gdx.graphics.getWidth()/8,Gdx.graphics.getHeight()/10);
        sairJogo.setPosition(((Gdx.graphics.getWidth()/2)-jogar.getWidth()/2),Gdx.graphics.getHeight()/2-jogar.getHeight()/10);
        sairJogo.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


        table.row();
        table.add();
        table.add(jogar).padTop(300);
        table.add();
        table.row();
        table.add();
        table.add(sairJogo).padTop(20);
        table.add();

        stage.addActor(table);


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();


        game.batch.setProjectionMatrix(stage.getCamera().combined);


        stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        stage.getViewport().update(width,height);
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
