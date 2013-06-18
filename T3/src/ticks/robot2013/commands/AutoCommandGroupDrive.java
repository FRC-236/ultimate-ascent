/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import ticks.robot2013.commands.drive.AutoDrive;
import ticks.robot2013.commands.shooter.ExtendShooter;
import ticks.robot2013.commands.shooter.RetractShooter;
import ticks.robot2013.commands.shooter.SetShooterSpeedinAuto;
import ticks.robot2013.commands.shooter.TimedFire;

/**
 * Drives back a bit, lines up, and SHOOTS!
 * @author team236
 */
public class AutoCommandGroupDrive extends CommandGroup {
    double backUpSpeed = 0.4;
    double backUpTime = 1;
    double autoShootSpeed = 2900;
    
    public AutoCommandGroupDrive() {
        //back up...
        addParallel(new RetractShooter());
        addSequential(new PrintCommand("starting auto drive cmd group"));
        addSequential(new SetShooterSpeedinAuto(autoShootSpeed, true));
        addSequential(new TimedFire());
        addSequential(new SetShooterSpeedinAuto(autoShootSpeed, true));
        addSequential(new WaitCommand(1));
        addSequential(new TimedFire());
        addSequential(new SetShooterSpeedinAuto(autoShootSpeed, true));
        addSequential(new WaitCommand(1));
        addSequential(new TimedFire());
        addSequential(new AutoDrive(1, 0.5, 0), 2.0);
        addSequential(new AutoDrive(0, -0.5, 0), 0.2);
        addSequential(new ExtendShooter());
    }
}
