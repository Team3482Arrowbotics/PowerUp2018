package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ElevatorUp extends InstantCommand{
	public void initialize(){
		Robot.elevator.moveUp();
	}

}
