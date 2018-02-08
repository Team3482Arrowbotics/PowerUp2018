package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem implements Runnable {
	//FIELDS
	protected static WPI_TalonSRX talon;
	protected static double targetPos, maxPos, minPos;
	protected static int currentStage, numStages;
	public static final int[] STAGE_POSITIONS = { 0, 2, 4, 6, 8 };
	public static final int MAX_POSITION = 1000000, MIN_POSITION = 0;

	// CONSTRUCTOR
	public Elevator() {
		talon = RobotMap.elevatorTalon;
		targetPos = 0;
		currentStage = 0;
		numStages = STAGE_POSITIONS.length;
		maxPos = MAX_POSITION;
		minPos = MIN_POSITION;
	}
	@Override
	protected void initDefaultCommand() {
		
	}
	
	// GETTERS AND SETTERS
	public static double getCurrentPos() {
		return talon.getSelectedSensorPosition(0);
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
		Elevator.targetPos = tPos;
	}
	//RUNS EVERY TICK (see teleopPeriodic)
	public void run() {
		talon.set(ControlMode.Position, targetPos);
	}
	//SET ELEVATOR POSITION
	public void set(double pos, boolean relative) {
		if(relative) {
			setTargetPos(getCurrentPos() + pos);
		}
		setTargetPos(pos);
	}
	
	public void set(double pos) {
		set(pos, false);
	}
	//MOVE ELEVATOR BY A*SPEED ENCODER TICKS EVERY ROBOT TICK
	//!!!!!!!!!!!!!!!!SPEED CAN BE NEGATIVE!!!!!!!!!!!!!!!!!!!!
	public void manualMove(double speed, int axis) {
		double a = Robot.oi.x.getRawAxis(axis);
		set(a * speed, true);
	}

}
