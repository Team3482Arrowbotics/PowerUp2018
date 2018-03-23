package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class TimedTurn extends TimedCommand{
	double speed;
	public TimedTurn(double timeout, double speed) {
		super(timeout);
		this.speed = speed;
	}
	
	protected void execute() {
		RobotMap.drive.arcadeDrive(0, speed);
	}
	protected void end() {
		RobotMap.drive.stopMotor();;
	}

}
