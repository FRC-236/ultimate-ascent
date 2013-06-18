package ticks.robot2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import ticks.robot2013.OI;
import ticks.robot2013.subsystems.Climber;
import ticks.robot2013.subsystems.ClimberDeployment;
import ticks.robot2013.subsystems.Deflector;
import ticks.robot2013.subsystems.Drive;
import ticks.robot2013.subsystems.ShooterExtender;
import ticks.robot2013.subsystems.ShooterFeeder;
import ticks.robot2013.subsystems.ShooterStopper;
import ticks.robot2013.subsystems.ShooterWheel;
import ticks.robot2013.subsystems.SimpleClimber;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    /**
     *
     */
    public static OI oi;
    public static ClimberDeployment climberDeployment = new ClimberDeployment();
    // Create a single static instance of all of your subsystems
    /**
     *
     */
    public static ShooterWheel shooterWheel = new ShooterWheel();
    /**
     *
     */
    public static ShooterFeeder shooterFeeder = new ShooterFeeder();
    /**
     *
     */
    public static ShooterStopper shooterStopper = new ShooterStopper();
    /**
     *
     */
    public static Drive drive = new Drive();
    public static Deflector deflector = new Deflector();
    /**
     *
     */
    /**
     * not started!!!!!!
     */
    //public static Climber climber = new Climber();
    /**
     *
     */
    public static ShooterExtender shooterExtender = new ShooterExtender();
    /**
     *
     */
    public static SimpleClimber simpleClimber = new SimpleClimber();
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
    }

    /**
     *
     * @param name
     */
    public CommandBase(String name) {
        super(name);
    }

    /**
     *
     */
    public CommandBase() {
        super();
    }
}
