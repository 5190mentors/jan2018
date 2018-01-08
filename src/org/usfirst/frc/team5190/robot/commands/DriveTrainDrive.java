package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTrainDrive extends Command {

	public DriveTrainDrive() {
		super("DriveTrainDrive");
		requires(RobotMap.drivetrain);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - DriveTrainDrive");
	}
	
	@Override
	protected void execute() {
		if (RobotMap.dtDriveWithXbox)
			RobotMap.drivetrain.drive(RobotMap.xboxoi.getXbox());
		else
			RobotMap.drivetrain.drive(RobotMap.joystickoi.getJoystick());
	}

	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - DriveTrainDrive");
	}
	
	@Override
	protected void interrupted() {
		System.out.println("Cancelling command - DriveTrainDrive");
	}
}
