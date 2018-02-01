package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
	private DoubleSolenoid leftPiston;
	private DoubleSolenoid rightPiston;
	public Intake(){
		leftPiston = RobotMap.intakePistonLeft;
		rightPiston = RobotMap.intakePistonRight;
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	public void switchIntakePistons(){
		if(leftPiston.get() == Value.kForward){
			leftPiston.set(Value.kReverse);
		} else {
			leftPiston.set(Value.kForward);
		}
		if(rightPiston.get() == Value.kForward){
			rightPiston.set(Value.kReverse);
		} else {
			rightPiston.set(Value.kForward);
		}
	}
}
