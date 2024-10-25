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

public class LostScreen implements Screen{
    private Stage stage;
    private Texture los_b;
    private Image los_image;
    private Texture back;
    private Image back_image;
    private Screen previousScreen;

    public LostScreen(Screen previousScreen) {
        this.previousScreen = previousScreen;
    }
    @Override
    public void show()
    {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        los_b=new Texture("lost.png");
        back=new Texture("backButton.png");
        los_image=new Image(los_b);
        back_image=new Image(back);

        los_image.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        back_image.setSize(Gdx.graphics.getWidth() / 20, Gdx.graphics.getHeight() / 20);
        back_image.setPosition(10, Gdx.graphics.getHeight() - back_image.getHeight() - 10);



        back_image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Go back to the previous screen
                ((Game) Gdx.app.getApplicationListener()).setScreen(previousScreen);
            }
        });
        stage.addActor(los_image);
        stage.addActor(back_image);


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
        los_image.setSize(width, height);

        // Reposition the back button to stay in the top-left corner
        back_image.setSize(width / 20, height / 20);
        back_image.setPosition(10, height - back_image.getHeight() - 10);
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
        los_b.dispose();
        back.dispose();
    }
}
