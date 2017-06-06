package ru.pelmegov.gig.keyboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import ru.pelmegov.gig.util.Setting;

public class PlayerKeyboard {

    private Body player;

    public PlayerKeyboard(Body player) {
        this.player = player;
    }

    public Integer getDirectionKeyPressed() {
        Integer direction = null;

        int horizontalForce = 0;
        int verticalForce = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= 1;
            direction = Setting.Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 1;
            direction = Setting.Direction.RIGHT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            verticalForce += 1;
            direction = Setting.Direction.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            verticalForce -= 1;
            direction = Setting.Direction.DOWN;
        }

        player.setLinearVelocity(horizontalForce * Setting.PLAYER_DEFAULT_SPEED,
                verticalForce * Setting.PLAYER_DEFAULT_SPEED);

        return direction;
    }
}
