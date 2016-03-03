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
	public double shoulderAngle = 0.0, elbowAngle = 0.0;
	public boolean forearmExtended = false;

	// Drive Train
	public Axes drive = new Axes(0, 0);
	public double driveSpeedMultiplier = 0.0;

	// Shooter
	public double shooterArmAngle = 0.0;
	public boolean enableShooter = false;
	public TernaryMotor.State belt = State.STOP;

	// Winch
	public TernaryMotor.State winch = State.STOP;
	
	/**
	 * Dumps all the data from the Controls object to the Smart Dashboard.
	 */
	public void debug() {
		SmartDashboard.putNumber("controls.shoulderAngle", this.shoulderAngle);
		SmartDashboard.putNumber("controls.elbowAngle", this.elbowAngle);
		SmartDashboard.putBoolean("controls.forearmExtended", this.forearmExtended);
		SmartDashboard.putNumber("controls.drive.x", this.drive.x);
		SmartDashboard.putNumber("controls.drive.y", this.drive.y);
		SmartDashboard.putNumber("controls.driveSpeedMultiplier", this.driveSpeedMultiplier);
		SmartDashboard.putNumber("controls.shooterArmAngle", this.shooterArmAngle);
		SmartDashboard.putString("controls.belt", this.belt.toString());
		SmartDashboard.putBoolean("controls.enableShooter", this.enableShooter);
		SmartDashboard.putString("controls.winch", this.winch.toString());
	}
}
