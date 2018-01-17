package org.usfirst.frc.team3482.robot.command;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class GyroTurn90 extends Command {
	double turnAngle = 90;
	boolean finished = false;
	public GyroTurn90() {

	}

	protected void initialize(){
		RobotMap.gyro.enable();
		RobotMap.gyro.setSetpoint(turnAngle);
	}
	protected void execute(){
		if(RobotMap.gyro.onTarget()) {
			finished = true;
		}
	}
	protected void end(){
		//stop
		RobotMap.gyro.reset();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

}
