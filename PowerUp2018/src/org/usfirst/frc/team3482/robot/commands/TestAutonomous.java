package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutonomous extends CommandGroup{
	
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	
	public TestAutonomous() {
		
		super();
		RobotMap.drive.disable();
		System.out.println("Test Auton");
		addSequential(new TimedMove(5, .25));
		RobotMap.drive.enable();
	}
}
