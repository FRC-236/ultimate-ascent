/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ticks.robot2013.RobotMap;
import ticks.robot2013.commands.CommandBase;
import ticks.robot2013.commands.climber.ManualDeployer;

/**
 *
 * @author team236
 */
public class ClimberDeployment extends Subsystem implements PIDSource, PIDOutput {
    SpeedController deploy;
    AnalogChannel deployPot;
    PIDController pid;
    DigitalInput min, max;
    
    
    public ClimberDeployment(){
        deploy = new Victor(RobotMap.ClimberMap.deployRelay);
        min = new DigitalInput(RobotMap.ClimberMap.climberLimit1);
        max = new DigitalInput(RobotMap.ClimberMap.climberLimit2);
        deployPot = new AnalogChannel(RobotMap.ClimberMap.deploymentPot);
        pid = new PIDController(-5, 0, 0, this, this);
        pid.setInputRange(0, 5);
        pid.enable();
        SmartDashboard.putData("deploymentPID", pid);
    }
    
    public void runManual(){
       
        double speed = CommandBase.oi.getUSB3().getRawAxis(2);
        if(min.get()&&(speed<0)){
            deploy.set(0);
        }else if(max.get()&&(speed>0)) {
            deploy.set(0);
        }else{
            deploy.set(speed);
        }
        SmartDashboard.putNumber("deployPosition", deployPot.getAverageVoltage());
    }
    
    public void runAuto(){
        if(Math.abs(pid.get())<0.1){
            deploy.set(0);
        }else{
            deploy.set(pid.get());
        }
        
        SmartDashboard.putNumber("deployPosition", deployPot.getAverageVoltage());
    }
    
    public double getVoltage(){
        return deployPot.getVoltage();
    }
    
    public void setSetpoint(double setpoint){
        pid.setSetpoint(setpoint);
    }
            
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new ManualDeployer());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public double pidGet() {
        return deployPot.getAverageVoltage();
    }

    public void pidWrite(double output) {
    }
}
