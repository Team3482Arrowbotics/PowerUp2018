package org.usfirst.frc.team3482.robot.command;

import edu.wpi.first.wpilibj.command.CommandGroup;

//assumes robot is at leftmost position
public class Right extends CommandGroup {
	//going for the switch when plate assignment is at right
	public Right() {
		addSequential(new DriveForwardAuto(6, 0.5));
		addSequential(new GyroTurn(90));
		addSequential(new DriveForwardAuto(5,0.5));
		//addSequential(new RaiseArms());
		//addSequential(new SpitCube());
	}
}
