package xyz.remexre.robotics.frc2016.util;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * MultiSpeedController allows proxying commands to multiple SpeedControllers.
 * @author Nathan Ringo
 */
public class MultiSpeedController implements SpeedController {
	private List<SpeedController> speedControllers;

	/**
	 * This constructor accepts varargs.
	 * @param controllers The SpeedControllers to proxy to.
	 */
	public MultiSpeedController(SpeedController ... controllers) {
		this(Arrays.asList(controllers));
	}

	/**
	 * This constructor accepts a list.
	 * @param controllers The SpeedControllers to proxy to.
	 */
	public MultiSpeedController(List<SpeedController> controllers) {
		this.speedControllers = controllers;
	}

	@Override
	public void pidWrite(double output) {
		this.speedControllers.forEach((sc) -> sc.pidWrite(output));
	}

	@Override
	public double get() {
		double sum = this.speedControllers.stream()
				.mapToDouble((sc) -> sc.get()).sum();
		return sum / this.speedControllers.size();
	}

	@Override
	public void set(double speed, byte syncGroup) {
		this.speedControllers.stream()
		.forEach((sc) -> sc.set(speed, syncGroup));
	}

	@Override
	public void set(double speed) {
		this.speedControllers.forEach((sc) -> sc.set(speed));
	}

	@Override
	public void setInverted(boolean isInverted) {
		this.speedControllers.forEach((sc) -> sc.setInverted(isInverted));
	}

	@Override
	public boolean getInverted() {
		return this.speedControllers.stream()
				.map((sc) -> sc.getInverted())
				.reduce((a, b) -> a && b)
				.get();
	}

	@Override
	public void disable() {
		this.speedControllers.forEach((sc) -> sc.disable());
	}

	@Override
	public void stopMotor() {
		this.speedControllers.forEach((sc) -> sc.stopMotor());
	}
}
