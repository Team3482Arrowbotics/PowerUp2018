package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

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
		RobotMap.intakeMotorLeft.set(-.8);
		RobotMap.intakeMotorRight.set(.8);
		System.out.println("Motors set!");
	}
	
	protected void execute() {
		//Robot.ledStrip.flash("white", 0.1);
		System.out.println("Intake Speed Left: " + RobotMap.intakeMotorLeft.get());
		System.out.println("Intake Speed Right: " + RobotMap.intakeMotorRight.get());
	}
	
	protected void end(){
		RobotMap.intakeMotorLeft.set(-0.2);
		RobotMap.intakeMotorRight.set(0.2);
		Robot.ledStrip.turnOff();
		Robot.isSpintake = false;
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished || isTimedOut();
	}

}
