package com.ansh;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game; // Make sure this is imported
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGameScreen implements Screen {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Texture arrowTexture;
    private ImageButton backButton;
    private Screen previousScreen;// Reference to the previous screen
    private Catapult cata;
    private Image cata_image;
    private BlackBird bb;
    private Image bb_image;
    private GlassBlock gbl1,gbl2,gbl3,gbl4,gbl5,gbl6;
    private Image gb1,gb2,gb3,gb4,gb5,gb6;
    private PigA piga;
    private Image pig_image;
    private Texture vButton;
    private Texture lButton;
    private Image v_image;
    private Image l_image;

    public World world; // Box2D world
    private final float PPM = 100f;

    private Body groundBody;


    // Constructor to pass the previous screen
    public MainGameScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.8f), true);
        // Initialize the stage and set it to handle input
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        cata=new Catapult();
        bb=new BlackBird(world,0.6f,1.3f);

        // Define the ground body
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody; // Ground doesn't move
        groundBodyDef.position.set(0, 0); // Set ground position at the bottom of the screen

// Create the ground body in the world
        groundBody = world.createBody(groundBodyDef);

// Define the ground shape as a box
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(Gdx.graphics.getWidth() / PPM / 2, 10 / PPM); // Width is screen width, height is small

// Create the fixture for collision
        FixtureDef groundFixtureDef = new FixtureDef();
        groundFixtureDef.shape = groundBox;
        groundFixtureDef.friction = 0.5f; // Optional: control sliding
        groundBody.createFixture(groundFixtureDef);

