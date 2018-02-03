package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.ElevatorDown;
import org.usfirst.frc.team3482.robot.commands.ElevatorUp;
import org.usfirst.frc.team3482.robot.commands.ManualElevator;
import org.usfirst.frc.team3482.robot.commands.Spintake;
import org.usfirst.frc.team3482.robot.commands.Spouttake;
import org.usfirst.frc.team3482.robot.commands.SwitchIntake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick x;
	public JoystickButton spintake;
	private JoystickButton spouttake;
	private JoystickButton switchtake;
	private JoystickButton elevatorUp;
	private JoystickButton elevatorDown;
	private JoystickButton manualElevator;
	public OI(){
		x = new Joystick(0);
		spintake = new JoystickButton(x, 6);
		spintake.whileHeld(new Spintake());
		spouttake = new JoystickButton(x, 5);
		spouttake.whileHeld(new Spouttake());
		
		switchtake = new JoystickButton(x, 1);
		switchtake.whenPressed(new SwitchIntake());
		
		elevatorUp = new JoystickButton(x, 2);
		elevatorUp.whenPressed(new ElevatorUp());
		
		elevatorDown = new JoystickButton(x, 3);
		elevatorDown.whenPressed(new ElevatorDown());
		
		manualElevator = new JoystickButton(x, 10);
		manualElevator.whileHeld(new ManualElevator());
	}
}
