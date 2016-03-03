package xyz.remexre.robotics.frc2016.controls;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import edu.wpi.first.wpilibj.Joystick;
import xyz.remexre.robotics.frc2016.RobotParts;
import xyz.remexre.robotics.frc2016.util.POVMath;;

/**
 * An enumeration of the buttons on a gamepad or joystick.
 * @author Nathan Ringo
 */
public enum GamepadButton {
	// Joystick
	JOYSTICK_TRIGGER(true, 1),
	JOYSTICK_THUMB(true, 2),
	JOYSTICK_BUTTON_3(true, 3),
	JOYSTICK_BUTTON_4(true, 4),
	JOYSTICK_BUTTON_5(true, 5),
	JOYSTICK_BUTTON_6(true, 6),
	JOYSTICK_BUTTON_7(true, 7),
	JOYSTICK_BUTTON_8(true, 8),
	JOYSTICK_BUTTON_9(true, 9),
	JOYSTICK_BUTTON_10(true, 10),
	JOYSTICK_BUTTON_11(true, 11),
	JOYSTICK_BUTTON_12(true, 12),
	
	// Gamepad
	A(false, 1), B(false, 2),
	X(false, 3), Y(false, 4),
	L1(false, 5), R1(false, 6),
	L2(false, (j) -> j.getRawAxis(RobotParts.AXES.GAMEPAD_L2) > 0.5),
	R2(false, (j) -> j.getRawAxis(RobotParts.AXES.GAMEPAD_R2) > 0.5),
	L3(false, 9), R3(false, 10),
	UP(false, (j) -> POVMath.isUp(j.getPOV())),
	DOWN(false, (j) -> POVMath.isDown(j.getPOV())),
	LEFT(false, (j) -> POVMath.isLeft(j.getPOV())),
	RIGHT(false, (j) -> POVMath.isRight(j.getPOV())),
	BACK(false, 7), START(false, 8);
	
	private boolean joystickType;
	private Predicate<Joystick> predicate;
	
	private GamepadButton(boolean joystickType, int buttonNum) {
		this(joystickType, (j) -> j.getRawButton(buttonNum));
	}
	
	private GamepadButton(boolean joystickType, Predicate<Joystick> predicate) {
		this.joystickType = joystickType;
		this.predicate = predicate;
	}

	/**
	 * Returns the buttons active on the given joystick.
	 * @param joystickType True if referring to the joystick, false if the gamepad.
	 * @param joystick The joystick object.
	 * @return The button, if the number is valid.
	 */
	public static Set<GamepadButton> get(boolean joystickType, Joystick joystick) {
		return Arrays.asList(GamepadButton.values()).stream()
				.filter((b) -> b.joystickType == joystickType)
				.filter((b) -> b.predicate.test(joystick))
				.collect(Collectors.toCollection(HashSet::new));
	}
}
