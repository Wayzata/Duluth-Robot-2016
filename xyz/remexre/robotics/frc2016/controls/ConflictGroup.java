package xyz.remexre.robotics.frc2016.controls;

import java.util.Arrays;
import java.util.List;

/**
 * Manages groups of conflicting buttons.
 * @author Nathan Ringo
 */
public class ConflictGroup {
	private List<GamepadButton> buttons;

	/**
	 * Creates a new ConflictGroup based on the given buttons.
	 * @param buttons The buttons in the ConflictGroup.
	 */
	public ConflictGroup(GamepadButton ... buttons) {
		this(Arrays.asList(buttons));
	}

	/**
	 * Creates a new ConflictGroup based on the given buttons.
	 * @param buttons The buttons in the ConflictGroup.
	 */
	public ConflictGroup(List<GamepadButton> buttons) {
		this.buttons = buttons;
	}

	/**
	 * Return whether the ConflictGroup includes the given button.
	 * @param button The button to test for.
	 * @return Whether the button is included.
	 */
	public boolean hasButton(GamepadButton button) {
		return this.buttons.contains(button);
	}

	/**
	 * Return all the GamepadButtons in the ConflictGroup.
	 * @return All conflicting buttons.
	 */
	public List<GamepadButton> getList() {
		return this.buttons;
	}

	/**
	 * All the defined conflict groups.
	 */
	public static final List<ConflictGroup> conflictGroups = Arrays.asList(
			new ConflictGroup(
					GamepadButton.L1,
					GamepadButton.R1),
			new ConflictGroup(
					GamepadButton.L2,
					GamepadButton.R2),
			new ConflictGroup(
					GamepadButton.L3,
					GamepadButton.R3)
			);
}
