package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Spintake extends Command{
	protected void initialize(){
		RobotMap.intakeMotor.set(0.5);
	}
	protected void interrupted(){
		RobotMap.intakeMotor.set(0);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
