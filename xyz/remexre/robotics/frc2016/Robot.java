package xyz.remexre.robotics.frc2016;

import java.util.HashSet;
import java.util.Set;

import edu.wpi.first.wpilibj.IterativeRobot;
import xyz.remexre.robotics.frc2016.controls.ControlScheme;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;
import xyz.remexre.robotics.frc2016.controls.schemes.ChloeMarcusControlScheme;
import xyz.remexre.robotics.frc2016.modules.Arm;
import xyz.remexre.robotics.frc2016.modules.Controllers;
import xyz.remexre.robotics.frc2016.modules.DriveTrain;
import xyz.remexre.robotics.frc2016.modules.Module;
import xyz.remexre.robotics.frc2016.modules.Shooter;
import xyz.remexre.robotics.frc2016.modules.Vision;
import xyz.remexre.robotics.frc2016.modules.Winch;

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
	private Set<Module> modules;

	@Override
	public void robotInit() {
		this.controllers = new Controllers(RobotParts.JOYSTICKS.DRIVE,
				RobotParts.JOYSTICKS.ARM);
		// TODO Add a control scheme chooser on the SmartDashboard.
		this.controlScheme = new ChloeMarcusControlScheme();

		this.modules = new HashSet<>();
		this.modules.add(new Arm(
				RobotParts.MOTORS.SHOULDER,
				RobotParts.MOTORS.ELBOW,
				RobotParts.MOTORS.FOREARM,
				RobotParts.SWITCHES.EXTEND,
				RobotParts.SWITCHES.RETRACT));
		this.modules.add(new DriveTrain(
				RobotParts.MOTORS.FRONT_LEFT,
				RobotParts.MOTORS.BACK_LEFT,
				RobotParts.MOTORS.FRONT_RIGHT,
				RobotParts.MOTORS.BACK_RIGHT));
		this.modules.add(new Shooter(
				RobotParts.MOTORS.SHOOTER,
				RobotParts.MOTORS.BELT,
				RobotParts.MOTORS.SHOOTER_ARM));
		this.modules.add(new Vision("cam0"));
		this.modules.add(new Winch(RobotParts.MOTORS.WINCH));

		this.modules.forEach((module) -> module.reset());
	}
	
	@Override
	public void autonomousInit() {
		this.modules.stream()
			.filter((m) -> m instanceof Shooter)
			.map((m) -> (Shooter) m);
	}

	@Override
	public void teleopPeriodic() {
		// Get the gamepad controls.
		Set<GamepadButton> buttons = this.controllers.getGamepadButtons(this.controlScheme);
		Controls controls = this.controlScheme.map(buttons,
				this.controllers.getDriveAxes(),
				this.controllers.getSlider(),
				this.controllers.getLeftAxes(),
				this.controllers.getRightAxes());
		controls.debug();

		// Send state to the modules.
		this.modules.forEach((module) -> module.control(controls));
	}
}
