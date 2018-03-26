package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.commands.Intake;
import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.SetIntakeIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GetBox extends CommandGroup{
	public GetBox() {
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
		addSequential(new Intake(.25));
		addSequential(new SetIntakeIn());
		addParallel(new Intake(.5));
		RobotMap.drive.stopMotor();;
	}
}
