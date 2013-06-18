/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.commands.shooter;

import ticks.robot2013.commands.CommandBase;

/**
 * This command is used to set the speed of the shooter.
 * It will finish automatically when the shooter gets to speed
 * Also, it adds the boost if ignoreSpeedBoost is false
 * @author team236
 */
public class SetShooterSpeedinAuto extends CommandBase {
    double setSpeed;
    boolean ignoreBoost;
    /**
     *
     * @param setSpeed
     * @param ignoreSpeedBoost
     */
    public SetShooterSpeedinAuto(double setSpeed, boolean ignoreSpeedBoost) {
        this.setSpeed = setSpeed;
        ignoreBoost = ignoreSpeedBoost;
        requires(shooterWheel);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    /**
     *
     */
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    /**
     *
     */
    protected void execute() {
        if(ignoreBoost){
            shooterWheel.shooterControl.setSpeed(setSpeed);
        }else{
            shooterWheel.shooterControl.setSpeed(setSpeed+oi.speedBoost());
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     *
     * @return
     */
    protected boolean isFinished() {
        return shooterWheel.shooterControl.atSpeed();
    }

    // Called once after isFinished returns true
    /**
     *
     */
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    /**
     *
     */
    protected void interrupted() {
    }
}
