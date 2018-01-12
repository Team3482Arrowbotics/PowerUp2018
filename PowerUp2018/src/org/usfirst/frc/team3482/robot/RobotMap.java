package org.usfirst.frc.team3482.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {
	public static WPI_TalonSRX frontLeft;
	public static WPI_TalonSRX frontRight;
	public static WPI_TalonSRX backLeft;
	public static WPI_TalonSRX backRight;
	public static WPI_TalonSRX intakeMotor;
	public static DifferentialDrive drive;
	public static SpeedControllerGroup left;
	public static SpeedControllerGroup right;
	public static void init(){
		frontLeft = new WPI_TalonSRX(5);
		frontRight = new WPI_TalonSRX(3);
		backLeft = new WPI_TalonSRX(0);
		backRight = new WPI_TalonSRX(12);
		intakeMotor = new WPI_TalonSRX(0);
		left = new SpeedControllerGroup(frontLeft, backLeft);
		right = new SpeedControllerGroup(frontRight, backRight);
		drive = new DifferentialDrive(left, right);
		drive.setDeadband(0.1);
	}
}
