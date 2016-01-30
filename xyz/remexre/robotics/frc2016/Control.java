package xyz.remexre.robotics.frc2016;

import xyz.remexre.robotics.frc2016.modules.DriveTrain;

/**
 * Control represents any action taken by the robot.
 * @author Nathan Ringo
 */
@FunctionalInterface
public interface Control {
	public void action(DriveTrain driveTrain);
}
