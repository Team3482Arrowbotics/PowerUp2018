package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.command.GyroTurn90;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick x;
	public JoystickButton turn90;
	public OI(){
		x = new Joystick(0);
		
		turn90 = new JoystickButton(x, 1);
		turn90.whenPressed(new GyroTurn90());
	}
}
