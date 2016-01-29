package xyz.remexre.robotics.frc2016;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * DriveTrain is a class that manages the robot's drive train as a whole.
 * @author Nathan Ringo
 */
public class DriveTrain {
	private RobotDrive drive;

	public DriveTrain(int frontLeftMotorID,
			int backLeftMotorID,
			int frontRightMotorID,
			int backRightMotorID) {
		this(new CANTalon(frontLeftMotorID), new CANTalon(backLeftMotorID),
			new CANTalon(frontRightMotorID), new CANTalon(backRightMotorID));
	}
	public DriveTrain(SpeedController frontLeft,
			SpeedController backLeft,
			SpeedController frontRight,
			SpeedController backRight) {
		MultiSpeedController left = new MultiSpeedController(frontLeft, backLeft),
				right = new MultiSpeedController(frontRight, backRight);
		this.drive = new RobotDrive(left, right);
	}

	public void brake() { this.drive.stopMotor(); }
	public void drive(Joystick joystick) { this.drive.arcadeDrive(joystick); }
}
