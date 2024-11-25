package com.ansh;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public class RedBird extends Bird {
    public Texture redimg=new Texture("redbirdfinal.png");
    private int specialpower;
    private int extraspeed;
    private Body redbody;
    private final float PPM = 100f;
    public RedBird(World world,float x,float y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //bodyDef.position.set(30 / PPM, 100 / PPM); // Initial position in meters
        bodyDef.position.set(x,y);

        redbody = world.createBody(bodyDef);


        CircleShape shape = new CircleShape();
        shape.setRadius(25 / PPM); // Circle radius in meters


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.restitution = 0.5f; // Bounciness
        fixtureDef.friction = 0.5f;

        redbody.createFixture(fixtureDef);
        shape.dispose();
    }
    public Body getRedbody()
    {
        return this.redbody;
    }

    public void use_special_ability()
    {

    }

    public void dropextrabomb()
    {

    }
    public void use_extra_speed()
    {

    }
}
