package xyz.remexre.robotics.frc2016.util;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * A simplifying class for motors that will always be run at the same speed.
 * @author Nathan Ringo
 */
public class TernaryMotor {
	private SpeedController motor;
	private double multiplier;
	
	/**
	 * Wraps a motor with a multiplier of 1.
	 * @param motor The motor to wrap.
	 */
	public TernaryMotor(SpeedController motor) {
		this(motor, 1.0);
	}
	
	/**
	 * Wraps a motor with a given multiplier.
	 * @param motor The motor to wrap.
	 * @param multiplier The multiplier.
	 */
	public TernaryMotor(SpeedController motor, double multiplier) {
		this.motor = motor;
		this.multiplier = multiplier;
	}
	
	/**
	 * Sets the motor to a given state.
	 * @param state The state.
	 */
	public void set(State state) {
		this.motor.set(state.speed * this.multiplier);
	}

	/**
	 * Sets the multiplier.
	 * @param multiplier The new multiplier.
	 * @return This object, for chaining.
	 */
	public TernaryMotor setMultiplier(double multiplier) {
		this.multiplier = multiplier;
		return this;
	}
	
	/**
	 * An enumeration to describe the valid states of a {@link TernaryMotor}.
	 * @author Nathan Ringo
	 */
	public static enum State {
		STOP(0),
		FORWARD(1),
		BACKWARD(-1);
		
		private double speed;
		private State(double speed) { this.speed = speed; }
	}
}
