package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.NextToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BlitzAutonomous extends CommandGroup{
	
	public BlitzAutonomous() {
		
		super();
		System.out.println("Blitzkreig!");
		addSequential(new WaitCommand(7));
		addSequential(new NextToSwitch());
	}
}
