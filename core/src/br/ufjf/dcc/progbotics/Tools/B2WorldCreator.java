package br.ufjf.dcc.progbotics.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc.progbotics.ProgBoticsGame;
import br.ufjf.dcc.progbotics.Screen.PlayScreen;
import br.ufjf.dcc.progbotics.Sprites.Alavanca;
import br.ufjf.dcc.progbotics.Sprites.Heroi;

/**
 * Created by Andre Luiz on 20/02/2018.
 */

public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map, List<Heroi> players,List<Alavanca> alavancas, PlayScreen screen){

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/ ProgBoticsGame.PPM, (rect.getY() + rect.getHeight()/2)/ ProgBoticsGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/ ProgBoticsGame.PPM, rect.getHeight()/2/ ProgBoticsGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);

        }
        int hn = 1;
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                bdef.type = BodyDef.BodyType.DynamicBody;
                bdef.position.set((rect.getX() + rect.getWidth() / 2) / ProgBoticsGame.PPM, (rect.getY() + rect.getHeight() / 2) / ProgBoticsGame.PPM);
                body = world.createBody(bdef);
                shape.setAsBox(rect.getWidth() / 2 / ProgBoticsGame.PPM, rect.getHeight() / 2 / ProgBoticsGame.PPM);
                fdef.shape = shape;
                Heroi h = new Heroi(world, screen, "heroi" + hn++, body);
                h.setB2Body(body);
                players.add(h);
        }
         hn = 0;
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / ProgBoticsGame.PPM, (rect.getY() + rect.getHeight() / 2) / ProgBoticsGame.PPM);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2 / ProgBoticsGame.PPM, rect.getHeight() / 2 / ProgBoticsGame.PPM);
            fdef.shape = shape;
            Alavanca h = new Alavanca(world, screen, players.get(hn), body);
            hn++;
            body.createFixture(fdef);
            h.setB2Body(body);
            //h.b2body.setTransform(bdef.position.x, bdef.position.y, 0);
            alavancas.add(h);
        }



    }


}
