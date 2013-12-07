/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.bullbots.stevie;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.bullbots.stevie.controller.JoystickController;
import org.bullbots.stevie.hardware.DriveTrain;
import org.bullbots.util.UserDebug;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
    private static JoystickController leftJoystick;
    private static JoystickController rightJoystick;
    private DriveTrain driveTrain;
    
    private final double P = 0.0;
    private final double I = 0.0;
    private final double D = 0.0;
            
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	UserDebug.print("Entered robotInit().\n--\n--\n--");
	leftJoystick = new JoystickController(1);
	rightJoystick = new JoystickController(2);			    // CORRECT NUMBER???
        	
	rightJoystick.setEnabled(false); // Set to one joystick by default
	
	driveTrain = new DriveTrain(P, I, D);
	UserDebug.print("Finished robotInit().\n--\n--\n--");
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
	UserDebug.print("Entered autonomousPeriodic().");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
	driveTrain.driveTestJag(1.0);
	
	// FOR FULLY DRIVING ROBOT ONLY
	//if(isDualJoystickMode()) dualJoystick();
	//else singleJoystick(); //agem
	
	// No other code should be here, code should be distributed to single/doubleJoystick methods
    }
    
    /**
     * Drives using a single joystick
     */
    private void singleJoystick() {
	JoystickController enabledJoystick = getEnabledJoystick();
	
	// Only driving with the joystick in use
	driveTrain.driveUsingVoltage(enabledJoystick.getYAxis(), enabledJoystick.getXAxis());
	
	checkJoysticks();
    }
    
    /**
     * Drives using two joysticks
     */
    private void dualJoystick() {
	driveTrain.driveUsingVoltage(leftJoystick.getYAxis(), rightJoystick.getYAxis());	    // MAY HAVE TO FLIP TO NEGATIVE VALUE
	
	checkJoysticks();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
	UserDebug.print("---> WARNING <---: Entered testPeriodic().");
    }
    
    private void checkJoysticks(){
	if(leftJoystick.isButtonDown(10)) leftJoystick.setEnabled(!leftJoystick.isEnabled());
	if(rightJoystick.isButtonDown(10)) rightJoystick.setEnabled(!rightJoystick.isEnabled());
    }
    
    public static boolean isDualJoystickMode() {
	return (leftJoystick.isEnabled() && rightJoystick.isEnabled());
    }
    
    public JoystickController getEnabledJoystick() {
	if(leftJoystick.isEnabled()) return leftJoystick;
	return rightJoystick;
    }
}
