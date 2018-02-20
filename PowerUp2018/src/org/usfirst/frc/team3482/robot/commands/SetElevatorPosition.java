package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetElevatorPosition extends Command {
	double pos;
	public SetElevatorPosition(double pos) {
		this.pos = pos;
	}
	protected void initialize() {
		Robot.elevator.set(pos);
	}
	protected void execute(){
		
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Math.abs(Robot.elevator.getCurrentPos() - pos) < 1000;
	}
}
