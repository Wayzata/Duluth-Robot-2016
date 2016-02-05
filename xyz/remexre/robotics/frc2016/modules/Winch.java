package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * The controlling class for the winch.
 * @author Nathan Ringo
 */
public class Winch {
	private CANTalon motor;
	
	/**
	 * Constructs a winch which uses a given motor.
	 * @param motor The motor that controls the winch.
	 */
	public Winch(CANTalon motor) {
		this.motor = motor;
	}
	
	/**
	 * Starts extending the rope.
	 */
	public void extend() { this.motor.set(1); }
	
	/**
	 * Starts retracting the rope.
	 */
	public void retract() { this.motor.set(-1); }
	
	/**
	 * Stops moving the rope.
	 */
	public void stop() { this.motor.set(0); }
}
