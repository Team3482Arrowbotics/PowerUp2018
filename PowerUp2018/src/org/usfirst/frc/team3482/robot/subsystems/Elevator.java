package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {
	public static WPI_TalonSRX talon;
	public static int position, maxPosition, minPosition;
	public static int[] positions = { 0, 2, 4, 6, 8 };
	private static final int ROTATIONS_PER_STAGE = 3;
	boolean locked;

	public boolean isLocked() {
		return locked;
	}

	// enable closed-loop control
	public void lock() {
		this.locked = true;
	}

	// disable closed-loop control
	public void unlock() {
		locked = false;
	}

	public Elevator() {
		position = 0;
		maxPosition = 4;
		minPosition = 0;
		talon = RobotMap.elevatorTalon;
	}

	public static int getPosition() {
		return position;
	}

	public static void setPosition(int pos) {
		// limit position between minPosition and maxPosition to prevent unwanted
		// elevator movement
		if (pos > maxPosition) {
			pos = maxPosition;
		}
		if (pos < minPosition) {
			pos = minPosition;
		}
		position = pos;
		System.out.println("Set elevator position to " + position);
	}

	public void moveUp() {
		setPosition(position + 1);
	}

	public void moveDown() {
		setPosition(position - 1);
	}

	// use closed-loop control to set elevator top certain position
	public void moveElevator() {
		talon.set(ControlMode.Position, positions[position] * 4096 * ROTATIONS_PER_STAGE);
	}
	public void moveElevatorManual() {
		RobotMap.elevatorTalon.set(Robot.oi.x.getRawAxis(5) * 0.5);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
