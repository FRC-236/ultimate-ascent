/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ticks.robot2013;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ticks.robot2013.commands.AutoCommandGroup;
import ticks.robot2013.commands.AutoCommandGroupDrive;
import ticks.robot2013.commands.AutoCommandGroupDriveStraight;
import ticks.robot2013.commands.CommandBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

//transition between teleop/autonomous is now working... Live window mode still doesn't work  :(
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    SendableChooser chooser;
    Compressor compressor;
    Relay cameraRelay;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().freeMemory());
        SmartDashboard.putString("Analog Potato", "........");
        //sets up compressor to run automatically
        compressor = new Compressor(RobotMap.compressorDIO, RobotMap.compressorRelay);
        compressor.start();
        cameraRelay = new Relay(RobotMap.ShooterMap.cameraLight, Relay.Direction.kForward);
        //puts the sendable chooser (radio buttons) on the smart dashboard
        //for different auto modes, different objects need to be added with
        //chooser.putObject("name", new yourCommand);
        chooser = new SendableChooser();
        chooser.addObject("shoot", new AutoCommandGroup());
        chooser.addDefault("shoot and drive", new AutoCommandGroupDrive());
        chooser.addObject("shoot and drive straight", new AutoCommandGroupDriveStraight());
        SmartDashboard.putData("auto mode", chooser);
        autonomousCommand = new AutoCommandGroupDrive();
        // instantiate the command used for the autonomous period
       
        //autonomousCommand = new AutoCommandGroupDrive();
        // Initialize all subsystems
        CommandBase.init();
        System.out.println("Analog Potato");
        SmartDashboard.putString("Analog Potato", "Analog Potato");
        SmartDashboard.putNumber("Preset 3 Set speed", 0);
        SmartDashboard.putNumber("testingDeploySetpoint", 0);
        System.out.println(Runtime.getRuntime().freeMemory());
        
    }

    /**
     *
     */
    public void autonomousInit() {
         autonomousCommand = (CommandGroup) chooser.getSelected();
        // schedule the autonomous command (example)
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        cameraRelay.set(Relay.Value.kForward);
    }

    /**
     *
     */
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        cameraRelay.set(Relay.Value.kOn);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