// Dispose the shape after creating the fixture
        groundBox.dispose();









        // Load the background texture
        backgroundTexture = new Texture("mainbacker.jpg"); // Replace with your background image path
        backgroundImage = new Image(backgroundTexture);


        // Set the background to fill the screen
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Add the background image to the stage
        stage.addActor(backgroundImage);
        //Catapult
        cata_image=new Image(cata.cataimg);
        cata_image.setSize(Gdx.graphics.getWidth() / 10, (Gdx.graphics.getHeight()+1000) / 10);
        cata_image.setPosition(30,10);
        stage.addActor(cata_image);
        //blackbird

        bb_image=new Image(bb.blackimg);
        bb_image.setSize((Gdx.graphics.getWidth()) / 10, Gdx.graphics.getHeight() / 10);
        //bb_image.setPosition(30,100);
        bb_image.setPosition(
            bb.getBlackbody().getPosition().x * PPM - bb_image.getWidth() / 2,
            bb.getBlackbody().getPosition().y * PPM - bb_image.getHeight() / 2
        );
        stage.addActor(bb_image);

        //structure of glass

        gbl1=new GlassBlock(world,4.5f,0.3f);
        gb1=new Image(gbl1.glassimg);
        gb1.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb1.setPosition(500,10);
        gb1.setPosition(
            gbl1.getGlassbody().getPosition().x * PPM - gb1.getWidth() / 2,
            gbl1.getGlassbody().getPosition().y * PPM - gb1.getHeight() / 2
        );
        stage.addActor(gb1);

        gbl2=new GlassBlock(world,4.5f,0.3f+gb1.getHeight()/PPM);
        gb2=new Image(gbl2.glassimg);
        gb2.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb2.setPosition(
            gbl2.getGlassbody().getPosition().x * PPM - gb2.getWidth() / 2,
            gbl2.getGlassbody().getPosition().y * PPM - gb2.getHeight() / 2
        );
        stage.addActor(gb2);


        gbl3=new GlassBlock(world,4.5f,0.3f+2*gb1.getHeight()/PPM);
        gb3=new Image(gbl3.glassimg);
        gb3.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);

        gb3.setPosition(
            gbl3.getGlassbody().getPosition().x * PPM - gb3.getWidth() / 2,
            gbl3.getGlassbody().getPosition().y * PPM - gb3.getHeight() / 2
        );
        stage.addActor(gb3);

        gbl4=new GlassBlock(world,4.5f,0.3f+3*gb1.getHeight()/PPM);
        gb4=new Image(gbl4.glassimg);
        gb4.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb4.setPosition(
            gbl4.getGlassbody().getPosition().x * PPM - gb4.getWidth() / 2,
            gbl4.getGlassbody().getPosition().y * PPM - gb4.getHeight() / 2
        );
        stage.addActor(gb4);

        gbl5=new GlassBlock(world,4.5f,0.3f+4*gb1.getHeight()/PPM);
        gb5=new Image(gbl5.glassimg);
        gb5.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb5.setPosition(
            gbl5.getGlassbody().getPosition().x * PPM - gb5.getWidth() / 2,
            gbl5.getGlassbody().getPosition().y * PPM - gb5.getHeight() / 2
        );
        stage.addActor(gb5);

        gbl6=new GlassBlock(world,4.5f,0.3f+5*gb1.getHeight()/PPM);
        gb6=new Image(gbl6.glassimg);
        gb6.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb6.setPosition(
            gbl6.getGlassbody().getPosition().x * PPM - gb6.getWidth() / 2,
            gbl6.getGlassbody().getPosition().y * PPM - gb6.getHeight() / 2
        );
        stage.addActor(gb6);

        piga=new PigA(world,4.5f,0.3f+6*gb1.getHeight()/PPM);
        pig_image=new Image(piga.pigApic);
        pig_image.setSize(Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        //pig_image.setPosition(500,6*(gb1.getHeight()+2));
        pig_image.setPosition(
            piga.getPigAbody().getPosition().x * PPM - pig_image.getWidth() / 2,
            piga.getPigAbody().getPosition().y * PPM - pig_image.getHeight() / 2
        );
        stage.addActor(pig_image);


        //shift to victory screen
        vButton=new Texture("vicButton.png");
        v_image=new Image(vButton);
        v_image.setSize(Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        v_image.setPosition(10, 380);
        stage.addActor(v_image);

        //shift to lost screen
        lButton=new Texture("lostButton.png");
        l_image=new Image(lButton);
        l_image.setSize(Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        l_image.setPosition(10, 320);
        stage.addActor(l_image);

        Screen v_s=new MainGameScreen(this.previousScreen);
        //adding listeners
        v_image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("victory screen clicked!");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new VictoryScreen(v_s));
            }
        });

        l_image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("lost screen clicked!");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LostScreen(v_s));
            }
        });



        // Load the arrow image texture
        arrowTexture = new Texture("arrowmain.jpg"); // Replace with your arrow image file

        // Create the back button using the arrow texture
        backButton = new ImageButton(new TextureRegionDrawable(arrowTexture));
        backButton.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 20); // Adjust the size as needed
        backButton.setPosition(10, Gdx.graphics.getHeight() - backButton.getHeight() - 10); // Top-left corner with padding

        // Add click listener to the back button
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Go back to the previous screen
                ((Game) Gdx.app.getApplicationListener()).setScreen(previousScreen);
            }
        });

        // Add the back button to the stage
        stage.addActor(backButton);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //world.step(1 / 60f, 6, 2);

        bb_image.setPosition(
            bb.getBlackbody().getPosition().x * PPM - bb_image.getWidth() / 2,
            bb.getBlackbody().getPosition().y * PPM - bb_image.getHeight() / 2
        );
        gb1.setPosition(
            gbl1.getGlassbody().getPosition().x * PPM - gb1.getWidth() / 2,
            gbl1.getGlassbody().getPosition().y * PPM - gb1.getHeight() / 2
        );
        gb2.setPosition(
            gbl2.getGlassbody().getPosition().x * PPM - gb2.getWidth() / 2,
            gbl2.getGlassbody().getPosition().y * PPM - gb2.getHeight() / 2
        );
        gb3.setPosition(
            gbl3.getGlassbody().getPosition().x * PPM - gb3.getWidth() / 2,
            gbl3.getGlassbody().getPosition().y * PPM - gb3.getHeight() / 2
        );
        gb4.setPosition(
            gbl4.getGlassbody().getPosition().x * PPM - gb4.getWidth() / 2,
            gbl4.getGlassbody().getPosition().y * PPM - gb4.getHeight() / 2
        );
        gb5.setPosition(
            gbl5.getGlassbody().getPosition().x * PPM - gb5.getWidth() / 2,
            gbl5.getGlassbody().getPosition().y * PPM - gb5.getHeight() / 2
        );
        gb6.setPosition(
            gbl6.getGlassbody().getPosition().x * PPM - gb6.getWidth() / 2,
            gbl6.getGlassbody().getPosition().y * PPM - gb6.getHeight() / 2
        );
        pig_image.setPosition(
            piga.getPigAbody().getPosition().x * PPM - pig_image.getWidth() / 2,
            piga.getPigAbody().getPosition().y * PPM - pig_image.getHeight() / 2
        );


        // Update and draw the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport with the new width and height
        stage.getViewport().update(width, height, true);

        // Update background size to fill the screen
        backgroundImage.setSize(width, height);

        // Update the positions and sizes of game elements
        cata_image.setSize(width / 10, (height + 1000) / 10);
        cata_image.setPosition(30, 10);

        bb_image.setSize(width / 10, height / 10);
        bb_image.setPosition(30, 100);

        // Reposition and resize glass blocks
        float blockHeight = height / 10;
        float blockWidth = width / 10;

        gb1.setSize(blockWidth, blockHeight);
        gb1.setPosition(500, 10);



        gb2.setSize(blockWidth, blockHeight);
        gb2.setPosition(500, gb1.getY() + blockHeight + 3);

        gb3.setSize(blockWidth, blockHeight);
        gb3.setPosition(500, gb2.getY() + blockHeight + 3);

        gb4.setSize(blockWidth, blockHeight);
        gb4.setPosition(500, gb3.getY() + blockHeight + 3);

        gb5.setSize(blockWidth, blockHeight);
        gb5.setPosition(500, gb4.getY() + blockHeight + 2);

        gb6.setSize(blockWidth, blockHeight);
        gb6.setPosition(500, gb5.getY() + blockHeight + 2);


        pig_image.setSize(blockWidth, blockHeight);
        pig_image.setPosition(500, gb6.getY() + blockHeight + 2);


        // Update the sizes and positions of buttons
        v_image.setSize(width / 10, height / 10);
        v_image.setPosition(10, 380);

        l_image.setSize(width / 10, height / 10);
        l_image.setPosition(10, 320);

        backButton.setSize(width / 20, height / 20);
        backButton.setPosition(10, height - backButton.getHeight() - 10);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        // Dispose of assets to free resources
        stage.dispose();
        backgroundTexture.dispose();
        arrowTexture.dispose();
        vButton.dispose();
        lButton.dispose();
    }
}
