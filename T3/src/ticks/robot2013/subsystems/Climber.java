/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import ticks.robot2013.RobotMap;

/**
 *  Class for interfacing with the climber
 * @author team236
 */

//Climbing positions
//0-not climbing...checking the docking switch
public class Climber extends Subsystem {
    int climberPosition = 1;
    int step3MinDistance = 100;
    int step4MaxDistance = 150;
    int step5MinDistance = 1000;
    int step6MaxDistance = 1050;
    int step8MinDistance = 1000;
    int step9MaxDistance = 1050;
    int step10MinDistance = 1000;
    int step11MaxDistance = 1050;
    int step12MinDistance = 1000;
    int step13MaxDistance = 1000;
    double bHooksEngage = 4;
    double aHooksEngage = 4;
    boolean firstRun1 = true, firstRun2 = true, firstRun3 = true, firstRun4 = true, firstRun5 = true,
            firstRun6 = true, firstRun7 = true, firstRun8 = true, firstRun9 = true, firstRun10 = true,
            firstRun11 = true, firstRun12 = true, firstRun13 = true;
    
    
    //IO declarations
    private DigitalInput limit1, limit2, limit3, limit4, limit5;
    private AnalogChannel potA1, potA2, potB1, potB2;
    private Encoder enc;
    private Relay deploy;
    private SpeedController climber1, climber2;
    
    /**
     * Default constructor
     */
    
    public Climber(){
        limit1 = new DigitalInput(RobotMap.ClimberMap.climberLimit1);
        limit2 = new DigitalInput(RobotMap.ClimberMap.climberLimit2);
        limit3 = new DigitalInput(RobotMap.ClimberMap.climberLimit3);
        limit4 = new DigitalInput(RobotMap.ClimberMap.climberLimit4);
        limit5 = new DigitalInput(RobotMap.ClimberMap.climberLimit5);
        potA1 = new AnalogChannel(RobotMap.ClimberMap.climberPotA1);
        potA2 = new AnalogChannel(RobotMap.ClimberMap.climberPotA2);
        potB1 = new AnalogChannel(RobotMap.ClimberMap.climberPotB1);
        potB2 = new AnalogChannel(RobotMap.ClimberMap.climberPotB2);
        enc = new Encoder(RobotMap.ClimberMap.climberEncA, RobotMap.ClimberMap.climberEncB, false, CounterBase.EncodingType.k4X);
        enc.setDistancePerPulse(1);
        enc.start();
        deploy = new Relay(RobotMap.ClimberMap.deployRelay);
        deploy.setDirection(Relay.Direction.kBoth);
        climber1 = new Victor(RobotMap.ClimberMap.climberPWM1);
        climber2 = new Victor(RobotMap.ClimberMap.climberPWM2);
    }
    
    /**
     *climb will automatically call each of the individual climbing
     * methods
     */
    public void climb(){
        switch(climberPosition){
            case 1: climb1();
                break;
            case 2: climb2();
                break;
            case 3: climb3();
                break;
            case 4: climb4();
                break;
            case 5: climb5();
                break;
            case 6: climb6();
                break;
            case 7: climb7();
                break;
            case 8: climb8();
                break;
            case 9: climb9();
                break;
            case 10: climb10();
                break;
            case 11: climb11();
                break;
            case 12: climb12();
                break;
            case 13: climb13();
                break;
            default: climberStop();
                break;
        }
    }
    
    private void climb1(){
        //wait for docking...
        boolean limitHit = isLimitPushed(1);
        if(firstRun1){
            System.out.println("Waiting for the climber to dock...");
        }
        if(limitHit){
            System.out.println("Climber is docked");
            System.out.println("Moving to step 2");
            climberPosition = 2;
        }
        firstRun1 = false;
        
    }
    
    private void climb2(){
        //deploy the climber if it is not all the way out
        if(isLimitPushed(3)){
            
            //we are all the way on the end
            //just to be safe, make sure we are not also at the beginning
            if(!isLimitPushed(2)){
                System.out.println("climber is all the way out...");
                //in here, we should be all the way deployed..
                climberPosition = 3;
            }
        }else{
           deploy.set(Relay.Value.kForward);
           if(firstRun2){
                System.out.println("Moving Climber out!");
                System.out.println("waiting for climber to get out...");
             }
        }
        firstRun2 = false;
    }
    
