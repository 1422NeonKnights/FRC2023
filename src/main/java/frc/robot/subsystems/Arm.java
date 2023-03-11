// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
  WPI_TalonSRX armMotor;
  /** Creates a new Arm. */
  public Arm() {
        // create motors
        armMotor = new WPI_TalonSRX(ArmConstants.ARM_MOTOR_ID);
    
        // factory reset
        armMotor.configFactoryDefault();
    
        // break mode
        armMotor.setNeutralMode(NeutralMode.Brake);
    
        //peak current
        armMotor.configPeakCurrentLimit(35, 10);
    
        // duartion
        armMotor.configPeakCurrentDuration(200, 10);
    
        //continuous
        armMotor.configContinuousCurrentLimit(30, 10);
    
        // enable
        armMotor.enableCurrentLimit(true);

        armMotor.setInverted(false);
  }


  public void armMove(double speed) {
    armMotor.set(speed * ArmConstants.ARM_MAXI_SPEED);
  }
  public void armStop()
  {
    armMotor.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
