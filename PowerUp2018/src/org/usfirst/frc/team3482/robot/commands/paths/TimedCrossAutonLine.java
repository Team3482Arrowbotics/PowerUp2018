package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.TimedMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedCrossAutonLine extends CommandGroup{
	
	public TimedCrossAutonLine() {
		super();
		addSequential(new TimedMove(4, 0.6));
	}
}