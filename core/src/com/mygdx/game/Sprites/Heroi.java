package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screen.PlayScreen;

/**
 * Created by Andre Luiz on 16/11/2017.
 */

public class Heroi extends Sprite {

    public World world;
    public Body b2body;
    public BodyDef bdef;
    private TextureRegion linkParadoFrente;

    public enum State {ParadoFrente, AndandoFrente, ParadoLado, AndandoLado, ParadoCostas, AndandoCostas}

    private State estadoAtual, estadoAnterior;
    private Animation andandoLados, andandoFrente, andandoCostas;
    private float stateTimer;
    private boolean andandoDireita;
    private float posX, posX2, posY, posY2;
    private float vX, vY, xis, ypi;

    public Heroi(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("link"));
        this.world = world;

        estadoAtual = State.ParadoFrente;
        stateTimer = 0;
        andandoDireita = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(getTexture(), 27 * i, 69, 20, 30));
        }
        andandoFrente = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(getTexture(), 27 * i, 5, 20, 30));
        }

        andandoCostas = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(getTexture(), 27 * i, 35, 20, 30));
        }

        andandoLados = new Animation(0.1f, frames);
        frames.clear();


        defineHeroi();
        linkParadoFrente = new TextureRegion(getTexture(), 27, 69, 20, 30);
        setBounds(this.b2body.getPosition().x, this.b2body.getPosition().y, 16 / MyGdxGame.PPM, 16 / MyGdxGame.PPM);
        setRegion(linkParadoFrente);


        posX = b2body.getPosition().x;
        posY = b2body.getPosition().y;
        vX = 0;
        vY = 0;
        posX2 = b2body.getPosition().x;
        posY2 = b2body.getPosition().y;
        xis = 0;
        ypi = 0;

    }

    public void defineHeroi() {

        bdef = new BodyDef();
        bdef.position.set(45 / MyGdxGame.PPM, 32 / MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / MyGdxGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);


    }


    public void update(float dt) {

         setRegion(getFrame(dt));

        movimentoControles();
        movimentos();

    }

    public void movimentoControles() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            posY = b2body.getPosition().y;
            posY2 = getY();
            vY = 0.3f;

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            posY = b2body.getPosition().y;
            posY2 = getY();
            vY = -0.3f;

        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {

            posX = b2body.getPosition().x;
            posX2 = getX();
            vX = 0.3f;
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2) {

            posX = b2body.getPosition().x;
            posX2 = getX();
            vX = -0.3f;

        }

    }

    public void movimentos() {

        b2body.setLinearVelocity(vX, vY);

        if (vX != 0) {
            setPosition(b2body.getPosition().x, b2body.getPosition().y - getHeight() / 2);
            xis = vX;
        } else {
            setPosition(posX2 + xis, b2body.getPosition().y - getHeight() / 2);
        }

        if (vY != 0) {
            setPosition(b2body.getPosition().x, b2body.getPosition().y - getHeight() / 2);
            ypi = vY;
        } else {
            setPosition(b2body.getPosition().x, posY2 + ypi);
        }


        if ((b2body.getPosition().x - posX >= 0.3) || (b2body.getPosition().x - posX <= -0.3)) {
            vX = 0;
        }
        if ((b2body.getPosition().y - posY >= 0.3) || (b2body.getPosition().y - posY <= -0.3)) {
            vY = 0;
        }


    }

    private TextureRegion getFrame(float dt) {
        estadoAtual = getState();

        TextureRegion region = null;

        switch (estadoAtual){

            case ParadoFrente:
                region = linkParadoFrente;
                break;
            case AndandoFrente:
                region = (TextureRegion) andandoFrente.getKeyFrame(stateTimer);
                break;
            case ParadoLado:
                break;
            case AndandoLado:
                region = (TextureRegion) andandoLados.getKeyFrame(stateTimer);
                break;
            case ParadoCostas:
                break;
            case AndandoCostas:
                region = (TextureRegion) andandoCostas.getKeyFrame(stateTimer);
                break;
            default:
                region = linkParadoFrente;
                break;
        }

        if((vX<0 || !andandoDireita)&& !region.isFlipX()){
            region.flip(true,false);
            andandoDireita = false;
        }else{
            if((vX>0 || andandoDireita)&& region.isFlipX()){
                region.flip(true,false);
                andandoDireita = true;
            }
        }

        //stateTimer = estadoAtual == estadoAnterior ? stateTimer + dt : 0;
        stateTimer =  stateTimer<0.4?stateTimer + 2*dt:0 ;
        estadoAnterior = estadoAtual;

        return  region;

    }


    private State getState() {


        if(vX != 0) {

            return State.AndandoLado;

        }else{
            if(vY > 0){
                return State.AndandoCostas;
            }else{
                if(vY<0){
                    return State.AndandoFrente;
                }
            }

        }
        return State.ParadoFrente;
    }

}
