package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class EnableDrive extends Command{
	public boolean enabled;
	
	public EnableDrive(boolean enable)
	{
		enabled = enable;
	}
	
	protected void initialize() {
		RobotMap.drive.enable(enabled);
	}
	
	protected void end() {
	
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub 
		return false;
	}

}
