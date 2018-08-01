package br.ufjf.dcc.progbotics.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import br.ufjf.dcc.progbotics.Sprites.Alavanca;
import br.ufjf.dcc.progbotics.Sprites.Heroi;

import java.util.List;

/**
 * Created by Andre Luiz on 20/02/2018.
 */

public class WorldContactListener implements ContactListener {


    private final List<Heroi> players;
    private final List<Alavanca> alavancas;

    public WorldContactListener(List<Heroi> players, List<Alavanca> alavancas) {
        this.players = players;
        this.alavancas = alavancas;
        System.out.println(alavancas.toString());
    }

    @Override
    public void beginContact(Contact contact) {

        if(contact == null) return;
        Fixture fixA, fixB;
        fixA = contact.getFixtureA();
        fixB = contact.getFixtureB();
        System.out.println(fixA +" "+fixB);
        System.out.println(fixA.getUserData() +" "+fixB.getUserData());
        if(fixA== null || fixB == null) return;
        if(fixA.getBody().getUserData()== null || fixB.getBody().getUserData() == null) return;

        System.out.println("A: "+ fixA.getBody().getUserData());
        System.out.println("B: "+ fixB.getBody().getUserData());

        if(fixA.getBody().getUserData().getClass().equals(Alavanca.class) && fixB.getBody().getUserData().getClass().equals(Heroi.class)){
            Heroi heroi = (Heroi) fixB.getBody().getUserData();
            Alavanca alavanca = (Alavanca) fixA.getBody().getUserData();
            if(alavanca.getHeroi().equals(heroi)){
                alavanca.alavancaChangeState();
                System.out.println("ALAVANCA LIGADA");
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}
