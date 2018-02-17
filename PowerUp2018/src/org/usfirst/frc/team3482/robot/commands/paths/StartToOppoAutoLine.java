package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartToOppoAutoLine extends CommandGroup {
	boolean opposite;
	int turnMultiplier;
	public StartToOppoAutoLine(boolean opposite) {
		super();
		this.opposite = opposite;
		if(opposite) {
			turnMultiplier = -1;
		} else {
			turnMultiplier = 1;
		}
		addSequential(new Turn(turnMultiplier * 62));
		addSequential(new Move(226));
		addSequential(new Turn(turnMultiplier * -62));
	}
}
