// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Claw extends SubsystemBase {
  //pneumatics variables
  private Compressor compressor;
  private DoubleSolenoid grabber;
  private boolean clawStat = false;

  //Smartdashboard values
  double compressorPressure;

  /** Creates a new Claw. */
  public Claw() {
    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    grabber = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);

    disableCompressor();
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
  public void pitchDown(){
    grabber.set(DoubleSolenoid.Value.kReverse);
    clawStat = false;
  }
  public void pitchUp(){
    grabber.set(DoubleSolenoid.Value.kForward);
    clawStat = true;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Compressor Status", compressor.isEnabled());
    SmartDashboard.putBoolean("Claw Status", isClawOpen());
    
    SmartDashboard.putNumber("Compressor Voltage", compressor.getCurrent());
  }
  
}
