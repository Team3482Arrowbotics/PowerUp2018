package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem implements Runnable {
	// FIELDS
	protected static WPI_TalonSRX elevatorTalon;
	protected static WPI_TalonSRX elevatorTalon2;
	protected static double targetPos;
	protected static double absolutePos;
	protected static double absoluteTarget;
	public static final int AXIS = Robot.oi.ELEVATOR_AXIS;
	private double AxisPos;
	public static final double AXIS_DEADZONE = 0.05;
	public static final int MAX_POSITION = 335000, ELEVATOR_SPEED = 25000;
	public static final double ELEVATOR_P_VALUE = 0.2, BOTTOM_POSITION = 0, TOP_POSITION = MAX_POSITION,
			SWITCH_POSITION = MAX_POSITION * .33, SCALE_POSITION = MAX_POSITION * 0.9, MANUAL_UP_SPEED = .4,
			MANUAL_DOWN_SPEED = -.2, ELEVATOR_FALLING_RATIO = 0.6;
	
	// 15:1 Gearbox Big Spool values: Max = 515000 P = 0.1, Speed = 20000
	// 15:1 Small Spool Max = 665000 Speed = 50000
	// 10:1 Big Spool Max = 335000 Speed = 20000
	//10:1 Fat Spool Max = 280000 Speed = 12000 P = .15
	// Old setup: Max = 33750 P = 1.5 Speed = 1000

	public static boolean locked;

	// CONSTRUCTOR
	public Elevator() {
		elevatorTalon = RobotMap.elevatorTalon;
		elevatorTalon2 = RobotMap.elevatorTalon2;
		locked = true;
		resetEncoder();
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void teleopRun()
	{
		AxisPos = -Robot.oi.flightStick.getRawAxis(AXIS);

		if (AxisPos > AXIS_DEADZONE) {
			Robot.isEMovingUp = true;
			Robot.isEMovingDown = false;
			set(AxisPos);
		} else if (AxisPos < -AXIS_DEADZONE) {
			Robot.isEMovingUp = false;
			Robot.isEMovingDown = true;
			set(AxisPos * ELEVATOR_FALLING_RATIO);
		} else {
			Robot.isEMovingUp = false;
			Robot.isEMovingDown = false;
		}
		
		
		run();
	}
	// RUNS EVERY TICK (see teleopPeriodic)
	public void run() {
		
		if (locked) {
			elevatorTalon.set(ControlMode.Position, targetPos);
		}

		elevatorTalon2.set(ControlMode.PercentOutput, -elevatorTalon.getMotorOutputPercent());

		// System.out.println("Set Pos: " + targetPos);
		System.out.println("Position: " + getCurrentPos());
		// RobotMap.elevatorTalon.getClosedLoopError(0));
		// System.out.println("Velocity: "+getCurrentVelocity());
		//System.out.println("Percent Output Motor 1: " + elevatorTalon.getMotorOutputPercent());
		// System.out.println("Percent Output Motor 2:
		// "+elevatorTalon2.getMotorOutputPercent());
		// System.out.println("Speed Ratio: "+ getSpeedRatio());
		// System.out.println("Turn Ratio: "+ getTurnRatio());
	}

	public void resetEncoder() {
		RobotMap.elevatorTalon.setSelectedSensorPosition(0, 0, 0);
		targetPos = 0;

		// SOMETIMES ENCODER VALUES FLIP WHEN SWAPPING MOTORS
		// IF BOTTOM IS MAX AND TOP IS 0, USE THIS

		// RobotMap.elevatorTalon.setSelectedSensorPosition(MAX_POSITION, 0, 0);
		// targetPos = MAX_POSITION;
	}

	// SET ELEVATOR POSITION
	public void set(double axisPos) {
		double axisRatio = axisPos;

		if(axisRatio<0)
		{
			axisRatio*=ELEVATOR_FALLING_RATIO;
		}
		
		axisRatio *= ELEVATOR_SPEED;
		axisRatio += getCurrentPos();

		if (axisRatio > MAX_POSITION) {
			axisRatio = MAX_POSITION;
		}

		if (axisRatio < 0) {
			axisRatio = 0;
		}

		targetPos = axisRatio;
	}

	public void autonomousSet(double pos) {
		//System.out.println(elevatorTalon.configPeakOutputReverse(ELEVATOR_FALLING_RATIO, 1));
		targetPos = pos;
	}
	
//	public void absoluteSet(double pos) {
//		absolutePos = pos;
//
//		while (absolutePos - getCurrentPos() > 500) {
//
//			if (absolutePos - getCurrentPos() > 0) {
//				absoluteTarget = set(1);
//			}
//			
//			if (absolutePos - getCurrentPos() < 0) {
//				absoluteTarget = set(-ELEVATOR_FALLING_RATIO);
//			}
//
//			elevatorTalon.set(ControlMode.Position, absoluteTarget);
//			elevatorTalon2.set(ControlMode.PercentOutput, -elevatorTalon.getMotorOutputPercent());
//		}
//		targetPos = getCurrentPos();
//	}

	public void lock(boolean islocked) {
		targetPos = getCurrentPos();
		locked = islocked;
	}

	public boolean isTop() {
		if (getCurrentPos() > (MAX_POSITION - (MAX_POSITION / 100))) {
			return true;
		}
		return false;
	}

	// GETTERS AND SETTERS
	public static double getCurrentPos() {
		return elevatorTalon.getSelectedSensorPosition(0);
	}

	public static double getCurrentVelocity() {
		return elevatorTalon.getSelectedSensorVelocity(0);
	}

	public static double getTargetPos() {
		return targetPos;
	}

	public static double getSpeedRatio() {

		// IF ENCODER VALUES FLIP (TOP IS 0, BOTTOM IS MAX)
		// return ((getCurrentPos() / MAX_POSITION) * 0.75) + 0.25;

		return (1 - ((getCurrentPos() / MAX_POSITION) * 0.75));
	}

	public static double getTurnRatio() {
		// IF ENCODER VALUES FLIP (TOP IS 0, BOTTOM IS MAX)
		// return ((getCurrentPos() / MAX_POSITION) * 0.5) + 0.5;

		return (1 - ((getCurrentPos() / MAX_POSITION) * 0.5));
	}
}
