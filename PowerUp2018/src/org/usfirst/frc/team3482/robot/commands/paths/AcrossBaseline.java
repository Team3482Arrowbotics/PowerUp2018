package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AcrossBaseline extends CommandGroup{
	boolean opposite;
	int turnMultiplier;
	public AcrossBaseline(boolean fromRight) {
		this.opposite = fromRight;
		if(opposite) {
			turnMultiplier = -1;
		} else {
			turnMultiplier = 1;
		}
		addSequential(new Turn(turnMultiplier * 90));
		addSequential(new Move(200));
		addSequential(new Turn(turnMultiplier * -90));
	}
}
