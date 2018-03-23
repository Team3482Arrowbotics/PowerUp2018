package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command{
	double distance;
	public Move(double distance) {
		this.distance = distance;
	}
	protected void initialize() { 
		RobotMap.drive.setTurning(false);
		RobotMap.encoders.reset();
		RobotMap.driveController.enable();
		RobotMap.driveController.setSetpoint(distance);
		RobotMap.rotationController.enable();
		RobotMap.rotationController.setSetpoint(RobotMap.navx.getYaw());
	}
	protected void end() {
		RobotMap.driveController.disable();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		System.out.println( "PID Error: " + RobotMap.driveController.getError());
		return RobotMap.driveController.onTarget();
	}

}
