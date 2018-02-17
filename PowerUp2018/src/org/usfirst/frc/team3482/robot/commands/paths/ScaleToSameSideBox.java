package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleToSameSideBox extends CommandGroup{
	boolean opposite;
	int turnMultiplier;
	public ScaleToSameSideBox(boolean fromRight) {
		//Precondition: Assume position 45 (if Left) or -45 (if Right)
		this.opposite = fromRight;
		if(opposite) {
			turnMultiplier = -1;
		} else {
			turnMultiplier = 1;
		}
		addSequential(new Turn(turnMultiplier * 135));
		addSequential(new Move(63));
	}
}
