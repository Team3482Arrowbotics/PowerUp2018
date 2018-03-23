package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedAutonomous extends CommandGroup{
	public TimedAutonomous(boolean switchOnLeft) {
		addSequential(new TimedMove(0.6, 0.5));
		if(switchOnLeft) {
			addSequential(new TimedTurn(0.5, -0.5));
			addSequential(new TimedMove(0.6, 0.5));
			addSequential(new TimedTurn(0.5, 0.5));
		}
		addSequential(new TimedMove(1.0, 0.5));
		addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
		addParallel(new Outtake(1.0));
		addParallel(new SetIntake(true));
	}
}
