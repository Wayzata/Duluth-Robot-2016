package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.CANTalon;
import xyz.remexre.robotics.frc2016.RobotParts;
import xyz.remexre.robotics.frc2016.util.TernaryMotor;

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
	public Shooter() {
		this.mainMotor = new TernaryMotor(new CANTalon(RobotParts.MOTORS.SHOOTER_MAIN));
		this.beltMotor = new TernaryMotor(new CANTalon (RobotParts.MOTORS.SHOOTER_BELT));
		this.armMotor = new TernaryMotor(new CANTalon (RobotParts.MOTORS.SHOOTER_ARM));
	}

	/**
	 * raises the arm
	 * lowers arm
	 * and stop arm
	 */
	public void ArmShooterMotor(TernaryMotor.State state) {
		this.armMotor.set(state); 
	}

	/**
	 * this pulls, pushes or stops the belt motor
	 */
	public void BeltShooterMotor(TernaryMotor.State state){
		this.beltMotor.set(state);
	}
	

	/**
	 * main motor in robot shooter spins and shoots the ball
	 */
	public boolean MainShooterMotor(TernaryMotor.State state) {
		this.mainMotor.set(state);

		// TODO
		return false;
	}
}
