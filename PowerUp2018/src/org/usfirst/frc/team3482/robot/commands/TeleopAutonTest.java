package org.usfirst.frc.team3482.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopAutonTest extends Command{
	boolean finished = false;
	public TeleopAutonTest(double timeout) {
		super(timeout);
	}
	public TeleopAutonTest() {
		super();
	}
	protected void initialize(){
		System.out.println("TeleopAutonTest Called");
		new TestAutonomous().start();
	}
	
	protected void execute() {
		//Robot.ledStrip.flash("white", 0.1);
	}
	
	protected void end(){

	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished || isTimedOut();
	}

}
