package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlitzAutonomous extends CommandGroup{
	
	public BlitzAutonomous() {
		
		super();
		
		addSequential(new TimedMove(2, 0.5));
	}
}
