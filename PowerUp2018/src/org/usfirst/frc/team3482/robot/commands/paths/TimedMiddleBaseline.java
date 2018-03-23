package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.TimedMove;
import org.usfirst.frc.team3482.robot.commands.TimedTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedMiddleBaseline extends CommandGroup{
	private int turn = 1;
	public TimedMiddleBaseline(boolean toRight) {
		
		if(toRight)
		{
			turn=-1;
		}
		addSequential(new TimedTurn(0.5, 0.5 * turn));
		addSequential(new TimedMove(0.3, 0.5));
		addSequential(new TimedTurn(0.5, 0.5 * -turn));
	}
}