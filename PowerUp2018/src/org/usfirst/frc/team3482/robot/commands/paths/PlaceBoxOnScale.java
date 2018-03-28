package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Outtake;
import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.commands.SetIntakeIn;
import org.usfirst.frc.team3482.robot.commands.SetIntakeOut;
import org.usfirst.frc.team3482.robot.commands.Turn;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

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
		addParallel(new Turn(turnMultiplier * 90));
		addParallel(new SetElevatorPosition(Elevator.SCALE_POSITION));
		addSequential(new WaitCommand(1));
		addParallel(new Outtake(0.35));
		addParallel(new SetIntakeOut());
		addSequential(new WaitCommand(.5));
		addSequential(new SetIntakeIn());
		addSequential(new WaitCommand(.6));
		addSequential(new SetElevatorPosition(Elevator.BUMP_POSITION));
	}

}
