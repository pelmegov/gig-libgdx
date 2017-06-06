package ru.pelmegov.gig.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Класс с глобальными настройками приложения
 */
public class Setting {

    /**
     * Скорость игрока по умолчанию
     */
    public static final int PLAYER_DEFAULT_SPEED = 4;

    public static final float PPM = 32f;

    /**
     * Количество столбцов в спрайте игрока
     */
    public static final int FRAME_COLS = 9;

    /**
     * Количество строк в спрайте игрока
     */
    public static final int FRAME_ROWS = 4;

    public static final int CAMERA_WIDTH = 720;
    public static final int CAMERA_HEIGHT = 480;

    /**
     * Цвет, в который будет закрашен весь фон сцены по умолчанию
     */
    public static void defaultColorRender() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Настройки направления игрока
     */
    public interface Direction {
        int UP = 0;
        int LEFT = 1;
        int DOWN = 2;
        int RIGHT = 3;
    }

    public static int getDefaultPlayerDirection() {
        return Direction.DOWN;
    }

}
