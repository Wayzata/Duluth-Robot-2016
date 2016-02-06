package xyz.remexre.robotics.frc2016.controls;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A base class for helping to implement ControlSchemes.
 * @author Nathan Ringo
 */
public abstract class ControlSchemeBase implements ControlScheme {
	private List<ConflictGroup> conflictGroups;
	
	/**
	 * Initializes the conflict group list with the given conflict groups.
	 * @param conflictGroups All the existing conflict groups.
	 */
	public ControlSchemeBase(ConflictGroup ... conflictGroups) {
		this.conflictGroups = Arrays.asList(conflictGroups);
	}
	
	@Override
	public List<ConflictGroup> getConflictGroups() {
		return this.conflictGroups;
	}
	
	@Override
	public Controls map(Set<GamepadButton> buttons) {
		Controls controls = new Controls();
		buttons.stream()
			.map(this::map)
			.forEach((fn) -> fn.accept(controls));
		return controls;
	}

	/**
	 * Maps a button to a function that mutates the controls object.
	 * @param button The button pressed.
	 * @return The mutating function.
	 */
	public abstract Consumer<Controls> map(GamepadButton button);
}
