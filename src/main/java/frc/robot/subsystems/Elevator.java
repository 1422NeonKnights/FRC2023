// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  
  WPI_TalonSRX towerMotor;
  WPI_TalonSRX jibMotor;

  
  /** Creates a new Elevator. */
  public Elevator() {
    // create motors
    towerMotor = new WPI_TalonSRX(ElevatorConstants.TOWER_MOTOR_ID);
    jibMotor = new WPI_TalonSRX(ElevatorConstants.JIB_MOTOR_ID);

    // factory reset
    towerMotor.configFactoryDefault();
    jibMotor.configFactoryDefault();

    // break mode
    towerMotor.setNeutralMode(NeutralMode.Brake);
    jibMotor.setNeutralMode(NeutralMode.Brake);

    //peak current
    towerMotor.configPeakCurrentLimit(35, 10);
    jibMotor.configPeakCurrentLimit(35, 10);

    // duartion
    towerMotor.configPeakCurrentDuration(200, 10);
    jibMotor.configPeakCurrentDuration(200, 10);

    //continuos
    towerMotor.configContinuousCurrentLimit(30, 10);
    jibMotor.configContinuousCurrentLimit(30, 10);

    // enable
    towerMotor.enableCurrentLimit(true);
    jibMotor.enableCurrentLimit(true);
  }

  public void elevatorUp() {
    towerMotor.set(1);
  }
  public void elevatorDown() {
    towerMotor.set(-1);
  }
  public void elevatorStop() {
    towerMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
