/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.Autonomous.StartPosition;
import org.usfirst.frc.team3482.robot.commands.paths.AutoTesting;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.subsystems.Intake;
import org.usfirst.frc.team3482.robot.subsystems.LED;
import org.usfirst.frc.team3482.robot.subsystems.LIDAR;
import org.usfirst.frc.team3482.robot.subsystems.Ultrasonic;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static OI oi;
	public static String gameData;
	public static LED ledStrip;
	public static Intake intake;
	public static Elevator elevator;
	public static LIDAR lidar;
	public static Ultrasonic rangeFinder;
	//public static UsbCamera camera;
	public static CameraServer cameraServer;
	public static final int ELEVATOR_UP_AXIS = 1, ELEVATOR_DOWN_AXIS = 1;
	public static final double ELEVATOR_AXIS_DEADZONE = 0.05, ELEVATOR_SPEED = 2000;
	public static boolean isElevatorTop;
	public static double elevatorTopSpeed = 0.5;
	public static boolean driveEnabled, switchOnLeft, scaleOnLeft, crossBaseline;
	public double speed;
	public double turnSpeed;
	public static boolean isSpintake;
	public static boolean isSpoutake;
	public static boolean isClimberhook;
	public static boolean isEMovingUp;
	public static boolean isEMovingDown;
	public static String spintakeColor;
	public static String spoutakeColor;
	public static String eUpColor;
	public static String eDownColor;
	public static String boxInColor;
	public static String boxOutColor;
	public String colorsArray[];
	public Preferences prefs;

	// 230 encoder ticks per foot
	// 19 ticks/inch
	// 0.05 in per tick?
	public SendableChooser<StartPosition> sPosChooser;
	public SendableChooser<String> baselineChooser;
	Command autoCommand;

	@Override
	public void robotInit() {
		oi = new OI();
		driveEnabled = true;
		isElevatorTop = false;
		isSpoutake = false;
		isSpintake = false;
		isClimberhook = false;
		isEMovingUp = false;
		isEMovingDown = false;

		colorsArray = new String[] { "red", "yellow", "green", "cyan", "white", "purple" };

		RobotMap.init();
		ledStrip = new LED();
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

		//camera = CameraServer.getInstance().startAutomaticCapture();
		
		spintakeColor = "white";
		spoutakeColor = "purple";
		eUpColor = "cyan";
		eDownColor = "yellow";
		boxInColor = "green";
		boxOutColor = "red";
		

		new Thread(() -> {
			while (true) {
				if (isSpintake) {
					ledStrip.flash(spintakeColor, 0.1);
				} else if (isSpoutake) {
					ledStrip.flash(spoutakeColor, 0.2);
				} else if (isEMovingUp) {
					ledStrip.flash(eUpColor, 0.2);
				} else if (isEMovingDown) {
					ledStrip.flash(eDownColor, 0.1);
				} else if (isClimberhook) {
					ledStrip.flashRainbow(colorsArray, 0.15);
				} else {
					ledStrip.ledBoxCondition(boxOutColor, boxInColor);
				}
				// System.out.println("Thread is running");
			}
		}).start();
	}

	public void disabledPeriodic() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	}

	@Override
	public void autonomousInit() {
		new AutoTesting().start();
		// gameData = DriverStation.getInstance().getGameSpecificMessage();
		// String[] data = gameData.split("");
		// switchOnLeft = data[0].equals("L");
		// scaleOnLeft = data[1].equals("L");
		// String baselineCrossed = baselineChooser.getSelected();
		// switch(baselineCrossed) {
		// case "base":
		// crossBaseline = true;
		// break;
		// case "diag":
		// crossBaseline = false;
		// break;
		// default:
		// crossBaseline = false;
		// }
		// StartPosition sPos = sPosChooser.getSelected();
		// new Autonomous(crossBaseline, switchOnLeft, scaleOnLeft, sPos).start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		elevator.run();
		SmartDashboard.putNumber("Left Encoder: ", RobotMap.encoderLeft.getDistance());
		SmartDashboard.putNumber("Left Encoder Raw: ", RobotMap.encoderLeft.get());
		SmartDashboard.putNumber("Right Encoder: ", RobotMap.encoderRight.getDistance());
		SmartDashboard.putNumber("Angle: ", RobotMap.navx.getYaw()); 
		SmartDashboard.putNumber("Lidar distance", lidar.getDistance());
		System.out.println("Current Position: " + Robot.elevator.getCurrentPos() + " Target Position: " + Robot.elevator.getTargetPos());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		// System.out.println("Position: " + elevator.getCurrentPos() + " Error: " +
		// RobotMap.elevatorTalon.getClosedLoopError(0));
		if (oi.flightStick.getRawAxis(ELEVATOR_UP_AXIS) < -ELEVATOR_AXIS_DEADZONE) {
			elevator.changePosition(ELEVATOR_SPEED, ELEVATOR_UP_AXIS);
			isEMovingUp = true;
			isEMovingDown = false;
		} else if (oi.flightStick.getRawAxis(ELEVATOR_DOWN_AXIS) > ELEVATOR_AXIS_DEADZONE) {
			elevator.changePosition(-ELEVATOR_SPEED * 0.6, ELEVATOR_DOWN_AXIS);
			isEMovingUp = false;
			isEMovingDown = true;
		} else {
			isEMovingUp = false;
			isEMovingDown = false;
		}

		double elevatorRatio = ((1 - (elevator.getCurrentPos() / Elevator.TOP_POSITION)) * 0.3) + 0.7;
		speed = -oi.x2.getRawAxis(1) * elevatorRatio;
		turnSpeed = oi.x2.getRawAxis(4) * elevatorRatio;
		// System.out.println("Ratio: " + elevatorRatio);
		// System.out.println("Left Encoder: " + RobotMap.encoderLeft.get() + " Right
		// Encoder: " + RobotMap.encoderRight.get());
		if (driveEnabled) {
			RobotMap.drive.arcadeDrive(speed * elevatorRatio, turnSpeed);
		}
		elevator.run();

		SmartDashboard.putBoolean("Is box in: ", !RobotMap.intakeLimitSwitch.get());

		if (elevator.isTop()) {
			oi.x.setRumble(RumbleType.kLeftRumble, 1.0);
			oi.x.setRumble(RumbleType.kRightRumble, 1.0);
		} else {
			oi.x.setRumble(RumbleType.kLeftRumble, 0.0);
			oi.x.setRumble(RumbleType.kRightRumble, 0.0);
		}
		
		if (oi.flightStick.getRawAxis(2) > 0.5) {
			RobotMap.climberHook.set(0.2);
			isClimberhook = true;
		} else if (oi.flightStick.getRawAxis(2) < -0.5) {
			RobotMap.climberHook.set(-0.2);
			isClimberhook = true;
		} else {
			RobotMap.climberHook.set(0.0);
			isClimberhook = false;
		}
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
