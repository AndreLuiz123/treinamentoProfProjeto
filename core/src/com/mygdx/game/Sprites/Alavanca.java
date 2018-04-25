package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.PlayScreen;

/**
 * Created by Andre Luiz on 15/04/2018.
 */

public class Alavanca extends Sprite{

    protected World world;
    protected TextureRegion alavanca;
    private enum State { DESLIGADA, LIGADA};
    private State estadoAtual;
    private boolean ligada;
    protected Heroi personagem;
    protected Body b2body;



    public  int t;

    public Alavanca (World world, PlayScreen screen, Heroi personagem){

        super(screen.getAtlas().findRegion("lpc-2"));
        int S=64;
         t=640;

        ligada = false;
        this.world = world;
        alavanca = new TextureRegion(getTexture(), 640, S*8, S, S);

        setBounds(0,0,24/ MyGdxGame.PPM,24/MyGdxGame.PPM);

        setRegion(alavanca);

        this.personagem = personagem;

        defineAlavanca();

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

    public void defineAlavanca() {
       BodyDef bdef = new BodyDef();
        bdef.position.set(95 / MyGdxGame.PPM, 65 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MyGdxGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        Fixture fixture = b2body.createFixture(fdef);

        fixture.setUserData(this);

    }

    public void colideComHeroi(){

        System.out.println("teste");

    }

    public Heroi getHeroi(){
        return personagem;
    }


}
