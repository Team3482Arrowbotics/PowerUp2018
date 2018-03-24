package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.TimedMove;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedCrossBaseline extends CommandGroup{
	private int turn = 1;
	public TimedCrossBaseline(boolean fromRight) {
		
		if(fromRight)
		{
			turn=-1;
		}
		
		addSequential(new Turn(90 * turn));
		addSequential(new TimedMove(0.6, 0.5));
		addSequential(new Turn(90 * -turn));
	}
}