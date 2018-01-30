package org.usfirst.frc.team3482.robot.command;

import edu.wpi.first.wpilibj.command.CommandGroup;

//assumes robot is at leftmost position
public class Right extends CommandGroup {
	//going for the switch when plate assignment is at right
	public Right() {
		addSequential(new DriveForwardAuto(8.5, 0.5));
		addSequential(new GyroTurn(90));
		addSequential(new DriveForwardAuto(5,0.5));
		//addSequential(new RaiseArms());
		//addSequential(new SpitCube());
		//step back, 
		//turn right, 
		//go forward, 
		//turn left, 
		//go, 
		//turn left, 
		//go forth a bit, 
		//pick up, 
		//go back 
		//scaleLfR OR
		//scaleRfR
		//Go to scale ACCORDING to game message
		//CONSIDER: getting cube from closer to alliance wall
	}
}
