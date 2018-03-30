package org.usfirst.frc.team3482.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team3482.robot.Robot;
import org.usfirst.frc.team3482.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class PWMLidar {
	int channel;
	DigitalInput in;
	Counter counter;
	private int printedWarningCount = 5;
	static final int CALIBRATION_OFFSET = 0;
	double last = 0;
	public boolean drasticChange = false;

	public PWMLidar(int channel) {
		this.channel = channel;
		in = new DigitalInput(channel);
		counter = new Counter(in);
		counter.setMaxPeriod(1.0);
		counter.setSemiPeriodMode(true);
		counter.reset();
	}

	public double getDistance() {
		
		double cm;
		if (counter.get() < 1) {
			if (printedWarningCount-- > 0) {
				System.out.println("Lidar waiting for distance measurement");
			}
			return 0;
		}
		// Period is in microseconds, multiply by 1 million to make numbers
		// nice, divide by 10 to get cm
		cm = (counter.getPeriod() * 1000000.0 / 10.0) + CALIBRATION_OFFSET;
		if(Math.abs(last - cm) > 100 && last != 0){
			drasticChange = true;
			System.out.println("DRASTIC");
		} else{
			drasticChange = false;
		}
		last = cm;
		return cm;
	}

	public void goToBox() {
		int counter = 0;
		while (getDistance() > 50) {
			counter++;
			if (counter > 2000) {
				RobotMap.drive.arcadeDrive(0, 0);
				return;
			}
			RobotMap.drive.arcadeDrive(.5, 0);
		}
		Robot.intake.setPistons(false);
		RobotMap.drive.arcadeDrive(0, 0);

	}

	public boolean boxInClampRange() {
		return RobotMap.intakeLidar.getDistance() < 50 && RobotMap.intakeLidar.getDistance() > 25
				&& Robot.intake.getPosition() == Value.kForward;
	}

	public boolean boxFullyInside() {
		return RobotMap.intakeLidar.getDistance() < 25 && Robot.intake.getPosition() == Value.kForward;
	}

}

// package org.usfirst.frc.team3504.robot;
//
// import edu.wpi.first.wpilibj.Counter;
// import edu.wpi.first.wpilibj.DigitalSource;
//
// public class LidarLitePWM {
/// *
// * Adjust the Calibration Offset to compensate for differences in each unit.
// * We've found this is a reasonably constant value for readings in the 25 cm
// to 600 cm range.
// * You can also use the offset to zero out the distance between the sensor and
// edge of the robot.
// */
// private static final int CALIBRATION_OFFSET = -18;
//
// private Counter counter;
// private int printedWarningCount = 5;
//
/// **
// * Create an object for a LIDAR-Lite attached to some digital input on the
// roboRIO
// *
// * @param source The DigitalInput or DigitalSource where the LIDAR-Lite is
// attached (ex: new DigitalInput(9))
// */
// public LidarLitePWM (DigitalSource source) {
// counter = new Counter(source);
// counter.setMaxPeriod(1.0);
// // Configure for measuring rising to falling pulses
// counter.setSemiPeriodMode(true);
// counter.reset();
// }
//
/// **
// * Take a measurement and return the distance in cm
// *
// * @return Distance in cm
// */
// public double getDistance() {
// double cm;
// /* If we haven't seen the first rising to falling pulse, then we have no
// measurement.
// * This happens when there is no LIDAR-Lite plugged in, btw.
// */
// if (counter.get() < 1) {
// if (printedWarningCount-- > 0) {
// System.out.println("LidarLitePWM: waiting for distance measurement");
// }
// return 0;
// }
// /* getPeriod returns time in seconds. The hardware resolution is
// microseconds.
// * The LIDAR-Lite unit sends a high signal for 10 microseconds per cm of
// distance.
// */
// cm = (counter.getPeriod() * 1000000.0 / 10.0) + CALIBRATION_OFFSET;
// return cm;
// }
// }