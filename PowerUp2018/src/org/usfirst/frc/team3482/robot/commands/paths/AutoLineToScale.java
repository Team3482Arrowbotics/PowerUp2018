package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLineToScale extends CommandGroup {
	public AutoLineToScale() {
		addSequential(new Move(168));
	}
}
