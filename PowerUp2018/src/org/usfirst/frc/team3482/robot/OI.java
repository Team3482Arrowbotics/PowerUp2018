package org.usfirst.frc.team3482.robot;

import org.usfirst.frc.team3482.robot.commands.Spintake;
import org.usfirst.frc.team3482.robot.commands.Spouttake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick x;
	public JoystickButton spintake;
	private JoystickButton spouttake;
	public OI(){
		x = new Joystick(0);
		spintake = new JoystickButton(x, 6);
		spintake.whileHeld(new Spintake());
		spouttake = new JoystickButton(x, 5);
		spouttake.whileHeld(new Spouttake());
	}
}
