package com.ansh;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.physics.box2d.*;

public class BlackBird extends Bird{
    public Texture blackimg=new Texture("blackbirdfinal.png");
    private int specialpower;
    private int extraspeed;
    private Body blackbody;
    private final float PPM = 100f;
    public int impact=1;
    public BlackBird(World world,float x,float y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        //bodyDef.position.set(30 / PPM, 100 / PPM); // Initial position in meters
        bodyDef.position.set(x,y);

        blackbody = world.createBody(bodyDef);


        CircleShape shape = new CircleShape();
        shape.setRadius(25 / PPM); // Circle radius in meters


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.restitution = 0.5f; // Bounciness
        fixtureDef.friction = 0.5f;

        blackbody.createFixture(fixtureDef);
        shape.dispose();
    }
    public Body getBlackbody()
    {
        return this.blackbody;
    }
    public void use_special_ability()
    {

    }

    public void use_extra_speed()
    {

    }
    public void movefaster()
    {

    }


}
