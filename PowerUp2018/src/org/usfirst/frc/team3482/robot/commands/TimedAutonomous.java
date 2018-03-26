package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.TimedCrossAutonLine;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.TimedNextToSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.TimedToGoal;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedAutonomous extends CommandGroup {
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;

	public TimedAutonomous(boolean switchOnLeft, String sPos) {

		super();
		switch (sPos) {
		case "LEFT":
			System.out.println("Left");
			addSequential(new TimedNextToSwitch());
			if (switchOnLeft) {
				//addSequential(new TimedCrossAutonLine());
				addSequential(new TimedToGoal(fromLeft));
				addSequential(new PlaceBoxOnSwitch());
			}

			
			

			break;

		case "MIDDLE":
			System.out.println("Middle");
			if (switchOnLeft) {
				addSequential(new TimedMiddleBaseline(toLeft));
			} else {
				addSequential(new TimedMiddleBaseline(toRight));

			}
			break;

		case "RIGHT":
			System.out.println("Right");
			if (switchOnLeft) {
				addSequential(new TimedCrossAutonLine());
			} else {
				addSequential(new TimedNextToSwitch());
				addSequential(new TimedToGoal(fromRight));
				addSequential(new PlaceBoxOnSwitch());
			}
			break;
		}
	}
}
