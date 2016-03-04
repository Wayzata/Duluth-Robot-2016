package xyz.remexre.robotics.frc2016.util;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BetterSendableChooser<T> {
	private String name;
	private SendableChooser sendableChooser;
	
	@SafeVarargs
	public BetterSendableChooser(String name, T ... options) {
		this.name = name;
		this.sendableChooser = new SendableChooser();
		
		if(options.length >= 1) {
			this.sendableChooser.addDefault(options[0].toString(), options[0]);
		}
		for(int i = 1; i < options.length; i++) {
			this.sendableChooser.addObject(options[i].toString(), options[i]);
		}
		
		this.send();
	}
	
	@SuppressWarnings("unchecked")
	public T get() {
		return (T) this.sendableChooser.getSelected();
	}
	
	public void send() {
		SmartDashboard.putData(this.name, this.sendableChooser);
	}
}
