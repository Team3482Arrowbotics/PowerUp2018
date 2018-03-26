package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.FindFirstBox;
import org.usfirst.frc.team3482.robot.commands.paths.GetBox;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutonomous extends CommandGroup{
	
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	
	public TestAutonomous() {
		
		super();
		addSequential(new FindFirstBox(fromRight));
		addSequential(new GetBox());
	}
}
