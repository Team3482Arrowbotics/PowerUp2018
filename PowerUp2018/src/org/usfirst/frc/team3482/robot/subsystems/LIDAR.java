package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.phoenix.CANifier;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LIDAR extends Subsystem{
	
	CANifier c;
	public LIDAR() {
		c = RobotMap.c;
	}
	public double getDistance() {
		double[] data = new double[2];
		c.getPWMInput(CANifier.PWMChannel.PWMChannel0, data);
		return data[0];
	}
	public boolean findFirstBox(boolean backwards, int distance) {
		int counter = 0;
		if(backwards) {
			while(getDistance() > distance) {
				counter ++; 
				if(counter > 2000) {
					RobotMap.drive.arcadeDrive(0, 0);
					return true;
				}
				RobotMap.drive.arcadeDrive(-.7, 0);
			}
		} else {
			while(getDistance() > distance) {
				counter ++; 
				if(counter > 2000) {
					RobotMap.drive.arcadeDrive(0, 0);
					return true;
				}
				RobotMap.drive.arcadeDrive(.7, 0);
			}
		}
		RobotMap.drive.arcadeDrive(0, 0);
		return false;
	}
	public void findNextBox(boolean backwards, int distance) {
		while(getDistance() <= distance) {
			if(backwards) {
				RobotMap.drive.arcadeDrive(-.7, 0);
			} else {
				RobotMap.drive.arcadeDrive(.7, 0);
			}
		}
		while(getDistance() > distance) {
			if(backwards) {
				RobotMap.drive.arcadeDrive(-.7, 0);
			} else {
				RobotMap.drive.arcadeDrive(.7, 0);
			}
		}
		
	}
	@Override
	protected void initDefaultCommand() {		
	}

}
