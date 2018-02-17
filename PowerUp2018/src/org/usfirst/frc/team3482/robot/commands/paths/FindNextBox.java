package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class FindNextBox extends InstantCommand{
	boolean backwards;
	public static int boxDistance = 2000;
	public FindNextBox(boolean backwards) {
		this.backwards = backwards;
	}
	public FindNextBox() {
		this.backwards = false;
	}
	protected void initialize() {
		Robot.lidar.findNextBox(backwards, boxDistance);
	}

}
