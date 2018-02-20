package org.usfirst.frc.team3482.robot.commands.paths;

import org.usfirst.frc.team3482.robot.commands.Move;
import org.usfirst.frc.team3482.robot.commands.SwitchIntake;
import org.usfirst.frc.team3482.robot.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoubleSwitchFromLeft extends CommandGroup{
	public DoubleSwitchFromLeft(){
		addSequential(new Move(155));
		addSequential(new Turn(90));
		addSequential(new PlaceBoxOnSwitch());
		addSequential(new Turn(-90));
		addSequential(new Move(80), 0.6);
		addSequential(new SwitchIntake());
		addSequential(new Turn(90));
		addSequential(new FindBox());
	}
}
