package org.usfirst.frc.team3482.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class PIDDifferentialDrive extends DifferentialDrive implements PIDOutput {
	// turns if true, straight if false
	boolean turning;
	RotationAdjuster rot;
	public static final double MAX_ACCELERATION = 0.04, MAX_TURN_ACCELERATION = 0.105;
	public double currentSpeed = 0, currentRotation = 0;
	public static boolean enabled = true;

	public PIDDifferentialDrive(SpeedController leftMotor, SpeedController rightMotor, RotationAdjuster r,
			boolean turn) {
		super(leftMotor, rightMotor);
		turning = turn;
		this.rot = r;
	}

	public PIDDifferentialDrive(SpeedController leftMotor, SpeedController rightMotor, RotationAdjuster r) {
		super(leftMotor, rightMotor);
		turning = false;
		this.rot = r;
	}

	public void pidWrite(double output) {
		if (turning) {
			arcadeDrive(0, output);
		} else {
			curvatureDrive(output, rot.adjustment, false);
		}

	}

	public boolean isTurning() {
		return turning;
	}

	public void setTurning(boolean turning) {
		this.turning = turning;
	}

	public void arcadeDrive(double speed, double rotation) {
		System.out.println("arcade driving");
		//Acceleration Control (Prevents bot tipping)
		if (Math.abs(speed - currentSpeed) > MAX_ACCELERATION) {
			if (speed < currentSpeed) {
				speed = currentSpeed - MAX_ACCELERATION;
			} else {
				speed = currentSpeed + MAX_ACCELERATION;
			}
		}
		if (Math.abs(rotation - currentRotation) > MAX_TURN_ACCELERATION) {
			if (rotation < currentRotation) {
				rotation = currentRotation - MAX_TURN_ACCELERATION;
			} else {
				rotation = currentRotation + MAX_TURN_ACCELERATION;
			}
		}
		
		currentSpeed = speed;
		currentRotation = rotation;
		
		//System.out.println("Current Speed: " + currentSpeed);
		
		if(rotation < this.m_deadband && rotation > -m_deadband) {
//			if(turning && speed > m_deadband || speed < -m_deadband) {
//				startAdjusting();
//				turning = false;
//				System.out.println("Does this wonky thing ever run?");
//			}
			curvatureDrive(speed, rot.adjustment, false);
		} else {
			if(!turning) {
				stopAdjusting();
			}
			turning = true;
			super.arcadeDrive(speed, rotation);
		}
	}

	private void stopAdjusting() {
		RobotMap.counteractDrift.disable();
		
	}

//	private void startAdjusting() {
//		RobotMap.counteractDrift.setSetpoint(RobotMap.navx.getYaw());
//		RobotMap.counteractDrift.enable();	
//	}
	
	public void enable(boolean enable)
	{
		enabled = enable;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
}
