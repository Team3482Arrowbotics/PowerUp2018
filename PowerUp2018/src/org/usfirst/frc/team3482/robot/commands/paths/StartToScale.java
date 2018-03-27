package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartToScale extends CommandGroup {
	public StartToScale() {
		addSequential(new SetElevatorPosition(Elevator.BUMP_POSITION));
		addSequential(new Move(AutoConstants.toScaleDistance));
	}
}
