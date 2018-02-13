package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDOutput;

public class DrivePIDOutput implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		RobotMap.drive.arcadeDrive(output, 0);
		// TODO Auto-generated method stub
		
	}

}
