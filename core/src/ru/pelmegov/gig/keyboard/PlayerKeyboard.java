package ru.pelmegov.gig.keyboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ru.pelmegov.gig.model.PlayerModel;
import ru.pelmegov.gig.setting.Setting;

public class PlayerKeyboard {

    private PlayerModel playerModel;

    public PlayerKeyboard(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public Integer getDirectionKeyPressed() {
        Integer direction = null;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            playerModel.getPlayer().x -= Setting.PLAYER_DEFAULT_SPEED * Gdx.graphics.getDeltaTime();
            direction = Setting.Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            playerModel.getPlayer().x += Setting.PLAYER_DEFAULT_SPEED * Gdx.graphics.getDeltaTime();
            direction = Setting.Direction.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerModel.getPlayer().y += Setting.PLAYER_DEFAULT_SPEED * Gdx.graphics.getDeltaTime();
            direction = Setting.Direction.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            playerModel.getPlayer().y -= Setting.PLAYER_DEFAULT_SPEED * Gdx.graphics.getDeltaTime();
            direction = Setting.Direction.DOWN;
        }
        return direction;
    }
}
