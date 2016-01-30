package xyz.remexre.robotics.frc2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import xyz.remexre.robotics.frc2016.modules.Controls;
import xyz.remexre.robotics.frc2016.modules.DriveTrain;

public class Robot extends IterativeRobot {
	private Controls controls;
	private DriveTrain driveTrain;
	
	@Override
	public void robotInit() {
		this.controls = new Controls(RobotParts.JOYSTICKS.DRIVE,
				RobotParts.JOYSTICKS.ARM);
		this.driveTrain = new DriveTrain(RobotParts.MOTORS.FRONT_LEFT,
				RobotParts.MOTORS.BACK_LEFT,
				RobotParts.MOTORS.FRONT_RIGHT,
				RobotParts.MOTORS.BACK_RIGHT);
	}
	
	@Override
	public void teleopPeriodic() {
		this.driveTrain.drive(this.controls.getDriveJoystick());
	}
}
