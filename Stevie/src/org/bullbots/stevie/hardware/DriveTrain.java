package org.bullbots.stevie.hardware;

import org.bullbots.stevie.Robot;

/**
 * @author Clay Kuznia
 */
public class DriveTrain {
    
    // For test jags only
    private final Jaguar TEST_JAG, TEST_JAG2, TEST_JAG3, TEST_JAG4;
    
    private final DualJaguar LEFT_DUAL_JAG;
    private final DualJaguar RIGHT_DUAL_JAG;
    private final DualJaguar WINCH_DUAL_JAG;
    
    public DriveTrain(double p, double i, double d) {
        LEFT_DUAL_JAG = new DualJaguar(6, 1, p, i, d);
	RIGHT_DUAL_JAG = new DualJaguar(4, 3, p, i, d);
	WINCH_DUAL_JAG = null;
        
        // For testing jags only
	TEST_JAG = new Jaguar(1, p, i, d);
        TEST_JAG2 = new Jaguar(4, p, i, d);
        TEST_JAG3 = new Jaguar(6, p, i, d);
        TEST_JAG4 = new Jaguar(3, p, i, d);
    }
    
    public void driveTestJag(double voltage) {
        // For testing jags only
	try{
            TEST_JAG.driveUsingVoltage(voltage);
            Thread.sleep(2000);
            TEST_JAG2.driveUsingVoltage(voltage);
            Thread.sleep(2000);
            TEST_JAG3.driveUsingVoltage(voltage);
            Thread.sleep(2000);
            TEST_JAG4.driveUsingVoltage(voltage);
            Thread.sleep(2000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
