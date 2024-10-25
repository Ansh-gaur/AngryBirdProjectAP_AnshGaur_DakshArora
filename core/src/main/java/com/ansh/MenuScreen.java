package com.ansh;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game; // Make sure this is imported
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    private Stage stage;
    private Texture startGameTexture;
    private Texture levelsTexture;
    private Texture oldGameTexture;
    private Image startGameImage;
    private Image levelsImage;
    private Image oldGameImage;
    private Texture backgroundTexture;
    private Image backgroundImage;

    @Override
    public void show() {
        // Initialize the stage and set it to handle input
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load textures for images
        startGameTexture = new Texture("angrybirdsbackground.jpg"); // Replace with your image file path
        levelsTexture = new Texture("playgame.png"); // Replace with your image file path
        oldGameTexture = new Texture("savedgame.png"); // Replace with your image file path

        // Create images
        startGameImage = new Image(startGameTexture);
        levelsImage = new Image(levelsTexture);
        oldGameImage = new Image(oldGameTexture);

        // Set the image sizes
        startGameImage.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        levelsImage.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        oldGameImage.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);

        // Load the background image texture
        backgroundTexture = new Texture("angryisnotbest.jpg"); // Replace with your background image path
        backgroundImage = new Image(backgroundTexture);

        // Set the background to fill the screen
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Set positions for images
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        startGameImage.setPosition((screenWidth - startGameImage.getWidth()) / 2, screenHeight * 0.6f);
        levelsImage.setPosition((screenWidth - levelsImage.getWidth()) / 2, screenHeight * 0.4f);
        oldGameImage.setPosition((screenWidth - oldGameImage.getWidth()) / 2, screenHeight * 0.2f);
        Screen notlove=new MenuScreen();
        // Add click listeners to images
        startGameImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Start Game clicked!");
                // Transition to the game start screen
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainGameScreen(notlove));
            }
        });

        levelsImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Levels clicked!");
                // Transition to the LevelsScreen
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelsScreen());
            }
        });

        oldGameImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Old Game clicked!");
                // Transition to load the old game
                ((Game) Gdx.app.getApplicationListener()).setScreen(new oldgamescreen());
            }
        });

        // Add images to the stage
        stage.addActor(backgroundImage);
        stage.addActor(startGameImage);
        stage.addActor(levelsImage);
        stage.addActor(oldGameImage);
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
        startGameImage.setSize(width / 10, height / 10);
        levelsImage.setSize(width / 10, height / 10);
        oldGameImage.setSize(width / 10, height / 10);

        // Update positions
        startGameImage.setPosition((width - startGameImage.getWidth()) / 2, height * 0.6f);
        levelsImage.setPosition((width - levelsImage.getWidth()) / 2, height * 0.4f);
        oldGameImage.setPosition((width - oldGameImage.getWidth()) / 2, height * 0.2f);
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
        startGameTexture.dispose();
        levelsTexture.dispose();
        oldGameTexture.dispose();
        backgroundTexture.dispose();
    }
}
