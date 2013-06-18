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
public class ShooterFeeder extends Subsystem {
    private DoubleSolenoid feeder;
    
    /**
     * default constructor
     */
    public ShooterFeeder(){
        feeder = new DoubleSolenoid(RobotMap.ShooterMap.shooterSolenoidA, RobotMap.ShooterMap.shooterSolenoidB);
    }
    
    /**
     * feeds the disc through the shooter
     */
    public void feedDisc(){
        feeder.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Retracts the feeder
     */
    public void retractFeeder(){
        feeder.set(DoubleSolenoid.Value.kReverse);
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
