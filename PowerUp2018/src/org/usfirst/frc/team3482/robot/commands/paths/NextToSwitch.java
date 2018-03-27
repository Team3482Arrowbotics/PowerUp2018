package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NextToSwitch extends CommandGroup{
	
	public NextToSwitch() {
		super();
		addSequential(new Move(AutoConstants.nextToSwitchDistance));
	}
}