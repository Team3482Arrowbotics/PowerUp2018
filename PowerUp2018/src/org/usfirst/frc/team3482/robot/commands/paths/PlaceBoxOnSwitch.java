package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Outtake;
import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.commands.SetIntakeIn;
import org.usfirst.frc.team3482.robot.commands.SetIntakeOut;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PlaceBoxOnSwitch extends CommandGroup{
	public PlaceBoxOnSwitch() {
		super();
		addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
		addSequential(new WaitCommand(1));
		addParallel(new Outtake(0.35));
		addParallel(new SetIntakeOut());
		addSequential(new WaitCommand(1));
		addSequential(new SetIntakeIn());
		addSequential(new WaitCommand(0.6));
		addSequential(new SetElevatorPosition(Elevator.BOTTOM_POSITION));
	}

}
