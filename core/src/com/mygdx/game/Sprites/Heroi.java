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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andre Luiz on 16/11/2017.
 */

public class Heroi extends Sprite {

    public World world;
    public Body b2body;
    public BodyDef bdef;
    private TextureRegion linkParadoFrente, paradoCostas, paradoLado;

    public enum State {ParadoFrente, AndandoFrente, ParadoLado, AndandoLado, ParadoCostas, AndandoCostas}

    private State estadoAtual, estadoAnterior;
    private Animation andandoLados, andandoFrente, andandoCostas;
    private float stateTimer;
    private boolean andandoDireita;
    private float posX, posX2, posY, posY2;
    private float vX, vY, xis, ypi;
    private float rotacao;
    public float cooldown;
    public List<String> comandos = new ArrayList<String>();
    public int comandoAtual = -1;

    public Heroi(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("lpc-2"));
        int S = 64;
        this.world = world;

        comandos = new ArrayList<String>(){{
           add("right");
           add("down");
           add("left");
           add("up");
           add("left");
        }};

        estadoAtual = State.ParadoFrente;
        stateTimer = 0;
        andandoDireita = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(getTexture(), S * i, S*10, S, S));
        }
        andandoFrente = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0; i < 9; i++) {
            frames.add(new TextureRegion(getTexture(), S * i, S*8, S, S));
        }

        andandoCostas = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0; i < 9; i++) {
            frames.add(new TextureRegion(getTexture(), S * i, S*9, S, S));
        }

        andandoLados = new Animation(0.1f, frames);
        frames.clear();


        defineHeroi();
        linkParadoFrente = new TextureRegion(getTexture(), 0, S*10, S, S);
        paradoCostas = new TextureRegion(getTexture(), 0, S*8, S, S);
        paradoLado = new TextureRegion(getTexture(), 0, S*9, S, S);
        setBounds(this.b2body.getPosition().x, this.b2body.getPosition().y, 24 / MyGdxGame.PPM, 24 / MyGdxGame.PPM);
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

    public void rodaComando(){
        if(cooldown > 0 || comandoAtual<0 || comandoAtual > comandos.size()-1) return;
        if(comandos.get(comandoAtual).equals("right")){
            andaParaDireita();
        } else if(comandos.get(comandoAtual).equals("up")){
            andaParaCima();
        } else if(comandos.get(comandoAtual).equals("left")){
            andaParaEsquerda();
        } else if(comandos.get(comandoAtual).equals("down")){
            andaParaBaixo();
        }
        comandoAtual ++;
        if(comandoAtual>=comandos.size()){
            comandoAtual = -1;
        }
    }

    public void defineHeroi() {

        bdef = new BodyDef();
        bdef.position.set(35 / MyGdxGame.PPM, 31 / MyGdxGame.PPM);
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
        rodaComando();
        movimentos(dt);
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - 0.28f*getHeight());

    }

    public void movimentoControles() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            comandoAtual = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            rodaComando();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            andaParaCima();

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            andaParaBaixo();
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {

            andaParaDireita();

        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {

            andaParaEsquerda();

        }

    }

    private void andaParaEsquerda() {
        posX = b2body.getPosition().x;
        posX2 = getX();
        vX = -0.3f;
        rotacao = 180;
        cooldown = 1.0f;
    }

    private void andaParaBaixo() {
        posY = b2body.getPosition().y;
        posY2 = getY();
        vY = -0.3f;
        rotacao = 270;
        cooldown = 1.0f;
    }

    private void andaParaCima() {
        posY = b2body.getPosition().y;
        posY2 = getY();
        vY = 0.3f;
        rotacao = 90;
        cooldown = 1.0f;
    }

    private void andaParaDireita() {
        posX = b2body.getPosition().x;
        posX2 = getX();
        vX = 0.3f;
        rotacao = 0;
        cooldown = 1.0f;
    }

    public void movimentos(float dt) {

        if(cooldown>0) {
            b2body.setLinearVelocity(vX, vY);
            cooldown -= dt;
        } else {
            b2body.setLinearVelocity(0,0);
            vX = 0;
            vY = 0;
        }
        /*
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
        */

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
                region = paradoLado;
                break;
            case AndandoLado:
                region = (TextureRegion) andandoLados.getKeyFrame(stateTimer);
                break;
            case ParadoCostas:
                region = paradoCostas;
                break;
            case AndandoCostas:
                region = (TextureRegion) andandoCostas.getKeyFrame(stateTimer);
                break;
            default:
                region = linkParadoFrente;
                break;
        }

        if((vX<0 || andandoDireita)&& region.isFlipX()){
            region.flip(true,false);
            andandoDireita = true;
        }else{
            if((vX>0 || !andandoDireita)&& !region.isFlipX()){
                region.flip(true,false);
                andandoDireita = false;
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
                }else{
                    if(rotacao == 90){
                        return  State.ParadoCostas;
                    }else{
                        if(rotacao == 270){
                            return  State.ParadoFrente;
                        }else{
                            if(rotacao==0 || rotacao==180){
                                return  State.ParadoLado;
                            }
                        }
                    }

                }
            }
        }
        return State.ParadoFrente;
    }

}
