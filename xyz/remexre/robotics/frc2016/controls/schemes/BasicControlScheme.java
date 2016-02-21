package xyz.remexre.robotics.frc2016.controls.schemes;

import java.util.function.Consumer;

import xyz.remexre.robotics.frc2016.controls.Axes;
import xyz.remexre.robotics.frc2016.controls.ConflictGroup;
import xyz.remexre.robotics.frc2016.controls.ControlSchemeBase;
import xyz.remexre.robotics.frc2016.controls.Controls;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;
import xyz.remexre.robotics.frc2016.controls.POV;
import xyz.remexre.robotics.frc2016.controls.POV.Horizontal;
import xyz.remexre.robotics.frc2016.controls.POV.Vertical;
import xyz.remexre.robotics.frc2016.util.TernaryMotor.State;

import static xyz.remexre.robotics.frc2016.controls.GamepadButton.*;
import static xyz.remexre.robotics.frc2016.util.TernaryMotor.State.*;

/**
 * Nathan's Preferred Controls. The control scheme is non-modal, but some
 * mappings may therefore be inconvenient at times, especially on the gamepad.
 * 
 * The arm's joints are controlled by the Y axes of the gamepad, where the left
 * side is the "elbow" joint and the right side is the "shoulder" joint. The
 * forearm is controlled by the up and down on the POV.
 * 
 * The main driving is controlled by the joystick.
 * 
 * The shooting wheels are controlled by the A button, and the belt is
 * controlled by the B button. The belt arm is raised and lowered with the R1
 * and L1 buttons, respectively.
 * 
 * The winch is tightened/retracted with the right on the POV, and
 * loosened/extended with left on the POV.
 * 
 * @author Nathan Ringo
 */
public class BasicControlScheme extends ControlSchemeBase {
	public BasicControlScheme() {
		super(new ConflictGroup(A),
				new ConflictGroup(B),
				new ConflictGroup(L1, R1));
	}

	@Override
	public Consumer<Controls> mapButtons(GamepadButton button) {
		switch(button) {
		// Shooter
		case A: return (c) -> c.enableShooter = true;
		case B: return (c) -> c.enableBelt = true;
		case L1: return (c) -> c.forearm = State.FORWARD;
		case R1: return (c) -> c.forearm = State.BACKWARD;
		// Do nothing on an unknown button
		default: return (c) -> {};
		}
	}

	@Override
	public Consumer<Controls> mapDriveAxes(Axes driveAxes) {
		return (c) -> c.drive = driveAxes;
	}
	
	@Override
	public Consumer<Controls> mapSlider(double slider) {
		return (c) -> c.driveSpeedMultiplier = (slider + 1) / 2;
	}

	@Override
	public Consumer<Controls> mapLeftAxes(Axes leftAxes) {
		return (c) -> c.elbowAngle += leftAxes.y;
	}

	@Override
	public Consumer<Controls> mapRightAxes(Axes rightAxes) {
		return (c) -> c.shoulderAngle += rightAxes.y;
	}

	@Override
	public Consumer<Controls> mapPOV(POV pov) {
		Consumer<Controls> forearm;
		if(pov.vertical == Vertical.UP) {
			forearm = (c) -> c.forearm = FORWARD;
		} else if(pov.vertical == Vertical.DOWN) {
			forearm = (c) -> c.forearm = BACKWARD;
		} else {
			forearm = (c) -> c.forearm = STOP;
		}

		Consumer<Controls> winch;
		if(pov.horizontal == Horizontal.LEFT) {
			winch = (c) -> c.winch = FORWARD;
		} else if(pov.horizontal == Horizontal.RIGHT) {
			winch = (c) -> c.winch = BACKWARD;
		} else {
			winch = (c) -> c.winch = STOP;
		}

		return forearm.andThen(winch);
	}
}
