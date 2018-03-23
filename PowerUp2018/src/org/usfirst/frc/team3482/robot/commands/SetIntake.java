package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetIntake extends InstantCommand{
	boolean in;
	public SetIntake(boolean b) {
		in = b;
	}
	protected void initialize() {
		Robot.intake.setPistons(in);
	}
}
