// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
      public static final class NeoMotorConstants {
      public static final double kFreeSpeedRpm = 5676;
      public static final double kVortexKv = 565;   // rpm/V
    }
  public static final class DriveConstants {
    // Driving Parameters - Note that these are not the maximum capable speeds of
    // the robot, rather the allowed maximum speeds
    // public static final double kMaxSpeedMetersPerSecond = 4.8;
    public static final double kMaxSpeedMetersPerSecond = 15;
    public static final double kMaxAngularSpeed = 2 * Math.PI; // radians per second
    //maybe slow this down?



    // Chassis configuration
    public static final double kTrackWidth = Units.inchesToMeters(21.5); //2.10.2025
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(21.5); //2.10.2025
    // Distance between front and back wheels on robot
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
        new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

    // Angular offsets of the modules relative to the chassis in radians
    public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
    public static final double kFrontRightChassisAngularOffset = 0;
    public static final double kBackLeftChassisAngularOffset = Math.PI;
    public static final double kBackRightChassisAngularOffset = Math.PI / 2;

    // SPARK MAX CAN IDs
    public static final int kFrontLeftDrivingCanId = 1;
    public static final int kRearLeftDrivingCanId = 3;
    public static final int kFrontRightDrivingCanId = 7;
    public static final int kRearRightDrivingCanId = 5;

    public static final int kFrontLeftTurningCanId = 2;
    public static final int kRearLeftTurningCanId = 4;
    public static final int kFrontRightTurningCanId = 8;
    public static final int kRearRightTurningCanId = 6;

    public static final boolean kGyroReversed = false;


        //added to support the flywheel code.

  }
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static final class ModuleConstants {
    // The MAXSwerve module can be configured with one of three pinion gears: 12T,
    // 13T, or 14T. This changes the drive speed of the module (a pinion gear with
    // more teeth will result in a robot that drives faster).
    public static final int kDrivingMotorPinionTeeth = 13;
    // Phil thinks we used medium

    // Calculations required for driving motor conversion factors and feed forward
    public static final double kDrivingMotorFreeSpeedRps = NeoMotorConstants.kFreeSpeedRpm / 60;
    public static final double kWheelDiameterMeters = 0.0762;
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
    // 45 teeth on the wheel's bevel gear, 22 teeth on the first-stage spur gear, 15
    // teeth on the bevel pinion
    public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);
    public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
        / kDrivingMotorReduction;
  }
  
  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    //public static final double kDriveDeadband = 0.05;
     public static final double kDriveDeadband = 0.10;  // KPH added 2/8/22
  }
  public static final class InternalConstants {
    //our constants that aren't declared in the shooter subsystem.
    //should be depreceated soon.
    public static final int kLoaderCanId = 11;
    public static final int kConveyorCanId = 12;

    public static final double kLauncherSpeed = 0.5;
    public static final double kLoaderSpeed = 1.0;
    public static final double kConveyorSpeed = -0.5;
  }
public static final class IntakeConstants {
    public static final int kIntakeCanId = 13;
    public static final double kIntakeSpeed = 60.0;
}

public static final class ShooterSubsystemConstants {
    public static final int kFeederMotorCanId = 11;    // SPARK Flex CAN ID
    public static final int kFlywheelMotorCanId = 9;  // SPARK Flex CAN ID (Right)
    public static final int kFlywheelFollowerMotorCanId = 10;  // SPARK Flex CAN ID (Left)

    public static final class FeederSetpoints {
      public static final double kFeed = 0.95;
    }

    public static final class FlywheelSetpoints {
      //default 5000 rpm
      // public static final double kShootRpm = -4000;
      public static final double kShootRpm = -6000;
      public static final double kVelocityTolerance = 100;
    }
  }
}
