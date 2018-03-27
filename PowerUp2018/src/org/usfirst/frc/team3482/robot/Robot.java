package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.paths.AutoConstants;
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
	//public static LIDAR intakeLidar;
	public static Ultrasonic rangeFinder;
	public static UsbCamera camera;
	public static CameraServer cameraServer;
	public static boolean switchOnLeft, scaleOnLeft, crossBaseline;
	public double speed;
	public double turnSpeed;
	public static boolean isSpintake, isSpoutake, isClimbing, isEMovingUp, isEMovingDown, boxInRange;
	public String colorsArray[];
	public Preferences prefs;

	// 230 encoder ticks per foot
	// 19 ticks/inch
	// 0.05 in per tick?
	public SendableChooser<String> sPosChooser;
	//	public SendableChooser<String> baselineChooser;
	public SendableChooser<String> autoChooser;
	public AutoConstants constants = new AutoConstants();
	String startPos;
	String autoType;
	Command autoCommand;


	@Override
	public void robotInit() {
		oi = new OI();
		isSpoutake = false;
		isSpintake = false;
		isClimbing = false;
		isEMovingUp = false;
		isEMovingDown = false;
		boxInRange = false;

		colorsArray = new String[] { "red", "purple", "white", "red", "white", "purple" };

		RobotMap.init();
		ledStrip = new LED();
		intake = new Intake();
		elevator = new Elevator();
		climber = new Climber();
		sideLidar = new LIDAR(RobotMap.c, CANifier.PWMChannel.PWMChannel0);
		//intakeLidar = new LIDAR(RobotMap.c, CANifier.PWMChannel.PWMChannel1);
		rangeFinder = new Ultrasonic(0);

		RobotMap.encoders.reset();

		sPosChooser = new SendableChooser<String>();
		sPosChooser.addObject("Middle", "MIDDLE");
		sPosChooser.addDefault("Left", "LEFT");
		sPosChooser.addObject("Right", "RIGHT");


		autoChooser = new SendableChooser<String>();
		autoChooser.addDefault("No Autonomous", "Null");
		autoChooser.addObject("Blitz", "Blitz");
		autoChooser.addObject("Timed", "Time");
		autoChooser.addObject("Two Box", "Two Box");
		autoChooser.addObject("Testing", "Test");
		autoChooser.addObject("Switch", "Switch");
		autoChooser.addObject("Scale", "Scale");

		//		autoChooser.addDefault("No Autonomous", autoType = "Null");
		//		autoChooser.addObject("Blitz", autoType = "Blitz");
		//		autoChooser.addObject("Timed Autonomous", autoType = "Time");
		//		autoChooser.addObject("Encoder Autonomous", autoType = "Encoders");
		//		autoChooser.addObject("Individual Function Testing", autoType = "Test");
		//		autoChooser.addObject("Switch Straight Ahead", autoType = "Basic");

		SmartDashboard.putData("Start Position" , sPosChooser);
		SmartDashboard.putData("Autonomous Path" , autoChooser);
		constants.set();

		camera = CameraServer.getInstance().startAutomaticCapture();

		new Thread(() -> {
			while (true) {
				if (RobotMap.intakeLidar.boxFullyInside()){
					ledStrip.turnColor("green");
				} else if (RobotMap.intakeLidar.boxInClampRange()) {
					ledStrip.turnColor("yellow");
				} else if (isSpintake) {
					ledStrip.flash("white", 0.1);
				} else if (isSpoutake) {
					ledStrip.flash("purple", 0.2);
				} else if (isEMovingUp) {
					ledStrip.flash("cyan", 0.2);
				} else if (isEMovingDown) {
					ledStrip.flash("yellow", 0.1);
				} else if (isClimbing) {
					ledStrip.flashRainbow(colorsArray, 0.15);
				} else {
					ledStrip.turnColor("red");
				}
			}
		}).start();
	}

	public void disabledPeriodic() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
	}

	@Override
	public void autonomousInit() {
		constants.get();
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		String[] data = gameData.split("");
		switchOnLeft = data[0].equals("L");
		scaleOnLeft = data[1].equals("L");

		//		String crossingMethod = baselineChooser.getSelected();
		//		if(crossingMethod == null) {
		//			crossingMethod = "";
		//		}
		//
		//		switch(crossingMethod) {
		//		case "base":
		//			crossBaseline = true;
		//			break;
		//		case "diag":
		//			crossBaseline = false;
		//			break;
		//		default:
		//			crossBaseline = true;
		//		}

		//String sPos = sPosChooser.getSelected();
		startPos =sPosChooser.getSelected();
		System.out.println("Start Position: " +startPos);
		autoType = autoChooser.getSelected();
		System.out.println(autoType);
		switch(autoType) {
		case "Two Box":
//			new TwoBoxAutonomous(switchOnLeft, scaleOnLeft, startPos).start();
			System.out.println("Running Two Box Auton");
			break;
		case "Timed":
//			new TimedAutonomous(switchOnLeft, startPos).start();
			System.out.println("Running Timed Auton");
			break;
		case "Test":
//			new TestAutonomous().start();
			System.out.println("Running Test Auton");
			break;
		case "Blitz":
			System.out.println("AAAAAAAAAAAAAA");
//			new BlitzAutonomous().start();
			break;
		case "Switch":
//			new SwitchAutonomous(switchOnLeft, startPos).start();
			System.out.println("Running Switch Auton");
			break;
		case "Scale":
//			new ScaleAutonomous(scaleOnLeft, startPos).start();
			System.out.println("Running Scale Auton");
			break;
		default:
			break;
		}
		System.out.println("Switch Distance: "+ AutoConstants.nextToSwitchDistance);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
//		elevator.run();
//		SmartDashboard.putNumber("Left Encoder: ", RobotMap.encoderLeft.getDistance());
//		SmartDashboard.putNumber("Right Encoder: ", RobotMap.encoderRight.getDistance());
//		SmartDashboard.putNumber("Angle: ", RobotMap.navx.getYaw());
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		elevator.teleopRun();
		System.out.println("Side Lidar: "+sideLidar.getDistance());
		System.out.println("Intake Lidar: "+RobotMap.intakeLidar.getDistance());

		// System.out.println("Left Encoder: " + RobotMap.encoderLeft.get() + " Right
		// Encoder: " + RobotMap.encoderRight.get());
		speed = -oi.xBox.getRawAxis(1) * Elevator.getSpeedRatio();
		turnSpeed = oi.xBox.getRawAxis(4) * Elevator.getTurnRatio();
		RobotMap.drive.arcadeDrive(speed, turnSpeed);
		// if(turnSpeed <= 0.1) {
		// RobotMap.rotationController.enable();
		// RobotMap.rotationController.setSetpoint(RobotMap.navx.getYaw());
		// } else {
		// RobotMap.rotationController.reset();
		// RobotMap.navx.reset();
		// RobotMap.rotationController.disable();
		// }
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
