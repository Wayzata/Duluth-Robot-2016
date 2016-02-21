package xyz.remexre.robotics.frc2016.util;

public class ArmMath {
	public static final double elbowMax = 0;
	public static final double elbowMin = -370;
	
	public static boolean breaksSafety(double shoulderAngle, double elbowAngle) {
		if(elbowAngle < elbowMin || elbowAngle > elbowMax)
			return true;
		return false;
	}
}
