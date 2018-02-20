package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartToScale extends CommandGroup {
	boolean fromRight;
	double turnMultiplier;
	public StartToScale(boolean fromRight) {
		this.fromRight = fromRight;
		if(fromRight){
			turnMultiplier = -1;
		} else{
			turnMultiplier = 1;
		}
		addSequential(new Move(290));
		addSequential(new Turn(turnMultiplier * 90));
	}
}
