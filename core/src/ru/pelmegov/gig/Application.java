package ru.pelmegov.gig;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import ru.pelmegov.gig.animation.PlayerAnimation;
import ru.pelmegov.gig.keyboard.PlayerKeyboard;
import ru.pelmegov.gig.model.PlayerModel;
import ru.pelmegov.gig.setting.Setting;

import static ru.pelmegov.gig.setting.Setting.CAMERA_HEIGHT;
import static ru.pelmegov.gig.setting.Setting.CAMERA_WIDTH;

public class Application extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture playerTexture;
    private TextureRegion currentFrame;
    private PlayerKeyboard playerKeyboard;
    private PlayerAnimation playerAnimation;
    private PlayerModel playerModel;
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private Integer direction;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // все что касается игрока
        playerTexture = new Texture(Gdx.files.internal("player-sprite.png"));
        playerAnimation = new PlayerAnimation(playerTexture);
        playerModel = new PlayerModel();
        playerKeyboard = new PlayerKeyboard(playerModel);

        // загружаем карту
        map = new TmxMapLoader().load("maps/firstmap.tmx");

        // создаем камеру, ширина и высота берется из конфигурационного файла
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        // хелпер рендеринга карты
        renderer = new OrthogonalTiledMapRenderer(map, 1, batch);

        // устанавливаем взгляд игрока по умолчанию
        direction = playerKeyboard.getDirectionKeyPressed();
    }

    @Override
    public void render() {
        // задаем цвет по умолчанию для всего канваса
        Setting.defaultColorRender();

        // рендерим карту
        renderer.setView(camera);
        renderer.render();

        // позиционируем камеру
        camera.position.x = playerModel.getPlayer().x;
        camera.position.y = playerModel.getPlayer().y;
        camera.update();

        // определяем нажата ли клавиша для перемещения игрока и в какую сторону его двигать
        direction = playerKeyboard.getDirectionKeyPressed();
        currentFrame = direction != null ? playerAnimation.getCurrentTexture(direction) : playerAnimation.getDefaultTexture();

        // рисуем игрока
        batch.begin();
        batch.draw(currentFrame, playerModel.getPlayer().x, playerModel.getPlayer().y);
        batch.end();
    }

}
