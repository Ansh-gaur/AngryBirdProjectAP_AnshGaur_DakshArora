package com.ansh;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class AngryBirdsGame extends ApplicationAdapter {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Skin skin;
    private TextButton oldGameButton;
    private TextButton newGameButton;

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load assets
        backgroundTexture = new Texture("angry_bird_background.png"); // Make sure the image is in your assets folder
        backgroundImage = new Image(backgroundTexture);

        // Create skin for UI components
        skin = new Skin(Gdx.files.internal("uiskin.json")); // You may need a skin file for the buttons

        // Create buttons
        oldGameButton = new TextButton("Continue Old Game", skin);
        newGameButton = new TextButton("Start New Game", skin);

        // Set button positions or use Table layout
        oldGameButton.setPosition(300, 200);
        newGameButton.setPosition(300, 150);

        // Add button listeners
        oldGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Code to continue old game
                System.out.println("Continuing old game...");
            }
        });

        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Code to start new game
                System.out.println("Starting new game...");
            }
        });

        // Add elements to stage
        stage.addActor(backgroundImage);
        stage.addActor(oldGameButton);
        stage.addActor(newGameButton);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
        skin.dispose();
    }
}
