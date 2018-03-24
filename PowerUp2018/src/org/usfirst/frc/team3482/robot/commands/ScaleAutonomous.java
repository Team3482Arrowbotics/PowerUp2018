package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.AcrossBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.AcrossBaselineToScale;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnScale;
import org.usfirst.frc.team3482.robot.commands.paths.StartToScale;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleBaseline;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleAutonomous extends CommandGroup{
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	public ScaleAutonomous(boolean switchOnLeft, String sPos) {

		super();
		switch(sPos) {
		case "LEFT":
			System.out.println("Left");
			if(!switchOnLeft) {
				addSequential(new StartToScale());
				addSequential(new AcrossBaselineToScale(fromLeft));
				addSequential(new PlaceBoxOnScale(fromRight));
			}
			else {
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromLeft));
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
				addSequential(new StartToScale());
				addSequential(new AcrossBaselineToScale(fromRight));
				addSequential(new PlaceBoxOnScale(fromLeft));
			}
			else {
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromRight));
			}
			break;
		}
	}
}
