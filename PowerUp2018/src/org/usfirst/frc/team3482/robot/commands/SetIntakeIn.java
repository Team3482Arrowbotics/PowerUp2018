package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetIntakeIn extends InstantCommand{

	public SetIntakeIn() {
		
	}
	protected void initialize() {
		Robot.intake.setPistons(false);
	}
}
