// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  private final NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-neon");

  private NetworkTableEntry tx = limelight.getEntry("tx");
  private NetworkTableEntry ty = limelight.getEntry("ty");
  private NetworkTableEntry tv = limelight.getEntry("tv");
  private NetworkTableEntry ta = limelight.getEntry("ta");
  /** Creates a new Vision. */
  public Vision() {
    
  }

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

  //sets LED state, on = 3 off = 1
  public void setLEDMode(String mode){
    if(mode.equals("on")){
      limelight.getEntry("ledMode").setNumber(3);
    }else if(mode.equals("off")){
      limelight.getEntry("ledMode").setNumber(1);
    }
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Horizontal Offset", getHorizontalOffset());
    SmartDashboard.putNumber("Vertical Offset", getVerticalOffset());
    SmartDashboard.putBoolean("Target Sensing", hasValidTarget());
    SmartDashboard.putNumber("Area", getDistance());
  }
}
