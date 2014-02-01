package org.bullbots.stevie.controller;

import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Clay Kuznia
 */
public class JoystickController {
    
    private final Joystick JOYSTICK;
    
    private boolean enabled;
    
    private final double DEADBAND = 0.05;
    
    public JoystickController(int port, boolean enabled) {
	JOYSTICK = new Joystick(port);
        this.enabled = enabled;
    }
    
    public double getXAxis(){
	if(!enabled) return 0.0;
	
	double value = JOYSTICK.getRawAxis(1);
	if(Math.abs(value) > DEADBAND) return value;
	return 0;
    }
    
    public double getYAxis(){
	if(!enabled) return 0.0;
	
	double value = JOYSTICK.getRawAxis(2);
	if(Math.abs(value) > DEADBAND) return value;
	return 0;
    }
    
    public boolean isButtonDown(int button){
	return JOYSTICK.getRawButton(button);
    }
    
    public boolean isEnabled() {
	return enabled;
    }
    
    public void setEnabled(boolean value) {
	enabled = value;
    }
}
