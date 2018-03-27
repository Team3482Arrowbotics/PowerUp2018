package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.commands.Intake;
import org.usfirst.frc.team3482.robot.commands.SetIntakeIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PickUpBox extends CommandGroup{
	
	public PickUpBox() {
		
		addSequential(new Intake(.25));
		addSequential(new SetIntakeIn());
		addParallel(new Intake(.5));
		RobotMap.drive.stopMotor();
	}
}
