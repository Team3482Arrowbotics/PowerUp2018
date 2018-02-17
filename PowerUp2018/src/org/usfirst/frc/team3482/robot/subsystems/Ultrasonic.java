package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ultrasonic extends AnalogInput {
	public static final double BOX_THRESHOLD = 1.0;

	public Ultrasonic(int channel) {
		super(channel);
	}

	public boolean goToBox() {
		int counter = 0;
		while (getVoltage() < BOX_THRESHOLD) {
			counter++;
			if (counter > 2000) {
				RobotMap.drive.arcadeDrive(0, 0);
				return true;
			}
			RobotMap.drive.arcadeDrive(-.5, 0);
		}
		RobotMap.drive.arcadeDrive(0, 0);
		return false;

	}

}
