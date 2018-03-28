package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.commands.Turn;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToBox extends CommandGroup{
	public GoToBox() {
		System.out.println("Getting Box");
		int counter = 0;
		addSequential(new SetElevatorPosition(Elevator.BOTTOM_POSITION));
		addSequential(new Turn(90));
		while (RobotMap.intakeLidar.getDistance() > 35 && counter < 100000) {
			counter++;
			RobotMap.drive.arcadeDrive(.5, 0);
		}
	}
}
