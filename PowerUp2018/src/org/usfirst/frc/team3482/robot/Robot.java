/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.MoveDistance;
import org.usfirst.frc.team3482.robot.subsystems.Chassis;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	public static OI oi;
	String gameData;
	public static Intake intake;
	public static Chassis chassis;
	public static Elevator elevator;
	public static final int ELEVATOR_UP_AXIS = 3, ELEVATOR_DOWN_AXIS = 2;
	public static final double ELEVATOR_AXIS_DEADZONE = 0.05, ELEVATOR_SPEED = 1000;
	public static boolean isElevatorTop;
	public static double elevatorTopSpeed = 0.5;
	public static boolean driveEnabled;
	public double speed;
	public double turnSpeed;

	@Override
	public void robotInit() {
		oi = new OI();
		driveEnabled = true;
		isElevatorTop = false;

		RobotMap.init();
		intake = new Intake();
		chassis = new Chassis();
		elevator = new Elevator();

		RobotMap.elevatorTalon.setSelectedSensorPosition(elevator.BOTTOM_POSITION, 0, 0);
		RobotMap.encoders.reset();
	}

	public void disabledPeriodic() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();

	}

	@Override
	public void autonomousInit() {
		new MoveDistance(1000).start();
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
		System.out.println("Position: " + elevator.getCurrentPos() + " Error: " + RobotMap.elevatorTalon.getClosedLoopError(0));
		if (oi.x.getRawAxis(ELEVATOR_UP_AXIS) > ELEVATOR_AXIS_DEADZONE
				&& oi.x.getRawAxis(ELEVATOR_DOWN_AXIS) < ELEVATOR_AXIS_DEADZONE) {
			elevator.changePosition(ELEVATOR_SPEED, ELEVATOR_UP_AXIS);
		} else if (oi.x.getRawAxis(ELEVATOR_DOWN_AXIS) > ELEVATOR_AXIS_DEADZONE
				&& oi.x.getRawAxis(ELEVATOR_UP_AXIS) < ELEVATOR_AXIS_DEADZONE) {
			elevator.changePosition(-ELEVATOR_SPEED * .6, ELEVATOR_DOWN_AXIS);
		}

		double elevatorRatio = ((1 - (elevator.getCurrentPos() / Elevator.TOP_POSITION)) * 0.5) + 0.5;
		speed = -oi.x.getRawAxis(1) * elevatorRatio;
		turnSpeed = oi.x.getRawAxis(4);
		System.out.println("Ratio: " + elevatorRatio);
		System.out.println(
				"Left Encoder: " + RobotMap.encoderLeft.get() + " Right Encoder: " + RobotMap.encoderRight.get());
		if (driveEnabled) {
			RobotMap.drive.arcadeDrive(speed, turnSpeed);
		}
		elevator.run();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
