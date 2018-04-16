package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.PlayScreen;

/**
 * Created by Andre Luiz on 15/04/2018.
 */

public class Alavanca extends Sprite {

    protected World world;
    protected TextureRegion alavancaDesligada;

    public  int t;

    public Alavanca (World world, PlayScreen screen){

        super(screen.getAtlas().findRegion("lpc-2"));
        int S=64;
         t=0;

        this.world = world;
        alavancaDesligada = new TextureRegion(getTexture(), t, S*8, S, S);
        setBounds(0,0,24/ MyGdxGame.PPM,24/MyGdxGame.PPM);
        setRegion(alavancaDesligada);
    }

    public void update(float dt){

        alavancaDesligada = new TextureRegion(getTexture(), t, 64*9, 64, 64);
        setBounds(0,0,24/ MyGdxGame.PPM,24/MyGdxGame.PPM);
        setRegion(alavancaDesligada);

    }


}
