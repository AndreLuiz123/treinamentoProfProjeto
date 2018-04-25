package com.mygdx.game.Tools;

import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Sprites.Alavanca;
import com.mygdx.game.Sprites.InteractiveTiledObject;

import java.util.ArrayList;

/**
 * Created by Andre Luiz on 20/02/2018.
 */

public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {


        System.out.println(contact.getFixtureA().getUserData());
        System.out.println(contact.getFixtureB().getUserData());
        if(contact.getFixtureB().getUserData().equals("Alavanca")){
            System.out.println("DEU CERTO");
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
