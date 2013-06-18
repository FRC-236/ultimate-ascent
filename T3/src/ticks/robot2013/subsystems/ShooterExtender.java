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
public class ShooterExtender extends Subsystem {
    private DoubleSolenoid extend;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    
    /**
     * default constructor
     */
    public ShooterExtender(){
        extend = new DoubleSolenoid(RobotMap.ShooterMap.shooterExtenderSolenoidA, RobotMap.ShooterMap.shooterExtenderSolenoidB);
    }
    
    
    /**
     * moves the shooter to the second position
     */
    public void extendShooter(){
        extend.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * moves the shooter to the first position
     */
    public void retractShooter(){
        extend.set(DoubleSolenoid.Value.kReverse);
    }
    /**
     * default command
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
