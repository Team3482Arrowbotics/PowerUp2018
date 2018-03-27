package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossFieldToScaleLane extends CommandGroup{
	boolean fromRight;
	int turnMultiplier;
	public CrossFieldToScaleLane(boolean fromRight) {
		this.fromRight = fromRight;
		if(fromRight) {
			turnMultiplier = -1;
		} else {
			turnMultiplier = 1;
		}
		addSequential(new Turn(turnMultiplier * 90));
		addSequential(new Move(AutoConstants.crossFieldToScale));
		addSequential(new Turn(turnMultiplier * -90));
	}
}
