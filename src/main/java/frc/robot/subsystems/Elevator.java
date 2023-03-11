// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  
  WPI_TalonSRX towerRightMotor;
  WPI_TalonSRX towerLeftMotor;
  
  /** Creates a new Elevator. */
  public Elevator() {
    // create motors
    towerLeftMotor = new WPI_TalonSRX(ElevatorConstants.TOWER_MOTOR_LEFT_ID);
    towerRightMotor = new WPI_TalonSRX(ElevatorConstants.TOWER_MOTOR_RIGHT_ID);

    // factory reset
    towerLeftMotor.configFactoryDefault();
    towerRightMotor.configFactoryDefault();

    // break mode
    towerLeftMotor.setNeutralMode(NeutralMode.Brake);
    towerRightMotor.setNeutralMode(NeutralMode.Brake);

    //peak current
    towerLeftMotor.configPeakCurrentLimit(35, 10);
    towerRightMotor.configPeakCurrentLimit(35, 10);

    // duartion
    towerLeftMotor.configPeakCurrentDuration(200, 10);
    towerRightMotor.configPeakCurrentDuration(200, 10);

    //continuous
    towerLeftMotor.configContinuousCurrentLimit(30, 10);
    towerRightMotor.configContinuousCurrentLimit(30, 10);

    // enable
    towerLeftMotor.enableCurrentLimit(true);
    towerRightMotor.enableCurrentLimit(true);

    towerRightMotor.setInverted(true);
    towerLeftMotor.setInverted(true);
  }

  public void elevatorMoveUp(double speed) {
    towerRightMotor.set(speed * ElevatorConstants.ELEVATOR_MAX_UP_SPEED);
    towerLeftMotor.set(speed * ElevatorConstants.ELEVATOR_MAX_UP_SPEED);
  }

  public void elevatorMoveDown(double speed) {
    towerRightMotor.set(speed * ElevatorConstants.ELEVATOR_MAX_DOWN_SPEED);
    towerLeftMotor.set(speed * ElevatorConstants.ELEVATOR_MAX_DOWN_SPEED);
  }
  /* 
  public void elevatorDown(double speed) {
    towerRightMotor.set(speed);
    towerLeftMotor.set(speed);
  }
  */
  public void elevatorStop() {
    towerRightMotor.set(0);
    towerLeftMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
