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
  public Compressor compressor;
  DoubleSolenoid grabber;

  //Smartdashboard values
  double compressorPressure;

  /** Creates a new Claw. */
  public Claw() {
    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    grabber = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  }

  public double getCompressorPressure(){
    return compressor.getPressure();
  }

  public boolean isClawOpen(){
    if(grabber.isRevSolenoidDisabled()){
      return true;
    }else{
      return false;
    }
  }
  public void pitchDown(){
    grabber.set(DoubleSolenoid.Value.kReverse);
  }
  public void pitchUp(){
    grabber.set(DoubleSolenoid.Value.kForward);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Claw Status", isClawOpen());
    SmartDashboard.putNumber("Compressure Pressure", getCompressorPressure());
  }
}
