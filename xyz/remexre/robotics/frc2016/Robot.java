package xyz.remexre.robotics.frc2016;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import xyz.remexre.robotics.frc2016.autonomous.ArmDownAutonomous;
import xyz.remexre.robotics.frc2016.autonomous.AutonomousProgram;
import xyz.remexre.robotics.frc2016.autonomous.BasicAutonomous;
import xyz.remexre.robotics.frc2016.controls.ControlScheme;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;
import xyz.remexre.robotics.frc2016.controls.schemes.ChloeMarcusControlScheme;
import xyz.remexre.robotics.frc2016.modules.Controllers;
import xyz.remexre.robotics.frc2016.modules.DriveTrain;
import xyz.remexre.robotics.frc2016.modules.Module;
import xyz.remexre.robotics.frc2016.modules.Shooter;
import xyz.remexre.robotics.frc2016.modules.Vision;

public class Robot extends IterativeRobot {
	private List<AutonomousProgram> autonomi;
	private SendableChooser autonomousChooser;
	private long autonomousStartTime;
	private Controllers controllers;
	private List<ControlScheme> controlSchemes;
	private SendableChooser controlSchemeChooser;
	private Set<Module> modules;
	
	@Override
	public void robotInit() {
		this.autonomi = new ArrayList<>();
		this.autonomi.add(new BasicAutonomous());
		this.autonomi.add(new ArmDownAutonomous());
		
		this.autonomousChooser = new SendableChooser();
		this.autonomousChooser.addDefault(this.autonomi.get(0).toString(), this.autonomi.get(0));
		for(int i = 1; i < this.autonomi.size(); i++) {
			AutonomousProgram program = this.autonomi.get(i);
			this.autonomousChooser.addObject(program.toString(), program);
		}
		
		this.controllers = new Controllers(RobotParts.JOYSTICKS.DRIVE,
				RobotParts.JOYSTICKS.ARM);
		
		this.controlSchemes = new ArrayList<>();
		this.controlSchemes.add(new ChloeMarcusControlScheme());

		this.controlSchemeChooser = new SendableChooser();
		this.controlSchemeChooser.addDefault(this.controlSchemes.get(0).toString(), this.controlSchemes.get(0));
		for(int i = 1; i < this.controlSchemes.size(); i++) {
			ControlScheme controlScheme = this.controlSchemes.get(i);
			this.controlSchemeChooser.addObject(controlScheme.toString(), controlScheme);
		}
		
		this.modules = new HashSet<>();
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

		this.modules.forEach((module) -> module.reset());
	}
	
	@Override
	public void autonomousInit() {
		this.autonomousStartTime = System.currentTimeMillis();
	}
	
	@Override
	public void autonomousPeriodic() {
		AutonomousProgram program = (AutonomousProgram) this.autonomousChooser.getSelected();
		Controls controls = program.periodic(System.currentTimeMillis() - this.autonomousStartTime);
		this.modules.forEach((module) -> module.control(controls));
	}

	@Override
	public void teleopPeriodic() {
		// Get the gamepad controls.
		ControlScheme controlScheme = (ControlScheme) this.controlSchemeChooser.getSelected();
		Set<GamepadButton> buttons = this.controllers.getGamepadButtons(controlScheme);
		Controls controls = controlScheme.map(buttons,
				this.controllers.getDriveAxes(),
				this.controllers.getSlider(),
				this.controllers.getLeftAxes(),
				this.controllers.getRightAxes());
		controls.debug();

		// Send state to the modules.
		this.modules.forEach((module) -> module.control(controls));
	}
}
