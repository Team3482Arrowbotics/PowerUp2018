package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurnAuto extends Command {
	
	double angle;
	
	public TurnAuto(int timedOut, double angle){
		super(timedOut);
		this.angle= angle;
	}
	
	protected void initialize()
	{
		RobotMap.drive.arcadeDrive(0, angle);
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
