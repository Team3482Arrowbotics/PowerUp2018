package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.TimedNextToSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.TimedToGoal;
import org.usfirst.frc.team3482.robot.commands.paths.ToOppositeSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SwitchAutonomous extends CommandGroup{
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	public SwitchAutonomous(boolean switchOnLeft, String sPos) {

		super();
		switch(sPos) {
		case "LEFT":
			System.out.println("Left");
			if(!switchOnLeft) {
				addSequential(new WaitCommand(5));
				addSequential(new ToOppositeSwitch(fromLeft));
				addSequential(new PlaceBoxOnSwitch());
			}
			else {
				addSequential(new TimedNextToSwitch());
				addSequential(new TimedToGoal(fromLeft));
				addSequential(new PlaceBoxOnSwitch());
			}
			break;

		case "MIDDLE":
			System.out.println("Middle");
			if(switchOnLeft) {
				addSequential(new TimedMiddleBaseline(toLeft));
			}
			else {
				addSequential(new TimedMiddleBaseline(toRight));
			}
			break;

		case "RIGHT":
			System.out.println("Right");
			if(switchOnLeft) {
				addSequential(new WaitCommand(5));
				addSequential(new ToOppositeSwitch(fromRight));
				addSequential(new PlaceBoxOnSwitch());
			}
			else {
				addSequential(new TimedNextToSwitch());
				addSequential(new TimedToGoal(fromRight));
				addSequential(new PlaceBoxOnSwitch());
			}
			break;
		}
	}
}
