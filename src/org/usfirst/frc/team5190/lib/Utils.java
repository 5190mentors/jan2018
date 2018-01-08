package org.usfirst.frc.team5190.lib;

public class Utils {
	public static float calculateFGain(float power, float feetPerSecond, float wheelSize, float sensorUnitsPerRotation) {
		return (power * 1023) / ((feetPerSecond * 12.0f) / (wheelSize * (float) Math.PI * 2) / 10.0f * sensorUnitsPerRotation);
	}

	public static double feetPerSecondToRPM(double feetPerSecond, double wheelRadius) {
		return (feetPerSecond * 12.0) / (wheelRadius * Math.PI * 2.0) * 60.0;
	}
	public static double ticksToInches(int encPosition, int driveTicksPerRotation, double wheelRadius) {
		double rotations = (double) encPosition / (double) driveTicksPerRotation;
		double distancePerRotation = wheelRadius * 2 * Math.PI;
		return rotations * distancePerRotation;
	}
}
