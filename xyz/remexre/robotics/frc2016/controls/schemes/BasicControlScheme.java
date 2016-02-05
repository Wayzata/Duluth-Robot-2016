package xyz.remexre.robotics.frc2016.controls.schemes;

import java.util.Optional;

import xyz.remexre.robotics.frc2016.controls.Control;
import xyz.remexre.robotics.frc2016.controls.ControlScheme;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;

/**
 * Nathan's Preferred Controls. The control scheme is non-modal, but some
 * mappings may therefore be inconvenient at times.
 * @author Nathan Ringo
 */
public class BasicControlScheme implements ControlScheme {
	@Override
	public Optional<Control> mapOne(GamepadButton button) {
		switch(button) {
		case R1: return Optional.of(Control.RETRACT_WINCH);
		case R2: return Optional.of(Control.RETRACT_FOREARM);
		case L1: return Optional.of(Control.RETRACT_WINCH);
		case L2: return Optional.of(Control.RETRACT_FOREARM);
		// TODO Finish other controls.
		default: return Optional.empty();
		}
	}
}
