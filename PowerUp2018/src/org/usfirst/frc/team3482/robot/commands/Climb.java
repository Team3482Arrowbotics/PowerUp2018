package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command{
	protected void initialize() {
		RobotMap.climber.set(-1);
	}
	protected void end() {
		RobotMap.climber.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
