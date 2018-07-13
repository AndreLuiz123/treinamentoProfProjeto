package br.ufjf.dcc.progbotics.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import br.ufjf.dcc.progbotics.MyGdxGame;

/**
 * Created by Andre Luiz on 20/02/2018.
 */

public abstract class InteractiveTiledObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;


    public InteractiveTiledObject(World world, TiledMap map, Rectangle bounds){

        this.world=world;
        this.map=map;
        this.bounds=bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth()/2)/ MyGdxGame.PPM, (bounds.getY() + bounds.getHeight()/2)/MyGdxGame.PPM);

        body=world.createBody(bdef);
        shape.setAsBox((bounds.getWidth()/2)/ MyGdxGame.PPM, (bounds.getHeight()/2)/MyGdxGame.PPM);
        fdef.shape=shape;

        fixture = body.createFixture(fdef);

    }

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();

        filter.categoryBits= filterBit;

        fixture.setFilterData(filter);

    }


}
