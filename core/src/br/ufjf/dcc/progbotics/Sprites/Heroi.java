package br.ufjf.dcc.progbotics.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import br.ufjf.dcc.progbotics.ProgBoticsGame;
import br.ufjf.dcc.progbotics.Screen.PlayScreen;

import java.util.ArrayList;

/**
 * Created by Andre Luiz on 16/11/2017.
 */

public class Heroi extends Sprite {

    public static final String COMMAND_UP = "up";
    public static final String COMMAND_RIGHT = "right";
    public static final String COMMAND_LEFT = "left";
    public static final String COMMAND_DOWN = "down";
    public static final String COMMAND_WAIT = "wait";
    private final String tipo;
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
    public ArrayList<String> comandos = new ArrayList<String>();
    public int comandoAtual = -1;
    public boolean pode= false;
    public boolean click= false;
    float vel;
    int corRobo;





    public Heroi(World world, PlayScreen screen, String tipo, Body body) {
        
        super(screen.getAtlas().findRegion("lpc-2"));
        this.tipo = tipo;
        int S = 64;
        this.world = world;

        comandos = new ArrayList<String>();

        estadoAtual = State.ParadoFrente;
        stateTimer = 0;
        andandoDireita = true;



        if(tipo.equals("heroi3")){
            corRobo=13;
        }else{
            if(tipo.equals("heroi2")){
                corRobo=5;
            }else{
                if(tipo.equals("heroi1")){
                    corRobo=9;
                }else{
                    if(tipo.equals("heroi4")){
                        corRobo=17;
                    }
                }
            }
        }



        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < 9; i++) {
            frames.add(new TextureRegion(getTexture(), S * i, S*(corRobo+1), S, S));

        }
        andandoFrente = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0; i < 9; i++) {
            frames.add(new TextureRegion(getTexture(), S * i, S*(corRobo-1), S, S));
        }

        andandoCostas = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 0; i < 9; i++) {
            frames.add(new TextureRegion(getTexture(), S * i, S*(corRobo), S, S));
        }

        andandoLados = new Animation(0.1f, frames);
        frames.clear();


        //defineHeroi(tipo);
        setB2Body(body);
        linkParadoFrente = new TextureRegion(getTexture(), 0, S*corRobo+1, S, S);
        paradoCostas = new TextureRegion(getTexture(), 0, S*corRobo-1, S, S);
        paradoLado = new TextureRegion(getTexture(), 0, S*corRobo, S, S);
        setBounds(this.b2body.getPosition().x, this.b2body.getPosition().y, S / ProgBoticsGame.PPM, S / ProgBoticsGame.PPM);
        setRegion(linkParadoFrente);


        posX = b2body.getPosition().x;
        posY = b2body.getPosition().y;
        vX = 0;
        vY = 0;
        posX2 = b2body.getPosition().x;
        posY2 = b2body.getPosition().y;
        xis = 0;
        ypi = 0;

        vel = 0.4f;

    }


    public void setB2Body(Body body){
        this.b2body = body;
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(64/6 / ProgBoticsGame.PPM);

        fdef.shape = shape;

        Fixture fixture = b2body.createFixture(fdef);

        fixture.setUserData(this);
        body.setUserData(this);

    }

    public void rodaComando(){
        if(cooldown > 0 || comandoAtual<0 || comandoAtual > comandos.size()-1) return;
        if(comandos.get(comandoAtual).equals(COMMAND_RIGHT)){
            vY=0;
            andaParaDireita();
        } else if(comandos.get(comandoAtual).equals(COMMAND_UP)){
            vX=0;
            andaParaCima();
        } else if(comandos.get(comandoAtual).equals(COMMAND_LEFT)){
            vY=0;
            andaParaEsquerda();
        } else if(comandos.get(comandoAtual).equals(COMMAND_DOWN)){
            vX=0;
            andaParaBaixo();
        } else if(comandos.get(comandoAtual).equals(COMMAND_WAIT)){
            naoAnda();
        }

            comandoAtual++;

        if(comandoAtual>=comandos.size()){
            comandoAtual = -1;
        }
    }

    public void colocaComandos(String comando){



            comandos.add(comandos.size(),comando);


    }

    public void defineHeroi(String tipo) {

        bdef = new BodyDef();
        bdef.position.set(35 / ProgBoticsGame.PPM, 31 / ProgBoticsGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(64/6 / ProgBoticsGame.PPM);

        fdef.shape = shape;

        Fixture fixture = b2body.createFixture(fdef);

        fixture.setUserData(this);
    }


    public void update(float dt) {

        setRegion(getFrame(dt));

        if(pode)
        rodaComando();

        movimentos(dt);
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - 0.28f*getHeight());
    }


    private void andaParaEsquerda() {
        posX = b2body.getPosition().x;
        posX2 = getX();
        vX = -1*vel;
        rotacao = 180;
        cooldown = 1.0f;
    }

    private void andaParaBaixo() {
        posY = b2body.getPosition().y;
        posY2 = getY();
        vY = -1*vel;
        rotacao = 270;
        cooldown = 1.0f;
    }

    private void andaParaCima() {
        posY = b2body.getPosition().y;
        posY2 = getY();
        vY = vel;
        rotacao = 90;
        cooldown = 1.0f;
    }

    private void andaParaDireita() {
        posX = b2body.getPosition().x;
        posX2 = getX();
        vX = vel;
        rotacao = 0;
        cooldown = 1.0f;
    }

    private void naoAnda() {
        posX = b2body.getPosition().x;
        posX2 = getX();
        vX = 0;
        vY = 0;
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


    public String getTipo() {
        return tipo;
    }
}
