package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AwayFromWall extends CommandGroup {
	public AwayFromWall() {
		addSequential(new Move(AutoConstants.awayFromWall));
	}
}
