/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.commands.drive;

import ticks.robot2013.commands.CommandBase;

/**
 *
 * @author team236
 */
public class DriveWithCameraAuto extends CommandBase {
    
    public DriveWithCameraAuto() {
        requires(drive);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        drive.driveWithCamera();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return drive.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
