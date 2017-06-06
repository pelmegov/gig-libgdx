package ru.pelmegov.gig;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import ru.pelmegov.gig.animation.PlayerAnimation;
import ru.pelmegov.gig.keyboard.PlayerKeyboard;
import ru.pelmegov.gig.model.PlayerModel;
import ru.pelmegov.gig.util.Setting;

import static ru.pelmegov.gig.util.Setting.PPM;

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

    // box2d
    private Box2DDebugRenderer b2dr;
    private World world;
    private Body player;

    private float SCALE = 2.0f;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        // создаем камеру
        camera = new OrthographicCamera();
        camera.setToOrtho(true, w / SCALE, h / SCALE);

        world = new World(new Vector2(0f, 0f), false);
        b2dr = new Box2DDebugRenderer();

        player = createBox(8, 10, 32, 32, false);

        // все что касается игрока
        playerTexture = new Texture(Gdx.files.internal("player-sprite.png"));
        playerAnimation = new PlayerAnimation(playerTexture);

        playerKeyboard = new PlayerKeyboard(player);

        // загружаем карту
        map = new TmxMapLoader().load("maps/testmap.tmx");

        // хелпер рендеринга карты
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        // устанавливаем взгляд игрока по умолчанию
        direction = playerKeyboard.getDirectionKeyPressed();
    }

    @Override
    public void render() {

        update(Gdx.graphics.getDeltaTime());

        // задаем цвет по умолчанию для всего канваса
        Setting.defaultColorRender();

        // определяем нажата ли клавиша для перемещения игрока и в какую сторону его двигать
        direction = playerKeyboard.getDirectionKeyPressed();
        currentFrame = direction != null ? playerAnimation.getCurrentTexture(direction) : playerAnimation.getDefaultTexture();

        // рендерим карту
        renderer.setView(camera);
        renderer.render();

        // рисуем
        batch.begin();
        batch.draw(currentFrame, player.getPosition().x * PPM - 32,
                player.getPosition().y * PPM - 24);
        batch.end();

        b2dr.render(world, camera.combined.scl(PPM));

        // если нажата клавиша escape, выходим
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    public void update(float delta) {
        world.step(1 / 60f, 6, 2);

        cameraUpdate(delta);

        batch.setProjectionMatrix(camera.combined);
    }

    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = player.getPosition().x * PPM;
        position.y = player.getPosition().y * PPM;
        camera.position.set(position);

        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / SCALE, height / SCALE);
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
    }

    public Body createBox(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic) {
            def.type = BodyDef.BodyType.StaticBody;
        } else {
            def.type = BodyDef.BodyType.DynamicBody;
        }

        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        pBody.createFixture(shape, 1.0f);
        shape.dispose();
        return pBody;
    }

}
