package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
// 230 encoder ticks per foot
// 19 ticks/inch
// 0.05 in per tick?
public class Move extends Command{
	double distance;
	public Move(double distance) {
		this.distance = distance;
	}
	protected void initialize() { 
		RobotMap.drive.setTurning(false);
		System.out.println("Encoder Value: " + RobotMap.encoderRight.getDistance());
		RobotMap.encoderRight.reset();
		System.out.println("Encoder Value Post Reset: " + RobotMap.encoderRight.getDistance());
		System.out.println("Target Position: " + distance);
		RobotMap.driveController.enable();
		RobotMap.driveController.setSetpoint(distance);
		//RobotMap.rotationController.enable();
		//RobotMap.rotationController.setSetpoint(RobotMap.navx.getYaw());
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
