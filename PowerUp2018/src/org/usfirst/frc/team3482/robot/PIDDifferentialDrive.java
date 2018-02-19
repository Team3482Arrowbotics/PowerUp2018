package org.usfirst.frc.team3482.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class PIDDifferentialDrive extends DifferentialDrive implements PIDOutput{
	//turns if true, straight if false
	boolean turning;
	RotationAdjuster rot;
	public PIDDifferentialDrive(SpeedController leftMotor, SpeedController rightMotor, RotationAdjuster r, boolean turn) {
		super(leftMotor, rightMotor);
		turning = turn;
		this.rot = r;
		// TODO Auto-generated constructor stub
	}
	public PIDDifferentialDrive(SpeedController leftMotor, SpeedController rightMotor, RotationAdjuster r) {
		super(leftMotor, rightMotor);
		turning = false;
		this.rot = r;
		// TODO Auto-generated constructor stub
	}
	public void pidWrite(double output) {
		if(turning) {
			arcadeDrive(0, output);
		} else {
			curvatureDrive(-output, rot.adjustment, false);
		}
		
	}
	public boolean isTurning() {
		return turning;
	}
	public void setTurning(boolean turning) {
		this.turning = turning;
	}

}
