package com.ansh;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game; // Make sure this is imported
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
    //1
    private Catapult cata;
    //2
    private Image cata_image;


    // Constructor to pass the previous screen
    public MainGameScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    public void show() {
        // Initialize the stage and set it to handle input
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        //3
        cata=new Catapult();


        // Load the background texture
        backgroundTexture = new Texture("mainbacker.jpg"); // Replace with your background image path
        backgroundImage = new Image(backgroundTexture);


        // Set the background to fill the screen
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Add the background image to the stage
        stage.addActor(backgroundImage);
        //4
        cata_image=new Image(cata.cataimg);
        cata_image.setSize(Gdx.graphics.getWidth() / 10, (Gdx.graphics.getHeight()+1000) / 10);
        cata_image.setPosition(30,10);
        stage.addActor(cata_image);

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

        // Update and draw the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Resize the stage's viewport
        stage.getViewport().update(width, height, true);

        // Adjust background image size to fill the new screen size
        backgroundImage.setSize(width, height);

        // Reposition the back button to stay in the top-left corner
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
    }
}
