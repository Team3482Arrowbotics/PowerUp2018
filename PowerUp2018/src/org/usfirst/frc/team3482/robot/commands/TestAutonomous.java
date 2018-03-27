package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.FindBox;
import org.usfirst.frc.team3482.robot.commands.paths.GoToBox;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutonomous extends CommandGroup{
	
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	
	public TestAutonomous() {
		
		super();
		addSequential(new FindBox(fromRight));
		addSequential(new GoToBox());
	}
}
