package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.TimedMove;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedNextToSwitch extends CommandGroup{
	
	public TimedNextToSwitch() {
		super();
		addSequential(new TimedMove(2.8, 0.6));
	}
}