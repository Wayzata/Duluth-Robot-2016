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
	private Controllers controllers;
	private ControlScheme controlScheme;

	private Arm arm; // TODO Implement Arm.
	private DriveTrain driveTrain;
	private Shooter shooter;
	private Vision vision;
	private TernaryMotor winch;

	@Override
	public void robotInit() {
		this.controllers = new Controllers(RobotParts.JOYSTICKS.DRIVE,
				RobotParts.JOYSTICKS.ARM);
		// TODO Add a control scheme chooser on the SmartDashboard.
		this.controlScheme = new BasicControlScheme();

		this.arm = new Arm(RobotParts.MOTORS.SHOULDER,
				RobotParts.MOTORS.ELBOW,
				RobotParts.MOTORS.FOREARM,
				RobotParts.SWITCHES.EXTEND,
				RobotParts.SWITCHES.RETRACT);
		this.driveTrain = new DriveTrain(RobotParts.MOTORS.FRONT_LEFT,
				RobotParts.MOTORS.BACK_LEFT,
				RobotParts.MOTORS.FRONT_RIGHT,
				RobotParts.MOTORS.BACK_RIGHT);
		this.shooter = new Shooter(RobotParts.MOTORS.SHOOTER,
				RobotParts.MOTORS.BELT,
				RobotParts.MOTORS.SHOOTER_ARM);
		this.vision = new Vision("cam0");
		// TODO Make a winch wrapper with safety measures.
		this.winch = new TernaryMotor(new CANTalon(RobotParts.MOTORS.WINCH));

	}

	@Override
	public void teleopPeriodic() {
		// Send a camera frame to the SmartDashboard.
		this.vision.sendFrame();

		// Get the gamepad controls.
		Set<GamepadButton> buttons = this.controllers.getGamepadButtons().stream()
				.filter(this.controlScheme.filter())
				.collect(Collectors.toCollection(HashSet::new));
		Controls controls = this.controlScheme.map(buttons,
				this.controllers.getDriveAxes(),
				this.controllers.getLeftAxes(),
				this.controllers.getRightAxes());

		// Control the arm.
		this.arm.shoulder(controls.shoulderAngle);
		this.arm.elbow(controls.elbowAngle);
		this.arm.forearm(controls.forearm);

		// Control the drive train.
		if(controls.drive.isZero()) this.driveTrain.brake();
		else this.driveTrain.drive(controls.drive);

		// Control the shooter.
		this.shooter.arm(controls.shooterArm);
		this.shooter.belt(controls.enableBelt);
		if(controls.enableShooter) this.shooter.shoot();

		// Control the winch.
		this.winch.set(controls.winch);
	}
}
