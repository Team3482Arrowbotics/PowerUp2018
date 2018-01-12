package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.Spintake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick x;
	public JoystickButton dav;
	public OI(){
		x = new Joystick(0);
		dav = new JoystickButton(x, 4);
		dav.whileHeld(new Spintake());
	}
}
