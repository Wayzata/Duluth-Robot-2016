package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.util.ArmMath;
import xyz.remexre.robotics.frc2016.util.TernaryMotor;
import xyz.remexre.robotics.frc2016.util.TernaryMotor.State;

/**
 * Controls the arm.
 * @author Nathan Ringo
 */
public class Arm implements Module {
	private CANTalon shoulderMotor, elbowMotor;
	private TernaryMotor forearmMotor;
	
	// Note: These switches are Normally Closed, not Normally Open.
	// Therefore, they return true when not depressed and false when depressed.
	private DigitalInput extendSwitch, retractSwitch;

	/**
	 * Initializes the arm.
	 * @param shoulderMotorID The ID of the motor closest to the robot.
	 * @param elbowMotorID The ID of the motor in the middle of the robot.
	 * @param forearmMotorID The ID of the motor farthest from the robot.
	 * @param extendSwitchID The switch triggered when the forearm overextends.
	 * @param retractSwitchID The switch triggered when the forearm overretracts.
	 */
	public Arm(int shoulderMotorID, int elbowMotorID, int forearmMotorID,
			int extendSwitchID, int retractSwitchID) {
		// Initialize the hardware.
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
		this.shoulderPID(1, 0, 0);
		this.elbowPID(1, 0, 0);
		
		// Set the maximum speeds.
		this.forearmMotor.setMultiplier(0.4);
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
	 * Sets the PID constants on the elbow joint motor.
	 * @param p The proportional constant.
	 * @param i The integral constant.
	 * @param d The derivative constant.
	 */
	public void elbowPID(double p, double i, double d) {
		this.elbowMotor.setPID(p, i, d);
	}

	/**
	 * Sets the angle the shoulder should aim for.
	 * @param angle The angle to aim for.
	 * @return If the safety was triggered.
	 */
	public boolean shoulder(double angle) {
		boolean unsafe = ArmMath.breaksSafety(angle, this.elbowMotor.getSetpoint());
		if(!unsafe) {
			SmartDashboard.putNumber("arm.shoulder", angle);
			this.shoulderMotor.setSetpoint(angle);
		}
		return unsafe;
	}

	/**
	 * Sets the angle the elbow should aim for.
	 * @param angle The angle to aim for.
	 * @return If the safety was triggered.
	 */
	public boolean elbow(double angle) {
		boolean unsafe = ArmMath.breaksSafety(this.shoulderMotor.getSetpoint(), angle);
		if(!unsafe) {
			SmartDashboard.putNumber("arm.elbow", angle);
			this.elbowMotor.setSetpoint(angle);
		}
		return unsafe;
	}

	/**
	 * Controls the forearm's extension, including safety with the two limit switches.
	 * @param state The extension state.
	 */
	public void forearm(boolean extended) {
		if(extended && !this.extendSwitch.get()) {
			// If we're trying to extend and we're already overextending, stop.
			SmartDashboard.putBoolean("arm.forearm.safe", false);
			this.forearmMotor.set(State.STOP);
			return;
		} else if(!extended && !this.retractSwitch.get()) {
			// If we're trying to retract and we're already overretracting,
			// stop.
			SmartDashboard.putBoolean("arm.forearm.safe", false);
			this.forearmMotor.set(State.STOP);
			return;
		} else {
			SmartDashboard.putBoolean("arm.forearm.safe", true);
			this.forearmMotor.set(extended ? State.FORWARD : State.BACKWARD);
		}
	}

	@Override
	public void control(Controls controls) {
//		this.shoulder(controls.shoulderAngle);
//		this.elbow(controls.elbowAngle);
		this.forearm(controls.forearmExtended);

		SmartDashboard.putBoolean("arm.retractSwitch", this.retractSwitch.get());
		SmartDashboard.putBoolean("arm.extendSwitch", this.extendSwitch.get());
	}
	
	@Override
	public void reset() {
		this.shoulderMotor.setPosition(0.0);
		this.shoulderMotor.setSetpoint(0.0);
		SmartDashboard.putNumber("arm.shoulder", 0.0);
		this.elbowMotor.setPosition(0.0);
		this.elbowMotor.setSetpoint(0.0);
		SmartDashboard.putNumber("arm.elbow", 0.0);
	}
}
