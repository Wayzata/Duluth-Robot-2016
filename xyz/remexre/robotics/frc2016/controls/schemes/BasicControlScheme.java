package xyz.remexre.robotics.frc2016.controls.schemes;

import java.util.function.Consumer;

import xyz.remexre.robotics.frc2016.controls.ConflictGroup;
import xyz.remexre.robotics.frc2016.controls.ControlSchemeBase;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;

import static xyz.remexre.robotics.frc2016.controls.GamepadButton.*;
import static xyz.remexre.robotics.frc2016.util.TernaryMotor.State.*;

/**
 * Nathan's Preferred Controls. The control scheme is non-modal, but some
 * mappings may therefore be inconvenient at times, especially on the gamepad.
 * @author Nathan Ringo
 */
public class BasicControlScheme extends ControlSchemeBase {
	public BasicControlScheme() {
		super(new ConflictGroup(L1, R1),
				new ConflictGroup(L2, R2));
	}

	@Override
	public Consumer<Controls> map(GamepadButton button) {
		switch(button) {
		// Arm
		case L2: return (c) -> c.forearm = BACKWARD;
		case R2: return (c) -> c.forearm = FORWARD;
		// TODO
		// Shooter
		// TODO
		// Winch
		case L1: return (c) -> c.winch = BACKWARD;
		case R1: return (c) -> c.winch = FORWARD;
		// Do nothing on an unknown button
		default: return (c) -> {};
		}
	}
}
