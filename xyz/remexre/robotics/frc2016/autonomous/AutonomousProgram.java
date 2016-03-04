package xyz.remexre.robotics.frc2016.autonomous;

import xyz.remexre.robotics.frc2016.controls.Controls;

public abstract class AutonomousProgram {
	public abstract Controls periodic(long timeInAutonomous);
	public abstract String toString();
}
