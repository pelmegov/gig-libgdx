package ru.pelmegov.gig;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.pelmegov.gig.animation.PlayerAnimation;
import ru.pelmegov.gig.keyboard.PlayerKeyboard;
import ru.pelmegov.gig.model.PlayerModel;
import ru.pelmegov.gig.setting.Setting;

public class Application extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture playerTexture;
    private TextureRegion currentFrame;
    private PlayerKeyboard playerKeyboard;
    private PlayerAnimation playerAnimation;
    private PlayerModel playerModel;

    @Override
    public void create() {
        batch = new SpriteBatch();
        playerTexture = new Texture(Gdx.files.internal("player-sprite.png"));
        playerAnimation = new PlayerAnimation(playerTexture);
        playerModel = new PlayerModel();
        playerKeyboard = new PlayerKeyboard(playerModel);
    }

    @Override
    public void render() {
        // задаем цвет по умолчанию для всего канваса
        Setting.defaultColorRender();

        // начало рисования
        batch.begin();

        Integer direction = playerKeyboard.getDirectionKeyPressed();
        currentFrame = direction != null ? playerAnimation.getCurrentTexture(direction) : playerAnimation.getDefaultTexture();
        batch.draw(currentFrame, playerModel.getPlayer().x, playerModel.getPlayer().y);

        // окончание рисования
        batch.end();
    }

}
