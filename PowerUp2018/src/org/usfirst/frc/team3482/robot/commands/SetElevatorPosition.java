package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetElevatorPosition extends InstantCommand {
	double pos;
	public SetElevatorPosition(double pos) {
		this.pos = pos;
	}
	protected void initialize() {
		Robot.elevator.autonomousSet(pos);
	}
}
