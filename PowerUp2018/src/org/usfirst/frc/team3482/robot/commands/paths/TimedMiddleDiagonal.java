package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.TimedMove;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedMiddleDiagonal extends CommandGroup {
	private int turn = -1;

	public TimedMiddleDiagonal(boolean toRight) {

		if (toRight) {
			turn = 1;
		}

		addSequential(new Turn(45 * turn));
		addSequential(new TimedMove(0.4, 0.6));
		addSequential(new Turn(45 * -turn));
	}
}