package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Outtake extends Command{
	protected void initialize(){
		RobotMap.intakeMotorLeft.set(-Robot.outtakeSpeed);
		RobotMap.intakeMotorRight.set(Robot.outtakeSpeed);
		System.out.println("Outtake Speed: "+ Robot.outtakeSpeed);
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
