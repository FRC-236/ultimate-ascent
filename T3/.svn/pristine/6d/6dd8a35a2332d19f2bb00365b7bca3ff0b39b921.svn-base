
package ticks.robot2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ticks.robot2013.commands.climber.AutoDeployTest;
import ticks.robot2013.commands.climber.AutoDeployer;
import ticks.robot2013.commands.climber.ManualDeployer;
import ticks.robot2013.commands.drive.DriveWithCamera;
import ticks.robot2013.commands.shooter.ExtendShooter;
import ticks.robot2013.commands.shooter.MoveServo;
import ticks.robot2013.commands.shooter.RetractShooter;
import ticks.robot2013.commands.shooter.SetShooterSpeed;
import ticks.robot2013.commands.shooter.SlowShotTimedFire;
import ticks.robot2013.commands.shooter.TimedFire;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick stick1, stick2, stick3, stick4;
    JoystickButton shooterPreset1, shooterPreset2, feedDisc;
    JoystickButton cameraAlign;
    JoystickButton shooterUp, shooterDown;
    JoystickButton manualDeploy, autoDeployClimb, autoDeployDock;
    JoystickButton stopShooter, slowShooter;
    JoystickButton start, back;
    /**
     * Default constructor
     */
    public OI(){
        stick1 = new Joystick(1);
        stick2 = new Joystick(2);
        stick3 = new Joystick(3);
        stick4 = new Joystick(4);
        start = new JoystickButton(stick3, 7);
        back = new JoystickButton(stick3, 8);
        start.whenPressed(new MoveServo(true));
        back.whenPressed(new MoveServo(false));
        shooterPreset1 = new JoystickButton(stick3, RobotMap.JoystickMap.preset1);
        shooterPreset2 = new JoystickButton(stick3, RobotMap.JoystickMap.preset2);
        feedDisc = new JoystickButton(stick3, RobotMap.JoystickMap.feedDisc);
        feedDisc.whenPressed(new TimedFire());
        cameraAlign = new JoystickButton(stick1, RobotMap.JoystickMap.cameraAlignment);
        cameraAlign.whileHeld(new DriveWithCamera());
        shooterUp = new JoystickButton(stick3, RobotMap.JoystickMap.shooterUp);
        shooterUp.whenPressed(new ExtendShooter());
        shooterDown = new JoystickButton(stick3, RobotMap.JoystickMap.shooterDown);
        shooterDown.whenPressed(new RetractShooter());
        manualDeploy = new JoystickButton(stick3, RobotMap.JoystickMap.manualDeploy);
        autoDeployClimb = new JoystickButton(stick3, RobotMap.JoystickMap.deployClimb);
        autoDeployDock = new JoystickButton(stick3, RobotMap.JoystickMap.deployDock);
        manualDeploy.whenPressed(new ManualDeployer());
        autoDeployClimb.whenPressed(new AutoDeployer(RobotMap.ClimberMap.climbingVoltage));
        autoDeployDock.whenPressed(new AutoDeployer(RobotMap.ClimberMap.dockingVoltage));
        slowShooter = new JoystickButton(stick3, RobotMap.JoystickMap.slowSpeed);
        slowShooter.whenPressed(new SetShooterSpeed(RobotMap.ShooterConstants.slow, false));
        stopShooter = new JoystickButton(stick3, RobotMap.JoystickMap.stopShooter);
        stopShooter.whenPressed(new SetShooterSpeed(0, false));
        
        
        
        //doesn't ignore boost
        shooterPreset1.whenPressed(new SetShooterSpeed(RobotMap.ShooterConstants.preset1, false));
        shooterPreset2.whenPressed(new SetShooterSpeed(RobotMap.ShooterConstants.preset2, false));
        SmartDashboard.putData(new AutoDeployTest());
        SmartDashboard.putData(new SlowShotTimedFire());
    }
    
    /**
     * Gets the speed boost axis of the joystick
     * @return The speed boost in RPM
     */
    public double speedBoost(){
        return (this.getUSB3().getRawAxis(RobotMap.JoystickMap.boosterAxis))*200;
    }
    
    /**
     * Gets the joystick object for USB1
     * @return
     */
    public Joystick getUSB1(){
        return stick1;
    }
    
    /**
     * Gets the Joystick object for USB 2
     * @return
     */
    public Joystick getUSB2(){
        return stick2;
    }
    
    /**
     * Gets the Joystick object of USB3
     * @return
     */
    public Joystick getUSB3(){
        return stick3;
    }
    
    /**
     * Gets the Joystick object for USB 4
     * @return
     */
    public Joystick getUSB4(){
        return stick4;
    }
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

