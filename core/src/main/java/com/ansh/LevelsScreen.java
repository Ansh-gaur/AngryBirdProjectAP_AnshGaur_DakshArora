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

public class LevelsScreen implements Screen {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;
    private Texture image1Texture;
    private Texture image2Texture;
    private Texture image3Texture;
    private Image image1;
    private Image image2;
    private Image image3;

    private Texture m_b;
    private Image menu_image;

    @Override
    public void show() {
        // Initialize the stage and set it to handle input
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load the background texture
        backgroundTexture = new Texture("angrybirdsbackground.jpg"); // Replace with your background image path
        backgroundImage = new Image(backgroundTexture);

        // Set the background to fill the screen
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        // Load textures for the three images
        image1Texture = new Texture("Level1.png"); // Replace with your image paths
        image2Texture = new Texture("Level2.png");
        image3Texture = new Texture("Level3.png");
        m_b=new Texture("menubutton.png");
        menu_image=new Image(m_b);
        menu_image.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 20);
        menu_image.setPosition(10, Gdx.graphics.getHeight() - menu_image.getHeight() - 10);

        // Create images
        image1 = new Image(image1Texture);
        image2 = new Image(image2Texture);
        image3 = new Image(image3Texture);

        // Set images to be the same size
        image1.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        image2.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        image3.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);


        // Add images to the stage
        stage.addActor(image1);
        stage.addActor(image2);
        stage.addActor(image3);
        Screen love = new LevelsScreen();

        // Add click listeners to images
        image1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Start Game clicked!");
                // Transition to the game start screen
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainGameScreen(love));
            }
        });

        // Add click listeners to images

        image2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Start Game clicked!");
                // Transition to the game start screen
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainGameScreen(love));
            }
        });

        // Add click listeners to images
        image3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Start Game clicked!");
                // Transition to the game start screen
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainGameScreen(love));
            }
        });

        menu_image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Return to menu clicked!");
                // Transition to the game start screen
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        });
        stage.addActor(menu_image);
        // Position images
        positionImages(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void positionImages(int width, int height) {
        // Set the background to fill the new screen size
        backgroundImage.setSize(width, height);

        // Set the images to be the same size based on the updated dimensions
        float imageWidth = width / 10;
        float imageHeight = height / 10;

        image1.setSize(imageWidth, imageHeight);
        image2.setSize(imageWidth, imageHeight);
        image3.setSize(imageWidth, imageHeight);

        // Calculate positions to center images horizontally and divide vertical space evenly
        float xPos1 = (width - imageWidth) / 2;
        float yPos1 = (height * 2 / 3) - (imageHeight / 2); // 1/3 from the top

        float xPos2 = (width - imageWidth) / 2;
        float yPos2 = (height / 2) - (imageHeight / 2); // Centered

        float xPos3 = (width - imageWidth) / 2;
        float yPos3 = (height * 1 / 3) - (imageHeight / 2); // 1/3 from the bottom

        // Position images vertically centered
        image1.setPosition(xPos1, yPos1);
        image2.setPosition(xPos2, yPos2);
        image3.setPosition(xPos3, yPos3);
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
        menu_image.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 20);
        menu_image.setPosition(10, Gdx.graphics.getHeight() - menu_image.getHeight() - 10);

        // Reposition the images to handle full screen and windowed mode adjustments
        positionImages(width, height);

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
        image1Texture.dispose();
        image2Texture.dispose();
        image3Texture.dispose();
    }
}
