package xyz.remexre.robotics.frc2016.modules;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.util.TernaryMotor;

public class Winch implements Module {
	private CANTalon motor;
	
	public Winch(int motorID) {
		this.motor = new CANTalon(motorID);
		this.motor.changeControlMode(TalonControlMode.Position);
		this.motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		this.motor.setPID(1, 0, 0);
	}
	
	@Override
	public void control(Controls controls) {
//		this.motor.setSetpoint(controls);
	}
}
