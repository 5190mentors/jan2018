/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class SubsystemsReset extends TimedCommand {
	public SubsystemsReset() {
		super("ResetSubsystems", 1);
		requires(RobotMap.drivetrain);
		requires(RobotMap.navigator);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - ResetSubsystems");		
		RobotMap.drivetrain.reset();
		RobotMap.navigator.reset();
	}

	@Override
	protected void end() {
		System.out.println("Leaving command - ResetSubsystems");
	}
}
