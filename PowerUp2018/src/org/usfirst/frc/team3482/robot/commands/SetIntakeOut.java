package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class SetIntakeOut extends InstantCommand{

	public SetIntakeOut() {
		
	}
	
	protected void initialize() {
		Robot.intake.setPistons(true);
	}
}
