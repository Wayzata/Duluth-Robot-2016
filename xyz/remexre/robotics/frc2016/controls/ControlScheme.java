package xyz.remexre.robotics.frc2016.controls;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a control scheme, which maps buttons to actions.
 * @author Nathan Ringo
 */
public interface ControlScheme {
	/**
	 * Returns the conflict groups associated with the control scheme.
	 * @return The conflict groups.
	 */
	public Collection<ConflictGroup> getConflictGroups();

	/**
	 * Converts a set of buttons to a controls object.
	 * @param buttons The buttons.
	 * @return The controls.
	 */
	public Controls map(Set<GamepadButton> buttons, Axes driveAxes, double slider, Axes leftAxes, Axes rightAxes);

	/**
	 * Returns a human-readable description of the control scheme.
	 * @return A string containing the description.
	 */
	public String toString();
	
	/**
	 * Creates a new filtering function for use in removing conflicting buttons.
	 * @return A function capable of being used in {@link Stream#filter(Predicate)}.
	 */
	public default Predicate<GamepadButton> filter() {
		Collection<ConflictGroup> allCGs = this.getConflictGroups();
		Set<ConflictGroup> cgSet = new HashSet<>();
		return (button) -> {
			Set<ConflictGroup> cgs = allCGs.stream()
					.filter((cg) -> cg.hasButton(button))
					.collect(Collectors.toCollection(HashSet::new));
			long numConflicts = cgs.stream()
					.filter((cg) -> cgSet.contains(cg))
					.collect(Collectors.counting());
			if(numConflicts > 0) return false;
			cgSet.addAll(cgs);
			return true;
		};
	}
	
	/**
	 * Returns the next control scheme to be used.
	 * @return A Control Scheme.
	 */
	public default ControlScheme next() {
		return this;
	}
}
