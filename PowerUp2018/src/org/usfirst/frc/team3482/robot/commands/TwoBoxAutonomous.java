package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.AcrossBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.CrossFieldToScaleLane;
import org.usfirst.frc.team3482.robot.commands.paths.FindBox;
import org.usfirst.frc.team3482.robot.commands.paths.GoToBox;
import org.usfirst.frc.team3482.robot.commands.paths.PickUpBox;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnScale;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.ScaleToSameSideBox;
import org.usfirst.frc.team3482.robot.commands.paths.StartToScale;
import org.usfirst.frc.team3482.robot.commands.paths.ToSwitchFromMiddle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBoxAutonomous extends CommandGroup{

	boolean fromRight = true, fromLeft = false, 
			toRight = true, toLeft = false;

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
					addSequential(new GoToBox());
					addSequential(new PickUpBox());
					addSequential(new PlaceBoxOnSwitch());
				} else { //switch on right
					addSequential(new CrossFieldToScaleLane(fromLeft));
					addSequential(new FindBox(fromRight));
					addSequential(new GoToBox());
					addSequential(new PickUpBox());
					addSequential(new PlaceBoxOnSwitch());
				}
			} else { //scale on right

				addSequential(new AcrossBaseline(fromLeft));
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));
				if(switchOnLeft) {
					addSequential(new CrossFieldToScaleLane(fromRight));
					addSequential(new FindBox(fromLeft));
					addSequential(new GoToBox());
					addSequential(new PickUpBox());
					addSequential(new PlaceBoxOnSwitch());
				} else { //switch on right
					addSequential(new FindBox(fromRight));
					addSequential(new GoToBox());
					addSequential(new PickUpBox());
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

				} else { //switch on right
					addSequential(new CrossFieldToScaleLane(fromLeft));
					addSequential(new FindBox(fromRight));
				}
				addSequential(new GoToBox());
				addSequential(new PickUpBox());
				addSequential(new PlaceBoxOnSwitch());

			} else { //scale on right
				addSequential(new ToSwitchFromMiddle(toRight));

				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));

				if(switchOnLeft) {
					addSequential(new CrossFieldToScaleLane(fromRight));
					addSequential(new FindBox(fromLeft));
					
				} else { //switch on right
					addSequential(new FindBox(fromRight));
				}
				addSequential(new GoToBox());
				addSequential(new PickUpBox());
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
					addSequential(new GoToBox());
					addSequential(new PickUpBox());
					addSequential(new PlaceBoxOnSwitch());
				} else {
					addSequential(new CrossFieldToScaleLane(fromLeft));
					addSequential(new FindBox(fromRight));
					addSequential(new GoToBox());
					addSequential(new PickUpBox());
					addSequential(new PlaceBoxOnSwitch());
				}
			} else {
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));
				if(switchOnLeft) {
					addSequential(new CrossFieldToScaleLane(fromRight));
					addSequential(new FindBox(fromLeft));
					addSequential(new GoToBox());
					addSequential(new PickUpBox());
					addSequential(new PlaceBoxOnSwitch());
				} else {
					addSequential(new FindBox(fromRight));
					addSequential(new GoToBox());
					addSequential(new PickUpBox());
					addSequential(new PlaceBoxOnSwitch());
				}
			}
			break;
		}
	}
}
