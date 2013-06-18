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
public class AutoDeployer extends CommandBase {
    double setpoint;
    public AutoDeployer(double setpoint) {
        requires(climberDeployment);
        this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        climberDeployment.setSetpoint(setpoint);
        climberDeployment.runAuto();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(climberDeployment.getVoltage()-setpoint)<0.1;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
