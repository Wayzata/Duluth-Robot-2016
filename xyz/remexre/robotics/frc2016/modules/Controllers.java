package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The controllers class manages {@link Joystick}s and other user inputs.
 * @author Nathan Ringo
 */
public class Controllers {
	private Joystick drive, arm;
	
	public Controllers(int driveID, int armID) {
		this.drive = new Joystick(driveID);
		this.arm = new Joystick(armID);
	}
	
	/**
	 * Returns the drive joystick, for usage with
	 * {@link RobotDrive#arcadeDrive(edu.wpi.first.wpilibj.GenericHID)} or
	 * {@link DriveTrain#drive(Joystick)}.
	 * @return The drive joystick.
	 */
	public Joystick getDriveJoystick() { return this.drive; }
}
