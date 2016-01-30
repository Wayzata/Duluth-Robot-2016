package xyz.remexre.robotics.frc2016;

import java.util.Set;

import edu.wpi.first.wpilibj.IterativeRobot;
import xyz.remexre.robotics.frc2016.controls.schemes.BasicControlScheme;
import xyz.remexre.robotics.frc2016.modules.Controllers;
import xyz.remexre.robotics.frc2016.modules.DriveTrain;

public class Robot extends IterativeRobot {
	private Controllers controls;
	private DriveTrain driveTrain;
	private ControlSchemeManager csm;
	
	@Override
	public void robotInit() {
		this.controls = new Controllers(RobotParts.JOYSTICKS.DRIVE,
				RobotParts.JOYSTICKS.ARM);
		this.driveTrain = new DriveTrain(RobotParts.MOTORS.FRONT_LEFT,
				RobotParts.MOTORS.BACK_LEFT,
				RobotParts.MOTORS.FRONT_RIGHT,
				RobotParts.MOTORS.BACK_RIGHT);
		this.csm = new ControlSchemeManager(new BasicControlScheme(this.controls));
	}
	
	@Override
	public void teleopPeriodic() {
		Set<Control> controls = this.csm.getControls();
		controls.stream().forEach((f) -> f.action(driveTrain));
	}
}
