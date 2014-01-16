package org.bullbots.stevie.hardware;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import org.bullbots.util.UserDebug;

/**
 * @author Clay Kuznia
 */
public class Cannon {
    
    // Shooting motors
    private final Victor motor1, motor2;
    
    // Servo shooting switches
    private final Servo leftServo, rightServo;
    
    private final double TOP_SPEED = 1.0;
    private final int TOLERANCE = 3;
    
    public Cannon() {
        UserDebug.print("\nooo\nooo\nooo\nEntered Cannon().");
        motor1 = new Victor(2);
        motor2 = new Victor(3);
        leftServo = new Servo(7);
        rightServo = new Servo(8);
        
        enableServos(false);
        enableMotors(false);
        UserDebug.print("Exited Cannon().\nooo\nooo\nooo");
    }
    
    public void enableMotors(boolean value) {
        // If enabled
        if(value) {
            motor1.set(TOP_SPEED);
            motor2.set(TOP_SPEED);
        }
        else {
            motor1.set(0);
            motor2.set(0.1); // The jag is offset so i need to offset the value (competition robot)
        }
    }
    
    public void enableServos(boolean value) {
        if(value){
            leftServo.setAngle(180.0);
            rightServo.setAngle(0.0);
        }
        else {
            leftServo.setAngle(0.0);
            rightServo.setAngle(180.0);
        }
    }
    
    public boolean servosReady(){
        return (Math.abs(leftServo.get()) <= TOLERANCE && Math.abs(leftServo.get()) <= TOLERANCE);
    }
}
