package xyz.remexre.robotics.frc2016.modules;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import xyz.remexre.robotics.frc2016.RobotParts;
import xyz.remexre.robotics.frc2016.controls.Axes;
import xyz.remexre.robotics.frc2016.controls.ControlScheme;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;

/**
 * Manages {@link Joystick}s and other user inputs.
 * @author Nathan Ringo
 */
public class Controllers {
	private Joystick joystick, gamepad;

	/**
	 * Constructs a controller based on the joystick numbers.
	 * @param driveID The number of the drive joystick.
	 * @param armID The number of the arm/shooter/winch game controller.
	 */
	public Controllers(int driveID, int armID) {
		this.joystick = new Joystick(driveID);
		this.gamepad = new Joystick(armID);
	}
	
	/**
	 * Returns all pressed buttons, minus any conflicting buttons.
	 * @param controlScheme The control scheme to load conflicts from.
	 * @return The buttons.
	 */
	public Set<GamepadButton> getGamepadButtons(ControlScheme controlScheme) {
		StringBuilder buttonString = new StringBuilder();
		
		Set<GamepadButton> buttons = GamepadButton.get(true, this.joystick);
		buttons.addAll(GamepadButton.get(false, this.gamepad));
		buttons = buttons.stream()
				.filter(controlScheme.filter())
				.map((button) -> {
					buttonString.append(button.toString());
					buttonString.append(' ');
					return button;
				})
				.collect(Collectors.toCollection(HashSet::new));
		
		SmartDashboard.putString("controllers.buttons", buttonString.toString());
		
		return buttons;
	}

	/**
	 * Returns Axes corresponding to the drive joystick.
	 * @return An Axes object.
	 */
	public Axes getDriveAxes() {
		double x = this.joystick.getRawAxis(RobotParts.AXES.JOYSTICK_X);
		double y = this.joystick.getRawAxis(RobotParts.AXES.JOYSTICK_Y);

		SmartDashboard.putNumber("controllers.drive.x", x);
		SmartDashboard.putNumber("controllers.drive.y", y);
		
		return new Axes(x, y);
	}

	/**
	 * Returns the slider position.
	 * @return The position, -1 to 1.
	 */
	public double getSlider() {
		double slider = this.joystick.getRawAxis(RobotParts.AXES.JOYSTICK_SLIDER);
		
		SmartDashboard.putNumber("controllers.slider", slider);
		
		return slider;
	}
	
	/**
	 * Returns Axes corresponding to the left thumbstick of the gamepad, after
	 * being adjusted for the speed multiplier.
	 * @return An Axes object.
	 */
	public Axes getLeftAxes() {
		double x = this.gamepad.getRawAxis(RobotParts.AXES.GAMEPAD_LEFT_X);
		double y = this.gamepad.getRawAxis(RobotParts.AXES.GAMEPAD_LEFT_Y);

		SmartDashboard.putNumber("controllers.left.x", x);
		SmartDashboard.putNumber("controllers.left.y", y);
		
		return new Axes(x, y);
	}

	/**
	 * Returns Axes corresponding to the right thumbstick of the gamepad, after
	 * being adjusted for the speed multiplier.
	 * @return An Axes object.
	 */
	public Axes getRightAxes() {
		double x = this.gamepad.getRawAxis(RobotParts.AXES.GAMEPAD_RIGHT_X);
		double y = this.gamepad.getRawAxis(RobotParts.AXES.GAMEPAD_RIGHT_Y);

		SmartDashboard.putNumber("controllers.right.x", x);
		SmartDashboard.putNumber("controllers.right.y", y);
		
		return new Axes(x, y);
	}
}
