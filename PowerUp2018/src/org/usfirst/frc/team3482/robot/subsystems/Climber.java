package org.usfirst.frc.team3482.robot.subsystems;
import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem implements Runnable {

	//FIELDS
	protected static WPI_TalonSRX climber;
	protected static WPI_TalonSRX climberHook;
	public static final int AXIS = Robot.oi.CLIMBER_AXIS;
	private double AxisPos = 0;
	public static final double AXIS_DEADZONE = 0.25;

	// CONSTRUCTOR
	public Climber() {
		climber = RobotMap.climber;
		climberHook = RobotMap.climberHook;
	}
	
	@Override
	protected void initDefaultCommand() {

	}

	//RUNS EVERY TICK (see teleopPeriodic)
	public void run() {

		AxisPos = -Robot.oi.flightStick.getRawAxis(AXIS);
		
		if(AxisPos > AXIS_DEADZONE || AxisPos < -AXIS_DEADZONE) {
			Robot.isClimberHook=true;
			spinHook(AxisPos);
		}
		else {
			Robot.isClimberHook=false;
			spinHook(0);
		}
		
	}
	
	public void spinHook(double axisPos) {
		climberHook.set(-axisPos * 0.3);
		System.out.println("Hook Spinning "+axisPos);
	}
	
	public void climb()
	{
		climber.set(-1.0);
		Robot.isClimberHook = true;
	}
	
	public void reverseClimb()
	{
		climber.set(1.0);
		Robot.isClimberHook = true;
	}
	
	public void stop()
	{
		climber.set(0);
		Robot.isClimberHook = false;
	}

}
