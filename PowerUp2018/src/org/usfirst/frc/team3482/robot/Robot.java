/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	public static OI oi;
	public static String gameData;
	public static Intake intake;
	public static Chassis chassis;
	public static Elevator elevator;
	public static boolean driveEnabled, switchOnLeft, scaleOnLeft;
	public double newSpeed;
	public double currentSpeed = 0;
	public static final double MAX_SPEED_CHANGE = 0.1;
	public double turnSpeed;
	public static double outtakeSpeed = 1;

	@Override
	public void robotInit() {
		oi = new OI();
		driveEnabled = true;
		RobotMap.init();
		intake = new Intake();
		chassis = new Chassis();
		elevator = new Elevator();
		RobotMap.encoders.reset();
	}

	public void disabledPeriodic() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();

	}

	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		String[] data = gameData.split("");
		switchOnLeft = data[0].equals("L");
		scaleOnLeft = data[1].equals("L");
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {

		outtakeSpeed = oi.flightStick.getRawAxis(3);
		outtakeSpeed = (outtakeSpeed*.5)+.5;
		
		elevator.run();

		newSpeed = -oi.xBox.getRawAxis(1) * elevator.getSpeedRatio();
		turnSpeed = oi.xBox.getRawAxis(4) * elevator.getTurnRatio();
		
		if (Math.abs(newSpeed - currentSpeed) > MAX_SPEED_CHANGE) {
			if (newSpeed < currentSpeed) {
				newSpeed = currentSpeed - MAX_SPEED_CHANGE;
			} else {
				newSpeed = currentSpeed + MAX_SPEED_CHANGE;
			}
		}
		
		if (driveEnabled) {
			RobotMap.drive.arcadeDrive(newSpeed, turnSpeed);
		}
		
		currentSpeed = newSpeed;
		
//		System.out.println("Ratio: " + elevatorRatio);
//		System.out.println(
//				"Left Encoder: " + RobotMap.encoderLeft.get() + " Right Encoder: " + RobotMap.encoderRight.get());
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
