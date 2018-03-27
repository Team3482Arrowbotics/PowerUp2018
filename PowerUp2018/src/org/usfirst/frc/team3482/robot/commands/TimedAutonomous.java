package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.TimedCrossAutonLine;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.NextToSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.SidePushToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedAutonomous extends CommandGroup {
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;

	public TimedAutonomous(boolean switchOnLeft, String sPos) {

		super();
		switch (sPos) {
		case "LEFT":
			System.out.println("Left");
			addSequential(new NextToSwitch());
			if (switchOnLeft) {
				//addSequential(new TimedCrossAutonLine());
				addSequential(new SidePushToSwitch(fromLeft));
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
				addSequential(new NextToSwitch());
				addSequential(new SidePushToSwitch(fromRight));
				addSequential(new PlaceBoxOnSwitch());
			}
			break;
		}
	}
}
