package xyz.remexre.robotics.frc2016.modules;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.wpi.first.wpilibj.Joystick;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;

/**
 * Manages {@link Joystick}s and other user inputs.
 * @author Nathan Ringo
 */
public class Controllers {
	private double safety;
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
	 * Returns whether the "brake button" is pressed or not.
	 * @return The button state.
	 */
	public boolean getBrakeButton() { return this.joystick.getRawButton(1); }
	
	/**
	 * Returns all pressed buttons on the gamepad.
	 * @return The buttons.
	 */
	public Set<GamepadButton> getGamepadButtons() {
		return IntStream.range(0, this.gamepad.getButtonCount())
			.filter(this.gamepad::getRawButton)
			.mapToObj(GamepadButton::get)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toCollection(HashSet::new));
	}
	
	/**
	 * Returns the speed requested by the driver via the joystick.
	 * @return The drive joystick's speed.
	 */
	public double getDriveSpeed() {
		return this.joystick.getY() * this.joystick.getZ() * this.safety;
	}
	
	/**
	 * Returns the amount of turning requested by the driver via the joystick.
	 * @return The drive joystick's turn factor.
	 */
	public double getDriveTurn() {
		return this.joystick.getX() * this.joystick.getZ() * this.safety;
	}

	/**
	 * Sets the safety factor to the reciprocal of the argument.
	 * @param i The reciprocal of the safety factor.
	 * @return This object, to allow chaining.
	 */
	public Controllers setSafety(double i) {
		this.safety = 1/i;
		return this;
	}
}
