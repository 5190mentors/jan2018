package org.usfirst.frc.team5190.robot;

import org.usfirst.frc.team5190.robot.commands.Navigate;
import org.usfirst.frc.team5190.robot.commands.SubsystemsReset;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class XBoxOI {
	private XboxController xbox = new XboxController(0);
	
	public XBoxOI() {

//		JoystickButton b1 = new JoystickButton(xbox, 1); // A
		JoystickButton b2 = new JoystickButton(xbox, 2); // B
		JoystickButton b3 = new JoystickButton(xbox, 3); // X
//		JoystickButton b4 = new JoystickButton(xbox, 4); // Y
//		JoystickButton b5 = new JoystickButton(xbox, 5); // Left trigger
//		JoystickButton b6 = new JoystickButton(xbox, 6); // Right trigger
//		JoystickButton b7 = new JoystickButton(joy, 7);
//		JoystickButton b8 = new JoystickButton(joy, 8);
//		JoystickButton b9 = new JoystickButton(joy, 9);
//		JoystickButton b10 = new JoystickButton(joy, 10);
//		JoystickButton b11 = new JoystickButton(joy, 11);
//		JoystickButton b12 = new JoystickButton(joy, 12);

		// Connect the buttons to commands
//		b5.whenPressed(new ClawOpenOrClose());
//		b6.whenPressed(new SubsystemsReset());
//		b4.whenPressed(new ElevatorUp());
//		b4.whenReleased(new ElevatorStop());
//		b1.whenPressed(new ElevatorDown());
//		b1.whenReleased(new ElevatorStop());
		b3.whenPressed(new Navigate());
		b2.whenPressed(new SubsystemsReset());
	}

	public XboxController getXbox() {
		return xbox;
	}
}
