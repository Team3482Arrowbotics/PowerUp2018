package org.usfirst.frc.team3482.robot.command;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardAuto extends Command{
	double speed;
	public DriveForwardAuto(double timeout, double speed){
		super(timeout);
		this.speed = speed;
	}
	protected void initialize(){
		//negative because drive motors reversed
		System.out.println("Started going forward");
		RobotMap.drive.arcadeDrive(0, -speed);
	}
	protected void execute(){
		
	}
	protected void end(){
		//stop
		System.out.println("Stopped going forward");
		RobotMap.drive.arcadeDrive(0, 0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}
	
}
