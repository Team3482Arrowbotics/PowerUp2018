package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToOppositeSwitch extends CommandGroup {
	public ToOppositeSwitch(boolean fromRight) {
		addSequential(new Move(40));
		addSequential(new AcrossBaseline(fromRight));
		addSequential(new Move(60));
	}
}
