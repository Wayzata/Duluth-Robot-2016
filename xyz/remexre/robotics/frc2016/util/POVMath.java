package xyz.remexre.robotics.frc2016.util;

public class POVMath {
	public static final boolean isUp(int pov) {
		return (pov >= 0 && pov < 90) || (pov >= 270 && pov < 360);
	}
	public static final boolean isDown(int pov) {
		return pov > 90 && pov < 270;
	}
	public static final boolean isLeft(int pov) {
		return pov > 180 && pov < 360;
	}
	public static final boolean isRight(int pov) {
		return pov > 0 && pov < 180;
	}
}
