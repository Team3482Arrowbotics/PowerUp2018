package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousFromRight extends CommandGroup {
	boolean switchOnLeft, scaleOnLeft;

	public AutonomousFromRight(boolean switchOnLeft, boolean scaleOnLeft) {
		this.switchOnLeft = switchOnLeft;
		this.scaleOnLeft = scaleOnLeft;
		// Move Past Switch
		addSequential(new Move(1000));
		if (!scaleOnLeft) {
			// Move to Scale
			addSequential(new Move(1000));
			// Turn Towards Scale and raise elevator
			addParallel(new Turn(-45));
			addParallel(new SetElevatorPosition(Elevator.SCALE_POSITION));
			// spit the box out
			addSequential(new Spouttake(0.25));
			// turn towards switch and move elevator down
			addParallel(new Turn(-135));
			addParallel(new SetElevatorPosition(Elevator.BOTTOM_POSITION));
			// intake out
			addSequential(new SwitchIntake());
			if (!switchOnLeft) {
				/////////////// RIGHT SCALE TO RIGHT SWITCH//////////////
				addSequential(new Move(1000));
				addSequential(new Turn(90));
				addSequential(new FindBox(true));
				////////////////////////////////////////////////////////

			} else {
				/////////////// RIGHT SCALE TO LEFT SWITCH//////////////
				addSequential(new Turn(45));
				addSequential(new Move(1000));
				addSequential(new Turn(22.5));
				addSequential(new Move(2000));
				addSequential(new Turn(22.5));
				addSequential(new Turn(180));
				addSequential(new FindBox());
				////////////////////////////////////////////////////////
			}
			/////////////// TURN AND GET BOX TO SWITCH/////////////////
			addSequential(new Turn(90));
			// approach box and shwoop
			addSequential(new GetBox());
			addParallel(new Spintake(0.25));
			// intake in
			addParallel(new SwitchIntake());
			// move up and dump box
			addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
			addSequential(new Spouttake(0.25));
			// intake out
			addSequential(new SwitchIntake());
			///////////////////////////////////////////////////////////
		}
		if (scaleOnLeft) {
			/////////////////// RIGHT START TO LEFT SCALE//////////////////
			addSequential(new Turn(90));
			addSequential(new Move(3000));
			addSequential(new Turn(-90));
			addSequential(new Move(1000));
			addParallel(new Turn(-45));
			addParallel(new SetElevatorPosition(Elevator.SCALE_POSITION));
			addSequential(new Spouttake(0.25));
			addParallel(new Turn(-135));
			addParallel(new SetElevatorPosition(Elevator.BOTTOM_POSITION));
			addSequential(new SwitchIntake());
			///////////////////////////////////////////////////
			if (switchOnLeft) {
				/////////////// LEFT SCALE TO LEFT SWITCH//////////////
				addSequential(new Move(1000));
				addSequential(new Turn(-90));
				addSequential(new FindBox());
				//////////////////////////////////////////////////////
			} else {
				/////////////// LEFT SCALE TO RIGHT SWITCH//////////////
				addSequential(new Turn(-45));
				addSequential(new Move(1000));
				addSequential(new Turn(-22.5));
				addSequential(new Move(2000));
				addSequential(new Turn(-22.5));
				addSequential(new FindBox(true));
				//////////////////////////////////////////////////////
				
			}
			/////////////// TURN AND GET BOX TO SWITCH/////////////////
			addSequential(new Turn(90));
			// approach box and shwoop
			addSequential(new GetBox());
			addParallel(new Spintake(0.25));
			// intake in
			addParallel(new SwitchIntake());
			// move up and dump box
			addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
			addSequential(new Spouttake(0.25));
			// intake out
			addSequential(new SwitchIntake());
			///////////////////////////////////////////////////////////
		}
	}

}
