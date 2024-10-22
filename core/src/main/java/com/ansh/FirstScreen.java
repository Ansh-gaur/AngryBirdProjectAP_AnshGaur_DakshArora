package com.ansh;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game; // Make sure you import Game
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class FirstScreen implements Screen {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private float elapsedTime = 0; // Track the elapsed time

    @Override
    public void show() {
        // Initialize the stage and set it to handle input
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load the background image texture
        backgroundTexture = new Texture("mainmage.jpg"); // Replace with your background image path
        backgroundImage = new Image(backgroundTexture);

        // Set the image to fill the screen
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Add the background image to the stage
        stage.addActor(backgroundImage);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the elapsed time
        elapsedTime += delta;

        // Check if 3 seconds have passed
        if (elapsedTime >= 3) {
            // Transition to the MenuScreen
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
        }

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
    }
}
