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

public class GlassBlock extends Block {
    public Texture glassimg=new Texture("glassblock.png");
    private Body glassbody;
    private final float PPM = 100f;
    public GlassBlock(World world,float x,float y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody; // Allow the block to move and fall
        bodyDef.position.set(x, y);

        glassbody = world.createBody(bodyDef);

        // Define the shape as a box
        PolygonShape shape = new PolygonShape();
        float width = 50 / PPM; // Adjust width of the block in meters
        float height = 100 / PPM; // Adjust height of the block in meters
        shape.setAsBox(width / 2, height / 2); // Set box dimensions (half-width and half-height)

        // Define the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f; // Adjust density for desired mass
        fixtureDef.restitution = 0.1f; // Low bounciness
        fixtureDef.friction = 0.5f;

        // Attach the fixture to the body
        glassbody.createFixture(fixtureDef);

        // Dispose of the shape after use
        shape.dispose();
    }
    public Body getGlassbody()
    {
        return this.glassbody;
    }




}
