package org.usfirst.frc.team3482.robot.subsystems;
import edu.wpi.first.wpilibj.PIDOutput;

public class SpeedPIDOutput implements PIDOutput {

	private double output;
	@Override
	public void pidWrite(double output) {
		this.output=output;
	}
	
	public double getOutput()
	{
		return output;
	}
}
