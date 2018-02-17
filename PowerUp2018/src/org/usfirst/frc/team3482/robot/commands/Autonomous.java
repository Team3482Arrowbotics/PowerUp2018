package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.AcrossBaseline;
import org.usfirst.frc.team3482.robot.commands.paths.AcrossBaselineFromMiddle;
import org.usfirst.frc.team3482.robot.commands.paths.AcrossSwitchLane;
import org.usfirst.frc.team3482.robot.commands.paths.AutoLineToScale;
import org.usfirst.frc.team3482.robot.commands.paths.FindBox;
import org.usfirst.frc.team3482.robot.commands.paths.GetBox;
import org.usfirst.frc.team3482.robot.commands.paths.MiddleStartToLeftAutoLine;
import org.usfirst.frc.team3482.robot.commands.paths.MiddleStartToRightAutoLine;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnScale;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.ScaleToSameSideBox;
import org.usfirst.frc.team3482.robot.commands.paths.StartToOppoAutoLine;
import org.usfirst.frc.team3482.robot.commands.paths.StartToScale;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup{
	public enum StartPosition {
		LEFT, MIDDLE, RIGHT;
	}
	boolean switchOnLeft, scaleOnLeft, crossBaseline;
	StartPosition sPos;
	public Autonomous(boolean switchOnLeft, boolean scaleOnLeft, boolean crossBaseline, StartPosition sPos) {
		super();
		this.switchOnLeft = switchOnLeft;
		this.scaleOnLeft = scaleOnLeft;
		this.crossBaseline = crossBaseline;
		this.sPos = sPos;
		addSequential(new Move(6));
		switch(sPos) {
		case LEFT:
			if(scaleOnLeft) {
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(false));
				addSequential(new ScaleToSameSideBox(false));
				if(switchOnLeft) {
					addSequential(new FindBox());
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				} else {
					addSequential(new AcrossSwitchLane(false));
					addSequential(new FindBox(true));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				}
			} else {
				if(crossBaseline) {
					addSequential(new AcrossBaseline(false));
					addSequential(new StartToScale());
				}
				else {
					addSequential(new StartToOppoAutoLine(false));
					addSequential(new AutoLineToScale());
				}
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
		case MIDDLE:
			if(scaleOnLeft) {
				
				if(crossBaseline) {
					addSequential(new AcrossBaselineFromMiddle(false));
					addSequential(new StartToScale());
				} else {
					addSequential(new MiddleStartToLeftAutoLine());
					addSequential(new AutoLineToScale());
				}
				addSequential(new PlaceBoxOnScale(false));
				addSequential(new ScaleToSameSideBox(false));
				
				if(switchOnLeft) {
					addSequential(new FindBox()); // true or false?

				} else {
					addSequential(new AcrossSwitchLane(false));
					addSequential(new FindBox(true)); // true or false?
				}
				addSequential(new GetBox());
				addSequential(new PlaceBoxOnSwitch());
				
			} else {
				if(crossBaseline) {
					addSequential(new AcrossBaselineFromMiddle(true));
				} else {
					addSequential(new MiddleStartToRightAutoLine());
					addSequential(new AutoLineToScale());
				}
				addSequential(new PlaceBoxOnScale(true));
				addSequential(new ScaleToSameSideBox(true));
				
				if(!switchOnLeft) {
					addSequential(new FindBox()); // true or false?

				} else {
					addSequential(new AcrossSwitchLane(true));
					addSequential(new FindBox(true)); // true or false?
				}
				addSequential(new GetBox());
				addSequential(new PlaceBoxOnSwitch());
			}
			break;
		case RIGHT:
			if(scaleOnLeft) {
				if(crossBaseline) {
					addSequential(new AcrossBaseline(true));
					addSequential(new StartToScale());
				}
				else {
					addSequential(new StartToOppoAutoLine(true));
					addSequential(new AutoLineToScale());
				}
				addSequential(new PlaceBoxOnScale(false));
				addSequential(new ScaleToSameSideBox(false));
				if(switchOnLeft) {
					addSequential(new FindBox());
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				} else {
					addSequential(new AcrossSwitchLane(false));
					addSequential(new FindBox(true));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				}

				

			} else {
				addSequential(new StartToScale());
				addSequential(new PlaceBoxOnScale(true));
				addSequential(new ScaleToSameSideBox(true));
				if(switchOnLeft) {
					addSequential(new AcrossSwitchLane(true));
					addSequential(new FindBox(true));
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				} else {
					addSequential(new FindBox());
					addSequential(new GetBox());
					addSequential(new PlaceBoxOnSwitch());
				}

			}
			break;
		}
		
	}
	
}
