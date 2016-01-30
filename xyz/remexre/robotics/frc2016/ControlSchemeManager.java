package xyz.remexre.robotics.frc2016;

import java.util.Set;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The ControlSchemeManager class handles selection of control schemes.
 * @author nathan
 *
 */
public class ControlSchemeManager implements ControlScheme {
	private SendableChooser chooser;
	
	public ControlSchemeManager(ControlScheme ... controlSchemes) {
		this.chooser = new SendableChooser();
		for(ControlScheme scheme : controlSchemes)
			this.chooser.addObject(scheme.getName(), scheme);
	}
	
	@Override
	public String getName() { return "Control Scheme Manager"; }
	
	@Override
	public Set<Control> getControls() {
		Object schemeObject = this.chooser.getSelected();
		if(!(schemeObject instanceof ControlScheme))
			return null;
		ControlScheme scheme = (ControlScheme) schemeObject;
		return scheme.getControls();
	}
}
