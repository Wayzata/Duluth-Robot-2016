package xyz.remexre.robotics.frc2016.controls.schemes;

import java.util.HashSet;
import java.util.Set;

import xyz.remexre.robotics.frc2016.Control;
import xyz.remexre.robotics.frc2016.ControlScheme;
import xyz.remexre.robotics.frc2016.controls.JoystickMoveControl;
import xyz.remexre.robotics.frc2016.modules.Controllers;

public class BasicControlScheme implements ControlScheme {
	private Controllers controls;
	
	public BasicControlScheme(Controllers controls) {
		this.controls = controls;
	}

	@Override
	public String getName() { return "Basic Controls"; }

	@Override
	public Set<Control> getControls() {
		Set<Control> controls = new HashSet<>();
		
		controls.add(new JoystickMoveControl(this.controls.getDriveJoystick()));
		
		return controls;
	}
}
