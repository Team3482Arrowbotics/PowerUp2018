package org.usfirst.frc.team3482.robot;

import java.util.Timer;
import java.util.TimerTask;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TwoMotorsOneEncoder extends WPI_TalonSRX{
	WPI_TalonSRX other;
	private Updater task;
	private java.util.Timer timer;
	public TwoMotorsOneEncoder(WPI_TalonSRX encoderTalon, WPI_TalonSRX otherTalon) {
		super(encoderTalon.getDeviceID());
		other = otherTalon;
		task = new Updater();
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 1, 0);
		
	}
	private class Updater extends TimerTask{

		@Override
		public void run() {
			other.set(get());
		}
		
	}

}
