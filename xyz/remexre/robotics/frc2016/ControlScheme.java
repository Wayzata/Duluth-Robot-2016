package xyz.remexre.robotics.frc2016;

import java.util.Set;

/**
 * The ControlScheme interface allows adding several selectable control schemes
 * to the robot.
 * @author Nathan Ringo
 */
public interface ControlScheme {
	public String getName();
	public Set<Control> getControls();
}
