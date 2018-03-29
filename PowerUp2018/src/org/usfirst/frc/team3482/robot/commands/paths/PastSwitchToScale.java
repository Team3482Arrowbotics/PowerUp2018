package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PastSwitchToScale extends CommandGroup {
	public PastSwitchToScale() {
		addSequential(new Move(AutoConstants.awayFromWall));
	}
}
