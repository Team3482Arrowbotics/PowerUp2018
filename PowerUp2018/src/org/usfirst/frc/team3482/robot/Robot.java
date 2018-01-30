/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.command.Left;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	OI oi;
	static boolean driveEnabled = true;
	public static boolean isDriveEnabled() {
		return driveEnabled;
	}


	public static void setDriveEnabled(boolean d) {
		driveEnabled = d;
	}


	@Override
	public void robotInit() {
		oi = new OI();
		RobotMap.init();
		
	}


	@Override
	public void autonomousInit() {
		new Left().start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("gyro value", RobotMap.gyro.get());
		SmartDashboard.putNumber("NavX yaw", RobotMap.navx.getYaw());
		double forwardSpeed = oi.x.getRawAxis(1);
		double turnSpeed = oi.x.getRawAxis(4);
		if(driveEnabled){
			RobotMap.drive.arcadeDrive(forwardSpeed, turnSpeed);			
		}
		Scheduler.getInstance().run();
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testInit(){
		LiveWindow.add(RobotMap.gyro);
	}
	public void testPeriodic() {
		
	}
	public void disabledInit(){
		RobotMap.gyro.disable();
	}
}
