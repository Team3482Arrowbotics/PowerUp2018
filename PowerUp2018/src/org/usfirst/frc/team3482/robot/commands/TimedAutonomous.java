package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.StartPosition;
import org.usfirst.frc.team3482.robot.commands.paths.TimedCrossBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.TimedDiagonal;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleDiagonal;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedAutonomous extends CommandGroup{
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	public TimedAutonomous(boolean crossBaseline, boolean switchOnLeft, StartPosition sPos) {
		
		super();
		
		addSequential(new TimedMove(0.6, 0.5));

		switch(sPos) {
		case LEFT:
			if(!switchOnLeft) {
				if(crossBaseline) {
					addSequential(new TimedCrossBaseline(fromLeft));
				}
				else {
					addSequential(new TimedDiagonal(fromLeft));
				}
			}
			break;

		case MIDDLE:
			if(switchOnLeft) {
				if(crossBaseline) {
					addSequential(new TimedMiddleBaseline(toLeft));
				}
				else {
					addSequential(new TimedMiddleDiagonal(toLeft));
				}
			}
			else {
				if(crossBaseline) {
					addSequential(new TimedMiddleBaseline(toRight));
				}
				else {
					addSequential(new TimedMiddleDiagonal(toRight));
				}
			}
			break;

		case RIGHT:
			if(switchOnLeft) {
				if(crossBaseline) {
					addSequential(new TimedCrossBaseline(fromRight));
				}
				else {
					addSequential(new TimedDiagonal(fromRight));
				}
			}
			break;
		}
		
		addSequential(new TimedMove(1.0, 0.5));
		addSequential(new PlaceBoxOnSwitch());
	}
}
