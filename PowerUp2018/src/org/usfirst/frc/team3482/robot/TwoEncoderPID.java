package org.usfirst.frc.team3482.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class TwoEncoderPID implements PIDSource{
	Encoder left, right;
	PIDSourceType type;
	boolean distance;
	public TwoEncoderPID(Encoder left, Encoder right, String type) {
		this.left = left;
		this.right = right;
		if(type.equals("Distance"))
		{
			this.type = PIDSourceType.kDisplacement;
			distance = true;
		}
		else if(type.equals("Speed"))
		{
			this.type = PIDSourceType.kRate;
			distance = false;
		}
	}
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		pidSource = type;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return type;
	}

	@Override
	public double pidGet() {
		if(distance)
		{
			return (left.getDistance()) + (right.getDistance())/2;
		}
		else
			return (left.getRate()) + (right.getRate())/2;
	}
	
	public void reset() {
		left.reset();
		right.reset();
	}
	
}
