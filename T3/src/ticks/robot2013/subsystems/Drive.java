/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ticks.robot2013.RobotMap;
import ticks.robot2013.commands.CommandBase;
import ticks.robot2013.commands.drive.DriveWithJoysticks;

/**
 *
 * @author team236
 */
public class Drive extends Subsystem implements PIDOutput, PIDSource{
    private SpeedController frontLeft, frontRight, rearLeft, rearRight;
    private RobotDrive drive;
    private PIDController pid;
    private double rampOutputx;
    private double rampOutputy;
    private double rampOutputr;
    
    /**
     * Default constructor
     */
    public Drive(){
        frontLeft = new Victor(RobotMap.DriveMap.frontLeft);
        frontRight = new Victor(RobotMap.DriveMap.frontRight);
        rearLeft = new Victor(RobotMap.DriveMap.rearLeft);
        rearRight = new Victor(RobotMap.DriveMap.rearRight);
        drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
        pid = new PIDController(0, 0, 0, this, this);
        pid.enable();
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        SmartDashboard.putData("Turing PID", pid);
    }
    
    /**
     * Normal drive method for use in driving with Joysticks
     */
    public void teleopDriveRamp(){
        //joysticks
        drive.mecanumDrive_Cartesian(rampx(CommandBase.oi.getUSB1().getX()), rampy(CommandBase.oi.getUSB1().getY()), rampr(CommandBase.oi.getUSB2().getX()), 0);
        //drive.mecanumDrive_Cartesian(CommandBase.oi.getUSB3().getRawAxis(1), CommandBase.oi.getUSB3().getRawAxis(2), CommandBase.oi.getUSB3().getRawAxis(6), 0);
    }
    
    public void teleopDrive(){
        drive.mecanumDrive_Cartesian(CommandBase.oi.getUSB1().getX(), CommandBase.oi.getUSB1().getY(), CommandBase.oi.getUSB2().getX(), 0);
    }
    
    public void autoDrive(double x, double y, double rotation){
        drive.mecanumDrive_Cartesian(x, y, rotation, 0);
    }
    
    /**
     * Drives the robot with information from the camera
     * If the camera is not connected, or our version of the dashboard software is not running,
     * it will not move.
     */
    public void driveWithCamera(){
        drive.mecanumDrive_Cartesian(0, 0, pid.get(), 0);
        SmartDashboard.putNumber("PID output", pid.get());
    }
    
     private double rampx(double input){
        if(rampOutputx>1){
            rampOutputx = 1;
        }
        if(rampOutputx<-1){
            rampOutputx = -1;
        }
        if(input<rampOutputx){
            rampOutputx = rampOutputx - 1;
        }
        if(input>rampOutputx){
            rampOutputx = rampOutputx + 1;
        }
        return rampOutputx;
    }
     private double rampy(double input){
        if(rampOutputy>1){
            rampOutputy = 1;
        }
        if(rampOutputy<-1){
            rampOutputy = -1;
        }
        if(input<rampOutputy){
            rampOutputy = rampOutputy - 1;
        }
        if(input>rampOutputy){
            rampOutputy = rampOutputy + 1;
        }
        return rampOutputy;
    }
     private double rampr(double input){
        if(rampOutputr>1){
            rampOutputr = 1;
        }
        if(rampOutputr<-1){
            rampOutputr = -1;
        }
        if(input<rampOutputr){
            rampOutputr = rampOutputr - 1;
        }
        if(input>rampOutputx){
            rampOutputr = rampOutputr + 1;
        }
        return rampOutputr;
    }
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    

    /**
     *
     */
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    /**
     *
     * @param output
     */
    public void pidWrite(double output) {
    }

    /**
     *
     * @return
     */
    public double pidGet() {
        return SmartDashboard.getNumber("coordinate1", 0);
    }
    
    public boolean onTarget(){
        return Math.abs(pidGet())<0.2;
    }
}