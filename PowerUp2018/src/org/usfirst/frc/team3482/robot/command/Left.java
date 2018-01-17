package org.usfirst.frc.team3482.robot.command;

import edu.wpi.first.wpilibj.command.CommandGroup;

//assumes robot is a leftmost position
public class Left extends CommandGroup {
	//going for the switch when plate assignment is at left
	public Left() {
		addSequential(new DriveForwardAuto(5,0.5));
		addSequential(new GyroTurn(90));
		//addSequential(new RaiseArms());
		//addSequential(new SpitCube());
	}

}
