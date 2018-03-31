package org.usfirst.frc.team3482.robot.commands;

import org.usfirst.frc.team3482.robot.commands.paths.AutoConstants;
import org.usfirst.frc.team3482.robot.commands.paths.NextToSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.PlaceBoxOnSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.SidePushToSwitch;
import org.usfirst.frc.team3482.robot.commands.paths.TimedCrossAutonLine;
import org.usfirst.frc.team3482.robot.commands.paths.TimedMiddleBaseline;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;
import org.usfirst.frc.team3482.robot.RobotMap;
import org.usfirst.frc.team3482.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedAutonomous extends CommandGroup {
	private boolean fromRight = true, fromLeft = false, toRight = true, toLeft = false;

	public TimedAutonomous(boolean switchOnLeft, String sPos) {
		super();
		addSequential(new EnableDrive(false));
		RobotMap.driveController.enable();
		addSequential(new Intake(0.01));
		addSequential(new SetElevatorPosition(Elevator.SWITCH_POSITION));
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
			System.out.println("MIDDLE");
			addSequential(new TimedMove(2, 0.5));
			if (switchOnLeft) {
				addSequential(new TimedMiddleBaseline(toLeft));
			} else {
				addSequential(new TimedMiddleBaseline(toRight));

			}
			addSequential(new TimedMove(AutoConstants.timedLastPush, 0.5));
			addSequential(new PlaceBoxOnSwitch());
			System.out.println("done");
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
		addSequential(new EnableDrive(true));
		RobotMap.driveController.disable();
	}
}
