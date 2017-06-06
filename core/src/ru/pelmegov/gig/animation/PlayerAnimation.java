package ru.pelmegov.gig.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.pelmegov.gig.util.Setting;

import static ru.pelmegov.gig.util.Setting.FRAME_COLS;
import static ru.pelmegov.gig.util.Setting.FRAME_ROWS;

/**
 * Класс, отвечающий за картинку игрока.
 * Анимацию и прочее.
 */
public class PlayerAnimation {

    private TextureRegion[][] textureRegions;
    private float stateTime;

    public PlayerAnimation(Texture playerTexture) {
        this.textureRegions = TextureRegion.split(playerTexture,
                playerTexture.getWidth() / FRAME_COLS,
                playerTexture.getHeight() / FRAME_ROWS);
    }

    // метод возвращает набор текстур для текущего взгляда игрока
    private TextureRegion[] animateMove(Integer direction) {
        TextureRegion[] moveRegions = new TextureRegion[FRAME_COLS];
        int index = 0;
        for (int i = 0; i < FRAME_COLS; i++) {
            moveRegions[index++] = textureRegions[direction][i];
        }
        return moveRegions;
    }

    /**
     * Метод возвращает текущую текстуру с учетом стороны взгляда персонажа игрока
     *
     * @param direction сторона, в которую смотрит игрок
     * @return текстура
     */
    public TextureRegion getCurrentTexture(Integer direction) {
        TextureRegion currentTexture;
        stateTime += Gdx.graphics.getDeltaTime();
        Animation<TextureRegion> playerAnimation =
                new Animation<TextureRegion>(0.05f, animateMove(direction));
        currentTexture = playerAnimation.getKeyFrame(stateTime, true);
        return currentTexture;
    }

    /**
     * Метод возвращает дефолтную настройку персонажа
     *
     * @return дефолтная текстура для персонажа
     */
    public TextureRegion getDefaultTexture() {
        return textureRegions[Setting.getDefaultPlayerDirection()][0];
    }
}
