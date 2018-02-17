package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem implements Runnable {
	//FIELDS
	protected static WPI_TalonSRX elevatorTalon;
	protected static double targetPos, maxPos, minPos;
	protected static int currentStage, numStages;
	public static final int BOTTOM_POSITION = 0, TOP_POSITION = 36000, SWITCH_POSITION = 8000, SCALE_POSITION = 33000;
	public static final int THRESHOLD_HEIGHT = TOP_POSITION-4000;
	public static boolean locked;

	// CONSTRUCTOR
	public Elevator() {
		elevatorTalon = RobotMap.elevatorTalon;
		targetPos = BOTTOM_POSITION;
		currentStage = 0;
		minPos = BOTTOM_POSITION;
		maxPos = TOP_POSITION;
		locked=true;
	}
	@Override
	protected void initDefaultCommand() {
		
	}
	
	// GETTERS AND SETTERS
	public static double getCurrentPos() {
		return elevatorTalon.getSelectedSensorPosition(0);
	}

	public static double getTargetPos() {
		return targetPos;
	}
	
	private static void setTargetPos(double tPos) {
		//limit movement within MIN_POSITION and MAX_POSITION
		if(tPos > maxPos) {
			tPos = maxPos;
		}
		if(tPos < minPos) {
			tPos = minPos;
		}
		targetPos = tPos;
		//System.out.println("Target Position After: " + targetPos);
	}
	//RUNS EVERY TICK (see teleopPeriodic)
	public void run() {
		if(locked)
		{
			elevatorTalon.set(ControlMode.Position, targetPos);
		}
	}
	
	//SET ELEVATOR POSITION
	public void set(double pos, boolean relative) {
		if(relative) {
			setTargetPos(getCurrentPos() + pos);
			return;
		}
		setTargetPos(pos);
	}
	
	public void set(double pos) {
		set(pos, false);
	}
	//MOVE ELEVATOR BY A*SPEED ENCODER TICKS EVERY ROBOT TICK
	//!!!!!!!!!!!!!!!!SPEED CAN BE NEGATIVE!!!!!!!!!!!!!!!!!!!!
	public void changePosition(double deltaPos, int axis) {
		double a = Robot.oi.x.getRawAxis(axis);
		System.out.println("Motor Output: " + elevatorTalon.getMotorOutputPercent());
		set(a * deltaPos, true);
	}
	
	public void lock(boolean islocked) {
		targetPos=getCurrentPos();
		locked=islocked;
	}
}
