// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public class DriveConstants{
    //ID values
    public static final int DRIVETRAIN_LEFT_BACK_TALON = 2;  //2 2
    public static final int DRIVETRAIN_LEFT_FRONT_TALON = 1; //1 3
    public static final int DRIVETRAIN_RIGHT_FRONT_TALON = 0; //0 4
    public static final int DRIVETRAIN_RIGHT_BACK_TALON = 3; //3 5

    //Joystick ID values
    public static final int LEFT_JOY = 0;
    public static final int RIGHT_JOY = 1;
    public static final int XBOX_JOY = 2;

    public static final double MAX_SPEED = 0.87;

    public static final double DRIVE_RAMP_RATE = 0.35;
    public static final double DRIVE_VOLTAGE_COMP = 9;
  }

  public class AutonomousConstants{
    //Autonomous
    public static final double AUTO_SPEED = 0.65;
    public static final double AUTO_TURNSPEED = 0.60;  
    public static final int AUTO_DRIVE_TIME = 3;

    //Autocorrection
    public static final double AUTOCORRECTION_SPEED = 0.55;
    public static final double AUTOCORRECTION_TURNSPEED = 0.50;
    public static final double AUTOCORRECTION_ANGLE = 6.9;
  }

  public class ElevatorConstants{
    //ID values
    public static final int TOWER_MOTOR_RIGHT_ID = 5; 
    public static final int TOWER_MOTOR_LEFT_ID = 4;
    public static final double ELEVATOR_MAX_SPEED = 0.4;

  }

  public class ArmConstants{
    public static final int ARM_MOTOR_ID = 6;
    public static final double ARM_MAXI_SPEED = 0.3;
  }

  public class VisionConstants{
    public static final double HORIZONTAL_CENTER = 0;
    public static final double VERTICAL_CENTER = 0;
    public static final double DISTANCE_CENTER = 0;

    public static final double HORIZONTAL_OFFSET_ALLOWANCE = 10;
    public static final double VERTICAL_OFFSET_ALLOWANCE = 7;
    public static final double DISTANCE_OFFSET_ALLOWANCE = 0.5;
    public static final double LIMELIGHT_MOUNT_ANGLE = 0;

    //inch
    public static final double CALLIBRATED_DISTANCE = 46;
    public static final double LIMELIGHT_MOUNT_HEIGHT = 11.5;
    public static final double GOAL_HEIGHT = 25;
  }

  public class ClawConstants{

  }

  public class TelemetryConstants{
    //ID values
    public static final int UP_LIMITSWITCH_ID = 4;
    public static final int DOWN_LIMITSWITCH_ID = 5;

    public static final int MAX_HALLSWITCH_ID = 6;
    public static final int MIN_HALLSWITCH_ID = 7;

    public static final int RIGHT_ENCODER_ID = 0;
    public static final int RIGHT_ENCODER_ID2 = 1;

    public static final int LEFT_ENCODER_ID = 2;
    public static final int LEFT_ENCODER_ID2 = 3;

    //encoder
    public static final double WHEEL_CIRCUM = 6*(Math.PI);
  }

  public static class Conversions{
    public static double AngleToRadians(double angle){
      return angle * (Math.PI/180);
    }

    public static double tickToInches(double ticks){
      return ticks * (Math.PI/90);
    }
  }
}
