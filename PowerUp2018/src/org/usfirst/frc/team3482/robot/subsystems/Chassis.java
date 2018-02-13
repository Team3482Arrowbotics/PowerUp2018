package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Chassis extends Subsystem{
	private double encoderLeftValue;
	private double encoderRightValue;
	private Encoder encoderLeft;
	private Encoder encoderRight;
	private PIDController drive;
	public Chassis() {
		drive = RobotMap.driveController;
		encoderLeft = RobotMap.encoderLeft;
		encoderRight = RobotMap.encoderRight;
	}

	public double getEncoderValues() {
		encoderLeftValue = Math.abs(encoderLeft.getDistance());
		encoderRightValue = Math.abs(encoderRight.getDistance());
		return (encoderLeftValue + encoderRightValue) / 2;
	}
	
	public void resetDriveEncoders() {
		encoderLeft.reset();
		encoderRight.reset();
	}
	
	public void moveDistance(double distance) {
		resetDriveEncoders();
		drive.setSetpoint(distance);
		resetDriveEncoders();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
