package org.usfirst.frc.team3482.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {
	public static WPI_TalonSRX frontLeft;
	public static WPI_TalonSRX frontRight;
	public static WPI_TalonSRX backLeft;
	public static WPI_TalonSRX backRight;
	public static DifferentialDrive drive;
	public static SpeedControllerGroup left;
	public static SpeedControllerGroup right;
	public static WPI_TalonSRX intakeMotorLeft;
	public static WPI_TalonSRX intakeMotorRight;
	public static DoubleSolenoid intakePistonLeft;
	public static DoubleSolenoid intakePistonRight;
	public static WPI_TalonSRX elevatorTalon;
	public static void init(){
		frontLeft = new WPI_TalonSRX(3);
		frontRight = new WPI_TalonSRX(12);
		backLeft = new WPI_TalonSRX(2);
		backRight = new WPI_TalonSRX(6);
		left = new SpeedControllerGroup(frontLeft, backLeft);
		right = new SpeedControllerGroup(frontRight, backRight);
		drive = new DifferentialDrive(left, right);
		drive.setDeadband(0.1);
		drive.setSafetyEnabled(false);
		
		intakeMotorLeft = new WPI_TalonSRX(9); 
		intakeMotorRight = new WPI_TalonSRX(4);
		
		intakePistonLeft = new DoubleSolenoid(5, 4);
		intakePistonRight = new DoubleSolenoid(7, 6);
		
		elevatorTalon = new WPI_TalonSRX(5);
		elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		elevatorTalon.config_kP(0, 0.9, 0);
		elevatorTalon.setInverted(false);
	}
}
