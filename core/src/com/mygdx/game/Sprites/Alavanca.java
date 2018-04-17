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
    protected TextureRegion alavanca;
    private enum State { DESLIGADA, LIGADA};
    private State estadoAtual;
    private boolean ligada;

    public  int t;

    public Alavanca (World world, PlayScreen screen){

        super(screen.getAtlas().findRegion("lpc-2"));
        int S=64;
         t=640;

        ligada = false;
        this.world = world;
        alavanca = new TextureRegion(getTexture(), 640, S*8, S, S);

        setBounds(0,0,24/ MyGdxGame.PPM,24/MyGdxGame.PPM);

        setRegion(alavanca);
    }

    public void update(float dt){

        estadoAlavanca();
        alavanca = new TextureRegion(getTexture(), t, 64*9, 64, 64);
        setBounds(80/ MyGdxGame.PPM,60/ MyGdxGame.PPM,24/ MyGdxGame.PPM,24/MyGdxGame.PPM);
        setRegion(alavanca);

    }

    public void estadoAlavanca(){

        if(!ligada){
           t=640;
        }else{
           t=580;
        }

    }

    public void alavancaChangeState(){

        if(ligada){
            ligada=false;
        }else{
            ligada = true;
        }

    }



}
