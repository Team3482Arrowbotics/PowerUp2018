package org.usfirst.frc.team3482.robot.command;

import edu.wpi.first.wpilibj.command.CommandGroup;

//assumes robot is a leftmost position
public class Left extends CommandGroup {
	//going for the switch when plate assignment is at left
	public Left() {
		//Put into switch
		addSequential(new DriveForwardAuto(2,0.5)); //little less than 3 ft on gravel
		addSequential(new GyroTurn(90));
		addSequential(new DriveForwardAuto(2, 0.2));
		//^^forward until "hit" switch wall (or approx. 3-4 in. before it) go tesssttttttt
		//addSequential(new RaiseArms());
		//addSequential(new SpitCube());
		
		//grab another cube
		//step back, 
		addSequential(new DriveForwardAuto(2,-0.5));
		//turn left, 
		//go forward a cube length,
		addSequential(new DriveForwardAuto(2,0.5));
		//turn right, 
		addSequential(new GyroTurn(90));
		//go, 
		addSequential(new DriveForwardAuto(2,0.5));
		//turn right, 
		addSequential(new GyroTurn(90));
		//go forth a bit, 
		addSequential(new DriveForwardAuto(2,0.5));
		//pick up, aka new intake();
		//go back, NOW FACING ALLIANCE WALL 
		//Go to scale ACCORDING to game message
		//scaleLfL
		//scaleRfL
		//VERSUS: getting cub from closer to alliance wall
		addSequential(new DriveForwardAuto(1, -0.75));
		addSequential(new GyroTurn(-90));
		
	}
	public void initialize(){
		System.out.println("Initialized");
	}

}
