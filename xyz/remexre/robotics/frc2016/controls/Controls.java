package xyz.remexre.robotics.frc2016.controls;

import xyz.remexre.robotics.frc2016.util.TernaryMotor;

/**
 * A class that stores the output of a ControlScheme.
 * @author Nathan Ringo
 */
public class Controls {
	// Arm
	public double shoulderAngle, elbowAngle;
	public TernaryMotor.State forearm;

	// Drive Train
	public Axes drive;
	public double driveMaxSpeed;

	// Shooter
	public TernaryMotor.State shooterArm;
	public boolean enableBelt, enableShooter;

	// Winch
	public TernaryMotor.State winch;
}
