package org.usfirst.frc.team3482.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class DPadButton extends Button{
	//0 for up, 90 for right, 180 for down, 270 for left
	public enum Direction{
		NORTH (0), NORTHEAST (45), EAST (90), SOUTHEAST (135), SOUTH (180),
		SOUTHWEST (225), WEST (270), NORTHWEST (315);
		int dir;
		private Direction(int dir){
			this.dir = dir;
		}
		int direction(){
			return dir;
		}
	}
	Direction direction;
	Joystick stick;
	public DPadButton(Joystick stick, Direction direction){
		super();
		this.stick = stick;
		this.direction = direction;
	}
	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return stick.getPOV() == direction.direction();
	}

}
