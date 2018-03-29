
package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.Climb;
import org.usfirst.frc.team3482.robot.commands.Intake;
import org.usfirst.frc.team3482.robot.commands.ManualElevatorSpeed;
import org.usfirst.frc.team3482.robot.commands.Outtake;
import org.usfirst.frc.team3482.robot.commands.ReverseClimb;
import org.usfirst.frc.team3482.robot.commands.SetElevatorPosition;
import org.usfirst.frc.team3482.robot.commands.SwitchIntake;
import org.usfirst.frc.team3482.robot.commands.ResetPID;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick flightStick;
	public Joystick xBox;
	public Joystick arcadeButtons;
	public JoystickButton spintake;
	private JoystickButton spouttake;
	private JoystickButton switchtake;
	private JoystickButton manualElevatorUp;
	private JoystickButton manualElevatorDown;
	private JoystickButton climbButton;
	private JoystickButton reverseClimbButton;
	private JoystickButton resetPID;
	private JoystickButton goToSwitch;
	private JoystickButton goToScale;
	private JoystickButton bumpElevator;
	private JoystickButton dropElevator;

	public static final int ELEVATOR_AXIS = 1;
	public static final int CLIMBER_AXIS = 2;

	public OI(){
		flightStick = new Joystick(0);
		xBox = new Joystick(1);
		arcadeButtons = new Joystick(2);
		
		bumpElevator = new JoystickButton(xBox, 4);
		bumpElevator.whenPressed(new SetElevatorPosition(Elevator.BUMP_POSITION));
		
		dropElevator = new JoystickButton(xBox, 5);
		dropElevator.whenPressed(new SetElevatorPosition(Elevator.BOTTOM_POSITION));
		
		spouttake = new JoystickButton(flightStick, 1);
		spouttake.whileHeld(new Outtake());
		
		spintake = new JoystickButton(flightStick, 2);
		spintake.whileHeld(new Intake());
		
		switchtake = new JoystickButton(flightStick, 3);
		switchtake.whenPressed(new SwitchIntake());
		
		reverseClimbButton = new JoystickButton(flightStick, 5);
		reverseClimbButton.whileHeld(new ReverseClimb());
		
		climbButton = new JoystickButton(flightStick, 6);
		climbButton.whileHeld(new Climb());
		
		resetPID = new JoystickButton(flightStick, 8);
		resetPID.whenPressed(new ResetPID());
		
		manualElevatorUp = new JoystickButton(flightStick, 9);
		manualElevatorUp.whileHeld(new ManualElevatorSpeed(Elevator.MANUAL_UP_SPEED));
		
		manualElevatorDown = new JoystickButton(flightStick, 10);
		manualElevatorDown.whileHeld(new ManualElevatorSpeed(Elevator.MANUAL_DOWN_SPEED));
		
		goToSwitch = new JoystickButton(flightStick, 11);
		goToSwitch.whenPressed(new SetElevatorPosition(Elevator.SWITCH_POSITION));
		
		goToScale = new JoystickButton(flightStick, 12);
		goToScale.whenPressed(new SetElevatorPosition(Elevator.SCALE_POSITION));
	}
}
