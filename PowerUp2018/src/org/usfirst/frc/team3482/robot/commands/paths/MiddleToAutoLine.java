package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleToAutoLine extends CommandGroup{
	public MiddleToAutoLine(boolean towardRight) {
		if(towardRight) {
			addSequential(new Turn(39.6));
			addSequential(new Move(136));
			addSequential(new Turn(-39.6));
		}
		
		else{
			addSequential(new Turn(-47.1));
			addSequential(new Move(154));
			addSequential(new Turn(47.1));
		}	
	}
}
