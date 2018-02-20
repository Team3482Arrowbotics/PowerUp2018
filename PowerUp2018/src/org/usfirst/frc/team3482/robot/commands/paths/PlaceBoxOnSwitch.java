package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.commands.Spouttake;
import org.usfirst.frc.team3482.robot.commands.Turn;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceBoxOnSwitch extends CommandGroup{
	public PlaceBoxOnSwitch() {
		super();
		addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
		addSequential(new Spouttake(1));
		addSequential(new SetElevatorPosition(Elevator.BOTTOM_POSITION));
	}

}
