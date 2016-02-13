package xyz.remexre.robotics.frc2016.modules;

import xyz.remexre.robotics.frc2016.controls.Controls;

/**
 * An interface for any and all modules of the program.
 * @author Nathan Ringo
 */
public interface Module {
	/**
	 * Completes the task the module was designed to fulfill.
	 * @param controls The {@link Controls} object.
	 */
	public void control(Controls controls);
}
