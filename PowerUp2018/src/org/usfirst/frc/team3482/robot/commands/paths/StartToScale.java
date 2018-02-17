package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartToScale extends CommandGroup {
	public StartToScale() {
		addSequential(new Move(273));
	}
}
