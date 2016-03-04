package xyz.remexre.robotics.frc2016.util;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class BetterSendableChooser<T> {
	private SendableChooser sendableChooser;
	
	public BetterSendableChooser() {
		this.sendableChooser = new SendableChooser();
	}
	public BetterSendableChooser(T ... options) {
		this();
		
		if(options.length >= 1) {
			this.sendableChooser.addDefault(options[0].toString(), options[0]);
		}
		for(int i = 1; i < options.length; i++) {
			this.sendableChooser.addObject(options[i].toString(), options[i]);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T get() {
		return (T) this.sendableChooser.getSelected();
	}
}
