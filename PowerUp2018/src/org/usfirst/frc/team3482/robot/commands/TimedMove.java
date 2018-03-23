package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class TimedMove extends TimedCommand{
	double speed;
	public TimedMove(double timeout, double speed) {
		super(timeout);
		this.speed = speed;
	}
	
	protected void execute() {
		RobotMap.drive.arcadeDrive(speed, 0);
	}
	protected void end() {
		RobotMap.drive.stopMotor();
	}

}
