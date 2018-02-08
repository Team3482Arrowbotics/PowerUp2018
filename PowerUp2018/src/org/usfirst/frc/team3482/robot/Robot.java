/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	public static OI oi;
	String gameData;
	public static Intake intake;
	public static Elevator elevator;
	public static final int ELEVATOR_UP_AXIS = 3, ELEVATOR_DOWN_AXIS = 2; 
	public static final double ELEVATOR_AXIS_DEADZONE = 0.05, ELEVATOR_SPEED = 1.0;

	@Override
	public void robotInit() {
		oi = new OI();
		
		RobotMap.init();
		intake = new Intake();
		elevator = new Elevator();
		RobotMap.elevatorTalon.setSelectedSensorPosition(0, 0, 0);
	}

	public void disabledPeriodic() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();

	}

	@Override
	public void autonomousInit() {

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
		System.out.println("Position: " + RobotMap.elevatorTalon.getSelectedSensorPosition(0) + " Error: " + RobotMap.elevatorTalon.getClosedLoopError(0));
		if(oi.x.getRawAxis(ELEVATOR_UP_AXIS) > ELEVATOR_AXIS_DEADZONE && oi.x.getRawAxis(ELEVATOR_DOWN_AXIS) < ELEVATOR_AXIS_DEADZONE) {
			elevator.manualMove(ELEVATOR_SPEED, ELEVATOR_UP_AXIS);
		}
		if(oi.x.getRawAxis(ELEVATOR_DOWN_AXIS) > ELEVATOR_AXIS_DEADZONE && oi.x.getRawAxis(ELEVATOR_UP_AXIS) < ELEVATOR_AXIS_DEADZONE) {
			elevator.manualMove(-ELEVATOR_SPEED, ELEVATOR_DOWN_AXIS);
		}
		double speed = oi.x.getRawAxis(1);
		double turnSpeed = oi.x.getRawAxis(4);
		if (RobotMap.drive.isAlive()) {
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
