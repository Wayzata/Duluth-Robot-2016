package xyz.remexre.robotics.frc2016.controls.schemes;

import java.util.function.Consumer;

import xyz.remexre.robotics.frc2016.controls.Axes;
import xyz.remexre.robotics.frc2016.controls.ConflictGroup;
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
	private double armAngle = 0;
	
	public ChloeMarcusControlScheme() {
		super(new ConflictGroup(GamepadButton.JOYSTICK_TRIGGER, GamepadButton.JOYSTICK_THUMB),
				new ConflictGroup(GamepadButton.R1, GamepadButton.L1),
				new ConflictGroup(GamepadButton.R2, GamepadButton.L2));
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
		case JOYSTICK_TRIGGER: return (c) -> c.shooter = State.FORWARD;
		case JOYSTICK_THUMB: return (c) -> c.shooter = State.BACKWARD;
		
		case R1: return (c) -> c.belt = State.FORWARD;
		case L1: return (c) -> c.belt = State.BACKWARD;
		
		case R2: return (c) -> this.armAngle += 50;
		case L2: return (c) -> this.armAngle -= 50;
		
		// Do nothing on an unknown button
		default: return (c) -> {};
		}
	}

	@Override
	public Consumer<Controls> mapDriveAxes(Axes driveAxes) {
		return (c) -> c.drive = driveAxes.times(-1);
	}
	
	@Override
	public String toString() { return "Chloe and Marcus"; }
}
