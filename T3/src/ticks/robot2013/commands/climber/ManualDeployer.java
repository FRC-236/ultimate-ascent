/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.commands.climber;

import ticks.robot2013.commands.CommandBase;

/**
 *
 * @author team236
 */
public class ManualDeployer extends CommandBase {
    
    public ManualDeployer() {
        requires(climberDeployment);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to runManual
    protected void execute() {
        climberDeployment.runManual();
    }

    // Make this return true when this Command no longer needs to runManual execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to runManual
    protected void interrupted() {
    }
}
