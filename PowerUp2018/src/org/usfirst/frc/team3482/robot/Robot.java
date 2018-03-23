package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.EncoderAutonomous;
import org.usfirst.frc.team3482.robot.commands.TestAutonomous;
import org.usfirst.frc.team3482.robot.commands.TimedAutonomous;
import org.usfirst.frc.team3482.robot.commands.paths.StartPosition;
import org.usfirst.frc.team3482.robot.subsystems.Climber;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.subsystems.Intake;
import org.usfirst.frc.team3482.robot.subsystems.LED;
import org.usfirst.frc.team3482.robot.subsystems.LIDAR;
import org.usfirst.frc.team3482.robot.subsystems.Ultrasonic;

import com.ctre.phoenix.CANifier;

import edu.wpi.cscore.UsbCamera;
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
	public static Climber climber;
	public static LIDAR sideLidar;
	public static LIDAR intakeLidar;
	public static Ultrasonic rangeFinder;
	public static UsbCamera camera;
	public static CameraServer cameraServer;
	public static boolean driveEnabled, switchOnLeft, scaleOnLeft, crossBaseline;
	public double speed;
	public double turnSpeed;
	public static boolean isSpintake;
	public static boolean isSpoutake;
	public static boolean isClimberHook;
	public static boolean isEMovingUp;
	public static boolean isEMovingDown;
	public String colorsArray[];
	public Preferences prefs;

	// 230 encoder ticks per foot
	// 19 ticks/inch
	// 0.05 in per tick?
	public SendableChooser<StartPosition> sPosChooser;
	public SendableChooser<String> baselineChooser;
	public SendableChooser<String> autoChooser;
	Command autoCommand;


	@Override
	public void robotInit() {
		oi = new OI();
		driveEnabled = true;
		isSpoutake = false;
		isSpintake = false;
		isClimberHook = false;
		isEMovingUp = false;
		isEMovingDown = false;

		colorsArray = new String[] { "red", "yellow", "green", "cyan", "white", "purple" };

		RobotMap.init();
		ledStrip = new LED();
		intake = new Intake();
		elevator = new Elevator();
		climber = new Climber();
		sideLidar = new LIDAR(RobotMap.c, CANifier.PWMChannel.PWMChannel0);
		intakeLidar = new LIDAR(RobotMap.c, CANifier.PWMChannel.PWMChannel1);
		rangeFinder = new Ultrasonic(0);

		RobotMap.encoders.reset();

		sPosChooser = new SendableChooser<StartPosition>();
		sPosChooser.setName("Start Position");
		sPosChooser.addObject("Middle", StartPosition.MIDDLE);
		sPosChooser.addDefault("Left", StartPosition.LEFT);
		sPosChooser.addObject("Right", StartPosition.RIGHT);

		baselineChooser = new SendableChooser<String>();
		baselineChooser.addDefault("Cross Baseline", "base");
		baselineChooser.addObject("Cross diagonally", "diag");


		autoChooser = new SendableChooser<String>();
		autoChooser.addDefault("Timed Autonomous", "Time");
		autoChooser.addObject("Encoder Autonomous", "Encoders");
		autoChooser.addObject("Individual Function Testing", "Test");

		SmartDashboard.putData(sPosChooser);
		SmartDashboard.putData(baselineChooser);

		camera = CameraServer.getInstance().startAutomaticCapture();

		new Thread(() -> {
			while (true) {
				if (isSpintake) {
					ledStrip.flash("white", 0.1);
				} else if (isSpoutake) {
					ledStrip.flash("purple", 0.2);
				} else if (isEMovingUp) {
					ledStrip.flash("cyan", 0.2);
				} else if (isEMovingDown) {
					ledStrip.flash("yellow", 0.1);
				} else if (isClimberHook) {
					ledStrip.flashRainbow(colorsArray, 0.15);
				} else {
					ledStrip.ledBoxCondition("red", "green");
				}
				//System.out.println("Side Lidar: "+sideLidar.getDistance());
			}
		}).start();
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
		
		String crossingMethod = baselineChooser.getSelected();
		
		switch(crossingMethod) {
		case "base":
			crossBaseline = true;
			break;
		case "diag":
			crossBaseline = false;
			break;
		default:
			crossBaseline = false;
		}

		StartPosition sPos = sPosChooser.getSelected();

		String autoType = autoChooser.getSelected();
		switch(autoType) {
		case "Encoders":
			new EncoderAutonomous(crossBaseline, switchOnLeft, scaleOnLeft, sPos).start();
			break;
		case "Timed":
			new TimedAutonomous(crossBaseline, switchOnLeft, sPos).start();
			break;
		case "Test":
			new TestAutonomous().start();
			break;
		}



	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		elevator.run();
		SmartDashboard.putNumber("Left Encoder: ", RobotMap.encoderLeft.getDistance());
		SmartDashboard.putNumber("Right Encoder: ", RobotMap.encoderRight.getDistance());
		SmartDashboard.putNumber("Angle: ", RobotMap.navx.getYaw());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		elevator.teleopRun();
		climber.run();

		speed = -oi.xBox.getRawAxis(1) * elevator.getSpeedRatio();
		turnSpeed = oi.xBox.getRawAxis(4) * elevator.getTurnRatio();

		// System.out.println("Left Encoder: " + RobotMap.encoderLeft.get() + " Right
		// Encoder: " + RobotMap.encoderRight.get());
		if (driveEnabled) {
			RobotMap.drive.arcadeDrive(speed, turnSpeed);
			// if(turnSpeed <= 0.1) {
			// RobotMap.rotationController.enable();
			// RobotMap.rotationController.setSetpoint(RobotMap.navx.getYaw());
			// } else {
			// RobotMap.rotationController.reset();
			// RobotMap.navx.reset();
			// RobotMap.rotationController.disable();
			// }
		}

		//SmartDashboard.putBoolean("Is box in: ", !RobotMap.intakePhotoelectric.get());

		if (elevator.isTop()) {
			oi.xBox.setRumble(RumbleType.kLeftRumble, 1.0);
			oi.xBox.setRumble(RumbleType.kRightRumble, 1.0);
		} else {
			oi.xBox.setRumble(RumbleType.kLeftRumble, 0.0);
			oi.xBox.setRumble(RumbleType.kRightRumble, 0.0);
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
