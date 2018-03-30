package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.TimedMove;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedMiddleBaseline extends CommandGroup{
	private int turn = -1;
	public TimedMiddleBaseline(boolean toRight) {
		
		if(toRight)
		{
			turn=1;
		}
		addSequential(new Turn(90 * turn));
		addSequential(new TimedMove(AutoConstants.timedCrossBaseline, 0.5));
		addSequential(new Turn(90 * -turn));
	}
}