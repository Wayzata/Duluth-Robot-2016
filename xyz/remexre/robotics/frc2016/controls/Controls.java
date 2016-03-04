package xyz.remexre.robotics.frc2016.controls;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import xyz.remexre.robotics.frc2016.util.TernaryMotor;
import xyz.remexre.robotics.frc2016.util.TernaryMotor.State;

/**
 * A class that stores the output of a ControlScheme.
 * @author Nathan Ringo
 */
public class Controls {
	// Drive Train
	public Axes drive = new Axes(0, 0);
	public int winMode = 0;
	
	// Shooter
	public double armAngle = 0.0;
	public TernaryMotor.State belt = State.STOP;
	public TernaryMotor.State shooter = State.STOP;
	
	/**
	 * Dumps all the data from the Controls object to the Smart Dashboard.
	 */
	public void debug() {
		SmartDashboard.putNumber("controls.drive.x", this.drive.x);
		SmartDashboard.putNumber("controls.drive.y", this.drive.y);
		SmartDashboard.putNumber("controls.arm", this.armAngle);
		SmartDashboard.putString("controls.belt", this.belt.toString());
		SmartDashboard.putString("controls.shooter", this.shooter.toString());
	}
}
