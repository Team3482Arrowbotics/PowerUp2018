package org.usfirst.frc.team3482.robot.subsystems;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.LEDChannel;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LED extends Subsystem{
	public static CANifier canifier;
	
	public LED() {
		canifier = RobotMap.c;
	}
	
	//BRG
	public void setColor(String color) {
		if(color.equals("red")) {
			RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelA);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelB);
			RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelC);
		} else if(color.equals("green")) {
			RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelA);
			RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelC);
		} else if(color.equals("white")) {
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelA);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelB);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelC);
		} else if(color.equals("purple")) {
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelA);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelB);
			RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelC);
		} else if(color.equals("yellow")) {
			RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelA);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelB);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelC);
		} else if(color.equals("cyan")) {
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelA);
			RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelB);
			RobotMap.c.setLEDOutput(1.0, LEDChannel.LEDChannelC);
		}
			
	}
	
	//GRB
	public void turnOff() {
		RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelA);
		RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelB);
		RobotMap.c.setLEDOutput(0.0, LEDChannel.LEDChannelC);
	}
	
	public void turnColor(String color) {
		setColor(color);
	}
	
	public void flash(String color, double blinkDelay) {
		turnColor(color);
		Timer.delay(blinkDelay);
		turnOff();
		Timer.delay(blinkDelay);
	}
	
	public void ledBoxCondition(String colorBoxOut, String colorBoxIn) {
		SmartDashboard.putNumber("LIDAR VALUE", RobotMap.intakeLidar.getDistance());
		if(RobotMap.intakeLidar.getDistance() < 50 && Robot.intake.getPosition()==Value.kForward) { //!RobotMap.intakePhotoelectric.get()
			//6 for approx. max number for box
			turnColor(colorBoxIn);
		}
		else{
			turnColor(colorBoxOut);
		}
	}

	public void flashRainbow(String[] colorsToFlash, double blinkDelay) {
		for(String colors : colorsToFlash) {
			turnColor(colors);
			Timer.delay(blinkDelay);
		}
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
