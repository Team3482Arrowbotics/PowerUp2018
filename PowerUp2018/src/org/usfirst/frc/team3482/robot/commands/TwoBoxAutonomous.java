package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.AcrossBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.AcrossBaselineFromMiddle;
import org.usfirst.frc.team3482.robot.commands.paths.AcrossSwitchLane;
import org.usfirst.frc.team3482.robot.commands.paths.AutoLineToScale;
import org.usfirst.frc.team3482.robot.commands.paths.FindBox;
import org.usfirst.frc.team3482.robot.commands.paths.GetBox;
import org.usfirst.frc.team3482.robot.commands.paths.MiddleToAutoLine;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnScale;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.ScaleToSameSideBox;
import org.usfirst.frc.team3482.robot.commands.paths.StartPosition;
import org.usfirst.frc.team3482.robot.commands.paths.StartToOppoAutoLine;
import org.usfirst.frc.team3482.robot.commands.paths.StartToScale;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBoxAutonomous extends CommandGroup{

	boolean fromRight = true, fromLeft = false, 
			toRight = true, toLeft = false;

	StartPosition sPos;
	public TwoBoxAutonomous(boolean switchOnLeft, boolean scaleOnLeft, String sPos) {
		super();
		addSequential(new Move(6));
		switch(sPos) {
		case "LEFT":
			if(scaleOnLeft) {
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromLeft));
				addSequential(new ScaleToSameSideBox(fromLeft));
				if(switchOnLeft) {
					addSequential(new FindBox(fromLeft));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				} else {
					addSequential(new AcrossSwitchLane(fromLeft));
					addSequential(new FindBox(fromRight));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				}
			} else {

				addSequential(new AcrossBaseline(fromLeft));
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(true));
				addSequential(new ScaleToSameSideBox(true));
				if(switchOnLeft) {
					addSequential(new AcrossSwitchLane(true));
					addSequential(new FindBox());
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				} else {
					addSequential(new FindBox(true));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				}
			}
			break;

		case "MIDDLE":
			if(scaleOnLeft) {

				addSequential(new AcrossBaselineFromMiddle(toLeft));
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromLeft));
				addSequential(new ScaleToSameSideBox(fromLeft));

				if(switchOnLeft) {
					addSequential(new FindBox(fromLeft));

				} else {
					addSequential(new AcrossSwitchLane(fromLeft));
					addSequential(new FindBox(fromRight));
				}
				addSequential(new GetBox());
				addSequential(new PlaceBoxOnSwitch());

			} else {
				addSequential(new AcrossBaselineFromMiddle(toRight));

				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));

				if(switchOnLeft) {
					addSequential(new AcrossSwitchLane(fromRight));
					addSequential(new FindBox(fromLeft));

				} else {
					addSequential(new FindBox(fromRight));
				}
				addSequential(new GetBox());
				addSequential(new PlaceBoxOnSwitch());
			}
			break;

		case "RIGHT":
			if(scaleOnLeft) {
				addSequential(new AcrossBaseline(fromRight));
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromLeft));
				addSequential(new ScaleToSameSideBox(fromLeft));
				if(switchOnLeft) {
					addSequential(new FindBox(fromLeft));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				} else {
					addSequential(new AcrossSwitchLane(fromLeft));
					addSequential(new FindBox(fromRight));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				}
			} else {
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));
				if(switchOnLeft) {
					addSequential(new AcrossSwitchLane(fromRight));
					addSequential(new FindBox(fromLeft));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				} else {
					addSequential(new FindBox(fromRight));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				}

			}
			break;
		}

	}

}
