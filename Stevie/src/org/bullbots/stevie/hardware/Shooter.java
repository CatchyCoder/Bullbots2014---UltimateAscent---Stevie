package org.bullbots.stevie.hardware;

import org.bullbots.stevie.controller.JoystickController;
import org.bullbots.util.UserDebug;

/**
 * @author Clay Kuznia
 */
public class Shooter {
    
    // victor lifter number is 5
    
    public Cannon cannon = new Cannon();
    public Hopper hopper = new Hopper();
    
    private boolean autoMode = true;
    
    public void update() {
        System.out.println("\nShooter.fire");
        // If the first slot is loaded, fire
        if(hopper.bottomSlotFull()) {
            UserDebug.print("slot 2 full");
            ///cannon.fire();
        }
	else {
            UserDebug.print("slot 2 empty");
            //cannon.setServoPositions(180.0);
	    // If there is a frisbee in slot 1 and the servos are ready, move it into slot 2
            if(hopper.topSlotFull() && cannon.servosReady()) {
                hopper.enableMotor(true);
                UserDebug.print("slot 1 full");
            }
            // If no frisbees are left, stop the wheels
	    else {
                UserDebug.print("slot 1 empty");
                hopper.enableMotor(false);
            }
        }
        
	// If all slots are empty, stop the wheels
        if(!hopper.topSlotFull() && !hopper.bottomSlotFull() && cannon.servosReady()) cannon.enableMotors(false);
    }
    
    public void updateSingleJoystick(JoystickController joystick) {
        // If there are no frisbees in the hopper
        if(hopper.topSlotFull() ) {
            cannon.enableMotors(true);
            if(hopper.bottomSlotFull()) hopper.enableMotor(false);
            else
        }
        
        
        
        
        
        // FIRST ATTEMPT
        /*if(joystick.isButtonDown(8)) autoMode = true;
        else if(joystick.isButtonDown(9)) autoMode = false;
        
        // User wants to spin wheel manually
        if(!autoMode) {
            if(joystick.isButtonDown(10)) cannon.enableMotors(true);
            else if(joystick.isButtonDown(11)) cannon.enableMotors(false);
        }
        
        if(hopper.slot2Full()) {
            hopper.enableMotor(false);
            if(autoMode) cannon.enableMotors(true);
            
            if(joystick.isButtonDown(2)) cannon.enableServos(true);
        }
        else {
            if(autoMode) cannon.enableMotors(hopper.slot1Full());
            cannon.enableServos(false);
            
           hopper.enableMotor(hopper.slot1Full());
        }*/
        
        
        // OLD
        /*if(hopper.slot2Full()) cannon.enableServos(true);
        else{
            cannon.enableServos(false);
            if(hopper.slot1Full() && cannon.servosReady()) hopper.enableMotor(true);
            else hopper.enableMotor(false);
        }
        if(!(hopper.slot1Full() && hopper.slot2Full()) && cannon.servosReady()) cannon.enableMotors(false);*/
    }
}
