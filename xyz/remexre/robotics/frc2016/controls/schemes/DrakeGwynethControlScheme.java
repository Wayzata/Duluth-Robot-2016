package xyz.remexre.robotics.frc2016.controls.schemes;

import java.util.function.Consumer;

import xyz.remexre.robotics.frc2016.controls.Axes;
import xyz.remexre.robotics.frc2016.controls.ConflictGroup;
import xyz.remexre.robotics.frc2016.controls.ControlSchemeBase;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;
import xyz.remexre.robotics.frc2016.util.TernaryMotor.State;

import static xyz.remexre.robotics.frc2016.controls.GamepadButton.*;

/**
 * Drake's and Gwyneth's Preferred Controls. The control scheme is non-modal,
 * so some mappings may therefore be inconvenient at times, especially on the
 * gamepad.
 * 
 * @author Nathan Ringo
 */
public class DrakeGwynethControlScheme extends ControlSchemeBase {
	private double armAngle = 0;
	
	public DrakeGwynethControlScheme() {
		super(new ConflictGroup(A, B),
				new ConflictGroup(X, Y));
	}
	
	@Override
	public Controls init() {
		Controls c = new Controls();
		c.armAngle = this.armAngle;
		return c;
	}

	@Override
	public Consumer<Controls> mapButtons(GamepadButton button) {
		switch(button) {
		// Shooter
		case JOYSTICK_THUMB: return (c) -> c.shooter = State.FORWARD;
		// ABXY Buttons
		case A: return (c) -> c.belt = State.FORWARD;
		case B: return (c) -> c.belt = State.BACKWARD;
		case X: return (c) -> this.armAngle -= 50;
		case Y: return (c) -> this.armAngle += 50;
		// Do nothing on an unknown button
		default: return (c) -> {};
		}
	}

	@Override
	public Consumer<Controls> mapDriveAxes(Axes driveAxes) {
		return (c) -> c.drive = driveAxes;
	}
	
	@Override
	public String toString() { return "Chloe and Marcus"; }
}
