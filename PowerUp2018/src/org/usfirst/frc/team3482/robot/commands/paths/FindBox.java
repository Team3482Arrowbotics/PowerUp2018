package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FindBox extends Command{
	boolean backwards;
	public static int boxDistance = 2000;
	public FindBox(boolean backwards) {
		this.backwards = backwards;
	}
	public FindBox() {
		this.backwards = false;
	}
	protected void initialize() {
		Robot.sideLidar.findFirstBox(backwards, boxDistance);
	}
	protected void execute() {
		
	}
	protected void end() {
		
	}
	@Override
	protected boolean isFinished() {
		return true;
	}

}