    private void climb3(){
        if(firstRun3){
            System.out.println("Step 3 starting");
            System.out.println("Starting Climber Motor");
            enc.reset();
            enc.start();
            enc.reset();
        }
        if(Math.abs(enc.getDistance())<step3MinDistance){
            setClimberMotor(-1);
        }else{
            setClimberMotor(0);
            System.out.println("traveled min distance");
            System.out.println("checking hooks...");
            climberPosition = 4;
        }
        firstRun3 = false;
    }
    
    private void climb4(){
        if((getPot(3)>bHooksEngage)&&(getPot(4)>bHooksEngage)){
            System.out.println("hooks are good!");
            System.out.println("moving on to step 5");
            climberPosition = 5;        
        }else{
            if(Math.abs(enc.getDistance())<step4MaxDistance){
                setClimberMotor(-1);
                if(firstRun4){
                    System.out.println("Hooks aren't there yet...");
                }
            }else{
                System.out.println("Hooks haven't engaged, and we've gone too far!");
                System.out.println("Stopping climb");
                setClimberMotor(0);
                climberPosition = 0;
            }
        }
        firstRun4 = false;
    }
        
    
    
    private void climb5(){
        if(firstRun5){
            enc.reset();
            enc.start();
            System.out.println("Moving Minimum distance for first climb");
        }
        if(Math.abs(enc.getDistance())<step5MinDistance){
            setClimberMotor(1);
        }else{
            setClimberMotor(0);
            climberPosition = 6;
        }
        firstRun5 = false;
    }
    
    private void climb6(){
        if((getPot(1)>aHooksEngage)&&(getPot(2)>aHooksEngage)){
            System.out.println("hooks are good!");
            System.out.println("moving on to step 7");
            climberPosition = 7;        
        }else{
            if(Math.abs(enc.getDistance())<step6MaxDistance){
                setClimberMotor(1);
                if(firstRun6){
                    System.out.println("Hooks aren't there yet...");
                }
                if(isLimitPushed(4)){
                    System.out.println("Hooks haven't engaged, and we've hit the limit switch...");
                    System.out.println("Stopping climb...");
                    setClimberMotor(0);
                    climberPosition = 0;
                }
            }else{
                System.out.println("Hooks haven't engaged, and we've gone too far!");
                System.out.println("Stopping climb");
                setClimberMotor(0);
                climberPosition = 0;
            }
        }
        firstRun6 = false;
    }
    
    private void climb7(){
        //deploy the climber if it is not all the way out
        if(isLimitPushed(2)){
            
            //we are all the way on the end
            //just to be safe, make sure we are not also at the beginning
            if(!isLimitPushed(3)){
                System.out.println("climber is all the way in...");
                //in here, we should be all the way deployed..
                climberPosition = 8;
            }
        }else{
           deploy.set(Relay.Value.kReverse);
           if(firstRun7){
                System.out.println("Moving Climber in!");
                System.out.println("waiting for climber to get in...");
             }
        }
        firstRun7 = false;
    }
    
    private void climb8(){
        if(firstRun8){
            enc.reset();
            enc.start();
            System.out.println("climbing some more!");
        }
        if(Math.abs(enc.getDistance())<step8MinDistance){
            setClimberMotor(-1);
        }else{
            System.out.println("Checking hooks...");
            climberPosition = 9;
        }
        firstRun8 = false;
    }
    //update
    
    private void climb9(){
        if((getPot(3)>aHooksEngage)&&(getPot(4)>aHooksEngage)){
            System.out.println("hooks are good!");
            System.out.println("moving on to step 10");
            climberPosition = 10;        
        }else{
            if(Math.abs(enc.getDistance())<step9MaxDistance){
                setClimberMotor(-1);
                if(firstRun9){
                    System.out.println("Hooks aren't there yet...");
                }
                if(isLimitPushed(4)){
                    System.out.println("Hooks haven't engaged, and we've hit the limit switch...");
                    System.out.println("Stopping climb...");
                    setClimberMotor(0);
                    climberPosition = 0;
                }
            }else{
                System.out.println("Hooks haven't engaged, and we've gone too far!");
                System.out.println("Stopping climb");
                setClimberMotor(0);
                climberPosition = 0;
            }
        }
        firstRun9 = false;
    }
    
    private void climb10(){
        if(firstRun10){
            enc.reset();
            enc.start();
            System.out.println("Moving Minimum distance for climb");
        }
        if(Math.abs(enc.getDistance())<step10MinDistance){
            setClimberMotor(1);
        }else{
            setClimberMotor(0);
            climberPosition = 11;
        }
        firstRun10 = false;
    }
    
