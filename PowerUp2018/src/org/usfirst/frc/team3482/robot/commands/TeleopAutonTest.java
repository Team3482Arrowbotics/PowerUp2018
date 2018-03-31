package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopAutonTest extends Command{
	boolean finished = false;
	double counter;
	double timeout;
	public TeleopAutonTest(double timeout) {
		super(timeout);
		this.timeout = timeout;
		counter = 0;
	}
	public TeleopAutonTest() {
		super();
		timeout = Integer.MAX_VALUE;
		counter = 0;
	}
	protected void initialize(){
		System.out.println("TeleopAutonTest Called");
		new TestAutonomous().start();
	}
	
	protected void execute() {
		//Robot.ledStrip.flash("white", 0.1);
		System.out.println("Testing Teleop Auton");
		counter++;
		System.out.println("Count " + counter);
		System.out.println("Timeout " + timeout);
	}
	
	protected void end(){
		System.out.println("Teleop Auton Finished");
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished || isTimedOut() || counter >= timeout;
	}

}
