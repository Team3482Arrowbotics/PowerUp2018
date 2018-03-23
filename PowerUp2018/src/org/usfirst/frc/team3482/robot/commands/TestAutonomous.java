package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.StartPosition;
import org.usfirst.frc.team3482.robot.commands.paths.TimedCrossBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.TimedDiagonal;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleDiagonal;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutonomous extends CommandGroup{
	
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	
	public TestAutonomous() {
		
		super();
		
		addSequential(new PlaceBoxOnSwitch());
	}
}
