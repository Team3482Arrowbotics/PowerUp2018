package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousFromMiddle extends CommandGroup {
	boolean switchOnLeft, scaleOnLeft;

	public AutonomousFromMiddle(boolean switchOnLeft) {
		this.switchOnLeft = switchOnLeft;
		this.scaleOnLeft = false;
		// Move Past Switch
		addSequential(new Move(1000));
		if (switchOnLeft) {
			// Move to switch
			addSequential(new Turn(-90));
			addSequential(new Move(900));
			// Turn Towards Switch
			addSequential(new Turn(90));
			addSequential(new Move(200));
			// spit the box out
			addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
			addSequential(new Spouttake(0.25));
			// turn towards switch and move elevator down
			addParallel(new Turn(-90));
			addParallel(new SetElevatorPosition(Elevator.BOTTOM_POSITION));
			///////////////TURN AND GET BOX/////////////////
			addSequential(new Turn(90));
			// approach box and shwoop
		}else
		{
			//assume no-lane/near-lane approach
		}

	}

}
