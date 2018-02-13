package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveDistance extends Command {
	private double distance;
	public MoveDistance(double distance) {
		this.distance = distance;
	}
	
	protected void initialize() {
		RobotMap.driveController.enable();
		Robot.chassis.moveDistance(distance);
	}
	
	protected void end() {
		RobotMap.driveController.disable();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return RobotMap.driveController.onTarget();
	}

}
