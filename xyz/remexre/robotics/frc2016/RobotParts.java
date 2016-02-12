package xyz.remexre.robotics.frc2016;

/**
 * RobotParts is a class of constants for part IDs.
 * @author Nathan Ringo
 */
public class RobotParts {
	/**
	 * The ID numbers of the USB joysticks.
	 * @author Nathan Ringo
	 */
	public static final class JOYSTICKS {
		public static final int DRIVE = 0;
		public static final int ARM = 1;
	}

	/**
	 * The ID numbers of the CANTalons.
	 * @author Nathan Ringo
	 */
	public static final class MOTORS {
		public static final int FRONT_LEFT = 17;
		public static final int FRONT_RIGHT = 18;
		public static final int BACK_LEFT = 20;
		public static final int BACK_RIGHT = 19;

		public static final int SHOULDER = 24;
		public static final int ELBOW = 25;
		public static final int FOREARM = 26;

		public static final int BELT = 21;
		public static final int SHOOTER_ARM = 23;
		public static final int SHOOTER = 22;

		public static final int WINCH = 27;
	}

	/**
	 * The ID numbers of switches.
	 * @author Nathan Ringo
	 */
	public static final class SWITCHES {
		public static final int RETRACT = 0;
		public static final int EXTEND = 1;
	}
}
