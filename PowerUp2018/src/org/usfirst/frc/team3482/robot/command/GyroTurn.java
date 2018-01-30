package org.usfirst.frc.team3482.robot.command;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class GyroTurn extends Command {
	double turnAngle = 0, counter = 0;
	public GyroTurn(double tA) {
		turnAngle = tA;
	}

	protected void initialize(){
		RobotMap.gyro.reset();
		RobotMap.navx.reset();
		RobotMap.gyro.enable();
		RobotMap.gyro.setSetpoint(turnAngle);
	}
	protected void execute(){
		if(RobotMap.gyro.onTarget()) {
			counter++; 
			System.out.println("OnTarget! - " + counter);
		}
	}
	protected void end(){
		//stop
		RobotMap.gyro.reset();
		RobotMap.navx.reset();
		counter = 0;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return counter >= 2; 
	}

}
