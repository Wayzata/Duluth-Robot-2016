package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xyz.remexre.robotics.frc2016.RobotParts;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.util.TernaryMotor;

/**
 * The controlling class for the Shooter
 * @author mariel
 */
public class Shooter implements Module {
	private TernaryMotor mainMotor;
	private TernaryMotor beltMotor;
	private CANTalon armMotor;

	/**
	 * Constructs the various shooter motors
	 */
	public Shooter(int shooterMotorID, int beltMotorID, int armMotorID) {
		this.mainMotor = new TernaryMotor(new CANTalon(RobotParts.MOTORS.SHOOTER));
		this.mainMotor.setMultiplier(0.6);
		this.beltMotor = new TernaryMotor(new CANTalon (RobotParts.MOTORS.BELT));
		this.armMotor = new CANTalon(RobotParts.MOTORS.SHOOTER_ARM);
		this.armMotor.changeControlMode(TalonControlMode.Position);
		this.armMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.armMotor.setPID(1, 0, 0);
	}

	/**
	 * raises the arm
	 * lowers arm
	 * and stop arm
	 * @param state
	 */
	public void arm(double angle) {
		this.armMotor.setSetpoint(angle);
	}

	/**
	 * this pulls, pushes or stops the belt motor
	 * @param running
	 */
	public void belt(TernaryMotor.State state){
		this.beltMotor.set(state);
	}


	/**
	 * this pulls, pushes or stops the belt motor
	 * @param running
	 */
	public void shooter(TernaryMotor.State state) {
		this.mainMotor.set(state);
	}
	
	@Override
	public void control(Controls controls) {
		this.arm(controls.armAngle);
		this.belt(controls.belt);
		this.shooter(controls.shooter);
	}
}
