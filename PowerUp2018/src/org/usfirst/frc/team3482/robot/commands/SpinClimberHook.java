package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SpinClimberHook extends Command{
	double speed;
	public SpinClimberHook(double speed) {
		super();
		this.speed = speed;
	}
	protected void initialize() {
//		RobotMap.climberHook.setSpeed(speed);
		RobotMap.climberHook.set(speed);
	}
	protected void end() {
//		RobotMap.climberHook.setSpeed(0);
		RobotMap.climberHook.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