    private void climb11(){
        if((getPot(1)>aHooksEngage)&&(getPot(2)>aHooksEngage)){
            System.out.println("hooks are good!");
            System.out.println("moving on to step 7");
            climberPosition = 12;        
        }else{
            if(Math.abs(enc.getDistance())<step11MaxDistance){
                setClimberMotor(1);
                if(firstRun11){
                    System.out.println("Hooks aren't there yet...");
                }
                if(isLimitPushed(4)){
                    System.out.println("Hooks haven't engaged, and we've hit the limit switch...");
                    System.out.println("Stopping climb...");
                    setClimberMotor(0);
                    climberPosition = 0;
                }
            }else{
                System.out.println("Hooks haven't engaged, and we've gone too far!");
                System.out.println("Stopping climb");
                setClimberMotor(0);
                climberPosition = 0;
            }
        }
        firstRun11 = false;
    }
    
    private void climb12(){
        if(firstRun12){
            enc.reset();
            enc.start();
            System.out.println("climbing some more!");
        }
        if(Math.abs(enc.getDistance())<step12MinDistance){
            setClimberMotor(-1);
        }else{
            System.out.println("Checking hooks...");
        }
        firstRun12 = false;
    }
    
    private void climb13(){
        if((getPot(3)>aHooksEngage)&&(getPot(4)>aHooksEngage)){
            System.out.println("hooks are good!");
            System.out.println("Done!");
            climberPosition = 0;        
        }else{
            if(Math.abs(enc.getDistance())<step13MaxDistance){
                setClimberMotor(-1);
                if(firstRun13){
                    System.out.println("Hooks aren't there yet...");
                }
                if(isLimitPushed(4)){
                    System.out.println("Hooks haven't engaged, and we've hit the limit switch...");
                    System.out.println("Stopping climb...");
                    setClimberMotor(0);
                    climberPosition = 0;
                }
            }else{
                System.out.println("Hooks haven't engaged, and we've gone too far!");
                System.out.println("Stopping climb");
                setClimberMotor(0);
                climberPosition = 0;
            }
        }
        firstRun13 = false;
    }
    
    /**
     *Turns off the climber motor and stops the climber deploy relay
     */
    public void climberStop(){
        setClimberMotor(0);
        deploy.set(Relay.Value.kOff);
    }

    /**
     *
     */
    public void initDefaultCommand() {
       
    }
    
    /**
     * Sets the climber motor speed
     * @param speed  the speed of the climber motor from -1 to 1
     */
    public void setClimberMotor(double speed){
        climber1.set(speed);
        climber2.set(speed);
    }
    
    /**
     * Returns if a limit has been pushed
     * @param limit the limit to check
     * @return if the limit has been pushed
     */
    public boolean isLimitPushed(int limit){
        boolean value;
        switch (limit) {
            case 1: value = limit1.get();
                break;
            case 2: value = limit2.get();
                break;
            case 3: value = limit3.get();
                break;
            case 4: value = limit4.get();
                break;
            case 5: value = limit5.get();
                break;
            default: value = false;
                break;
        }
        return value;
    }
    
    /**
     * Gets the position of the climber
     * Use simple to get position outside of class.
     * @return the position measured in pulses
     */
    public double getPosition(){
        return enc.getDistance();
    }
    
    /**
     * Returns the potentiometer voltage for a specific hook pot.
     * @param pot The potentiometer to check.
     * @return The voltage 0-5V
     */
    public double getPot(int pot){
        double value;
        switch (pot) {
            case 1: value = potA1.getVoltage();
                break;
            case 2: value = potA2.getVoltage();
                break;
            case 3: value = potB1.getVoltage();
                break;
            case 4: value = potB2.getVoltage();
                break;
            default: value = 0;
                break;
        }
        return value;
    }
    
    
    /**
     *Sets the speed of the climber motors without using the step method.
     * @param speed Speed for climber motors
     * 
     */
    public void simpleSetClimbSpeed(double speed){
        setClimberMotor(speed);
    }
    
    /**
     * Returns the number of climber encoder pulses since the last reset.
     * @return current climber position in pulses
     */
    public double simpleGetClimbPosition(){
        return enc.getDistance();
    }
    
    /**
     *Resets the climber encoder
     */
    public void simpleResetClimbCounter(){
        enc.reset();   
    }
    
}
