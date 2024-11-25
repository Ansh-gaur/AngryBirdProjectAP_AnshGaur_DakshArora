package com.ansh;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class IronBlock extends Block {
    public Texture ironimg=new Texture("ironblock.png");
    private Body ironbody;
    private final float PPM = 100f;
    public IronBlock(World world, float x, float y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //bodyDef.position.set(30 / PPM, 100 / PPM); // Initial position in meters
        bodyDef.position.set(x,y);

        ironbody = world.createBody(bodyDef);


        CircleShape shape = new CircleShape();
        shape.setRadius(25 / PPM); // Circle radius in meters


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.restitution = 0.5f; // Bounciness
        fixtureDef.friction = 0.5f;

        ironbody.createFixture(fixtureDef);
        shape.dispose();
    }
    public Body getIronbody()
    {
        return this.ironbody;
    }
}
