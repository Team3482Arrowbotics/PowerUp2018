package org.usfirst.frc.team3482.robot.commands.paths;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoConstants {
	
	public static double nextToSwitchDistance;
	public static double passSwitchDistance;
	public static double toScaleDistance;
	public static double crossFieldToScaleDistance;
	public static double middleToLeftSwitchDistance;
	public static double middleToRightSwitchDistance;
	public static double lastPushDistance;
	public static double scaleToSameSideBoxDistance;
	public static double baselineDistance;
	
	public AutoConstants() {
		nextToSwitchDistance = 150;
		passSwitchDistance = 200;
		baselineDistance = 200;
		toScaleDistance = 290;
		crossFieldToScaleDistance = 250;
		middleToLeftSwitchDistance = 25;
		middleToRightSwitchDistance = 25;
		lastPushDistance = 20;
		scaleToSameSideBoxDistance = 63;
	}
	
	public void set() {
		SmartDashboard.putNumber("Next To Switch Distance" , nextToSwitchDistance);
		SmartDashboard.putNumber("Pass Switch Distance" , passSwitchDistance);
		SmartDashboard.putNumber("Scale Distance" , toScaleDistance);
		SmartDashboard.putNumber("Last Push Distance" , lastPushDistance);
		SmartDashboard.putNumber("Middle Baseline Right Distance" , middleToRightSwitchDistance);
		SmartDashboard.putNumber("Middle Baseline Left Distance" , middleToLeftSwitchDistance);
		SmartDashboard.putNumber("Cross Field To Scale Distance" , crossFieldToScaleDistance);
		SmartDashboard.putNumber("Scale To Same Side Box Distance" , scaleToSameSideBoxDistance);
		SmartDashboard.putNumber("Baseline Distance" , baselineDistance);
	}
	
	public void get() {
		nextToSwitchDistance = SmartDashboard.getNumber("Switch Distance" , nextToSwitchDistance);
		passSwitchDistance = SmartDashboard.getNumber("Pass Switch Distance" , passSwitchDistance);
		toScaleDistance = SmartDashboard.getNumber("Scale Distance" , toScaleDistance);
		lastPushDistance = SmartDashboard.getNumber("Last Push Distance" , lastPushDistance);
		middleToLeftSwitchDistance = SmartDashboard.getNumber("Middle Baseline Left Distance" , middleToLeftSwitchDistance);
		middleToRightSwitchDistance = SmartDashboard.getNumber("Middle Baseline Right Distance" , middleToRightSwitchDistance);
		crossFieldToScaleDistance = SmartDashboard.getNumber("Cross Field To Scale Distance" , crossFieldToScaleDistance);
		scaleToSameSideBoxDistance = SmartDashboard.getNumber("Scale To Same Side Box Distance" , scaleToSameSideBoxDistance);
		baselineDistance = 	SmartDashboard.getNumber("Baseline Distance" , baselineDistance);

	}
}
