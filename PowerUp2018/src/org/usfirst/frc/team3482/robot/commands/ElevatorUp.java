package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ElevatorUp extends InstantCommand{
	public void initialize(){
		RobotMap.elevatorTalon.set(ControlMode.Position, -10000);
	}

}
