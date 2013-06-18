/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import java.util.Timer;
import java.util.TimerTask;
import ticks.robot2013.RobotMap;
import ticks.robot2013.commands.CommandBase;
import ticks.robot2013.commands.climber.SimpleClimberSimpleControl;

/**
 *
 * @author team236
 */
public class SimpleClimber extends Subsystem {
    boolean rampEnabled = true;
    
    double rampOutput;
    private Encoder enc;
    private SpeedController climber1, climber2;
    private DigitalInput  min, max;
    public double SETPOINT_1 = 1000, SETPOINT_2 = 1000, SETPOINT_3 = 1000, SETPOINT_4 = 1000;
    private double currentPosition;
    private ClimbLock climbLock;
    private java.util.Timer timer;
    private DigitalInput hook1, hook2;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public SimpleClimber(){
        timer = new Timer();
        climbLock = new ClimbLock();
        timer.scheduleAtFixedRate(climbLock, 0L, 50);
        enc = new Encoder(RobotMap.ClimberMap.climberEncA, RobotMap.ClimberMap.climberEncB, false, CounterBase.EncodingType.k1X);
        enc.start();
        climber1 = new Victor(RobotMap.ClimberMap.climberPWM1);
        climber2 = new Victor(RobotMap.ClimberMap.climberPWM2);
        min = new DigitalInput(RobotMap.ClimberMap.climberLimit3);
        max = new DigitalInput(RobotMap.ClimberMap.climberLimit4);
        hook1 = new DigitalInput(RobotMap.ClimberMap.hookLimit1);
        hook2 = new DigitalInput(RobotMap.ClimberMap.hookLimit2);
    }
    
    public boolean hook1Pressed(){
        return hook1.get();
    }
    
    
    public boolean hook2Pressed(){
        return hook2.get();
    }
    
    private class ClimbLock extends TimerTask {
        private boolean isLocked = true;

        public void run() {
            System.out.println(CommandBase.oi.getUSB1().getRawButton(1));
            if(CommandBase.oi.getUSB1().getRawButton(1)&&CommandBase.oi.getUSB2().getRawButton(1)){
                isLocked = false;
            }
        }
        
        public synchronized boolean isLocked(){
            return isLocked;
        }
        
        private boolean unlocked(){
            System.out.println(CommandBase.oi.getUSB1().getRawButton(1));
            System.out.println(CommandBase.oi.getUSB2().getRawButton(1));
            return CommandBase.oi.getUSB1().getRawButton(1)&&CommandBase.oi.getUSB2().getRawButton(1);
        }
        
    }
    
    public void climbToSetpoint(double desiredSetpoint){
        
        
        double climberOutput = 0;
        currentPosition = enc.getDistance();
        if(currentPosition>desiredSetpoint){
            if(!inTolerance(currentPosition, desiredSetpoint, 100)){
                climberOutput = 1;
                
            }
        }else{
            if(!inTolerance(currentPosition, desiredSetpoint, 100)){
                climberOutput = -1;
            }
        }
        if(rampEnabled){
            setClimberSpeed(ramp(climberOutput));
        }else{
            setClimberSpeed(climberOutput);
        }
    }
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        //this will stop any positional control and replace it with a joystick for the climber.
        
        setDefaultCommand(new SimpleClimberSimpleControl());
    }
    
    public void setClimberSpeed(double speed){
        if(min.get()&&(speed<0)){
            climber1.set(0);
            climber2.set(0);
        }else if(max.get()&&(speed>0)) {
            climber1.set(0);
            climber2.set(0);
        }else{
            climber1.set(speed);
            climber2.set(speed);
        }
        
    }
    
    public double climberPosition(){
        return enc.get();
    }
    
    public boolean inTolerance(double in1, double in2, double tolerance){
        return Math.abs(in1-in2)<tolerance;
    }
    private double ramp(double input){
        if(rampOutput>1){
            rampOutput = 1;
        }
        if(rampOutput<-1){
            rampOutput = -1;
        }
        if(input<rampOutput){
            rampOutput = rampOutput - 0.06;
        }
        if(input>rampOutput){
            rampOutput = rampOutput + 0.06;
        }
        return rampOutput;
    }
}
