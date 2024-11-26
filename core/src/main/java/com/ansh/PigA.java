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

public class PigA extends Pig{
    public Texture pigApic=new Texture("pigafinal.png");
    private Body pigabody;
    private final float PPM = 100f;
    public PigA(World world, float x, float y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody; // Allow the pig to move dynamically
        bodyDef.position.set(x, y);

        pigabody = world.createBody(bodyDef);

        // Define the rectangular shape
        PolygonShape shape = new PolygonShape();
        float width = 50 / PPM; // Adjust the width of the rectangle
        float height = 50 / PPM; // Adjust the height of the rectangle
        shape.setAsBox(width / 2, height / 2); // Set half-width and half-height

        // Define the fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.8f; // Adjust density for desired weight
        fixtureDef.restitution = 0.2f; // Less bounciness
        fixtureDef.friction = 0.7f; // Higher friction for stability

        // Attach the fixture to the body
        pigabody.createFixture(fixtureDef);

        // Dispose of the shape after use
        shape.dispose();
    }
    public Body getPigAbody()
    {
        return this.pigabody;
    }
}
