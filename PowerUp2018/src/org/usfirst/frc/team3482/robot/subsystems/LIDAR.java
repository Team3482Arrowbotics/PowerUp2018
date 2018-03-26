package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.PWMChannel;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LIDAR extends Subsystem{
	
	CANifier c;
	PWMChannel channel;
	public LIDAR(CANifier c, PWMChannel channel) {
		this.c = c;
		this.channel = channel;
	}
	public double getDistance() {
		double[] data = new double[2];
		c.getPWMInput(channel, data);
		return data[0];
	}
	public boolean findFirstBox(boolean backwards, int distance) {
		System.out.println("Finding Box");
		int counter = 0;
		if(backwards) {
			while(getDistance() > distance) {
				counter ++; 
				if(counter > 10000) {
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
	public void goToBox() {
		int counter = 0;
		while(getDistance() > 200) {
			counter ++; 
			if(counter > 2000) {
				RobotMap.drive.arcadeDrive(0, 0);
				return;
			}
			RobotMap.drive.arcadeDrive(.7, 0);
		}
		RobotMap.drive.arcadeDrive(0, 0);
	}
	@Override
	protected void initDefaultCommand() {		
	}

}
