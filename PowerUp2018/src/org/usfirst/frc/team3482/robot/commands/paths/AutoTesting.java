package org.usfirst.frc.team3482.robot.commands.paths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTesting extends CommandGroup{
	public AutoTesting(){
		//addSequential(new StartToScale(false));
		//addSequential(new PlaceBoxOnScale(false));
		addSequential(new DoubleSwitchFromLeft());
	}
}
