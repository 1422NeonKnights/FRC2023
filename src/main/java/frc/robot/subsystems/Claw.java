// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {
  //pneumatics variables
  private Compressor compressor;
  private DoubleSolenoid leftGrabber;
  private DoubleSolenoid rightGrabber;

  private boolean clawStat = false;

  /** Creates a new Claw. */
  public Claw() {
    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    leftGrabber = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    rightGrabber = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
  }

  public void enableCompressor(){
    compressor.enableDigital();
  }

  public void disableCompressor(){
    compressor.disable();
  }

  public boolean isClawOpen(){
    if(clawStat){
      return true;
    }else{
      return false;
    }
  }

  public void coneOpenClaw(){
    leftGrabber.set(Value.kReverse);
    rightGrabber.set(Value.kReverse);
    clawStat = false;
  }

  public void cubeOpenClaw(){
    leftGrabber.set(Value.kReverse);
    clawStat = false;
  }
  
  public void closeClaw(){
    leftGrabber.set(Value.kForward);
    rightGrabber.set(Value.kForward);
    clawStat = true;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Compressor Status", compressor.isEnabled());
    SmartDashboard.putBoolean("Claw Status", isClawOpen());
  }
  
}
