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
	public Controls map(Set<GamepadButton> buttons, Axes driveAxes, double slider, Axes leftAxes, Axes rightAxes) {
		Controls controls = this.pre();
		buttons.stream()
			.map(this::mapButtons)
			.forEach((fn) -> fn.accept(controls));
		this.mapDriveAxes(driveAxes.zeroIf(0.2)).accept(controls);
		this.mapSlider(slider).accept(controls);
		this.mapLeftAxes(leftAxes.zeroIf(0.2)).accept(controls);
		this.mapRightAxes(rightAxes.zeroIf(0.2)).accept(controls);
		return controls;
	}
	
	/**
	 * Creates the initial controls object.
	 * @return The initial controls object.
	 */
	public abstract Controls pre();
	
	/**
	 * Post-processingly modifies the controls object.
	 * @param controls The controls object to modify.
	 */
	public void post(Controls controls) {}

	/**
	 * Maps a button to a function that mutates the controls object.
	 * @param button The button pressed.
	 * @return The mutating function.
	 */
	public Consumer<Controls> mapButtons(GamepadButton button) { return (c) -> {}; }

	/**
	 * Maps the state of the drive axes to a function that mutates the controls
	 * object.
	 * @param driveAxes The drive axes.
	 * @return The mutating function.
	 */
	public Consumer<Controls> mapDriveAxes(Axes driveAxes) { return (c) -> {}; }

	/**
	 * Maps the state of the slider to a function that mutates the controls
	 * object.
	 * @param slider The slider's position, -1 to 1.
	 * @return The mutating function.
	 */
	public Consumer<Controls> mapSlider(double slider) { return (c) -> {}; }
	
	/**
	 * Maps the state of the left axes to a function that mutates the controls
	 * object.
	 * @param driveAxes The left axes.
	 * @return The mutating function.
	 */
	public Consumer<Controls> mapLeftAxes(Axes leftAxes) { return (c) -> {}; }

	/**
	 * Maps the state of the right axes to a function that mutates the controls
	 * object.
	 * @param driveAxes The right axes.
	 * @return The mutating function.
	 */
	public Consumer<Controls> mapRightAxes(Axes rightAxes) { return (c) -> {}; }
}
