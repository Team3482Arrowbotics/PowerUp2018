/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.Autonomous.StartPosition;
import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.subsystems.Intake;
import org.usfirst.frc.team3482.robot.subsystems.LIDAR;
import org.usfirst.frc.team3482.robot.subsystems.Ultrasonic;

import com.ctre.phoenix.CANifier.LEDChannel;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static OI oi;
	public static String gameData;
	public static Intake intake;
	public static Elevator elevator;
	public static LIDAR lidar;
	public static Ultrasonic rangeFinder;
	public static UsbCamera camera;
	public static CameraServer cameraServer;
	public static final int ELEVATOR_UP_AXIS = 3, ELEVATOR_DOWN_AXIS = 2;
	public static final double ELEVATOR_AXIS_DEADZONE = 0.05, ELEVATOR_SPEED = 2000;
	public static boolean isElevatorTop;
	public static double elevatorTopSpeed = 0.5;
	public static boolean driveEnabled, switchOnLeft, scaleOnLeft, crossBaseline;
	public double speed;
	public double turnSpeed;	
	//230 encoder ticks per foot
	//19 ticks/inch
	//0.05 in per tick?
	public SendableChooser<StartPosition> sPosChooser;
	public SendableChooser<String> baselineChooser;
	Command autoCommand;

	@Override
	public void robotInit() {
		oi = new OI();
		driveEnabled = true;
		isElevatorTop = false;

		RobotMap.init();
		intake = new Intake();
		elevator = new Elevator();
		lidar = new LIDAR();
		rangeFinder = new Ultrasonic(0);
		
		RobotMap.elevatorTalon.setSelectedSensorPosition(Elevator.BOTTOM_POSITION, 0, 0);
		RobotMap.encoders.reset();
		
		sPosChooser = new SendableChooser<StartPosition>();
		sPosChooser.setName("Start Position");
		sPosChooser.addObject("Middle", StartPosition.MIDDLE);
		sPosChooser.addDefault("Left", StartPosition.LEFT);
		sPosChooser.addObject("Right", StartPosition.RIGHT);
		
		baselineChooser = new SendableChooser<String>();
		baselineChooser.addDefault("Cross Baseline", "base");
		baselineChooser.addObject("Cross diagonally", "diag");
		
		SmartDashboard.putData(sPosChooser);
		SmartDashboard.putData(baselineChooser);
		
		camera = CameraServer.getInstance().startAutomaticCapture();
	}

	public void disabledPeriodic() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();

	}

	@Override
	public void autonomousInit() {
		new Move(20).start();
//		gameData = DriverStation.getInstance().getGameSpecificMessage();
//		String[] data = gameData.split("");
//		switchOnLeft = data[0].equals("L");
//		scaleOnLeft = data[1].equals("L");
//		String baselineCrossed = baselineChooser.getSelected();
//		switch(baselineCrossed) {
//		case "base":
//			crossBaseline = true;
//			break;
//		case "diag":
//			crossBaseline = false;
//			break;
//		default:
//			crossBaseline = false;
//		}
//		StartPosition sPos = sPosChooser.getSelected();
//		new Autonomous(crossBaseline, switchOnLeft, scaleOnLeft, sPos).start();
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
		//System.out.println("Ratio: " + elevatorRatio);
		//System.out.println("Left Encoder: " + RobotMap.encoderLeft.get() + " Right Encoder: " + RobotMap.encoderRight.get());
		if (driveEnabled) {
			RobotMap.drive.arcadeDrive(speed * elevatorRatio, turnSpeed);
		}
		elevator.run();
		
		if(RobotMap.intakeLimitSwitch.get()) {
			RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelA);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelB);
		} else {
			RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelA);
		}
		
		SmartDashboard.putBoolean("Limit Switch: ", RobotMap.intakeLimitSwitch.get());
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
