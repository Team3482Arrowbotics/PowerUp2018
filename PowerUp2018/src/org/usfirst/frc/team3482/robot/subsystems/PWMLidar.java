package org.usfirst.frc.team3482.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class PWMLidar {
	int channel;
	DigitalInput in;
	Counter counter;
	private int printedWarningCount = 5;
	static final int CALIBRATION_OFFSET = -18;
	public PWMLidar(int channel) {
		this.channel = channel;
		in = new DigitalInput(channel);
		counter = new Counter(in);
		counter.setMaxPeriod(1.0);
	}
	public double getDistance() {
		double cm;
		if(counter.get() < 1) {
			if(printedWarningCount-- < 0) {
				System.out.println("Lidar waiting for distance measurement");
			}
			return 0;
		}
		cm = (counter.getPeriod()* 1000000.0 / 10.0) + CALIBRATION_OFFSET;
		return cm;
	}
}
