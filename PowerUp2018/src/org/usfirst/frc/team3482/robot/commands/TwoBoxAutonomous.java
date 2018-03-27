package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.AcrossBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.CrossFieldToScale;
import org.usfirst.frc.team3482.robot.commands.paths.FindBox;
import org.usfirst.frc.team3482.robot.commands.paths.GetBox;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnScale;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.ScaleToSameSideBox;
import org.usfirst.frc.team3482.robot.commands.paths.StartPosition;
import org.usfirst.frc.team3482.robot.commands.paths.StartToScale;
import org.usfirst.frc.team3482.robot.commands.paths.ToSwitchFromMiddle;

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
					addSequential(new CrossFieldToScale(fromLeft));
					addSequential(new FindBox(fromRight));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				}
			} else {

				addSequential(new AcrossBaseline(fromLeft));
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));
				if(switchOnLeft) {
					addSequential(new CrossFieldToScale(fromRight));
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

				addSequential(new ToSwitchFromMiddle(toLeft));
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromLeft));
				addSequential(new ScaleToSameSideBox(fromLeft));

				if(switchOnLeft) {
					addSequential(new FindBox(fromLeft));

				} else {
					addSequential(new CrossFieldToScale(fromLeft));
					addSequential(new FindBox(fromRight));
				}
				addSequential(new GetBox());
				addSequential(new PlaceBoxOnSwitch());

			} else {
				addSequential(new ToSwitchFromMiddle(toRight));

				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));

				if(switchOnLeft) {
					addSequential(new CrossFieldToScale(fromRight));
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
					addSequential(new CrossFieldToScale(fromLeft));
					addSequential(new FindBox(fromRight));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				}
			} else {
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));
				if(switchOnLeft) {
					addSequential(new CrossFieldToScale(fromRight));
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
