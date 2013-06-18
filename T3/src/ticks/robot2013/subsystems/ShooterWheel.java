/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ticks.robot2013.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Timer;
import ticks.robot2013.RobotMap;

/**
 *
 * @author team236
 */
public class ShooterWheel extends Subsystem {

    /**
     * main shooter control object
     */
    public ShooterSpeedControl shooterControl;
    private java.util.Timer timer;
    double mode;
    //1-bang-bang...
    //2-pid w/ integrated output...
    //3-pid w/ ff velocity term...
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    /**
     * Default shooter
     */
    public ShooterWheel() {

        shooterControl = new ShooterSpeedControl();
        timer = new Timer();
        //calls the shooterControl.run method in a seperate thread
        timer.scheduleAtFixedRate(shooterControl, 0, 10);
        //NOTE--Consider adding a "slow mode"  instead of 0-1, use 0-0.
    }

    /**
     * The speed control class for the shooter
     */
    public class ShooterSpeedControl extends java.util.TimerTask implements PIDOutput, PIDSource {

        private Counter counter;
        private SpeedController motor1;
        private double setSpeed = 0;
        private boolean firstCross;
        private double lastSpeed;
        int i = 0;
        private PIDController mode2Pid;
        private double kP, kV;

        /**
         * Default constructor
         */
        public ShooterSpeedControl() {
            counter = new Counter(RobotMap.ShooterMap.counter);
            motor1 = new Victor(RobotMap.ShooterMap.shooterPWM1);
            counter.start();
            counter.setMaxPeriod(1);
            //maintains speed control at low speeds
            mode2Pid = new PIDController(0, 0, 0, this, this);
            mode2Pid.setOutputRange(-0.03, 0.03);
            mode2Pid.enable();

        }

        /**
         * Run.....
         */
        public void run() {
            mode = 1;
            i++;
            double currentSpeed = 60 / counter.getPeriod();
            if (mode == 2) {
                mode2Pid.setSetpoint(setSpeed);
                motor1.set(range(1, 0, mode2Pid.get() + motor1.get()));
            } else if (mode == 3) {
                motor1.set((kP * (setSpeed - currentSpeed)) + kV * setSpeed);
            } else {
                double lowspeed = 0;
                double highspeed = 1;

                if (setSpeed < 1500) {
                    lowspeed = 0.1;
                    highspeed = 0.25;
                } else {
                    lowspeed = 0.2;
                    highspeed = 1;
                }
                if (setSpeed == 0) {
                    motor1.set(0);
                } else {
                    if (currentSpeed > setSpeed) {
                        motor1.set(lowspeed);
                    } else {
                        motor1.set(highspeed);
                    }
                }
            }
            if (i == 10) {
                SmartDashboard.putNumber("Shooter Speed", currentSpeed);
                SmartDashboard.putNumber("Desired Speed", setSpeed);
                SmartDashboard.putNumber("Output Speed", motor1.get());
                SmartDashboard.putBoolean("At Speed", withinTolerance(currentSpeed, setSpeed, 20) && (!isGoingUp(currentSpeed, lastSpeed)));
                lastSpeed = currentSpeed;
                i = 0;
            }

        }

        /**
         * Sets the speed of the shooter
         *
         * @param setSpeed shooter speed in RPM...
         */
        public synchronized void setSpeed(double setSpeed) {
            this.setSpeed = setSpeed;
        }

        private synchronized double range(double upper, double lower, double value) {
            double output = value;
            if (output > upper) {
                output = upper;
            }
            if (output < lower) {
                output = lower;
            }
            return output;
        }

        /**
         * Returns the current speed.
         *
         * @return the speed (:
         */
        public synchronized double getSpeed() {
            return setSpeed;
        }

        public boolean withinTolerance(double in1, double in2, double tolerance) {
            return Math.abs(in1 - in2) < tolerance;
        }

        public boolean isGoingUp(double current, double last) {
            if ((current > last)) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * Returns if the shooter is within 100 rpm of setpoint Maybe it should
         * be less?
         *
         * @return boolean atSpeed
         */
        public synchronized boolean atSpeed() {
            return Math.abs(this.getSpeed() - (60 / counter.getPeriod())) < 20;

        }

        public void pidWrite(double output) {
        }

        public double pidGet() {
            return 60 / counter.getPeriod();
        }
    }

    /**
     * Default command
     */
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
