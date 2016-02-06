package xyz.remexre.robotics.frc2016;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import xyz.remexre.robotics.frc2016.controls.ControlScheme;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;
import xyz.remexre.robotics.frc2016.controls.schemes.BasicControlScheme;
import xyz.remexre.robotics.frc2016.modules.Arm;
import xyz.remexre.robotics.frc2016.modules.Controllers;
import xyz.remexre.robotics.frc2016.modules.DriveTrain;
import xyz.remexre.robotics.frc2016.modules.Shooter;
import xyz.remexre.robotics.frc2016.modules.Vision;
import xyz.remexre.robotics.frc2016.util.TernaryMotor;

/**
 * The Contingency Robot should not be used unless the "real robot" really and
 * truly cannot be -- the new programmers are <b>not</b> going to be up to
 * debugging this code, and they need to learn both how to write code by
 * themselves and debug it.
 * @author Nathan Ringo
 */
public class Robot extends IterativeRobot {
	private Arm arm; // TODO Implement Arm.
	private Controllers controllers;
	private DriveTrain driveTrain;
	private Shooter shooter; // TODO Implement Shooter.
	private Vision vision;
	private TernaryMotor winch;
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
		this.winch = new TernaryMotor(new CANTalon(RobotParts.MOTORS.WINCH));
		
		// TODO Add a control scheme chooser on the SmartDashboard.
		this.controlScheme = new BasicControlScheme();
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
		Set<GamepadButton> buttons = this.controllers.getGamepadButtons().stream()
			.filter(this.controlScheme.filter())
			.collect(Collectors.toCollection(HashSet::new));
		Controls controls = this.controlScheme.map(buttons);
		// Control the arm.
		this.arm.shoulder(controls.shoulder);
		this.arm.elbow(controls.elbow);
		this.arm.forearm(controls.forearm);
		// Control the shooter.
		this.shooter.arm(controls.shooterArm);
		this.shooter.belt(controls.shooterArm);
		if(controls.shooter) this.shooter.shoot();
		// Control the winch.
		this.winch.set(controls.winch);
	}
}
