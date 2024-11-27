package com.ansh;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game; // Make sure this is imported
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainGameScreen2 implements Screen {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Texture arrowTexture;
    private ImageButton backButton;
    private Screen previousScreen;// Reference to the previous screen
    private Catapult cata;
    private Image cata_image;
    private RedBird rr;
    private Image rr_image;
    private IronBlock gbl1,gbl2,gbl3,gbl4,gbl5,gbl6,gbl7,gbl8,gbl9,gbl10,gbl11;
    private Image gb1,gb2,gb3,gb4,gb5,gb6,gb7,gb8,gb9,gb10,gb11;
    private PigB pigb;
    private Image pig_image;
    private Texture vButton;
    private Texture lButton;
    private Image v_image;
    private Image l_image;

    public World world; // Box2D world
    private final float PPM = 100f;

    private Body groundBody; // The Box2D body for the ground

    //Variables for dragging
    private boolean isDragging = false; // Whether the bird is being dragged
    private Vector2 dragStart = new Vector2(); // Start position of the drag
    private Vector2 dragEnd = new Vector2(); // End position of the drag
    private Vector2 birdInitialPosition = new Vector2();

    //Variables for collision detection
    private boolean pigHit = false; // Flag to check if pig was hit
    private float postLaunchTimer = 3f;// Time to wait after launch to determine result

    private Texture s_b;
    private Image save_image;




    // Constructor to pass the previous screen
    public MainGameScreen2(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.8f), true);

        //Adding contact Listener
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                // Check if the pig and the bird are in contact
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if ((fixtureA.getBody() == pigb.getPigBbody() && fixtureB.getBody() == rr.getRedbody()) ||
                    (fixtureB.getBody() == pigb.getPigBbody() && fixtureA.getBody() == rr.getRedbody())) {
                    pigHit = true;
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
        });
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        cata = new Catapult();
        rr = new RedBird(world, 0.6f, 1.3f);

        // Define the ground body
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody; // Ground doesn't move
        groundBodyDef.position.set(Gdx.graphics.getWidth() / PPM / 2, 0); // Set ground position at the bottom of the screen

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
        cata_image = new Image(cata.cataimg);
        cata_image.setSize(Gdx.graphics.getWidth() / 10, (Gdx.graphics.getHeight() + 1000) / 10);
        cata_image.setPosition(30, 10);
        stage.addActor(cata_image);

        rr_image = new Image(rr.redimg);
        rr_image.setSize((Gdx.graphics.getWidth()) / 10, Gdx.graphics.getHeight() / 10);
        //bb_image.setPosition(30,100);
        rr_image.setPosition(
            rr.getRedbody().getPosition().x * PPM - rr_image.getWidth() / 2,
            rr.getRedbody().getPosition().y * PPM - rr_image.getHeight() / 2
        );
        stage.addActor(rr_image);

        gbl1 = new IronBlock(world, 4.5f, 0.3f);
        gb1 = new Image(gbl1.ironimg);
        gb1.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb1.setPosition(500,10);
        gb1.setPosition(
            gbl1.getIronbody().getPosition().x * PPM - gb1.getWidth() / 2,
            gbl1.getIronbody().getPosition().y * PPM - gb1.getHeight() / 2
        );
        stage.addActor(gb1);

        gbl2 = new IronBlock(world, 4.5f, 0.3f + gb1.getHeight() / PPM);
        gb2 = new Image(gbl2.ironimg);
        gb2.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb2.setPosition(
            gbl2.getIronbody().getPosition().x * PPM - gb2.getWidth() / 2,
            gbl2.getIronbody().getPosition().y * PPM - gb2.getHeight() / 2
        );
        stage.addActor(gb2);


        gbl3 = new IronBlock(world, 4.5f, 0.3f + 2 * gb1.getHeight() / PPM);
        gb3 = new Image(gbl3.ironimg);
        gb3.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);

        gb3.setPosition(
            gbl3.getIronbody().getPosition().x * PPM - gb3.getWidth() / 2,
            gbl3.getIronbody().getPosition().y * PPM - gb3.getHeight() / 2
        );
        stage.addActor(gb3);

        gbl4 = new IronBlock(world, 4.5f, 0.3f + 3 * gb1.getHeight() / PPM);
        gb4 = new Image(gbl4.ironimg);
        gb4.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb4.setPosition(
            gbl4.getIronbody().getPosition().x * PPM - gb4.getWidth() / 2,
            gbl4.getIronbody().getPosition().y * PPM - gb4.getHeight() / 2
        );
        stage.addActor(gb4);

        gbl5 = new IronBlock(world, 4.5f, 0.3f + 4 * gb1.getHeight() / PPM);
        gb5 = new Image(gbl5.ironimg);
        gb5.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb5.setPosition(
            gbl5.getIronbody().getPosition().x * PPM - gb5.getWidth() / 2,
            gbl5.getIronbody().getPosition().y * PPM - gb5.getHeight() / 2
        );
        stage.addActor(gb5);

        gbl6 = new IronBlock(world, 4.5f, 0.3f + 5 * gb1.getHeight() / PPM);
        gb6 = new Image(gbl6.ironimg);
        gb6.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb6.setPosition(
            gbl6.getIronbody().getPosition().x * PPM - gb6.getWidth() / 2,
            gbl6.getIronbody().getPosition().y * PPM - gb6.getHeight() / 2
        );
        stage.addActor(gb6);

        gbl7 = new IronBlock(world, 3.85f, 0.3f);
        gb7 = new Image(gbl7.ironimg);
        gb7.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb1.setPosition(500,10);
        gb7.setPosition(
            gbl7.getIronbody().getPosition().x * PPM - gb7.getWidth() / 2,
            gbl7.getIronbody().getPosition().y * PPM - gb7.getHeight() / 2
        );
        stage.addActor(gb7);

        gbl8 = new IronBlock(world, 3.85f, 0.3f + gb7.getHeight() / PPM);
        gb8 = new Image(gbl8.ironimg);
        gb8.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb8.setPosition(
            gbl8.getIronbody().getPosition().x * PPM - gb8.getWidth() / 2,
            gbl8.getIronbody().getPosition().y * PPM - gb8.getHeight() / 2
        );
        stage.addActor(gb8);

        gbl9 = new IronBlock(world, 3.85f, 0.3f + 2*gb7.getHeight() / PPM);
        gb9 = new Image(gbl9.ironimg);
        gb9.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb9.setPosition(
            gbl9.getIronbody().getPosition().x * PPM - gb9.getWidth() / 2,
            gbl9.getIronbody().getPosition().y * PPM - gb9.getHeight() / 2
        );
        stage.addActor(gb9);

        gbl10 = new IronBlock(world, 3.85f, 0.3f + 3*gb7.getHeight() / PPM);
        gb10 = new Image(gbl10.ironimg);
        gb10.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb10.setPosition(
            gbl10.getIronbody().getPosition().x * PPM - gb10.getWidth() / 2,
            gbl10.getIronbody().getPosition().y * PPM - gb10.getHeight() / 2
        );
        stage.addActor(gb10);

        gbl11 = new IronBlock(world, 3.85f, 0.3f + 4*gb7.getHeight() / PPM);
        gb11 = new Image(gbl11.ironimg);
        gb11.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //gb2.setPosition(500,gb1.getHeight()+3);
        gb11.setPosition(
            gbl11.getIronbody().getPosition().x * PPM - gb11.getWidth() / 2,
            gbl11.getIronbody().getPosition().y * PPM - gb11.getHeight() / 2
        );
        stage.addActor(gb11);



        pigb = new PigB(world, 4.5f, 0.3f + 6 * gb1.getHeight() / PPM + 0.1f);
        pig_image = new Image(pigb.pigBpic);
        pig_image.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        //pig_image.setPosition(500,6*(gb1.getHeight()+2));
        pig_image.setPosition(
            pigb.getPigBbody().getPosition().x * PPM - pig_image.getWidth() / 2,
            pigb.getPigBbody().getPosition().y * PPM - pig_image.getHeight() / 2
        );
        stage.addActor(pig_image);


        //shift to victory screen
        vButton = new Texture("vicButton.png");
        v_image = new Image(vButton);
        v_image.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        v_image.setPosition(10, 380);
        stage.addActor(v_image);

        //shift to lost screen
        lButton = new Texture("lostButton.png");
        l_image = new Image(lButton);
        l_image.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        l_image.setPosition(10, 320);
        stage.addActor(l_image);

        s_b=new Texture("savebutton11.png");
        save_image=new Image(s_b);
        save_image.setSize(Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
        save_image.setPosition(10, 250);
        stage.addActor(save_image);

        Screen v_s = new MainGameScreen2(this.previousScreen);
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

        save_image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Save game clicked!");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
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


        rr_image.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                isDragging = true;
                dragStart.set(x + rr_image.getX(), y + rr_image.getY()); // Initial touch position
                birdInitialPosition.set(rr.getRedbody().getPosition()); // Store initial position of the bird
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if (isDragging) {
                    // Update the drag position (adjust for image offset)
                    dragEnd.set(x + rr_image.getX(), y + rr_image.getY());
                    // Update bird's image position to follow drag
                    rr_image.setPosition(dragEnd.x - rr_image.getWidth() / 2, dragEnd.y - rr_image.getHeight() / 2);
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (isDragging) {
                    isDragging = false;

                    // Calculate drag vector and release velocity
                    Vector2 releaseVector = new Vector2(
                        birdInitialPosition.x * PPM - dragEnd.x,
                        birdInitialPosition.y * PPM - dragEnd.y
                    ).scl(1 / PPM); // Scale to world coordinates
                    rr.getRedbody().setType(BodyDef.BodyType.DynamicBody);
                    // Apply impulse to bird's Box2D body
                    rr.getRedbody().applyLinearImpulse(
                        releaseVector.scl(10f), // Adjust the scalar for desired strength
                        rr.getRedbody().getWorldCenter(),
                        true
                    );

                    // Reset bird's image position to be updated by physics
                    rr_image.setPosition(
                        rr.getRedbody().getPosition().x * PPM - rr_image.getWidth() / 2,
                        rr.getRedbody().getPosition().y * PPM - rr_image.getHeight() / 2
                    );
                }
            }
        });
    }
    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //world.step(1 / 60f, 6, 2);
        world.step(1 / 60f, 6, 2);

        // Update bird position (if not dragging)
        if (!isDragging) {
            rr_image.setPosition(
                rr.getRedbody().getPosition().x * PPM - rr_image.getWidth() / 2,
                rr.getRedbody().getPosition().y * PPM - rr_image.getHeight() / 2
            );
        }

        /*
        bb_image.setPosition(
            bb.getBlackbody().getPosition().x * PPM - bb_image.getWidth() / 2,
            bb.getBlackbody().getPosition().y * PPM - bb_image.getHeight() / 2
        );*/
        gb1.setPosition(
            gbl1.getIronbody().getPosition().x * PPM - gb1.getWidth() / 2,
            gbl1.getIronbody().getPosition().y * PPM - gb1.getHeight() / 2
        );
        gb2.setPosition(
            gbl2.getIronbody().getPosition().x * PPM - gb2.getWidth() / 2,
            gbl2.getIronbody().getPosition().y * PPM - gb2.getHeight() / 2
        );
        gb3.setPosition(
            gbl3.getIronbody().getPosition().x * PPM - gb3.getWidth() / 2,
            gbl3.getIronbody().getPosition().y * PPM - gb3.getHeight() / 2
        );
        gb4.setPosition(
            gbl4.getIronbody().getPosition().x * PPM - gb4.getWidth() / 2,
            gbl4.getIronbody().getPosition().y * PPM - gb4.getHeight() / 2
        );
        gb5.setPosition(
            gbl5.getIronbody().getPosition().x * PPM - gb5.getWidth() / 2,
            gbl5.getIronbody().getPosition().y * PPM - gb5.getHeight() / 2
        );

        gb6.setPosition(
            gbl6.getIronbody().getPosition().x * PPM - gb6.getWidth() / 2,
            gbl6.getIronbody().getPosition().y * PPM - gb6.getHeight() / 2
        );
        gb7.setPosition(
            gbl7.getIronbody().getPosition().x * PPM - gb7.getWidth() / 2,
            gbl7.getIronbody().getPosition().y * PPM - gb7.getHeight() / 2
        );
        gb8.setPosition(
            gbl8.getIronbody().getPosition().x * PPM - gb8.getWidth() / 2,
            gbl8.getIronbody().getPosition().y * PPM - gb8.getHeight() / 2
        );
        gb9.setPosition(
            gbl9.getIronbody().getPosition().x * PPM - gb9.getWidth() / 2,
            gbl9.getIronbody().getPosition().y * PPM - gb9.getHeight() / 2
        );

        gb10.setPosition(
            gbl10.getIronbody().getPosition().x * PPM - gb10.getWidth() / 2,
            gbl10.getIronbody().getPosition().y * PPM - gb10.getHeight() / 2
        );

        gb11.setPosition(
            gbl11.getIronbody().getPosition().x * PPM - gb11.getWidth() / 2,
            gbl11.getIronbody().getPosition().y * PPM - gb11.getHeight() / 2
        );
        if (pigHit) {
            // Hide the pig by removing it from the stage or setting it to invisible
            pig_image.setVisible(false);

            // Proceed to the victory screen after the post-launch timer
            postLaunchTimer -= delta;
            if (postLaunchTimer <= 0) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new VictoryScreen(new MainGameScreen2(previousScreen)));
            }
        } else if (rr.getRedbody().getType() == BodyDef.BodyType.DynamicBody) {
            // If the bird is launched but the pig is not hit
            postLaunchTimer -= delta;
            if (postLaunchTimer <= 0) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LostScreen(new MainGameScreen2(previousScreen)));
            }
        }

        // Update pig's position only if it is visible
        if (pig_image.isVisible()) {
            pig_image.setPosition(
                pigb.getPigBbody().getPosition().x * PPM - pig_image.getWidth() / 2,
                pigb.getPigBbody().getPosition().y * PPM - pig_image.getHeight() / 2
            );
        }


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

        rr_image.setSize(width / 10, height / 10);
        rr_image.setPosition(30, 100);

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

