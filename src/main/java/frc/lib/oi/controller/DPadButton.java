package frc.lib.oi.controller;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class DPadButton extends Trigger {

    // Direction State
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    // Variables
    private XboxController controller;
    private Direction direction;

    public DPadButton(XboxController controller, Direction direction) {
        // Variables
        this.controller = controller;
        this.direction = direction;
    }

    @Override
    public boolean get() {
        return degreesToDirection(controller.getPOV()) == direction;
    }

    public Direction degreesToDirection(int degrees) {
        if (degrees == 0) {
            return Direction.UP;
        } else if (degrees == 90) {
            return Direction.RIGHT;
        } else if (degrees == 180) {
            return Direction.DOWN;
        } else {
            return Direction.LEFT;
        }
    }
}
