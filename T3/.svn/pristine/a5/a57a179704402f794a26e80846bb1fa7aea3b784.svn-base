/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import ticks.robot2013.commands.shooter.ExtendShooter;
import ticks.robot2013.commands.shooter.RetractShooter;
import ticks.robot2013.commands.shooter.SetShooterSpeedinAuto;
import ticks.robot2013.commands.shooter.TimedFire;

/**
 *
 * @author team236
 */
public class AutoCommandGroup extends CommandGroup {
    
    /**
     *
     */
    public AutoCommandGroup() {
        addParallel(new RetractShooter());
        addSequential(new SetShooterSpeedinAuto(2900, true));
        addSequential(new WaitCommand(1));
        addSequential(new TimedFire());
        addSequential(new SetShooterSpeedinAuto(2900, true));
        addSequential(new WaitCommand(1));
        addSequential(new TimedFire());
        addSequential(new SetShooterSpeedinAuto(2900, true));
        addSequential(new WaitCommand(1));
        addSequential(new TimedFire());
        addSequential(new ExtendShooter());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
