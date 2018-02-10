package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ManualElevatorSpeed extends Command{
	private double speed;
	public ManualElevatorSpeed(double speed) {
		this.speed=speed;
	}
	protected void initialize(){
		Robot.elevator.lock(false);
		RobotMap.elevatorTalon.set(speed);
		System.out.println("Motors set!");
	}
	protected void end(){
		RobotMap.elevatorTalon.set(0);
		Robot.elevator.lock(true);
		
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
