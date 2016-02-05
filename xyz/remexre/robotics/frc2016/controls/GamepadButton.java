package xyz.remexre.robotics.frc2016.controls;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An enumeration of the buttons on a gamepad.
 * @author Nathan Ringo
 */
public enum GamepadButton {
	A, B, X, Y,
	UP, DOWN, LEFT, RIGHT,
	START, SELECT,
	L1, L2, L3,
	R1, R2, R3;
	
	/**
	 * Returns the button enumerated by the given button number.
	 * @param buttonNumber The number of the button.
	 * @return The button, if the number is valid.
	 */
	public static Optional<GamepadButton> get(int buttonNumber) {
		GamepadButton[] buttons = GamepadButton.values();
		if(buttonNumber < buttons.length)
			return Optional.of(buttons[buttonNumber]);
		else
			return Optional.empty();
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
