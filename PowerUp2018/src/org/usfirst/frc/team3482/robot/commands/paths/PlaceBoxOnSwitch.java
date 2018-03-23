package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Outtake;
import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.commands.SetIntakeIn;
import org.usfirst.frc.team3482.robot.commands.SetIntakeOut;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceBoxOnSwitch extends CommandGroup{
	public PlaceBoxOnSwitch() {
		super();
		addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
		//addSequential() delay?
		addSequential(new Outtake(0.25));
		addParallel(new SetIntakeOut());
		// delay ?
		addSequential(new SetIntakeIn());
		addSequential(new SetElevatorPosition(Elevator.BOTTOM_POSITION));
	}

}
