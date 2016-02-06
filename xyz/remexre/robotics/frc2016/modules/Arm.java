package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
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
	}

	public void shoulder(TernaryMotor.State state) {
		// TODO Safety measures
		this.shoulderMotor.set(state.get());
	}

	public void elbow(TernaryMotor.State state) {
		// TODO Safety measures
		this.elbowMotor.set(state.get());
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
