package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LastPushToSwitch extends CommandGroup {
	public LastPushToSwitch(){
		addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
		addSequential(new Move(AutoConstants.lastPush));
	}
}
