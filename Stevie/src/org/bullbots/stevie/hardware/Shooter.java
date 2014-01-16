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
    
    public void update(JoystickController joystick) {
        // If there are no frisbees in the hopper
        if(hopper.isEmpty()) {
            hopper.enableMotor(false);
            cannon.enableMotors(false);
        }
        else {
            cannon.enableMotors(true);
            // If hopper is COMPLETELY full
            if(hopper.isFull()) hopper.enableMotor(false);
            else if(hopper.topSlotFull()) hopper.enableMotor(2000);
            else {
                hopper.enableMotor(false);
                // Only shoot if the servos are ready and there is a frisbee in the chamber
                if(joystick.isButtonDown(1) && cannon.servosReady()) cannon.enableServos(true);
            }
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
