package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FindBox extends CommandGroup{
	int direction = 1;
	public FindBox(boolean fromRight) {
//		System.out.println("Finding Box");
		int counter = 0;
		if(!fromRight) {
			direction = -1;
		}
		System.out.println("Distance: "+RobotMap.sideLidar.getDistance());
		while(RobotMap.sideLidar.getDistance() > 500 && counter < 100000) {
			counter ++; 
			RobotMap.drive.arcadeDrive(direction * .5, 0);
		}
		RobotMap.drive.arcadeDrive(0, 0);
	}
}
