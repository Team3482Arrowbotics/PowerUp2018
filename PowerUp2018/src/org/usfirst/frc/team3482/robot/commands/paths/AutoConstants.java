package org.usfirst.frc.team3482.robot.commands.paths;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoConstants {
	
	public static double nextToSwitch;
	public static double passSwitch;
	public static double toScale;
	public static double crossFieldToScale;
	public static double crossFieldToSwitch;
	public static double middleToLeftSwitch;
	public static double middleToRightSwitch;
	public static double middleToRightScaleLane;
	public static double middleToLeftScaleLane;
	public static double lastPush;
	public static double scaleToSameSideBox;
	public static double baseline;
	public static double backup;
	public static double awayFromWall;
	
	public AutoConstants() {
		nextToSwitch = 150;
		passSwitch = 200;
		baseline = 200;
		toScale = 290;
		crossFieldToScale = 250;
		crossFieldToSwitch = 225;
		middleToLeftSwitch = 25;
		middleToRightSwitch = 25;
		lastPush = 20;
		scaleToSameSideBox = 63;
		middleToLeftScaleLane = 80;
		middleToRightScaleLane = 80;
		backup = -20;
		awayFromWall = 36;
	}
	
	public void set() {
		SmartDashboard.putNumber("Next To Switch" , nextToSwitch);
		SmartDashboard.putNumber("Pass Switch" , passSwitch);
		SmartDashboard.putNumber("To Scale" , toScale);
		SmartDashboard.putNumber("Last Push" , lastPush);
		SmartDashboard.putNumber("Middle to Right Switch" , middleToRightSwitch);
		SmartDashboard.putNumber("Middle to Left Switch" , middleToLeftSwitch);
		SmartDashboard.putNumber("Middle to Right Scale" , middleToRightScaleLane);
		SmartDashboard.putNumber("Cross Field To Scale" , crossFieldToScale);
		SmartDashboard.putNumber("Cross Field To Switch" , crossFieldToSwitch);
		SmartDashboard.putNumber("Scale To Same Side Box" , scaleToSameSideBox);
		SmartDashboard.putNumber("Cross Baseline" , baseline);
		SmartDashboard.putNumber("Backup w/ Box" , backup);
		SmartDashboard.putNumber("Move Away From Wall" , awayFromWall);
	}
	
	public void get() {
		nextToSwitch = SmartDashboard.getNumber("Switch Distance" , nextToSwitch);
		passSwitch = SmartDashboard.getNumber("Pass Switch Distance" , passSwitch);
		toScale = SmartDashboard.getNumber("Scale Distance" , toScale);
		lastPush = SmartDashboard.getNumber("Last Push Distance" , lastPush);
		middleToLeftSwitch = SmartDashboard.getNumber("Middle Baseline Left Distance" , middleToLeftSwitch);
		middleToRightSwitch = SmartDashboard.getNumber("Middle Baseline Right Distance" , middleToRightSwitch);
		crossFieldToScale = SmartDashboard.getNumber("Cross Field To Scale Distance" , crossFieldToScale);
		crossFieldToSwitch = SmartDashboard.getNumber("Cross Field To Switch" , crossFieldToSwitch);
		scaleToSameSideBox = SmartDashboard.getNumber("Scale To Same Side Box Distance" , scaleToSameSideBox);
		baseline = 	SmartDashboard.getNumber("Baseline Distance" , baseline);
		backup = SmartDashboard.getNumber("Backup", backup);
		awayFromWall = SmartDashboard.getNumber("Move Away From Wall" , awayFromWall);
	}
}
