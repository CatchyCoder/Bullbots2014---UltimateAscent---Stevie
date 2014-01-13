package org.bullbots.stevie.hardware;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import org.bullbots.util.UserDebug;

/**
 * @author Clay Kuznia
 */
public class Hopper {
    
    private DigitalInput topSwitch, bottomSwitch;
    private Victor vic;
    
    private final double VIC_SPEED = -1.0;
    
    public Hopper() {
        UserDebug.print("\nooo\nooo\nooo\nEntered Hopper().");
        topSwitch = new DigitalInput(8);
        bottomSwitch = new DigitalInput(2);
        vic = new Victor(4);
        UserDebug.print("Exited Hopper().\nooo\nooo\nooo");
    }
    
    public void enableMotor(boolean value) {
        // If motor is enabled
        if(value) {
            vic.set(VIC_SPEED);
        }
        else {
            vic.set(0.0);
        }
    }
    
    public boolean topSlotFull() {
        return topSwitch.get();
    }
    
    public boolean bottomSlotFull() {
        return bottomSwitch.get();
    }
    
    public boolean isEmpty() {
        return !(topSlotFull() && bottomSlotFull());
    }
}
