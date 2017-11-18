package org.usfirst.frc.team2642.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2642.robot.Robot;
import org.usfirst.frc.team2642.robot.RobotMap;

public class ToggleVision extends Command {

	protected void execute() {
		Robot.enableVisionTracking(!RobotMap.visionEnabled);
	}

	protected boolean isFinished() {
		return true;
	}
}
