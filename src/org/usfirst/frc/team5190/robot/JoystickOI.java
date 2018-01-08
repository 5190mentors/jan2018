package org.usfirst.frc.team5190.robot;

import org.usfirst.frc.team5190.robot.commands.Navigate;
import org.usfirst.frc.team5190.robot.commands.SubsystemsReset;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class JoystickOI {
	private Joystick joy = new Joystick(0);
	
	public JoystickOI() {
		// Create some buttons
//		JoystickButton b1 = new JoystickButton(joy, 1);
//		JoystickButton b2 = new JoystickButton(joy, 2);
//		JoystickButton b3 = new JoystickButton(joy, 3);
//		JoystickButton b4 = new JoystickButton(joy, 4);
//		JoystickButton b5 = new JoystickButton(joy, 5);
		JoystickButton b6 = new JoystickButton(joy, 6);
		JoystickButton b7 = new JoystickButton(joy, 7);
//		JoystickButton b8 = new JoystickButton(joy, 8);
//		JoystickButton b9 = new JoystickButton(joy, 9);
//		JoystickButton b10 = new JoystickButton(joy, 10);
//		JoystickButton b11 = new JoystickButton(joy, 11);
//		JoystickButton b12 = new JoystickButton(joy, 12);

		// Connect the buttons to commands
//		b1.whenPressed(new ClawOpenOrClose());
//		b4.whenPressed(new SubsystemsReset());
//		b2.whenPressed(new ElevatorUp());
//		b2.whenReleased(new ElevatorStop());
//		b3.whenPressed(new ElevatorDown());
//		b3.whenReleased(new ElevatorStop());
		b6.whenPressed(new Navigate());
		b7.whenPressed(new SubsystemsReset());
	}

	public Joystick getJoystick() {
		return joy;
	}
}
