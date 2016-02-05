package xyz.remexre.robotics.frc2016.controls;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a control scheme, which maps buttons to actions.
 * @author Nathan Ringo
 */
@FunctionalInterface
public interface ControlScheme {
	/**
	 * Maps a single button to a control.
	 * @param button The button.
	 * @return The control.
	 */
	public Optional<Control> mapOne(GamepadButton button);
	
	/**
	 * Converts a set of buttons to a set of controls.
	 * @param buttons The buttons.
	 * @return The controls.
	 */
	public default Set<Control> mapAll(Set<GamepadButton> buttons) {
		return buttons.stream()
			.map(this::mapOne)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toCollection(HashSet::new));
	}
}
