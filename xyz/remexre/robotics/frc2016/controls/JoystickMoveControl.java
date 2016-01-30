package xyz.remexre.robotics.frc2016.controls;

import edu.wpi.first.wpilibj.Joystick;
import xyz.remexre.robotics.frc2016.Control;
import xyz.remexre.robotics.frc2016.modules.DriveTrain;

public class JoystickMoveControl implements Control {
	private Joystick joystick;
	
	public JoystickMoveControl(Joystick joystick) {
		this.joystick = joystick;
	}

	@Override
	public void action(DriveTrain driveTrain) {
		driveTrain.drive(joystick);
	}
}
