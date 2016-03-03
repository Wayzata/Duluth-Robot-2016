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
	private double shooterArmAngle = 0;
	private double shoulderAngle = 0;
	private double elbowAngle = 0;
	private boolean forearmExtended = false;
	
	public DrakeGwynethControlScheme() {
		super(new ConflictGroup(A, B),
				new ConflictGroup(X, Y),
				new ConflictGroup(L1, R1),
				new ConflictGroup(L2, R2),
				new ConflictGroup(L3, R3),
				new ConflictGroup(UP, DOWN),
				new ConflictGroup(LEFT, RIGHT),
				new ConflictGroup(BACK, START));
	}
	
	@Override
	public Controls init() {
		Controls c = new Controls();
		c.driveSpeedMultiplier = 0.5;
		c.shooterArmAngle = this.shooterArmAngle;
		c.shoulderAngle = this.shoulderAngle;
		c.elbowAngle = this.elbowAngle;
		c.forearmExtended = this.forearmExtended;
		return c;
	}

	@Override
	public Consumer<Controls> mapButtons(GamepadButton button) {
		switch(button) {
		// Shooter
		case JOYSTICK_TRIGGER: return (c) -> c.driveSpeedMultiplier = 1.0;
		case JOYSTICK_THUMB: return (c) -> c.enableShooter = true;
		// ABXY Buttons
		case A: return (c) -> c.belt = State.FORWARD;
		case B: return (c) -> c.belt = State.BACKWARD;
		case X: return (c) -> this.shooterArmAngle -= 50;
		case Y: return (c) -> this.shooterArmAngle += 50;
		// LR Buttons
		case L1: return (c) -> c.winch = State.FORWARD;
		case R1: return (c) -> c.winch = State.BACKWARD;
		case L2: return (c) -> this.shoulderAngle -= 10;
		case R2: return (c) -> this.shoulderAngle += 10;
		// POV
		case UP: return (c) -> this.elbowAngle += 10;
		case DOWN: return (c) -> this.elbowAngle -= 10;
		// Start/Select
		case BACK: return (c) -> this.forearmExtended = false;
		case START: return (c) -> this.forearmExtended = true;
		// Do nothing on an unknown button
		default: return (c) -> {};
		}
	}

	@Override
	public Consumer<Controls> mapDriveAxes(Axes driveAxes) {
		return (c) -> c.drive = driveAxes;
	}

	@Override
	public Consumer<Controls> mapSlider(double slider) {
		return (c) -> c.driveSpeedMultiplier *= (slider + 1) / 2;
	}

	@Override
	public Consumer<Controls> mapLeftAxes(Axes leftAxes) {
		return (c) -> c.elbowAngle += leftAxes.y;
	}

	@Override
	public Consumer<Controls> mapRightAxes(Axes rightAxes) {
		return (c) -> c.shoulderAngle += rightAxes.y;
	}
}
