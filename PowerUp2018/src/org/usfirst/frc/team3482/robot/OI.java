
package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.Climb;
import org.usfirst.frc.team3482.robot.commands.ManualElevatorSpeed;
import org.usfirst.frc.team3482.robot.commands.Spintake;
import org.usfirst.frc.team3482.robot.commands.Spouttake;
import org.usfirst.frc.team3482.robot.commands.SwitchIntake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick x;
	public Joystick x2;
	public Joystick flightStick;
	public Joystick arcadeButtons;
	public JoystickButton spintake;
	private JoystickButton spouttake;
	private JoystickButton switchtake;
	private JoystickButton manualElevatorUp;
	private JoystickButton manualElevatorDown;
	private JoystickButton spinClimberButton;
	private JoystickButton climbButton;
	private DPadButton spinClimberReverseButton;

	public OI(){
		x = new Joystick(0);
		x2 = new Joystick(1);
		flightStick = new Joystick(2);
		arcadeButtons = new Joystick(3);
		
		spintake = new JoystickButton(flightStick, 2);
		spintake.whileHeld(new Spintake());
		
		spouttake = new JoystickButton(flightStick, 1);
		spouttake.whileHeld(new Spouttake());
		
		switchtake = new JoystickButton(flightStick, 3);
		switchtake.whenPressed(new SwitchIntake());
		
		manualElevatorUp = new JoystickButton(flightStick, 9);
		manualElevatorUp.whileHeld(new ManualElevatorSpeed(1));
		manualElevatorDown = new JoystickButton(flightStick, 10);
		manualElevatorDown.whileHeld(new ManualElevatorSpeed(-.6));
		
		climbButton = new JoystickButton(flightStick, 5);
		climbButton.whileHeld(new Climb());
		
		/*1 outake
		 * 2 intake
		 * 4 toggle pistons
		 * Forward back up and down elevator
		 * Twist = spin climberhook
		 * 9 & 10 manual climb
		 * 5 = climb
		 * */
	}
}
