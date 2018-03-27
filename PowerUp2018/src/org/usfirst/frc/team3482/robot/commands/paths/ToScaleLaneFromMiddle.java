package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToScaleLaneFromMiddle extends CommandGroup{
	boolean fromRight;
	int turnMultiplier;
	double distance;
	public ToScaleLaneFromMiddle(boolean toRight) {
		if(toRight) {
			turnMultiplier = -1;
			distance = AutoConstants.middleToRightScaleLane;
		} else {
			turnMultiplier = 1;
			distance = AutoConstants.middleToLeftScaleLane;
		}
		
		addSequential(new Turn(turnMultiplier * -90));
		addSequential(new Move(distance));
		addSequential(new Turn(turnMultiplier * 90));
	}
}
