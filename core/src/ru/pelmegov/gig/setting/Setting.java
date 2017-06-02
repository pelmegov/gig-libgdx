package ru.pelmegov.gig.setting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Класс с глобальными настройками приложения
 */
public class Setting {

    /**
     * Скорость игрока по умолчанию
     */
    public static final int PLAYER_DEFAULT_SPEED = 150;

    /**
     * Количество столбцов в спрайте игрока
     */
    public static final int FRAME_COLS = 9;

    /**
     * Количество строк в спрайте игрока
     */
    public static final int FRAME_ROWS = 4;

    /**
     * Цвет, в который будет закрашен весь фон сцены по умолчанию
     */
    public static void defaultColorRender() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
