package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xyz.remexre.robotics.frc2016.util.TernaryMotor;
import xyz.remexre.robotics.frc2016.util.TernaryMotor.State;

/**
 * Controls the arm.
 * @author Nathan Ringo
 */
public class Arm {
	private CANTalon shoulderMotor, elbowMotor;
	private TernaryMotor forearmMotor;
	private DigitalInput extendSwitch, retractSwitch;

	public Arm(int shoulderMotorID, int elbowMotorID, int forearmMotorID,
			int extendSwitchID, int retractSwitchID) {
		this.shoulderMotor = new CANTalon(shoulderMotorID);
		this.elbowMotor = new CANTalon(elbowMotorID);
		this.forearmMotor = new TernaryMotor(new CANTalon(forearmMotorID));
		this.extendSwitch = new DigitalInput(extendSwitchID);
		this.retractSwitch = new DigitalInput(retractSwitchID);
		// Set up the modes on the joint motors.
		this.shoulderMotor.changeControlMode(TalonControlMode.Position);
		this.shoulderMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.elbowMotor.changeControlMode(TalonControlMode.Position);
		this.elbowMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		// Set up PID constants to safe defaults.
		this.shoulderMotor.setPID(1, 0, 0);
		this.elbowMotor.setPID(1, 0, 0);
	}

	/**
	 * Sets the angle the shoulder should aim for.
	 * @param angle The angle to aim for.
	 * @return If the safety was triggered.
	 */
	public boolean shoulder(double angle) {
		// TODO Safety measures
		this.shoulderMotor.set(angle);
		return false; // TODO
	}

	/**
	 * Sets the PID constants on the shoulder joint motor.
	 * @param p The proportional constant.
	 * @param i The integral constant.
	 * @param d The derivative constant.
	 */
	public void shoulderPID(double p, double i, double d) {
		this.shoulderMotor.setPID(p, i, d);
	}

	/**
	 * Sets the angle the elbow should aim for.
	 * @param angle The angle to aim for.
	 * @return If the safety was triggered.
	 */
	public boolean elbow(double angle) {
		// TODO Safety measures
		this.elbowMotor.set(angle);
		return false; // TODO
	}
	
	/**
	 * Sets the PID constants on the elbow joint motor.
	 * @param p The proportional constant.
	 * @param i The integral constant.
	 * @param d The derivative constant.
	 */
	public void elbowPID(double p, double i, double d) {
		this.elbowMotor.setPID(p, i, d);
	}

	/**
	 * Controls the forearm's extension, including safety with the two limit switches.
	 * @param state The extension state.
	 */
	public void forearm(TernaryMotor.State state) {
		if(state == State.FORWARD && this.extendSwitch.get()) {
			return;
		} else if(state == State.BACKWARD && this.retractSwitch.get()) {
			return;
		} else {
			this.forearmMotor.set(state);
		}
	}
}
