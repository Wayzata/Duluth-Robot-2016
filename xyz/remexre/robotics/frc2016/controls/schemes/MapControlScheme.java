package xyz.remexre.robotics.frc2016.controls.schemes;

import java.util.Map;
import java.util.Optional;

import xyz.remexre.robotics.frc2016.controls.Control;
import xyz.remexre.robotics.frc2016.controls.ControlScheme;
import xyz.remexre.robotics.frc2016.controls.GamepadButton;

/**
 * A Control Scheme that uses a map.
 * @author Nathan Ringo
 */
public class MapControlScheme implements ControlScheme {
	private Map<GamepadButton, Control> entries;
	
	/**
	 * Creates a new control scheme with a given map.
	 * @param map The map of buttons to controls.
	 */
	public MapControlScheme(Map<GamepadButton, Control> map) {
		this.entries = map;
	}
	
	@Override
	public Optional<Control> mapOne(GamepadButton button) {
		return Optional.ofNullable(this.entries.get(button));
	}
}
