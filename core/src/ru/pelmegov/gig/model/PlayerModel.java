package ru.pelmegov.gig.model;

import java.awt.*;

/**
 * Класс с моделью игрока,
 * фигура игрока может в последующем измениться.
 * TODO продумать от кого отнаследоваться этим классом.
 */
public class PlayerModel {

    private Rectangle player;

    public PlayerModel() {
        this.player = new Rectangle();
        this.player.x = 20;
        this.player.y = 20;
    }

    public Rectangle getPlayer() {
        return player;
    }
}
