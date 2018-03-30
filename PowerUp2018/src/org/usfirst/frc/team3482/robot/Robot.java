package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.BlitzAutonomous;
import org.usfirst.frc.team3482.robot.commands.Outtake;
import org.usfirst.frc.team3482.robot.commands.ScaleAutonomous;
import org.usfirst.frc.team3482.robot.commands.SwitchAutonomous;
import org.usfirst.frc.team3482.robot.commands.TestAutonomous;
import org.usfirst.frc.team3482.robot.commands.TimedAutonomous;
import org.usfirst.frc.team3482.robot.commands.TwoBoxAutonomous;
import org.usfirst.frc.team3482.robot.commands.paths.AutoConstants;
import org.usfirst.frc.team3482.robot.subsystems.Climber;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.subsystems.Intake;
import org.usfirst.frc.team3482.robot.subsystems.LED;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static OI oi;
	public static String gameData;
	public static LED ledStrip;
	public static Intake intake;
	public static Elevator elevator;
	public static Climber climber;
	public static UsbCamera camera;
	public static CameraServer cameraServer;
	public static boolean switchOnLeft, scaleOnLeft, crossBaseline;
	public double speed;
	public double turnSpeed;
	public static boolean isSpintake, isSpoutake, isClimbing, isEMovingUp, isEMovingDown, boxInRange;
	public String colorsArray[];
	public Preferences prefs;
	public SendableChooser<String> sPosChooser;
	public SendableChooser<String> autoChooser;
	public AutoConstants constants = new AutoConstants();
	public static String startPos;
	public static String autoType;
	public static Command autoCommand;


	@Override
	public void robotInit() {
		RobotMap.init();
		oi = new OI();
		isSpoutake = false;
		isSpintake = false;
		isClimbing = false;
		isEMovingUp = false;
		isEMovingDown = false;
		boxInRange = false;

		colorsArray = new String[] { "red", "green", "yellow", "cyan", "white", "purple" };

		
		ledStrip = new LED();
		intake = new Intake();
		elevator = new Elevator();
		climber = new Climber();
		

//		RobotMap.encoders.reset();

		sPosChooser = new SendableChooser<String>();
		sPosChooser.addObject("Middle", "MIDDLE");
		sPosChooser.addDefault("Left", "LEFT");
		sPosChooser.addObject("Right", "RIGHT");

		autoChooser = new SendableChooser<String>();
		autoChooser.addDefault("No Autonomous", "Null");
		autoChooser.addObject("Blitz", "Blitz");
		autoChooser.addObject("Timed", "Timed");
		autoChooser.addObject("Two Box", "Two Box");
		autoChooser.addObject("Testing", "Test");
		autoChooser.addObject("Switch", "Switch");
		autoChooser.addObject("Scale", "Scale");

		SmartDashboard.putData("Start Position" , sPosChooser);
		SmartDashboard.putData("Autonomous Path" , autoChooser);
		
		constants.set();

		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(20);

		new Thread(() -> {
			while (true) {
				if (RobotMap.intakeLidar.boxFullyInside()){
					ledStrip.turnColor("green");
				} else if (RobotMap.intakeLidar.boxInClampRange()) {
					ledStrip.turnColor("yellow");
				} else if (isSpoutake) {
					ledStrip.flash("purple", 0.2);
				} else if (isClimbing) {
					ledStrip.flashRainbow(colorsArray, 0.15);
				} else {
					ledStrip.turnColor("red");
				}
				if (elevator.isTop()) {
					oi.xBox.setRumble(RumbleType.kLeftRumble, 1.0);
					oi.xBox.setRumble(RumbleType.kRightRumble, 1.0);
				} else {
					oi.xBox.setRumble(RumbleType.kLeftRumble, 0.0);
					oi.xBox.setRumble(RumbleType.kRightRumble, 0.0);
				}
			}
		}).start();
	}

	public void disabledPeriodic() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		SmartDashboard.putNumber("Side Lidar Distance", RobotMap.sideLidar.getDistance());
		SmartDashboard.putNumber("Intake Lidar Distance", RobotMap.intakeLidar.getDistance());
		SmartDashboard.putBoolean("DrasticChange?", RobotMap.intakeLidar.drasticChange);
	}

	@Override
	public void autonomousInit() {
		constants.get();
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		String[] data = gameData.split("");
		switchOnLeft = data[0].equals("L");
		scaleOnLeft = data[1].equals("L");

		startPos =sPosChooser.getSelected();
		System.out.println("Start Position: " +startPos);
		
		autoType = autoChooser.getSelected();
		System.out.println(autoType);
		
		switch(autoType) {
		case "Two Box":
			autoCommand = new TwoBoxAutonomous(switchOnLeft, scaleOnLeft, startPos);
			System.out.println("Running Two Box Auton");
			break;
		case "Timed":
			autoCommand = new TimedAutonomous(switchOnLeft, startPos);
			System.out.println("Running Timed Auton");
			break;
		case "Test":
			autoCommand = new TestAutonomous();
			System.out.println("Running Test Auton");
			break;
		case "Blitz":
			System.out.println("AAAAAAAAAAAAAA");
			autoCommand = new BlitzAutonomous();
			break;
		case "Switch":
			autoCommand = new SwitchAutonomous(switchOnLeft, startPos);
			System.out.println("Running Switch Auton");
			break;
		case "Scale":
			autoCommand = new ScaleAutonomous(scaleOnLeft, startPos);
			System.out.println("Running Scale Auton");
			break;
		default:
			autoCommand = new WaitCommand(0);
			break;
		}
		autoCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		elevator.run();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopInit(){
		if(autoCommand != null)
			autoCommand.cancel();
		RobotMap.driveController.disable();
	}
	
	public void teleopPeriodic() {
		elevator.teleopRun();
//		System.out.println("Side Lidar: "+RobotMap.sideLidar.getDistance());
//		System.out.println("Intake Lidar: "+RobotMap.intakeLidar.getDistance());
		System.out.println("Drive Encoder Right Value: " +RobotMap.encoderRight.getDistance());
//		System.out.println("Drive Encoder Left Value: " +RobotMap.encoderLeft.getDistance());

		speed = -oi.xBox.getRawAxis(1) * Elevator.getSpeedRatio();
		turnSpeed = oi.xBox.getRawAxis(4) * Elevator.getTurnRatio();
		
		if(RobotMap.drive.isEnabled())
		{
			System.out.println("Drive Enabled");
			RobotMap.drive.arcadeDrive(speed, turnSpeed);
		}
		
		Outtake.outtakeSpeed = ((2 - (Robot.oi.flightStick.getRawAxis(3) + 1)) * 0.25 + .5);
		constants.get();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
