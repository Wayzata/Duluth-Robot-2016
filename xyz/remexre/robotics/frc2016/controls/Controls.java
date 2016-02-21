package xyz.remexre.robotics.frc2016.controls;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import xyz.remexre.robotics.frc2016.util.TernaryMotor;
import xyz.remexre.robotics.frc2016.util.TernaryMotor.State;

/**
 * A class that stores the output of a ControlScheme.
 * @author Nathan Ringo
 */
public class Controls {
	// Arm
	public double shoulderAngle, elbowAngle;
	public TernaryMotor.State forearm = State.STOP;

	// Drive Train
	public Axes drive = new Axes(0, 0);
	public double driveSpeedMultiplier;

	// Shooter
	public TernaryMotor.State shooterArm = State.STOP;
	public boolean enableBelt, enableShooter;

	// Winch
	public TernaryMotor.State winch = State.STOP;
	
	/**
	 * Dumps all the data from the Controls object to the Smart Dashboard.
	 */
	public void debug() {
		SmartDashboard.putNumber("controls.shoulderAngle", this.shoulderAngle);
		SmartDashboard.putNumber("controls.elbowAngle", this.elbowAngle);
		SmartDashboard.putString("controls.forearm", this.forearm.toString());
		SmartDashboard.putNumber("controls.drive.x", this.drive.x);
		SmartDashboard.putNumber("controls.drive.y", this.drive.y);
		SmartDashboard.putNumber("controls.driveSpeedMultiplier", this.driveSpeedMultiplier);
		SmartDashboard.putString("controls.shooterArm", this.shooterArm.toString());
		SmartDashboard.putBoolean("controls.enableBelt", this.enableBelt);
		SmartDashboard.putBoolean("controls.enableShooter", this.enableShooter);
		SmartDashboard.putString("controls.winch", this.winch.toString());
	}
}
