package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.command.Turn90;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick x;
	public JoystickButton turn;
	public OI(){
		x = new Joystick(0);
		turn = new JoystickButton(x, 1);
		turn.whenPressed(new Turn90());
	}
}
