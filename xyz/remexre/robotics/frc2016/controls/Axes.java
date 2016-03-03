package xyz.remexre.robotics.frc2016.controls;

public class Axes {
	public double x, y;

	public Axes(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Axes times(double z) {
		return new Axes(this.x * z, this.y * z);
	}

	public boolean isZero() { return this.isZero(0.1); }
	public boolean isZero(double cutoff) {
		return Math.abs(this.x) < cutoff && Math.abs(this.y) < cutoff;
	}
	
	public double cartesianDistance() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}
}
