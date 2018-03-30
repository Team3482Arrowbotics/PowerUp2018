package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command{
	double angle;
	int counter;
	public Turn(double angle) {
		super(0.5);
		this.angle = angle;
		counter = 0;
	}
	protected void initialize() {
		RobotMap.drive.setTurning(true);
//		RobotMap.navx.reset();
		RobotMap.navx.zeroYaw();
		RobotMap.rotationController.enable();
		RobotMap.rotationController.setSetpoint(angle);
	}
	protected void execute(){
		counter++;
		System.out.println(counter);
	}
	protected void end() {
		RobotMap.rotationController.disable();
		RobotMap.drive.stopMotor();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		//System.out.println( "PID Error: " + RobotMap.rotationController.getError());
		//System.out.println( "PID Speed: " + RobotMap.rotationController.get());
		return RobotMap.rotationController.onTarget() || counter > 70;
	}
}
