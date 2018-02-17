package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AcrossBaselineFromMiddle extends CommandGroup{
	boolean fromRight;
	int turnMultiplier;
	double distance;
	public static final double LEFT_DISTANCE = 12, RIGHT_DISTANCE = 12;
	public AcrossBaselineFromMiddle(boolean towardsRight) {
		this.fromRight = towardsRight;
		if(towardsRight) {
			turnMultiplier = -1;
			distance = RIGHT_DISTANCE;
		} else {
			turnMultiplier = 1;
			distance = LEFT_DISTANCE;
		}
		addSequential(new Turn(turnMultiplier * -90));
		addSequential(new Move(distance));
		addSequential(new Turn(turnMultiplier * 90));
	}
}
