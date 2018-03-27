package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SidePushToSwitch extends CommandGroup{
	private int turn = 1;
	public SidePushToSwitch(boolean fromRight) {
		super();
		if(fromRight)
		{
			turn=-1;
		}
		addSequential(new Turn(90 * turn));
		addSequential(new LastPushToSwitch());
	}
}