package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.OI;
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
	public static final int AXIS = OI.ELEVATOR_AXIS;
	private double AxisPos;
	public static final double AXIS_DEADZONE = 0.1;
	public static final int MAX_POSITION = 335000, ELEVATOR_SPEED = 25000;
	public static final double ELEVATOR_P_VALUE = 0.2, ELEVATOR_D_VALUE = 0.2, BOTTOM_POSITION = 0, TOP_POSITION = MAX_POSITION,
			BUMP_POSITION = MAX_POSITION * .2, SWITCH_POSITION = MAX_POSITION * .33, SCALE_POSITION = MAX_POSITION * 0.9, MANUAL_UP_SPEED = .8,
			MANUAL_DOWN_SPEED = -.6, ELEVATOR_FALLING_RATIO = 0.55;

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
		//System.out.println("Position: " + getCurrentPos());
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
	}

	public void set(double axisPos) {
		double position = axisPos;

		if(position<0)
		{
			position*=ELEVATOR_FALLING_RATIO;
		}

		position *= ELEVATOR_SPEED;
		position += getCurrentPos();

		if (position > MAX_POSITION) {
			position = MAX_POSITION;
		}

		if (position < 0) {
			position = 0;
		}

		targetPos = position;
	}

	public void absoluteSet(double pos) {
		targetPos = pos;
	}

	public void lock(boolean islocked) {
		targetPos = getCurrentPos();
		locked = islocked;
	}

	public boolean isTop() {
		if (getCurrentPos() > (MAX_POSITION - (MAX_POSITION / 80))) {
			return true;
		}
		return false;
	}

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
		return (1 - ((getCurrentPos() / MAX_POSITION) * 0.85) + 0.15);
	}

	public static double getTurnRatio() {
		return (1 - ((getCurrentPos() / MAX_POSITION) * 0.65) + 0.35);
	}
}
