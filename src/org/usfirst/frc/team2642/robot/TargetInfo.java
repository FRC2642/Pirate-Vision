package org.usfirst.frc.team2642.robot;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class TargetInfo {
	private static int numTargets = 0;
	private static Rect rect1;
	private static final Object imgLock = new Object();
	private static double centerX = Double.MAX_VALUE;
	private static double centerY = Double.MAX_VALUE;
	private static double cnterTargetArea = Double.MAX_VALUE;

	public static void setFilterContours(ArrayList<MatOfPoint> matOfPoints) {
		synchronized (imgLock) {
			if (matOfPoints.size() >= 1 && RobotMap.visionEnabled) {
				numTargets = 1;
				rect1 = (Rect) Imgproc.boundingRect(matOfPoints.get(0));
				centerX = 2 * rect1.x + rect1.width - (RobotMap.IMG_WIDTH / 2);
				centerY = 2 * rect1.y + rect1.height - (RobotMap.IMG_HEIGHT / 2);
				cnterTargetArea = rect1.area();
			} else {
				numTargets = 0;
				centerX = Double.MAX_VALUE;
				centerY = Double.MAX_VALUE;
				cnterTargetArea = Double.MAX_VALUE;
			}
		}
	}

	public static double getCenterX() {
		return centerX;
	}

	public static double getCenterY() {
		return centerY;
	}

	public static double getCenterTargetArea() {
		return cnterTargetArea;
	}

	public static int getNumTargets() {
		return numTargets;
	}
}