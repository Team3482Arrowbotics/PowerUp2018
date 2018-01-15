package org.usfirst.frc.team3482.robot.command;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardAuto extends Command{
	double speed;
	public DriveForwardAuto(int timeout, double speed){
		super(timeout);
		this.speed = speed;
	}
	protected void initialize(){
		RobotMap.drive.arcadeDrive(speed, 0);
	}
	protected void execute(){
		
	}
	protected void end(){
		//stop
		RobotMap.drive.arcadeDrive(0, 0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}
	
}
