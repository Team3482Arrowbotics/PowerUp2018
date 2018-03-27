package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleToSameSideBox extends CommandGroup{
	boolean fromRight;
	int turnMultiplier;
	public ScaleToSameSideBox(boolean fromRight) {
		//Precondition: Assume position 90 (if Left) or -90 (if Right)
		this.fromRight = fromRight;
		if(fromRight) {
			turnMultiplier = -1;
		} else {
			turnMultiplier = 1;
		}
		addSequential(new Turn(turnMultiplier * 90));
		addSequential(new Move(AutoConstants.scaleToSameSideBoxDistance));
	}
}
