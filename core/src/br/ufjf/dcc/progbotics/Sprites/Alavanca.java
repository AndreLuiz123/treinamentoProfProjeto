package br.ufjf.dcc.progbotics.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import br.ufjf.dcc.progbotics.ProgBoticsGame;
import br.ufjf.dcc.progbotics.Screen.PlayScreen;

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
    public Body b2body;



    public  int t, S;

    public Alavanca (World world, PlayScreen screen, Heroi personagem, Body body){

        super(screen.getAtlas().findRegion("lpc-2"));
         S=64;
         t=640;

        ligada = false;
        this.world = world;

        if(personagem.getTipo().equals("heroi1") ){
            alavanca = new  TextureRegion(getTexture(), S*9, S*4+5, S, S);
        }else{
            if(personagem.getTipo().equals("heroi2") ){
                alavanca = new  TextureRegion(getTexture(), S*9, S*6+5, S, S);
            }else{
                if(personagem.getTipo().equals("heroi3") ){
                    alavanca = new TextureRegion(getTexture(), S*9, S*7+15, S, S);
                }
            }
        }

        setBounds(0,0,24/ ProgBoticsGame.PPM,24/ ProgBoticsGame.PPM);

        setRegion(alavanca);

        this.personagem = personagem;



        //defineAlavanca();
        setB2Body(body);

    }



    public void update(float dt){

        //vermelho: TextureRegion(getTexture(), S*9, S*4+5, S, S);
        //azul:  TextureRegion(getTexture(), S*9, S*6+5, S, S);
        //amarelo:  TextureRegion(getTexture(), S*9, S*7+15, S, S);
        //apagado: new TextureRegion(getTexture(), S*10 - 19, S*7+15, S, S);

        estadoAlavanca();
       // alavanca = new TextureRegion(getTexture(), S*9, S*7+15, S, S);
        setBounds(this.b2body.getPosition().x - 15/ ProgBoticsGame.PPM, this.b2body.getPosition().y - 5/ ProgBoticsGame.PPM,24/ ProgBoticsGame.PPM,24/ ProgBoticsGame.PPM);
        setRegion(alavanca);

    }

    public void estadoAlavanca(){

        if(!ligada){
            alavanca = getAlavanca();
        //   t=640;
        }else{
           // t=S*10 - 19;

            alavanca =  new TextureRegion(getTexture(), S*10 - 19, S*7+15, S, S);
        }

    }

    public void alavancaChangeState(){


            if(!ligada){
                ligada=true;
                System.out.println("teste");
            }
    }

    public void setB2Body(Body body){
        this.b2body = body;
        body.setUserData(this);
    }

    public void defineAlavanca() {
       BodyDef bdef = new BodyDef();
        bdef.position.set(35 / ProgBoticsGame.PPM, 100 / ProgBoticsGame.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(64/6 / ProgBoticsGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        Fixture fixture = b2body.createFixture(fdef);

        fixture.setUserData(this);

    }

    public void colideComHeroi(){



    }

    public Heroi getHeroi(){
        return personagem;
    }

    public boolean isLigada() {
        return ligada;
    }

    public TextureRegion getAlavanca() {
        return alavanca;
    }




}



