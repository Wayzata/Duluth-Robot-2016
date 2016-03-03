package xyz.remexre.robotics.frc2016.controls.schemes;

import java.util.function.Consumer;

import xyz.remexre.robotics.frc2016.controls.Axes;
import xyz.remexre.robotics.frc2016.controls.ControlSchemeBase;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;
import xyz.remexre.robotics.frc2016.util.TernaryMotor.State;

/**
 * Chloe's and Marcus' Preferred Controls when hardware doesn't work.
 * 
 * @author Nathan Ringo
 */
public class ChloeMarcusControlScheme extends ControlSchemeBase {
	private double shooterArmAngle = 0;
	private double shoulderAngle = 0;
	private double elbowAngle = 0;
	
	public ChloeMarcusControlScheme() {
		super(); // TODO
	}
	
	@Override
	public Controls init() {
		Controls c = new Controls();
		c.driveSpeedMultiplier = 1.0;
		c.shooterArmAngle = this.shooterArmAngle;
		c.shoulderAngle = this.shoulderAngle;
		c.elbowAngle = this.elbowAngle;
		return c;
	}

	@Override
	public Consumer<Controls> mapButtons(GamepadButton button) {
		switch(button) {
		// Arm
		case UP: return (c) -> c.forearmExtended = true;
		case DOWN: return (c) -> c.forearmExtended = false;
		// Drive Train
//		case JOYSTICK_THUMB: return (c) -> c.driveSpeedMultiplier = 1.0;
		// Shooter
		case JOYSTICK_TRIGGER: return (c) -> c.enableShooter = true;
		case JOYSTICK_BUTTON_6:
		case R1: return (c) -> c.belt = State.FORWARD;
		case JOYSTICK_BUTTON_4:
		case L1: return (c) -> c.belt = State.BACKWARD;
		case JOYSTICK_BUTTON_5:
		case R2: return (c) -> this.shooterArmAngle += 50;
		case JOYSTICK_BUTTON_3:
		case L2: return (c) -> this.shooterArmAngle -= 50;
		// Winch
		case A: return (c) -> c.winch = State.FORWARD;
		case B: return (c) -> c.winch = State.BACKWARD;
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
//		return (c) -> c.driveSpeedMultiplier *= (slider + 1);
		return (c) -> {};
	}

	@Override
	public Consumer<Controls> mapLeftAxes(Axes leftAxes) {
		return (c) -> this.shoulderAngle += 10 * leftAxes.y;
	}

	@Override
	public Consumer<Controls> mapRightAxes(Axes rightAxes) {
		return (c) -> this.elbowAngle += 10 * rightAxes.y;
	}
}
