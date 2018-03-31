package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.ToSwitchFromMiddle;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.commands.paths.AwayFromWall;
import org.usfirst.frc.team3482.robot.commands.paths.LastPushToSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.SidePushToSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.NextToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchAutonomous extends CommandGroup{
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;
	public SwitchAutonomous(boolean switchOnLeft, String sPos) {

		super();
		addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
		switch(sPos) {
		case "LEFT":
			System.out.println("Left");
			if(!switchOnLeft) {
//				addSequential(new WaitCommand(5));
//				addSequential(new ToOppositeSwitch(fromLeft));
//				addSequential(new PlaceBoxOnSwitch());
				addSequential(new NextToSwitch());
			}
			else {
				addSequential(new NextToSwitch());
				addSequential(new SidePushToSwitch(fromLeft));
				addSequential(new PlaceBoxOnSwitch());
			}
			break;

		case "MIDDLE":
			System.out.println("Middle");
			addSequential(new AwayFromWall());
			if(switchOnLeft) {
				addSequential(new ToSwitchFromMiddle(toLeft));
			}
			else {
				addSequential(new ToSwitchFromMiddle(toRight));
			}
			addSequential(new LastPushToSwitch());
			addSequential(new PlaceBoxOnSwitch());
			break;

		case "RIGHT":
			System.out.println("Right");
			if(switchOnLeft) {
//				addSequential(new WaitCommand(5));
//				addSequential(new ToOppositeSwitch(fromRight));
//				addSequential(new PlaceBoxOnSwitch());
				addSequential(new NextToSwitch());
			}
			else {
				addSequential(new NextToSwitch());
				addSequential(new SidePushToSwitch(fromRight));
				addSequential(new PlaceBoxOnSwitch());
			}
			break;
		}
	}
}
