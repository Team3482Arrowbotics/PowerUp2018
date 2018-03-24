package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.subsystems.DrivePIDOutput;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.subsystems.PWMLidar;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
	public static DigitalInput intakePhotoelectric;
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
//	public static CANifier c2;
	public static PIDController counteractDrift; 
	public static RotationAdjuster rotationAdjuster;
	public static PWMLidar intakeLidar;
	
	public static void init() {
		frontLeft = new WPI_TalonSRX(3);		
		frontRight = new WPI_TalonSRX(12);
		backLeft = new WPI_TalonSRX(2);
		backRight = new WPI_TalonSRX(6);
		
		encoderLeft = new Encoder(0, 1);
		encoderRight = new Encoder(2, 3);
		encoderRight.setReverseDirection(true);
		encoderLeft.setDistancePerPulse(0.05);
		encoderRight.setDistancePerPulse(0.05);
		encoders = new TwoEncoderPID(encoderLeft, encoderRight);
		
		rotationAdjuster = new RotationAdjuster();
		
		left = new SpeedControllerGroup(frontLeft, backLeft);
		right = new SpeedControllerGroup(frontRight, backRight);
		drive = new PIDDifferentialDrive(left, right, rotationAdjuster);
		drivePID = new DrivePIDOutput();
		driveController = new PIDController(0.1, 0, 0, encoderRight, drive);
		driveController.setOutputRange(-.7, .7);
		drive.setDeadband(0.1);
		drive.setSafetyEnabled(false);
		driveController.setAbsoluteTolerance(1);

		intakeMotorLeft = new WPI_TalonSRX(8);
		intakeMotorRight = new WPI_TalonSRX(4);
		
		//intakePhotoelectric = new DigitalInput(4); 

		intakePistonLeft = new DoubleSolenoid(5, 4);
		intakePistonRight = new DoubleSolenoid(7, 6);

		elevatorTalon = new WPI_TalonSRX(5);
		elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		elevatorTalon.config_kP(0, Elevator.ELEVATOR_P_VALUE, 0);
		elevatorTalon.setInverted(false);
		
		elevatorTalon2 = new WPI_TalonSRX(7);
		
		navx = new AHRSPID(Port.kMXP);
		
		rotationController = new PIDController(0.05, 0, 0, navx, drive);
		rotationController.setInputRange(-180, 180);
		rotationController.setOutputRange(-.7, .7);
		rotationController.setContinuous(true);
		rotationController.setAbsoluteTolerance(1);
		
		counteractDrift = new PIDController(0.05, 0, 0, navx, rotationAdjuster);
		counteractDrift.setInputRange(-180, 180);
		counteractDrift.setOutputRange(-.7, .7);
		counteractDrift.setContinuous(true);
		counteractDrift.setAbsoluteTolerance(1);
		
		climberHook = new WPI_TalonSRX(9);
		climber = new WPI_TalonSRX(0);
		
		c = new CANifier(0);
		
		intakeLidar = new PWMLidar(4);
//		c2 = new CANifier(4);
	}
}
