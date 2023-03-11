// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Conversions;
import frc.robot.Constants.VisionConstants;

public class Vision extends SubsystemBase {
  private final NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-neon");

  private NetworkTableEntry tx = limelight.getEntry("tx");
  private NetworkTableEntry ty = limelight.getEntry("ty");
  private NetworkTableEntry tv = limelight.getEntry("tv");
  private NetworkTableEntry ta = limelight.getEntry("ta");

  private NetworkTableEntry camMode = limelight.getEntry("camMode");
  private NetworkTableEntry ledMode = limelight.getEntry("ledMode");
  /** Creates a new Vision. */
  public Vision() {}

  public boolean hasValidTarget(){
    return tv.getDouble(0) == 1;
  }

  public double getHorizontalOffset(){
    return tx.getDouble(0);
  }

  public double getVerticalOffset(){
    return ty.getDouble(0);
  }

  public double getDistance(){
    return ta.getDouble(0);
  }

  //sets LED state, on = 3 off = 1 blinking = 2
  public void setLEDMode(String mode){
    if(mode.equals("on")){
      ledMode.setNumber(3);
    }else if(mode.equals("off")){
      ledMode.setNumber(1);
    }else if(mode.equals("blinking")){
      ledMode.setNumber(2);
    }
  }

  //vision processor = 0, drive cam = 1
  public void setCamMode(String mode){
    if(mode.equals("vision")){
      camMode.setNumber(0);
    }else if(mode.equals("drive")){
      camMode.setNumber(1);
    }
  }

  //d = (h2-h1) / tan(a1+a2)
  //distance = height difference / tan(angle from parallel)
  public double getLLGoalDistance() {
    return (VisionConstants.GOAL_HEIGHT - VisionConstants.LIMELIGHT_MOUNT_HEIGHT) 
            / Math.tan(VisionConstants.LIMELIGHT_MOUNT_ANGLE + Conversions.AngleToRadians(getVerticalOffset()));
  }

  //a1 = arctan((h2-h1)/d) - a2
  public double getLLMountAngle() {
    return (Math.atan((VisionConstants.GOAL_HEIGHT - VisionConstants.LIMELIGHT_MOUNT_HEIGHT)/VisionConstants.CALLIBRATED_DISTANCE) 
            - Conversions.AngleToRadians(getVerticalOffset()));
  }

  


  @Override
  public void periodic() {
    SmartDashboard.putNumber("Horizontal Offset", getHorizontalOffset());
    SmartDashboard.putNumber("Vertical Offset", getVerticalOffset());
    SmartDashboard.putBoolean("Target Sensing", hasValidTarget());
    SmartDashboard.putNumber("Area", getDistance());
    SmartDashboard.putNumber("Goal Distance", getLLGoalDistance());
  }
}
