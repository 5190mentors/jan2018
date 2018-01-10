package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.lib.Utils;
import org.usfirst.frc.team5190.robot.RobotMap;
import org.usfirst.frc.team5190.robot.commands.DriveTrainDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	WPI_TalonSRX frontLeftMotor;
	WPI_TalonSRX rearLeftMotor;
	WPI_TalonSRX frontRightMotor;
	WPI_TalonSRX rearRightMotor;

	DifferentialDrive drive;
	AHRS navx;

	public DriveTrain() {
		super("Drive Train");

		// Initialize all actuators and sensors of the drive train
		frontLeftMotor = new WPI_TalonSRX(RobotMap.dtFrontLeft);
		frontRightMotor = new WPI_TalonSRX(RobotMap.dtFrontRight);
		rearLeftMotor = new WPI_TalonSRX(RobotMap.dtRearLeft);
		rearRightMotor = new WPI_TalonSRX(RobotMap.dtRearRight);

		// master - follower
		rearLeftMotor.follow(frontLeftMotor);
		rearRightMotor.follow(frontRightMotor);
		
		// Brake mode for masters, Coast mode for followers
		frontLeftMotor.setNeutralMode(NeutralMode.Brake);
		frontRightMotor.setNeutralMode(NeutralMode.Brake);
		rearLeftMotor.setNeutralMode(NeutralMode.Coast);
		rearRightMotor.setNeutralMode(NeutralMode.Coast);

        frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        frontLeftMotor.setSensorPhase(RobotMap.dtReverseSensors);
        frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        frontRightMotor.setSensorPhase(RobotMap.dtReverseSensors);
        
        // control mode
		if (RobotMap.dtVelocityMode) {
	        frontLeftMotor.config_kP(0, 0.6, 0);
	        frontLeftMotor.config_kF(0, Utils.calculateFGain(1, RobotMap.dtMaxRobotSpeedAtHighGear, RobotMap.dtDriveWheelRadius, RobotMap.dtTicksPerRotation), 0);
	        
	        frontRightMotor.config_kP(0, 0.6, 0);
	        frontRightMotor.config_kF(0, Utils.calculateFGain(1, RobotMap.dtMaxRobotSpeedAtHighGear, RobotMap.dtDriveWheelRadius, RobotMap.dtTicksPerRotation), 0);

			// Ramping for masters, no ramping for followers
//			frontLeftMotor.configClosedloopRamp(2, 0);
//			frontRightMotor.configClosedloopRamp(2, 0);
//			rearLeftMotor.configClosedloopRamp(0, 0);
//			rearRightMotor.configClosedloopRamp(0, 0);
		}
		else {
//			frontLeftMotor.configOpenloopRamp(2, 0);
//			frontRightMotor.configOpenloopRamp(2, 0);
//			rearLeftMotor.configOpenloopRamp(0, 0);
//			rearRightMotor.configOpenloopRamp(0, 0);			
		}
		
//		LiveWindow.addActuator("Drive Train", "Front Left Motor", (Jaguar) RobotMap.frontLeftMotor);
//		LiveWindow.addActuator("Drive Train", "Rear Left Motor", (Jaguar) RobotMap.rearLeftMotor);
//		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Jaguar) RobotMap.frontRightMotor);
//		LiveWindow.addActuator("Drive Train", "Rear Right Motor", (Jaguar) RobotMap.rearRightMotor);

		drive = new DifferentialDrive(frontLeftMotor, frontRightMotor); 

		if (RobotMap.dtEnableNavX) {
			navx = new AHRS(SPI.Port.kMXP, RobotMap.dtNavUpdateHz);
			navx.reset();
			navx.setAngleAdjustment(RobotMap.nvInitialHeading);
//			LiveWindow.addSensor("Drive Train", "NavX", RobotMap.navx);
		}

		if (RobotMap.dtReverseDrive) {
			frontLeftMotor.setInverted(true);
			frontRightMotor.setInverted(true);
			rearLeftMotor.setInverted(true);
			rearRightMotor.setInverted(true);
		}

//		SmartDashboard.putNumber("Left Sensor Velocity", frontLeftMotor.getSelectedSensorVelocity(0));
//		SmartDashboard.putNumber("Right Sensor Velocity", frontRightMotor.getSelectedSensorVelocity(0));
		
		reset();
	}
	
	public void reset() {		
		// stop the motors
		frontLeftMotor.stopMotor();
		frontRightMotor.stopMotor();
		
    	System.out.println("Drive Train: Reset completed");
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveTrainDrive());
	}

	public void drive(Joystick joy) {
		double throttle = joy.getRawAxis(2);
		double y = joy.getY();
		double x = joy.getX();
		drive.arcadeDrive(y*throttle, x*throttle);
	}
	
	public void drive(XboxController xbox) {
		drive.tankDrive(-0.5* xbox.getY(Hand.kLeft), -0.5* xbox.getY(Hand.kRight));
	}
	
	// inputs are in -1 to 1 linear velocity
	public void setVelocity(double leftValue, double rightValue)
	{
		if (RobotMap.dtVelocityMode) {
	        if (leftValue >= 0.0) {
	            leftValue = leftValue * leftValue;
	        }
	        else {
	            leftValue = -(leftValue * leftValue);
	        }
	        
	        if (rightValue >= 0.0) {
	            rightValue = rightValue * rightValue;
	        }
	        else {
	            rightValue = -(rightValue * rightValue);
	        }
	
	        // convert from -1,1 scale to linear speed f/s scale
	        leftValue *= RobotMap.nvMaxVelocity;
	        rightValue *= RobotMap.nvMaxVelocity;
	
	        // convert into rpm before setting the motors
			frontLeftMotor.set(ControlMode.Velocity, Utils.feetPerSecondToRPM(leftValue, RobotMap.dtDriveWheelRadius));
			frontRightMotor.set(ControlMode.Velocity, Utils.feetPerSecondToRPM(rightValue, RobotMap.dtDriveWheelRadius));
		}
		else {
			// control the speed for now
			if (leftValue > 0.2) leftValue = 0.2;
			if (leftValue < -0.2) leftValue = -0.2;
			if (rightValue > 0.2) rightValue = 0.2;
			if (rightValue < -0.2) rightValue = -0.2;
			
			frontLeftMotor.set(ControlMode.PercentOutput, leftValue);
			frontRightMotor.set(ControlMode.PercentOutput, rightValue);			
		}
	}
	
	public int getLeftTicks() {
		return frontLeftMotor.getSensorCollection().getQuadraturePosition();
	}
	
	public int getRightTicks() {
		return frontRightMotor.getSensorCollection().getQuadraturePosition();
	}
	
	public double getAngle() {
		return navx.getAngle();
	}
	
	public double getPitch() {
		return navx.getPitch();
	}
}