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
		Controls controls = this.init();
		buttons.stream()
			.map(this::mapButtons)
			.forEach((fn) -> fn.accept(controls));
		this.mapDriveAxes(driveAxes).accept(controls);
		this.mapSlider(slider).accept(controls);
		this.mapLeftAxes(leftAxes).accept(controls);
		this.mapRightAxes(rightAxes).accept(controls);
		return controls;
	}
	
	/**
	 * Creates the initial controls object.
	 * @return The initial controls object.
	 */
	public abstract Controls init();

	/**
	 * Maps a button to a function that mutates the controls object.
	 * @param button The button pressed.
	 * @return The mutating function.
	 */
	public abstract Consumer<Controls> mapButtons(GamepadButton button);

	/**
	 * Maps the state of the drive axes to a function that mutates the controls
	 * object.
	 * @param driveAxes The drive axes.
	 * @return The mutating function.
	 */
	public abstract Consumer<Controls> mapDriveAxes(Axes driveAxes);

	/**
	 * Maps the state of the slider to a function that mutates the controls
	 * object.
	 * @param slider The slider's position, -1 to 1.
	 * @return The mutating function.
	 */
	public abstract Consumer<Controls> mapSlider(double slider);
	
	/**
	 * Maps the state of the left axes to a function that mutates the controls
	 * object.
	 * @param driveAxes The left axes.
	 * @return The mutating function.
	 */
	public abstract Consumer<Controls> mapLeftAxes(Axes leftAxes);

	/**
	 * Maps the state of the right axes to a function that mutates the controls
	 * object.
	 * @param driveAxes The right axes.
	 * @return The mutating function.
	 */
	public abstract Consumer<Controls> mapRightAxes(Axes rightAxes);
}
