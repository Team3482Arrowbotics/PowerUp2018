package org.usfirst.frc.team3482.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class RotationAdjuster implements PIDOutput{
	double adjustment;
	@Override
	public void pidWrite(double output) {
		adjustment = output;
	}

}