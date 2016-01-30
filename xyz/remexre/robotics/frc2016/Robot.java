package xyz.remexre.robotics.frc2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import xyz.remexre.robotics.frc2016.modules.Controls;
import xyz.remexre.robotics.frc2016.modules.DriveTrain;

public class Robot extends IterativeRobot {
	private Controls controls;
	private DriveTrain driveTrain;
	
	@Override
	public void robotInit() {
		this.controls = new Controls(0, 1);
		this.driveTrain = new DriveTrain(17, 18, 19, 20);
	}
	
	@Override
	public void teleopPeriodic() {
		this.driveTrain.drive(this.controls.getDriveJoystick());
	}
}
