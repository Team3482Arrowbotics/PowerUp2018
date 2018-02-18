package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.DPadButton.Direction;
import org.usfirst.frc.team3482.robot.commands.ManualElevatorSpeed;
import org.usfirst.frc.team3482.robot.commands.SpinClimberHook;
import org.usfirst.frc.team3482.robot.commands.Spintake;
import org.usfirst.frc.team3482.robot.commands.Spouttake;
import org.usfirst.frc.team3482.robot.commands.SwitchIntake;
import org.usfirst.frc.team3482.robot.commands.Climb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick x;
	public JoystickButton spintake;
	private JoystickButton spouttake;
	private JoystickButton switchtake;
	private DPadButton manualElevatorUp;
	private DPadButton manualElevatorDown;
	private DPadButton spinClimberButton;
	private JoystickButton climbButton;
	private DPadButton spinClimberReverseButton;

	public OI(){
		x = new Joystick(0);
		
		spintake = new JoystickButton(x, 6);
		spintake.whileHeld(new Spintake());
		
		spouttake = new JoystickButton(x, 5);
		spouttake.whileHeld(new Spouttake());
		
		switchtake = new JoystickButton(x, 1);
		switchtake.whenPressed(new SwitchIntake());
		
		manualElevatorUp = new DPadButton(x, Direction.NORTH);
		manualElevatorUp.whileHeld(new ManualElevatorSpeed(1));
		manualElevatorDown = new DPadButton(x, Direction.SOUTH);
		manualElevatorDown.whileHeld(new ManualElevatorSpeed(-.6));
		
		spinClimberButton = new DPadButton(x, Direction.WEST);
		spinClimberButton.whileHeld(new SpinClimberHook(-0.35));
		
		spinClimberReverseButton = new DPadButton(x, Direction.EAST);
		spinClimberReverseButton.whileHeld(new SpinClimberHook(0.35));
		
		climbButton = new JoystickButton(x, 3);
		climbButton.whileHeld(new Climb());
	}
}
