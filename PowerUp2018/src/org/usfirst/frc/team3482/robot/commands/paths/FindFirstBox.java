package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FindFirstBox extends CommandGroup{
	int direction = 1;
	public FindFirstBox(boolean fromRight) {
		System.out.println("Finding Box");
		int counter = 0;
		if(!fromRight) {
			direction = -1;
		}
		System.out.println("Distance: "+Robot.sideLidar.getDistance());
		while(Robot.sideLidar.getDistance() > 500) {
			//counter ++; 
			//if(counter > 10000) {
				RobotMap.drive.arcadeDrive(0, 0);
			//}
			RobotMap.drive.arcadeDrive(direction * .5, 0);
		}
		RobotMap.drive.arcadeDrive(0, 0);
	}
}
