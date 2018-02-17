package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AcrossSwitchLane extends CommandGroup{
	boolean opposite;
	int turnMultiplier;
	public AcrossSwitchLane(boolean opposite) {
		//Precondition: 180 degree position
		super();
		this.opposite = opposite;
		if(opposite) {
			turnMultiplier = -1;
		} else {
			turnMultiplier = 1;
		}
		addSequential(new Turn(turnMultiplier * -90));
		addSequential(new Move(200));
		
	}
}
