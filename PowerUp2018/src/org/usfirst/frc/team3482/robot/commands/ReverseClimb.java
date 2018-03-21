package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReverseClimb extends Command{
	protected void initialize() {
		Robot.climber.reverseClimb();
	}
	protected void end() {
		Robot.climber.stop();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
