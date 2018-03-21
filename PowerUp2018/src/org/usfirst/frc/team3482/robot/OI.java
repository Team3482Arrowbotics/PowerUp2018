
package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.Climb;
import org.usfirst.frc.team3482.robot.commands.Intake;
import org.usfirst.frc.team3482.robot.commands.ManualElevatorSpeed;
import org.usfirst.frc.team3482.robot.commands.Outtake;
import org.usfirst.frc.team3482.robot.commands.ReverseClimb;
import org.usfirst.frc.team3482.robot.commands.SwitchIntake;
import org.usfirst.frc.team3482.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick flightStick;
	public Joystick xBox;
	public JoystickButton spintake;
	private JoystickButton spouttake;
	private JoystickButton switchtake;
	private JoystickButton manualElevatorUp;
	private JoystickButton manualElevatorDown;
	private JoystickButton climbButton;
	private JoystickButton reverseClimbButton;
	
	private JoystickButton spinClimberButton;
	private DPadButton spinClimberReverseButton;
	public static final int ELEVATOR_AXIS = 1;
	public static final int CLIMBER_AXIS = 2;

	public OI(){
		flightStick = new Joystick(0);
		xBox = new Joystick(1);
		spintake = new JoystickButton(flightStick, 2);
		spintake.whileHeld(new Intake());
		
		spouttake = new JoystickButton(flightStick, 1);
		spouttake.whileHeld(new Outtake());
		
		switchtake = new JoystickButton(flightStick, 3);
		switchtake.whenPressed(new SwitchIntake());
		
		manualElevatorUp = new JoystickButton(flightStick, 9);
		manualElevatorUp.whileHeld(new ManualElevatorSpeed(Elevator.MANUAL_UP_SPEED));
		manualElevatorDown = new JoystickButton(flightStick, 10);
		manualElevatorDown.whileHeld(new ManualElevatorSpeed(Elevator.MANUAL_DOWN_SPEED));
		
		climbButton = new JoystickButton(flightStick, 6);
		climbButton.whileHeld(new Climb());
		
		reverseClimbButton = new JoystickButton(flightStick, 5);
		reverseClimbButton.whileHeld(new ReverseClimb());
	}
}
