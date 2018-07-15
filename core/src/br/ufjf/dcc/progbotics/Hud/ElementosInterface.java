package br.ufjf.dcc.progbotics.Hud;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Andre Luiz on 20/02/2018.
 */

public class ElementosInterface extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;

    private Button lado;

    ElementosInterface(){
        Skin mySkin = new Skin(Gdx.files.internal("orange/skin/uiskin.json"));

        lado = new TextButton("Andar para direita",mySkin,"small");

        stage.addActor(lado);

    }





}
