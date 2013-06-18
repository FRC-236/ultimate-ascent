package ticks.robot2013;


  
 
public class RobotMap {
    public static final int compressorDIO = 2,
    compressorRelay = 2;

    public static class ShooterMap{
        public static final int shooterPWM1 = 2,
        counter = 1,
        shooterSolenoidA = 6,
        shooterSolenoidB = 5,
        shooterStopperSolenoidA = 3,
        shooterStopperSolenoidB = 4;
        public static final int  cameraLight = 1;
        public static final int shooterExtenderSolenoidA = 1,
        shooterExtenderSolenoidB = 2;
    }
    public static class ShooterConstants{
        public static final double preset1 = 3000,
        preset2 = 3600;
        public static final double slow = 600;
    }
    public static class JoystickMap{
        public static final int preset1 = 5,
        preset2 = 6;
        public static final int boosterAxis = 3;
        public static final int feedDisc = 1;
        public static final int shooterUp = 4,
        shooterDown = 3;
        public static final int cameraAlignment = 1;
        public static final int manualDeploy = 2, deployDock = 7, deployClimb = 8;
        public static final int slowSpeed = 9, stopShooter = 10;
    }
    public static class DriveMap{
        public static final int frontLeft = 1,
        frontRight = 6,
        rearLeft = 3,
        rearRight = 8;
    }
    public static class ClimberMap{
        public static final int climberPWM1 = 4,
        climberPWM2 = 7,
        deployRelay = 5;
        public static final int climberEncA = 7,
        climberEncB = 8;
        public static final int climberLimit1 = 3,
        climberLimit2 = 4,
        climberLimit3 = 5,
                climberLimit4 = 6,
        climberLimit5 = 11;
        public static final int climberPotA1 = 5,
        climberPotA2 = 2,
        climberPotB1 = 3,
        climberPotB2 = 4;
        public static final int hookLimit1 = 14, hookLimit2 = 13;
        public static final int deploymentPot = 1;
        public static final int dockingVoltage = 0, climbingVoltage = 0;
    }
}
