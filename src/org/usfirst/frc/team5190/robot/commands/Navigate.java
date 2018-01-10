/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team5190.robot.commands;

import org.usfirst.frc.team5190.robot.Arcade;
import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Navigate extends Command {
	
	public Navigate() {
		super("Navigate");
		requires(RobotMap.navigator);
//		requires(RobotMap.drivetrain);
	}

	@Override
	protected void initialize() {
		System.out.println("Entering command - Navigate");
		RobotMap.navigator.prepareToNavigate(Arcade.Position.ALLIANCE_SWITCH_LEFT);
	}

	@Override
	protected void execute() {
		RobotMap.navigator.navigate();
	}

	@Override
	protected boolean isFinished() {
		return RobotMap.navigator.isFinished();
	}
	
	@Override
	protected void end() {
		RobotMap.navigator.reset();
		System.out.println("Leaving command - Navigate");
	}	

	@Override
	protected void interrupted() {
		RobotMap.navigator.reset();
		System.out.println("Cancelling command - Navigate");
	}
}
