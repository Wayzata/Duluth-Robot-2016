package xyz.remexre.robotics.frc2016.controls;

/**
 * A class to represent the output of a POV selector.
 * @author Nathan Ringo
 */
public class POV {
	public Horizontal horizontal;
	public Vertical vertical;

	/**
	 * Creates a POV based on the given Horizontal and Vertical.
	 * @param pov The angle.
	 */
	public POV(Horizontal horizontal, Vertical vertical) {
		this.horizontal = horizontal;
		this.vertical = vertical;
	}

	/**
	 * Creates a POV based on the given angle.
	 * @param pov The angle.
	 */
	public POV(int pov) {
		this(Horizontal.get(pov), Vertical.get(pov));
	}

	/**
	 * An enumeration describing the horizontality of a given POV.
	 * @author Nathan Ringo
	 */
	public static enum Horizontal {
		NULL,
		RIGHT,
		LEFT;

		/**
		 * Returns the Horizontal cooresponding to a given POV angle.
		 * @param pov The angle.
		 * @return The Horizontal.
		 */
		public static Horizontal get(int pov) {
			if(pov > 0 && pov < 180) return RIGHT;
			else if(pov > 180 && pov < 360) return LEFT;
			else return NULL;
		}
	}

	/**
	 * An enumeration describing the verticality of a given POV.
	 * @author Nathan Ringo
	 */
	public static enum Vertical {
		NULL,
		UP,
		DOWN;

		/**
		 * Returns the Vertical cooresponding to a given POV angle.
		 * @param pov The angle.
		 * @return The Vertical.
		 */
		public static Vertical get(int pov) {
			if(pov > 270 || pov < 90) return UP;
			else if(pov > 90 && pov < 270) return DOWN;
			else return NULL;
		}
	}
}
