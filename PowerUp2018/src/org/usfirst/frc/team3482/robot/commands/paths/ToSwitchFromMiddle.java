package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ToSwitchFromMiddle extends CommandGroup{
	boolean fromRight;
	int turnMultiplier;
	double distance;
	public ToSwitchFromMiddle(boolean toRight) {
		if(toRight) {
			turnMultiplier = -1;
			distance = AutoConstants.middleToRightSwitch;
		} else {
			turnMultiplier = 1;
			distance = AutoConstants.middleToLeftSwitch;
		}
		
		addSequential(new Turn(turnMultiplier * -90));
		addSequential(new Move(distance));
		addSequential(new Turn(turnMultiplier * 90));
	}
}
