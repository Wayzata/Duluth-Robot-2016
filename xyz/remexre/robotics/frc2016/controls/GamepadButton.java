package xyz.remexre.robotics.frc2016.controls;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An enumeration of the buttons on a gamepad or joystick.
 * @author Nathan Ringo
 */
public enum GamepadButton {
	// TODO Order these correctly, or add e.g. a Predicate<Joystick> to check.
	JOYSTICK_TRIGGER,
	A, B, X, Y,
	L1, R1,
	BACK, START,
	L3, R3;

	/**
	 * Returns the button enumerated by the given button number.
	 * @param joystick True if referring to the joystick, false if the gamepad.
	 * @param buttonNumber The number of the button.
	 * @return The button, if the number is valid.
	 */
	public static Optional<GamepadButton> get(boolean joystick, int buttonNumber) {
		if(joystick) {
			if(buttonNumber == 0) return Optional.of(JOYSTICK_TRIGGER);
			else return Optional.empty();
		} else {
			GamepadButton[] buttons = GamepadButton.values();
			if(buttonNumber < buttons.length)
				return Optional.of(buttons[buttonNumber]);
			else
				return Optional.empty();
		}
	}

	/**
	 * Returns all the controls activated by the given button numbers.
	 * @param buttonNumbers The numbers of all active buttons.
	 * @return All the controls which are activated.
	 */
	public static Set<GamepadButton> get(Set<Integer> buttonNumbers) {
		return Arrays.stream(GamepadButton.values())
				.filter((c) -> buttonNumbers.contains(c.ordinal()))
				.collect(Collectors.toCollection(HashSet::new));
	}
}
