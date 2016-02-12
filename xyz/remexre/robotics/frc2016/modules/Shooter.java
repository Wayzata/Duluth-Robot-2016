package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.CANTalon;
import xyz.remexre.robotics.frc2016.RobotParts;
import xyz.remexre.robotics.frc2016.util.TernaryMotor;
import xyz.remexre.robotics.frc2016.util.TernaryMotor.State;

/**
 * The controlling class for the Shooter
 * @author mariel
 */
public class Shooter {
	private TernaryMotor mainMotor;
	private TernaryMotor beltMotor;
	private TernaryMotor armMotor;

	/**
	 * Constructs the various shooter motors
	 */
	public Shooter(int shooterMotorID, int beltMotorID, int armMotorID) {
		this.mainMotor = new TernaryMotor(new CANTalon(RobotParts.MOTORS.SHOOTER));
		this.beltMotor = new TernaryMotor(new CANTalon (RobotParts.MOTORS.BELT));
		this.armMotor = new TernaryMotor(new CANTalon (RobotParts.MOTORS.SHOOTER_ARM));
	}

	/**
	 * raises the arm
	 * lowers arm
	 * and stop arm
	 * @param state
	 */
	public void arm(TernaryMotor.State state) {
		this.armMotor.set(state); 
	}

	/**
	 * this pulls, pushes or stops the belt motor
	 * @param running
	 */
	public void belt(boolean running){
		this.beltMotor.set(running ? TernaryMotor.State.FORWARD : TernaryMotor.State.STOP);
	}

	/**
	 * Tries to shoot a ball. If the ball cannot be shot (for example, if no
	 * ball is in the shooter), this will return false.
	 * @return Whether the ball was able to be shot.
	 */
	public boolean shoot() {
		this.mainMotor.set(State.FORWARD);

		// TODO
		return false;
	}
}
