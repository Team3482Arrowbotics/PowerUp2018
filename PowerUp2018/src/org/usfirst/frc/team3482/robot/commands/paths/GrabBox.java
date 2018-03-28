package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.commands.Intake;
import org.usfirst.frc.team3482.robot.commands.SetIntakeIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabBox extends CommandGroup{
	
	public GrabBox() {
		addSequential(new Intake(.1));
		addSequential(new SetIntakeIn());
		addParallel(new Intake(.2));
		RobotMap.drive.stopMotor();
	}
}
