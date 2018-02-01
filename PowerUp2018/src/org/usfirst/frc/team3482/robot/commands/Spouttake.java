package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Spouttake extends Command{
	protected void initialize(){
		RobotMap.intakeMotorLeft.set(-.75);
		RobotMap.intakeMotorRight.set(.75);
		System.out.println("Motors set!");
	}
	protected void end(){
		RobotMap.intakeMotorLeft.set(0);
		RobotMap.intakeMotorRight.set(0);		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
