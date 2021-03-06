package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Spouttake extends Command{
	public Spouttake(double timeout) {
		super(timeout);
	}
	
	public Spouttake() {
		super();
	}
	
	protected void initialize(){
		RobotMap.intakeMotorLeft.set(1);
		RobotMap.intakeMotorRight.set(-1);
		System.out.println("Motors set!");
		Robot.isSpoutake = true;
	}
	
	protected void execute() {
		//Robot.ledStrip.flash("purple", 0.2);
	}
	
	protected void end(){
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
