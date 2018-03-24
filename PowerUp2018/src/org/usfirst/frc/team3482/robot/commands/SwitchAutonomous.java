package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.StartPosition;
import org.usfirst.frc.team3482.robot.commands.paths.TimedCrossBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.TimedDiagonal;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleDiagonal;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchAutonomous extends CommandGroup{
		
	public SwitchAutonomous() {
		
		super();
		addSequential(new TimedMove(1.5, 0.5));
		addSequential(new PlaceBoxOnSwitch());
	}
}
