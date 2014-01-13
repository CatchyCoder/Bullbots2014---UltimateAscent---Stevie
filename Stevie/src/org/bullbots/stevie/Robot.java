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
import org.bullbots.stevie.hardware.Shooter;
import org.bullbots.util.UserDebug;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
    private final double P = 0.0;
    private final double I = 0.0;
    private final double D = 0.0;
    
    private static JoystickController joystick1, joystick2;
    
    private DriveTrain driveTrain;
    private Shooter shooter;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	UserDebug.print("\nooo\nooo\nooo\nEntered robotInit().");
        
        /* NOTE: If only one joystick is connected, then the last instantiated JoystickController
         * class is the object that will have control of the joystick. (Therefore NEVER disable
         * joystick2 with only one joystick connected)
         */
        joystick1 = new JoystickController(1, false);
        joystick2 = new JoystickController(2, true);
        
        driveTrain = new DriveTrain(P, I, D);
        shooter = new Shooter();
        
        UserDebug.print("Exited robotInit().\nooo\nooo\nooo");
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
	if(isDualJoystickMode()) dualJoystick();
	else singleJoystick();
        
	// No other code should be here, code should be distributed to single/doubleJoystick methods
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
	UserDebug.print("---> WARNING <---: Entered testPeriodic().");
    }
    
    /**
     * Drives using a single joystick
     */
    private void singleJoystick() {
        // Finding out what joystick is enabled
	JoystickController enabledJoystick = getEnabledJoystick();
	
	// Only driving with the joystick in use
	//driveTrain.driveUsingVoltage(enabledJoystick.getYAxis(), enabledJoystick.getXAxis());
        driveTrain.driveTestJag(1.0);
        
        shooter.updateSingleJoystick(enabledJoystick);
	
	checkJoystickActivation();
    }
    
    /**
     * Drives using two joysticks
     */
    private void dualJoystick() {
	driveTrain.driveUsingVoltage(joystick1.getYAxis(), joystick2.getYAxis());	    // MAY HAVE TO FLIP TO NEGATIVE VALUE
	
	checkJoystickActivation();
    }
    
    private void checkJoystickActivation(){
        final int ENABLE_BUTTON = 4;
        final int DISABLE_BUTTON = 7;
        
	if(joystick1.isButtonDown(ENABLE_BUTTON)) joystick1.setEnabled(true);
        else if(joystick1.isButtonDown(DISABLE_BUTTON)) joystick1.setEnabled(false);
        
        if(joystick2.isButtonDown(ENABLE_BUTTON)) joystick2.setEnabled(true);
        else if(joystick2.isButtonDown(DISABLE_BUTTON))joystick2.setEnabled(false);
    }
    
    public static boolean isDualJoystickMode() {
	return (joystick1.isEnabled() && joystick2.isEnabled());
    }
    
    public JoystickController getEnabledJoystick() {
	if(joystick1.isEnabled()) return joystick1;
	return joystick2;
    }
}
