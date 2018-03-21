 package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.commands.Outtake;
import org.usfirst.frc.team3482.robot.commands.Turn;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceBoxOnScale extends CommandGroup{
	boolean fromRight;
	int turnMultiplier;
	public PlaceBoxOnScale(boolean fromRight) {
		super();
		this.fromRight = fromRight;
		if(fromRight) {
			turnMultiplier = -1;
		} else {
			turnMultiplier = 1;
		}
		addParallel(new Turn(turnMultiplier * 45));
		addParallel(new SetElevatorPosition(Elevator.SCALE_POSITION));
		addSequential(new Outtake(0.25));
		addSequential(new SetElevatorPosition(Elevator.BOTTOM_POSITION));
	}

}
