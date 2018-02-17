package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleStartToRightAutoLine extends CommandGroup{
	public MiddleStartToRightAutoLine() {
		addSequential(new Turn(39.6));
		addSequential(new Move(136));
		addSequential(new Turn(-39.6));
	}
}
