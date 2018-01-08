package org.usfirst.frc.team5190.robot;

import org.usfirst.frc.team5190.robot.commands.Navigate;
import org.usfirst.frc.team5190.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5190.robot.subsystems.Navigator;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command autonomousCommand;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		// Initialize all subsystems
		RobotMap.drivetrain = new DriveTrain();
		RobotMap.navigator = new Navigator();

		if (RobotMap.dtDriveWithXbox)
			RobotMap.xboxoi = new XBoxOI();
		else
			RobotMap.joystickoi = new JoystickOI();
		
		// instantiate the command used for the autonomous period
		autonomousCommand = new Navigate();

		// Show what command your subsystem is running on the SmartDashboard
//		SmartDashboard.putData(drivetrain);
//		SmartDashboard.putDate(navigator);
	}

	@Override
	public void autonomousInit() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.charAt(0) == 'L')
		{
			//Put left auto code here
			autonomousCommand.start();
		} else {
			//Put right auto code here
			autonomousCommand.start();
		}		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		autonomousCommand.cancel();		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	@Override
	public void disabledInit() {
		RobotMap.drivetrain.reset();
		RobotMap.navigator.reset();
	}
}
