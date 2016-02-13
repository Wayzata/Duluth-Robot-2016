package xyz.remexre.robotics.frc2016.modules;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;
import xyz.remexre.robotics.frc2016.controls.Controls;

/**
 * Manages the camera, for display to the driver.
 * @author Nathan Ringo
 */
public class Vision implements Module {
	private Image frame;
	private USBCamera camera;

	/**
	 * Constructs a vision instance based on a camera's name.
	 * @param cameraName The name of the camera.
	 */
	public Vision(String cameraName) {
		this(new USBCamera(cameraName));
	}

	/**
	 * Constructs a vision instance based on an existing {@link USBCamera}.
	 * @param camera The camera to use.
	 */
	public Vision(USBCamera camera) {
		this.frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		this.camera = camera;
		this.camera.openCamera();
		this.camera.startCapture();
		this.camera.setWhiteBalanceAuto();
		this.camera.setExposureAuto();
	}

	/**
	 * Retrieves a single frame from the camera and sends it to the
	 * SmartDashboard.
	 */
	@Override
	public void control(Controls controls) {
		camera.getImage(this.frame);
		CameraServer.getInstance().setImage(this.frame);
	}
}
