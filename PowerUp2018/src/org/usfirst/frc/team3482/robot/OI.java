
package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.DPadButton.Direction;
import org.usfirst.frc.team3482.robot.commands.ManualElevatorSpeed;
import org.usfirst.frc.team3482.robot.commands.SpinClimberHook;
import org.usfirst.frc.team3482.robot.commands.Intake;
import org.usfirst.frc.team3482.robot.commands.Outtake;
import org.usfirst.frc.team3482.robot.commands.SwitchIntake;
import org.usfirst.frc.team3482.robot.commands.Climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick flightStick;
	public Joystick xBox;
	public JoystickButton spintake;
	private JoystickButton spouttake;
	private JoystickButton switchtake;
	private JoystickButton manualElevatorUp;
	private JoystickButton manualElevatorDown;
	private JoystickButton spinClimberButton;
	private JoystickButton climbButton;
	private DPadButton spinClimberReverseButton;
	public static final int ELEVATOR_AXIS = 1;

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
		manualElevatorUp.whileHeld(new ManualElevatorSpeed(1));
		manualElevatorDown = new JoystickButton(flightStick, 10);
		manualElevatorDown.whileHeld(new ManualElevatorSpeed(-.8));
		
//		spinClimberButton = new JoyStickButton(flightStick, Direction.WEST);
//		spinClimberButton.whileHeld(new SpinClimberHook(-0.2));
//		
//		spinClimberReverseButton = new DPadButton(flightStick, Direction.EAST);
//		spinClimberReverseButton.whileHeld(new SpinClimberHook(0.2));
		
		climbButton = new JoystickButton(flightStick, 6);
		climbButton.whileHeld(new Climb());
		
//		reverseClimbButton = new JoystickButton(flightStick, 5);
//		reverseClimbButton.whileHeld(new Climb());
	}
}
