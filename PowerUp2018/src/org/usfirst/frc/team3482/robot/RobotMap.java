package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.DrivePIDOutput;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class RobotMap {
	public static WPI_TalonSRX frontLeft;
	public static WPI_TalonSRX frontRight;
	public static WPI_TalonSRX backLeft;
	public static WPI_TalonSRX backRight;
	public static PIDDifferentialDrive drive;
	public static PIDController driveController;
	public static SpeedControllerGroup left;
	public static SpeedControllerGroup right;
	public static WPI_TalonSRX intakeMotorLeft;
	public static WPI_TalonSRX intakeMotorRight;
	public static DoubleSolenoid intakePistonLeft;
	public static DoubleSolenoid intakePistonRight;
	public static DigitalInput intakeLimitSwitch;
	public static WPI_TalonSRX elevatorTalon;
	public static WPI_TalonSRX elevatorTalon2;
 	public static Encoder encoderRight;
	public static Encoder encoderLeft;
	public static DrivePIDOutput drivePID;
	public static TwoEncoderPID encoders;
	public static PIDController rotationController;
	public static AHRSPID navx;
	public static WPI_TalonSRX climberHook;
	public static WPI_TalonSRX climber;
	public static PIDController climberHookController;
	public static WPI_TalonSRX emptyTalon;
	public static CANifier c;
	public static PIDController counteractDrift; 
	public static RotationAdjuster rotationAdjuster;

	public static void init() {
		frontLeft = new WPI_TalonSRX(3);
		frontRight = new WPI_TalonSRX(12);
		backLeft = new WPI_TalonSRX(2);
		backRight = new WPI_TalonSRX(6);
		
		encoderRight = new Encoder(0, 1);
		encoderLeft = new Encoder(2, 3);
		encoderLeft.setReverseDirection(true);
		encoderLeft.setDistancePerPulse(0.05179);
		encoderRight.setDistancePerPulse(0.05179);
		encoders = new TwoEncoderPID(encoderLeft, encoderRight);
		
		rotationAdjuster = new RotationAdjuster();
		left = new SpeedControllerGroup(frontLeft, backLeft);
		right = new SpeedControllerGroup(frontRight, backRight);
		drive = new PIDDifferentialDrive(left, right, rotationAdjuster);
		drivePID = new DrivePIDOutput();
		driveController = new PIDController(0.05, 0, 0, encoderLeft, drive);
		driveController.setOutputRange(-.6, .6);
		drive.setDeadband(0.1);
		drive.setSafetyEnabled(false);
		driveController.setAbsoluteTolerance(2);

		intakeMotorLeft = new WPI_TalonSRX(8);
		intakeMotorRight = new WPI_TalonSRX(4);
		
		intakeLimitSwitch = new DigitalInput(4); 

		intakePistonLeft = new DoubleSolenoid(5, 4);
		intakePistonRight = new DoubleSolenoid(7, 6);

		elevatorTalon = new WPI_TalonSRX(5);
		elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		elevatorTalon.config_kP(0, 1.5, 0);
		elevatorTalon.setInverted(false);
		
		elevatorTalon2 = new WPI_TalonSRX(0);
//		elevator = new TwoMotorsOneEncoder(elevatorTalon);
		
		navx = new AHRSPID(Port.kMXP);
		rotationController = new PIDController(0.1, 0, 0, navx, drive);
		rotationController.setInputRange(-180, 180);
		rotationController.setOutputRange(-.65, .65);
		rotationController.setContinuous(true);
		rotationController.setAbsoluteTolerance(2);
		
		
		counteractDrift = new PIDController(0.08, 0, 0, navx, rotationAdjuster);
		counteractDrift.setInputRange(-180, 180);
		counteractDrift.setOutputRange(-.7, .7);
		counteractDrift.setContinuous(true);
		counteractDrift.setAbsoluteTolerance(1);
		
		climberHook = new WPI_TalonSRX(9); //Talon 9
		climber = new WPI_TalonSRX(7); //Talon 7
		
		c = new CANifier(0);
	}
}
