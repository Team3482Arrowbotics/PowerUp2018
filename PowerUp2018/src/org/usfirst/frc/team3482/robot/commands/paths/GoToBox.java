package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToBox extends CommandGroup{
	public GoToBox() {
		System.out.println("Getting Box");
		int counter = 0;
		while (RobotMap.intakeLidar.getDistance() > 35) {
			counter++;
			if (counter > 10000) {
				RobotMap.drive.stopMotor();;
				return;
			}
			RobotMap.drive.arcadeDrive(.5, 0);
		}
	}
}
