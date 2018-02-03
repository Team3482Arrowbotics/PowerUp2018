package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualElevator extends Command{
	protected void initialize() {
		Robot.elevator.unlock();
	}
	protected void end() {
		Robot.elevator.lock();
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
