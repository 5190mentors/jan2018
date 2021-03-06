package org.usfirst.frc.team5190.robot.subsystems;

import org.usfirst.frc.team5190.lib.MyEncoderFollower;
import org.usfirst.frc.team5190.robot.Arcade;
import org.usfirst.frc.team5190.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

public class Navigator extends Subsystem {
	
	MyEncoderFollower left, right;
	
	public Navigator() {
		super("Navigator");
	}
	
	public void prepareToNavigate(Arcade.Position target) {
		
	   	System.out.println("Navigator: Preparing");
		// TODO: use real waypoints
		Waypoint points[] = new Waypoint[2];
		points[0] = new Waypoint(0, 0, Pathfinder.d2r(90));
		points[1] = new Waypoint(0, 5, Pathfinder.d2r(90));
//		points[2] = new Waypoint(-10, 8, Pathfinder.d2r(90));
//		points[3] = new Waypoint(-5, 10, Pathfinder.d2r(0));
//		points[4] = new Waypoint(-3, 10, Pathfinder.d2r(0));

		Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, RobotMap.nvTimeDelta, RobotMap.nvMaxVelocity, RobotMap.nvMaxAcceleration, RobotMap.nvMaxJerk);
		Trajectory trajectory = Pathfinder.generate(points, config);
	   	System.out.println("Navigator: Points");
		
		for (int i = 0; i < trajectory.length(); i++) {
		    Trajectory.Segment seg = trajectory.get(i);		    
		    System.out.printf("Navigator: Trajectory - %f,%f,%f,%f,%f,%f,%f\n", seg.x, seg.y, Math.toDegrees(seg.heading), seg.position, seg.velocity, seg.acceleration, seg.jerk);
		}

		TankModifier modifier = new TankModifier(trajectory);
		modifier.modify(RobotMap.dtWheelWidth / 12.0);
		
		left = new MyEncoderFollower(modifier.getLeftTrajectory());
		right = new MyEncoderFollower(modifier.getRightTrajectory());

		left.configureEncoder(RobotMap.drivetrain.getLeftTicks(), RobotMap.dtTicksPerRotation, 2 * RobotMap.dtDriveWheelRadius / 12.0);
		right.configureEncoder(RobotMap.drivetrain.getRightTicks(), RobotMap.dtTicksPerRotation, 2 * RobotMap.dtDriveWheelRadius / 12.0);
		
		// output velocity will be linear velocity of wheels between -1 and 1
		left.configurePIDVA(1.0, 0.0, 0.0, 1.0 / RobotMap.nvMaxVelocity, 0);
		right.configurePIDVA(1.0, 0.0, 0.0, 1.0 / RobotMap.nvMaxVelocity, 0);
	}
	
	public boolean isFinished()
	{
		return left.isFinished() || right.isFinished();
	}
		
	public void navigate()
	{
		double l = left.calculate(RobotMap.drivetrain.getLeftTicks());
		double r = right.calculate(RobotMap.drivetrain.getRightTicks());
	
		double gyro_heading = RobotMap.drivetrain.getAngle();
		double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

		double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
		double turn = 0.8 * (-1.0/80.0) * angleDifference;

	    System.out.printf("Navigator: Output - %f, %f, %f, %f, %f, %f, %f\n", l, r, turn, gyro_heading, desired_heading, RobotMap.drivetrain.getLeftTicks(), RobotMap.drivetrain.getRightTicks());

		// output velocity will be linear velocity of wheels between -1 and 1
//		RobotMap.drivetrain.setVelocity(l + turn, r - turn);
	    RobotMap.drivetrain.setVelocity(l, l);
	}
	
	@Override
	public void initDefaultCommand() {
	}
	
	public void reset() {
		RobotMap.drivetrain.reset();
    	System.out.println("Navigator: Reset completed");
	}
}