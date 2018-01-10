package org.usfirst.frc.team5190.robot;

import org.usfirst.frc.team5190.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5190.robot.subsystems.Navigator;

public class RobotMap {
	
	// DRIVE TRAIN
	public static final boolean dtDriveWithXbox = false;
	public static boolean dtReverseDrive = false;				// turn this on to reverse all driving	
	public static boolean dtReverseSensors = false;				// turn this on to reverse all driving	

	public static final float dtDriveWheelRadius = 2;			// inches
	public static final float dtWheelWidth = 25;				// inches
	public static final float dtRobotPower = 1f;
	public static final float dtMaxRobotSpeedAtHighGear = 18.3f * dtRobotPower;
	public static final float dtMaxRobotSpeedAtLowGear = 10.33f * dtRobotPower;
	public static final boolean dtVelocityMode = false;

	public static final int dtFrontLeft = 1;					// port configuration for drive motors
	public static final int dtRearLeft = 2;
	public static final int dtFrontRight = 3;
	public static final int dtRearRight = 4;	
	public static final boolean dtEnableNavX = true;			// turn this off for simulation
	public static final byte dtNavUpdateHz = 20;
	public static final int dtTicksPerRotation = 1440;
	public static final int dtDriveCyclesPerRotation = 360;
	public static DriveTrain drivetrain;
	
	// NAVIGATOR
	public static final Arcade.Position nvInitialPosition = Arcade.Position.ALLIANCE_WALL_LEFT_STATION; 
	public static final double nvInitialHeading = 90.0; 

	// old values 0.05 | 5.57
	public static final double nvTimeDelta = 0.05;				// s
	public static final double nvMaxVelocity = 2.0;				// f/s	(1.7 m/s)
	public static final double nvMaxAcceleration = 6.56;		// f/s*s (2.0 m/s*s)
	public static final double nvMaxJerk = 196.85;				// f/s*s*s (60 m/s*s*s)
	public static Navigator navigator;
	
	// INPUT DEVICES
	public static JoystickOI joystickoi;
	public static XBoxOI xboxoi;
}