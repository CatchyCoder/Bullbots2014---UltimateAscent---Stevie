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
        /*if(hopper.isEmpty()) {
            hopper.enableMotor(false);
            cannon.enableMotors(false);
        }
        else {
            cannon.enableMotors(true);
            // If hopper is COMPLETELY full
            if(hopper.isFull()) hopper.enableMotor(false);
            else if(hopper.topSlotFull()) hopper.enableMotor(2000);
            else {
                if(cannon.servosReady()) hopper.enableMotor(false);
                // Only shoot if the and there is a frisbee in the chamber
                if(joystick.isButtonDown(1)) cannon.enableServos(true);
                else cannon.enableServos(false);
            }
            
        }*/
        
        //cannon.enableMotors(true);
        if(hopper.bottomSlotFull()){
            hopper.enableMotor(false);
        }
        else if(hopper.topSlotFull()) hopper.enableMotor(true);
        else {
            //cannon.enableMotors(false);
        }
        if(joystick.isButtonDown(1)) cannon.enableServos(true);
        else cannon.enableServos(false);
        
        if(joystick.isButtonDown(10)) cannon.enableMotors(true);
        else if(joystick.isButtonDown(11)) cannon.enableMotors(false);
    }
}
