package org.bullbots.stevie.hardware;

import org.bullbots.stevie.Robot;

/**
 * @author Clay Kuznia
 */
public class DriveTrain {
    
    private final Jaguar TEST_JAG;
    
    private final DualJaguar LEFT_DUAL_JAG;
    private final DualJaguar RIGHT_DUAL_JAG;
    private final DualJaguar WINCH_DUAL_JAG;
    
    public DriveTrain(double p, double i, double d) {
	TEST_JAG = new Jaguar(6, p, i, d);		    // Configure what jag to use
	
	/*LEFT_DUAL_JAG = new DualJaguar(1, 1, p, i, d);	    // CONFIGURE ALL BOARD-ID's
	RIGHT_DUAL_JAG = new DualJaguar(1, 1, p, i, d);
	WINCH_DUAL_JAG = new DualJaguar(1, 1, p, i, d);*/
	LEFT_DUAL_JAG = null;
	RIGHT_DUAL_JAG = null;
	WINCH_DUAL_JAG = null;
    }
    
    public void driveTestJag(double voltage) {
	TEST_JAG.driveUsingVoltage(voltage);
    }
    
    public void driveUsingVoltage(double forwardVoltage, double turnVoltage) {
	if(!Robot.isDualJoystickMode()) {
	    double leftVoltage = -(forwardVoltage - turnVoltage);
	    double rightVoltage = (forwardVoltage + turnVoltage);
	    
	    LEFT_DUAL_JAG.driveUsingVoltage(leftVoltage);
	    RIGHT_DUAL_JAG.driveUsingVoltage(rightVoltage);	    
	}
	else { // Using two joysticks
	    double leftVoltage = forwardVoltage;
	    double rightVoltage = turnVoltage;
	    LEFT_DUAL_JAG.driveUsingVoltage(leftVoltage);
	    RIGHT_DUAL_JAG.driveUsingVoltage(rightVoltage);
	}
    }
    
    public void driveUsingSpeed(double forwardRPM, double turnRPM) {		// MAY NEED A SPEED FACTOR (100)
	if(!Robot.isDualJoystickMode()) {
	    double leftRPM = -(forwardRPM - turnRPM);
	    double rightRPM = (forwardRPM + turnRPM);

	    LEFT_DUAL_JAG.driveUsingSpeed(leftRPM);
	    RIGHT_DUAL_JAG.driveUsingSpeed(rightRPM);
	}
	else { // Using two joysticks
	    double leftRPM = forwardRPM;
	    double rightRPM = turnRPM;
	    LEFT_DUAL_JAG.driveUsingVoltage(leftRPM);
	    RIGHT_DUAL_JAG.driveUsingVoltage(rightRPM);
	}
    }
    
    public void driveUsingPosition(double forwardRotations, double turnRotations) {
	if(!Robot.isDualJoystickMode()) {
	    double leftRotations = -(forwardRotations - turnRotations);
	    double rightRotations = (forwardRotations + turnRotations);

	    LEFT_DUAL_JAG.driveUsingPosition(leftRotations);
	    RIGHT_DUAL_JAG.driveUsingPosition(rightRotations);
	}
	else { // Using two joysticks
	    double leftRotations = forwardRotations;
	    double rightRotations = turnRotations;
	    LEFT_DUAL_JAG.driveUsingVoltage(leftRotations);
	    RIGHT_DUAL_JAG.driveUsingVoltage(rightRotations);
	}
    }
}
