package org.usfirst.frc.team3482.robot.command;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAuto extends Command {
	
	double speed;
	
	public TurnAuto(int timeout, double speed){
		super(timeout);
		this.speed= speed;
	}
	
	protected void initialize()
	{
		RobotMap.drive.arcadeDrive(0, speed);
		//speed and angle for rotation
	}

	protected void execute()
	{
		
	}
	
	protected void end()
	{
		//stop all rotation.
		RobotMap.drive.arcadeDrive(0, 0);
	}
	
	protected boolean isFinished()
	{
		return isTimedOut();
	}
}
