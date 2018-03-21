package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Intake extends Command{
	boolean finished = false;
	public Intake(double timeout) {
		super(timeout);
	}
	public Intake() {
		super();
	}
	protected void initialize(){
		Robot.isSpintake = true;
		RobotMap.intakeMotorLeft.set(-.75);
		RobotMap.intakeMotorRight.set(.75);
		System.out.println("Motors set!");
	}
	
	protected void execute() {
		//Robot.ledStrip.flash("white", 0.1);
	}
	
	protected void end(){
		RobotMap.intakeMotorLeft.set(0);
		RobotMap.intakeMotorRight.set(0);
		Robot.ledStrip.turnOff();
		Robot.isSpintake = false;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished || isTimedOut();
	}

}
