package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.AcrossBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.CrossFieldToScaleLane;
import org.usfirst.frc.team3482.robot.commands.paths.CrossFieldToSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.FindBox;
import org.usfirst.frc.team3482.robot.commands.paths.GoToBox;
import org.usfirst.frc.team3482.robot.commands.paths.GrabBox;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnScale;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.ScaleToSameSideBox;
import org.usfirst.frc.team3482.robot.commands.paths.StartToScale;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoBoxAutonomous extends CommandGroup{

	boolean fromRight = true, fromLeft = false, 
			toRight = true, toLeft = false;

	public TwoBoxAutonomous(boolean switchOnLeft, boolean scaleOnLeft, String sPos) {
		super();
		switch(sPos) {
		case "LEFT":
			if(scaleOnLeft) {
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromLeft));
				addSequential(new ScaleToSameSideBox(fromLeft));
				addSequential(new FindBox(fromLeft));
				addSequential(new GoToBox());
				addSequential(new GrabBox());
				
				if(!switchOnLeft) { //switch on right
					addSequential(new CrossFieldToSwitch(fromLeft));
				}
				addSequential(new PlaceBoxOnSwitch());

			} else { //scale on right
				addSequential(new AcrossBaseline(fromLeft));
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));
				addSequential(new FindBox(fromRight));
				addSequential(new GoToBox());
				addSequential(new GrabBox());
				
				if(switchOnLeft) {
					addSequential(new CrossFieldToSwitch(fromRight));
				}
				addSequential(new PlaceBoxOnSwitch());
			}
			break;

		case "RIGHT":
			if(scaleOnLeft) {
				addSequential(new AcrossBaseline(fromRight));
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromLeft));
				addSequential(new ScaleToSameSideBox(fromLeft));
				addSequential(new FindBox(fromLeft));
				addSequential(new GoToBox());
				addSequential(new GrabBox());
				if(!switchOnLeft) {
					addSequential(new CrossFieldToScaleLane(fromLeft));
				} 
					addSequential(new PlaceBoxOnSwitch());
			} else { //scale on right
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(fromRight));
				addSequential(new ScaleToSameSideBox(fromRight));
				addSequential(new FindBox(fromRight));
				addSequential(new GoToBox());
				addSequential(new GrabBox());
				
				if(switchOnLeft) {
					addSequential(new CrossFieldToScaleLane(fromRight));
				}
					addSequential(new PlaceBoxOnSwitch());
			}
			break;
		}
	}
}
