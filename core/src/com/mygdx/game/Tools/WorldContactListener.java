package com.mygdx.game.Tools;

import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Sprites.Alavanca;
import com.mygdx.game.Sprites.Heroi;
import com.mygdx.game.Sprites.InteractiveTiledObject;

import java.util.ArrayList;
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
    }

    @Override
    public void beginContact(Contact contact) {

        if(contact == null) return;
        Fixture fixA, fixB;
        fixA = contact.getFixtureA();
        fixB = contact.getFixtureB();
        if(fixA== null || fixB == null) return;
        if(fixA.getUserData()== null || fixB.getUserData() == null) return;

        System.out.println("A: "+ fixA.getUserData());
        System.out.println("B: "+ fixB.getUserData());

        if(fixA.getUserData().getClass().equals(Heroi.class) && fixB.getUserData().getClass().equals(Alavanca.class)){
            Heroi heroi = (Heroi) fixA.getUserData();
            Alavanca alavanca = (Alavanca) fixB.getUserData();
            if(alavanca.getHeroi().equals(heroi)){
                alavanca.alavancaChangeState();
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
