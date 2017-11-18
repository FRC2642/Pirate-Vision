
package org.usfirst.frc.team2642.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static UsbCamera camera;

	//Changes camera settings for vision tracking
	public static void enableVisionTracking(boolean enabled) {
		if (enabled) { //Vision Mode
			camera.setBrightness(20);
			camera.setExposureManual(5);
			RobotMap.visionEnabled = true;

		} else { //Driving Mode
			camera.setBrightness(30);
			camera.setExposureManual(35);
			RobotMap.visionEnabled = false;
		}
	}

	@Override
	public void robotInit() {
		oi = new OI();
		SmartDashboard.putString("Robot Code Version", RobotMap.robotVersion);

		// Set up camera
		camera = CameraServer.getInstance().startAutomaticCapture("Camera", RobotMap.CAMERA_USB);
		camera.setResolution(RobotMap.IMG_WIDTH, RobotMap.IMG_HEIGHT);
		camera.setFPS(RobotMap.CAMERA_FPS);

		enableVisionTracking(RobotMap.visionEnabled);

		VisionThread gearVisionThread = new VisionThread(camera, new Pipeline(), gearpipeline -> {
			TargetInfo.setFilterContours(gearpipeline.filterContoursOutput());
		});
		gearVisionThread.start();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putNumber("Target X", TargetInfo.getCenterX());
		SmartDashboard.putNumber("Target Y", TargetInfo.getCenterY());
		SmartDashboard.putNumber("Target Area", TargetInfo.getCenterTargetArea());
		SmartDashboard.putNumber("Number of Targets", TargetInfo.getNumTargets());
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
