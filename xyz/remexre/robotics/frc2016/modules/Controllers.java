package xyz.remexre.robotics.frc2016.modules;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.wpi.first.wpilibj.Joystick;
import xyz.remexre.robotics.frc2016.controls.Axes;
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
	 * Returns all pressed buttons on the gamepad.
	 * @return The buttons.
	 */
	public Set<GamepadButton> getGamepadButtons() {
		Set<GamepadButton> buttons = this.getGamepadButtons(this.joystick, true);
		buttons.addAll(this.getGamepadButtons(this.gamepad, false));
		return buttons;
	}

	/**
	 * Returns the pressed buttons on the given gamepad.
	 * @param joystick The joystick to check.
	 * @param ident The identifier to pass to {@link GamepadButton#get(boolean, int)}.
	 * @return The buttons.
	 */
	private Set<GamepadButton> getGamepadButtons(Joystick joystick, boolean ident) {
		return IntStream.range(0, joystick.getButtonCount())
				.filter(joystick::getRawButton)
				.mapToObj((n) -> GamepadButton.get(ident, n))
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toCollection(HashSet::new));
	}

	/**
	 * Returns Axes corresponding to the drive joystick, after being adjusted
	 * for the speed multiplier.
	 * @return An Axes object.
	 */
	public Axes getDriveAxes() {
		return new Axes(
				this.joystick.getX(),
				this.joystick.getY()
				).times((this.joystick.getZ() + 1) / 2);
	}

	/**
	 * Returns Axes corresponding to the left thumbstick of the gamepad, after
	 * being adjusted for the speed multiplier.
	 * @return An Axes object.
	 */
	public Axes getLeftAxes() {
		return new Axes(
				this.joystick.getRawAxis(0),
				this.joystick.getRawAxis(1)
				);
	}

	/**
	 * Returns Axes corresponding to the right thumbstick of the gamepad, after
	 * being adjusted for the speed multiplier.
	 * @return An Axes object.
	 */
	public Axes getRightAxes() {
		return new Axes(
				this.joystick.getRawAxis(4),
				this.joystick.getRawAxis(5)
				);
	}
}
