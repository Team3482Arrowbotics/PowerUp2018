package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Outtake extends Command {
	public static double outtakeSpeed;

	public Outtake(double timeout) {
		super(timeout);
		outtakeSpeed = .8;
	}

	public Outtake() {
		super();
	}

	protected void initialize() {
		RobotMap.intakeMotorLeft.set(outtakeSpeed);
		RobotMap.intakeMotorRight.set(-outtakeSpeed);
		Robot.isSpoutake = true;
	}

	protected void execute() {

	}

	protected void end() {
		RobotMap.intakeMotorLeft.set(0);
		RobotMap.intakeMotorRight.set(0);
		Robot.isSpoutake = false;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false || isTimedOut();
	}

}
