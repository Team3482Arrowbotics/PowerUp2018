package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.CrossFieldToScaleLane;
import org.usfirst.frc.team3482.robot.commands.paths.PassSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.PastSwitchToScale;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnScale;
import org.usfirst.frc.team3482.robot.commands.paths.StartToScale;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleAutonomous extends CommandGroup{
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	public ScaleAutonomous(boolean scaleOnLeft, String sPos) {
		super();
		switch(sPos) {
		case "LEFT":
			System.out.println("Left");
			if(!scaleOnLeft) { //Scale on right
				addSequential(new PassSwitch());
				addSequential(new CrossFieldToScaleLane(fromLeft));
				addSequential(new PastSwitchToScale());
				addSequential(new PlaceBoxOnScale(fromRight));
			}
			else { //Scale on left
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromLeft));
			}
			break;

//		case "MIDDLE":
//			System.out.println("Middle");
//			if(scaleOnLeft) {
//				addSequential(new TimedMiddleBaseline(toLeft));
//			}
//			else {
//				addSequential(new TimedMiddleBaseline(toRight));
//			}
//			break;

		case "RIGHT":
			System.out.println("Right");
			if(scaleOnLeft) {
				addSequential(new PassSwitch());
				addSequential(new CrossFieldToScaleLane(fromRight));
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
