package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.commands.TimedMove;
import org.usfirst.frc.team3482.robot.commands.Turn;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedToGoal extends CommandGroup{
	private int turn = 1;
	public TimedToGoal(boolean fromRight) {
		super();
		if(fromRight)
		{
			turn=-1;
		}
		addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
		addSequential(new Turn(90 * turn));
		addSequential(new TimedMove(1, .4));
	}
}