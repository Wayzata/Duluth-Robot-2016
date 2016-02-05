package xyz.remexre.robotics.frc2016;

import java.util.HashMap;
import java.util.Set;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import xyz.remexre.robotics.frc2016.controls.Control;
import xyz.remexre.robotics.frc2016.controls.ControlScheme;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;
import xyz.remexre.robotics.frc2016.controls.schemes.MapControlScheme;
import xyz.remexre.robotics.frc2016.modules.Arm;
import xyz.remexre.robotics.frc2016.modules.Controllers;
import xyz.remexre.robotics.frc2016.modules.DriveTrain;
import xyz.remexre.robotics.frc2016.modules.Shooter;
import xyz.remexre.robotics.frc2016.modules.Vision;
import xyz.remexre.robotics.frc2016.modules.Winch;

public class Robot extends IterativeRobot {
	private Arm arm; // TODO Implement Arm.
	private Controllers controllers;
	private DriveTrain driveTrain;
	private Shooter shooter; // TODO Implement Shooter.
	private Vision vision;
	private Winch winch;
	private ControlScheme controlScheme;
	
	@Override
	public void robotInit() {
		this.controllers = new Controllers(RobotParts.JOYSTICKS.DRIVE,
				RobotParts.JOYSTICKS.ARM).setSafety(4);
		this.driveTrain = new DriveTrain(RobotParts.MOTORS.FRONT_LEFT,
				RobotParts.MOTORS.BACK_LEFT,
				RobotParts.MOTORS.FRONT_RIGHT,
				RobotParts.MOTORS.BACK_RIGHT);
		this.vision = new Vision("cam0");
		this.winch = new Winch(new CANTalon(RobotParts.MOTORS.WINCH));
		
		// WARNING: This creates an anonymous subclass of HashMap. So don't abuse.
		this.controlScheme = new MapControlScheme(new HashMap<GamepadButton, Control>(){{
			this.put(GamepadButton.R1, Control.RETRACT_WINCH);
			this.put(GamepadButton.R2, Control.EXTEND_FOREARM);
			this.put(GamepadButton.L1, Control.EXTEND_WINCH);
			this.put(GamepadButton.L2, Control.RETRACT_FOREARM);
		}});
	}
	
	@Override
	public void teleopPeriodic() {
		// Send a camera frame to the SmartDashboard.
		this.vision.sendFrame();
		// Drive based on the controls.
		if(this.controllers.getBrakeButton())
			this.driveTrain.brake();
		else
			this.driveTrain.drive(this.controllers.getDriveSpeed(),
					this.controllers.getDriveTurn());
		// Get the gamepad controls.
		Set<Control> controls = this.controllers.getControls(this.controlScheme);
		// Control the arm.
		//  Start with the shoulder joint.
		if(controls.contains(Control.ACUTIFY_SHOULDER))
			this.arm.acutifyShoulder();
		else if(controls.contains(Control.OBTUSIFY_SHOULDER))
			this.arm.obtusifyShoulder();
		else
			this.arm.stopShoulder();
		//  Then the elbow joint.
		if(controls.contains(Control.ACUTIFY_ELBOW))
			this.arm.acutifyElbow();
		else if(controls.contains(Control.OBTUSIFY_ELBOW))
			this.arm.obtusifyElbow();
		else
			this.arm.stopElbow();
		//  And lastly the forearm.
		if(controls.contains(Control.EXTEND_FOREARM))
			this.arm.extendForearm();
		else if(controls.contains(Control.RETRACT_FOREARM))
			this.arm.retractForearm();
		else
			this.arm.stopForearm();
		// Control the shooter.
		//  First the arm.
		if(controls.contains(Control.RAISE_SHOOTER_ARM))
			this.shooter.raiseArm();
		else if(controls.contains(Control.LOWER_SHOOTER_ARM))
			this.shooter.lowerArm();
		else
			this.shooter.stopArm();
		//  Then the belt.
		if(controls.contains(Control.PULL_SHOOTER_BELT))
			this.shooter.pullBelt();
		else if(controls.contains(Control.PUSH_SHOOTER_BELT))
			this.shooter.pushBelt();
		else
			this.shooter.stopBelt();
		// Control the winch.
		if(controls.contains(Control.EXTEND_WINCH))
			this.winch.extend();
		else if(controls.contains(Control.RETRACT_WINCH))
			this.winch.retract();
		else
			this.winch.stop();
	}
}
