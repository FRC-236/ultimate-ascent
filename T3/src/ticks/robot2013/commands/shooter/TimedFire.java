/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 *
 * @author team236
 */
public class TimedFire extends CommandGroup {
    
    /**
     * The timed sequence of solenoid commands used to fire the disc.  
     */
    public TimedFire() {
        addSequential(new PrintCommand("retracting stopper"));
        addSequential(new ReleaseStopper(), 0.3);
        addSequential(new PrintCommand("feeding disc"));
        addSequential(new FeedDisc(), 0.6);
        addSequential(new ActivateStopper(), 0.1);
        addSequential(new RetractFeeder(), 0.2);
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
