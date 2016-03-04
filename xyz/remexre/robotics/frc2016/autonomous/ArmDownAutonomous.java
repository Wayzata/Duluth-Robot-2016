package xyz.remexre.robotics.frc2016.autonomous;

import xyz.remexre.robotics.frc2016.controls.Axes;
import xyz.remexre.robotics.frc2016.controls.Controls;

public class ArmDownAutonomous extends AutonomousProgram {	
	@Override
	public Controls periodic(long timeInAutonomous) {
		double speed = 0.0;
		if(timeInAutonomous <= 500)
			speed = timeInAutonomous / 500.0;
		else if(500 < timeInAutonomous && timeInAutonomous < 3000)
			speed = 1.0;
		
		Controls controls = new Controls();
		controls.armAngle = -2950;
		controls.drive = new Axes(0.0, speed);
		return controls;
	}

	@Override
	public String toString() {
		return "Forward for 3 seconds with arm down";
	}
}
