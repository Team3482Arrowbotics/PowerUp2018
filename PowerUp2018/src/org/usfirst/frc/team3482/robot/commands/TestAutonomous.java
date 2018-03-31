package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.commands.paths.FindBox;
import org.usfirst.frc.team3482.robot.commands.paths.GoToBox;
import org.usfirst.frc.team3482.robot.commands.paths.GrabBox;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutonomous extends CommandGroup{
	
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	
	public TestAutonomous() {
		
		super();
//		addSequential(new EnableDrive(false));
//		System.out.println("Test Auton");
//		addSequential(new FindBox(fromRight));
//		addSequential(new GoToBox());
//		addSequential(new GrabBox());
//		System.out.println("Starting Move");
		addSequential(new Move(2));
//		System.out.println("Done Moving");
		addSequential(new EnableDrive(true));
	}
}
