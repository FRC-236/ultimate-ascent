/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import ticks.robot2013.RobotMap;

/**
 *
 * @author team236
 */
public class ShooterStopper extends Subsystem {
    private DoubleSolenoid stopper;
    
    /**
     * default constructor
     */
    public ShooterStopper(){
        stopper = new DoubleSolenoid(RobotMap.ShooterMap.shooterStopperSolenoidA, RobotMap.ShooterMap.shooterStopperSolenoidB);
    }
    
    /**
     * releases the stopper
     */
    public void releaseStopper(){
        stopper.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * activates the stopper
     */
    public void activateStopper(){
        stopper.set(DoubleSolenoid.Value.kReverse);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    /**
     * default command
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
