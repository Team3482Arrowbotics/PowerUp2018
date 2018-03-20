package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.DPadButton.Direction;
import org.usfirst.frc.team3482.robot.commands.ManualElevatorSpeed;
import org.usfirst.frc.team3482.robot.commands.Intake;
import org.usfirst.frc.team3482.robot.commands.Outtake;
import org.usfirst.frc.team3482.robot.commands.SwitchIntake;
import org.usfirst.frc.team3482.robot.commands.ManualElevatorSpeed;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick flightStick;
	public Joystick xBox;
	public JoystickButton intake;
	private JoystickButton outtake;
	private JoystickButton switchtake;
	private DPadButton manualElevatorUp;
	private DPadButton manualElevatorDown;
	public static final int ELEVATOR_AXIS = 1;

	public OI(){
		flightStick = new Joystick(0);
		xBox = new Joystick(1);
		
		intake = new JoystickButton(flightStick, 2);
		intake.whileHeld(new Intake());
		
		outtake = new JoystickButton(flightStick, 1);
		outtake.whileHeld(new Outtake());
		
		switchtake = new JoystickButton(flightStick, 3);
		switchtake.whenPressed(new SwitchIntake());
		
//		manualElevatorUp = new DPadButton(flightStick, Direction.NORTH);
//		manualElevatorUp.whileHeld(new ManualElevatorSpeed(1));
//		manualElevatorDown = new DPadButton(flightStick, Direction.SOUTH);
//		manualElevatorDown.whileHeld(new ManualElevatorSpeed(-.75));
	}
}
