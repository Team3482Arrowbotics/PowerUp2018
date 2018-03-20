package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem implements Runnable {
	//FIELDS
	protected static WPI_TalonSRX elevatorTalon;
	protected static WPI_TalonSRX elevatorTalon2;
	protected static double targetPos;
	public static final int AXIS = Robot.oi.ELEVATOR_AXIS; 
	private double AxisPos;
	public static final double AXIS_DEADZONE = 0.05;
	public static final int MAX_POSITION = 515000, ELEVATOR_SPEED = 20000;
	public static final double	ELEVATOR_P_VALUE = 0.1;

	//15:1 Gearbox values: Max = 515000 P = 0.1, Speed = 20000
	
	//Old setup: Max = 33750 P = 1.5 Speed = 1000

	public static boolean locked;

	// CONSTRUCTOR
	public Elevator() {
		elevatorTalon = RobotMap.elevatorTalon;
		elevatorTalon2 = RobotMap.elevatorTalon2;
		locked=true;
		resetEncoder();
	}
	
	@Override
	protected void initDefaultCommand() {

	}

	//RUNS EVERY TICK (see teleopPeriodic)
	public void run() {

		AxisPos = -Robot.oi.flightStick.getRawAxis(AXIS);

		if(AxisPos > AXIS_DEADZONE || AxisPos < -AXIS_DEADZONE) {
			changePosition(AxisPos);
		}

		//System.out.println("Set Pos: " + targetPos);
		
		if(locked)
		{
			elevatorTalon.set(ControlMode.Position, targetPos);
			elevatorTalon2.set(ControlMode.PercentOutput, -elevatorTalon.getMotorOutputPercent());
			
			//System.out.println("Position: " + getCurrentPos() + " Error: " + RobotMap.elevatorTalon.getClosedLoopError(0));
			//System.out.println("Velocity: "+getCurrentVelocity());
			//System.out.println("Percent Output Motor 1: "+elevatorTalon.getMotorOutputPercent());
			//System.out.println("Percent Output Motor 2: "+elevatorTalon2.getMotorOutputPercent());
			//System.out.println("Speed Ratio: "+ getSpeedRatio());
			//System.out.println("Turn Ratio: "+ getTurnRatio());
		}
	}
	
	public void resetEncoder()
	{
		RobotMap.elevatorTalon.setSelectedSensorPosition(0, 0, 0);
		targetPos = 0;
		
		//SOMETIMES ENCODER VALUES FLIP WHEN SWAPPING MOTORS
		//IF BOTTOM IS MAX AND TOP IS 0, USE THIS
		
		//RobotMap.elevatorTalon.setSelectedSensorPosition(MAX_POSITION, 0, 0);
		//targetPos = MAX_POSITION;
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
		
		//IF ENCODER VALUES FLIP (TOP IS 0, BOTTOM IS MAX)
		//return ((getCurrentPos() / MAX_POSITION) * 0.75) + 0.25;
		
		return (1-((getCurrentPos() / MAX_POSITION) * 0.75));
	}
	
	public static double getTurnRatio()
	{
		//IF ENCODER VALUES FLIP (TOP IS 0, BOTTOM IS MAX)
		//return ((getCurrentPos() / MAX_POSITION) * 0.5) + 0.5;
		
		return (1-((getCurrentPos() / MAX_POSITION) * 0.5));
	}

	//SET ELEVATOR POSITION
	public void set(double pos, boolean relative) {
		if(relative) {
			//System.out.println("Target Position Before: " + (getCurrentPos() + pos));
			pos+=getCurrentPos();
		}
		
		if(pos > MAX_POSITION) {
			pos = MAX_POSITION;
		}
		
		if(pos < 0) {
			pos = 0;
		}
		
		targetPos = pos;
	}

	public void set(double pos) {
		set(pos, false);
	}

	public void changePosition(double axisPos) {
		System.out.println("Axis Output" + axisPos);
		//System.out.println("Motor Output: " + elevatorTalon.getMotorOutputPercent());
		set(axisPos * ELEVATOR_SPEED, true); // add true when suing original function
	}

	public void lock(boolean islocked) {
		targetPos=getCurrentPos();
		locked=islocked;
	}
}
