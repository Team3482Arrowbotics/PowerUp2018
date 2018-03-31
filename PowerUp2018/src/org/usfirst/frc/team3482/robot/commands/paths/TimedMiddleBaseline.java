package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.TimedMove;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedMiddleBaseline extends CommandGroup{
	private int turn = -1;
	private double time;
	public TimedMiddleBaseline(boolean toRight) {
		
		if(toRight)
		{
			turn=1;
			time = AutoConstants.timedCrossBaselineRight;
		}
		else
		{
			time = AutoConstants.timedCrossBaselineRight;
		}
		addSequential(new Turn(90 * turn));
		addSequential(new TimedMove(time, 0.5));
		addSequential(new Turn(90 * -turn));
	}
}