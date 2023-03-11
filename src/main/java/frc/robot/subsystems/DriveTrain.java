// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrain extends SubsystemBase {

  //define TalonSRX
  WPI_TalonSRX rightMotor1;
  WPI_TalonSRX rightMotor2;
  WPI_TalonSRX leftMotor1;
  WPI_TalonSRX leftMotor2;

  //groups
  MotorControllerGroup leftMotors;
  MotorControllerGroup rightMotors;

  DifferentialDrive differentialDrive;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    //config motors
    // create motors
    rightMotor1 = new WPI_TalonSRX(DriveConstants.DRIVETRAIN_RIGHT_FRONT_TALON);
    rightMotor2 = new WPI_TalonSRX(DriveConstants.DRIVETRAIN_RIGHT_BACK_TALON);
    leftMotor1 = new WPI_TalonSRX(DriveConstants.DRIVETRAIN_LEFT_FRONT_TALON);
    leftMotor2 = new WPI_TalonSRX(DriveConstants.DRIVETRAIN_LEFT_BACK_TALON);

    // factory reset
    rightMotor1.configFactoryDefault();
    rightMotor2.configFactoryDefault();
    leftMotor1.configFactoryDefault();
    leftMotor2.configFactoryDefault();

    // break mode
    rightMotor1.setNeutralMode(NeutralMode.Brake);
    rightMotor2.setNeutralMode(NeutralMode.Brake);
    leftMotor1.setNeutralMode(NeutralMode.Brake);
    leftMotor2.setNeutralMode(NeutralMode.Brake);

    //current limiting
    // peak current
    rightMotor1.configPeakCurrentLimit(35, 10);
    rightMotor2.configPeakCurrentLimit(35, 10);
    leftMotor1.configPeakCurrentLimit(35, 10);
    leftMotor2.configPeakCurrentLimit(35, 10);
    // duration
    rightMotor1.configPeakCurrentDuration(200, 10);
    rightMotor2.configPeakCurrentDuration(200, 10);
    leftMotor1.configPeakCurrentDuration(200, 10);
    leftMotor2.configPeakCurrentDuration(200, 10);
    // continuous
    rightMotor1.configContinuousCurrentLimit(30, 10);
    rightMotor2.configContinuousCurrentLimit(30, 10);
    leftMotor1.configContinuousCurrentLimit(30, 10);
    leftMotor2.configContinuousCurrentLimit(30, 10);
    // enable
    rightMotor1.enableCurrentLimit(true);
    rightMotor2.enableCurrentLimit(true);
    leftMotor1.enableCurrentLimit(true);
    leftMotor2.enableCurrentLimit(true);

    //inversion
    rightMotor1.setInverted(true);
    rightMotor2.setInverted(true);
    leftMotor1.setInverted(false);
    leftMotor2.setInverted(false);

    //Open loop ramp(prevent sudden speed changes)
    rightMotor1.configOpenloopRamp(DriveConstants.DRIVE_RAMP_RATE);
    rightMotor2.configOpenloopRamp(DriveConstants.DRIVE_RAMP_RATE);
    leftMotor1.configOpenloopRamp(DriveConstants.DRIVE_RAMP_RATE);
    leftMotor2.configOpenloopRamp(DriveConstants.DRIVE_RAMP_RATE);
    rightMotor1.configClosedloopRamp(DriveConstants.DRIVE_RAMP_RATE);
    rightMotor2.configClosedloopRamp(DriveConstants.DRIVE_RAMP_RATE);
    leftMotor1.configClosedloopRamp(DriveConstants.DRIVE_RAMP_RATE);
    leftMotor2.configClosedloopRamp(DriveConstants.DRIVE_RAMP_RATE);

    // Decreases power to extend battery life. Can be done manually if needed using motor.configVoltageCompSaturation(VOLTS)
    rightMotor1.configVoltageCompSaturation(DriveConstants.DRIVE_VOLTAGE_COMP);
    rightMotor2.configVoltageCompSaturation(DriveConstants.DRIVE_VOLTAGE_COMP);
    leftMotor1.configVoltageCompSaturation(DriveConstants.DRIVE_VOLTAGE_COMP);
    leftMotor2.configVoltageCompSaturation(DriveConstants.DRIVE_VOLTAGE_COMP);

    rightMotor1.enableVoltageCompensation(true);
    rightMotor2.enableVoltageCompensation(true);
    leftMotor1.enableVoltageCompensation(true);
    leftMotor2.enableVoltageCompensation(true);
    
    // Makes motors stop if they have not been updated in a set amout of time. AKA MOTOR SAFETY
    rightMotor1.setSafetyEnabled(true);
    rightMotor2.setSafetyEnabled(true);
    leftMotor1.setSafetyEnabled(true);
    leftMotor2.setSafetyEnabled(true);

    /* Copy paste code cuz im lazy
     rightMotor1.
     rightMotor2.
     leftMotor1.
     leftMotor2.
    */

    //motor controller groups
    rightMotors = new MotorControllerGroup(rightMotor1, rightMotor2);
    leftMotors = new MotorControllerGroup(leftMotor1, leftMotor2);

    //differential drive for arcade/tank
    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }


  //tank drive
  public void tankDrive(double right, double left){
    differentialDrive.tankDrive(left, right);
  }

  //arcade drive
  public void arcadeDrive(double moveSpeed, double rotateSpeed){
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }

  public void stopMotors(){
    rightMotor1.set(0);
    rightMotor2.set(0);
    leftMotor1.set(0);
    leftMotor2.set(0);
  }


  //average of 4 motors
  public double getAvgTemperature(){
    return (rightMotor1.getTemperature()+rightMotor2.getTemperature()
            +leftMotor1.getTemperature()+leftMotor2.getTemperature())/4;
  }

  //average of 4 motors in percentages
  public double getAvgSpeed(){
    return ((int)Math.abs((rightMotor1.getMotorOutputPercent()* 100)+ (rightMotor2.getMotorOutputPercent() * 100)
            + (leftMotor1.getMotorOutputPercent()* 100)+ (leftMotor2.getMotorOutputPercent()* 100)))/4;
  }

  @Override
  public void periodic() {

    // This method will be called once per scheduler run
    SmartDashboard.putNumber("FR Volts", rightMotor1.getBusVoltage());
    SmartDashboard.putNumber("FL Volts", leftMotor1.getBusVoltage());
    SmartDashboard.putNumber("BR Volts", rightMotor2.getBusVoltage());
    SmartDashboard.putNumber("BL Volts", leftMotor2.getBusVoltage());
    
    SmartDashboard.putNumber("FR Speed", rightMotor1.getMotorOutputPercent()*100);
    SmartDashboard.putNumber("FL Speed", leftMotor1.getMotorOutputPercent()*100);
    SmartDashboard.putNumber("BR Speed", rightMotor2.getMotorOutputPercent()*100);
    SmartDashboard.putNumber("BL Speed", leftMotor2.getMotorOutputPercent()*100);
    SmartDashboard.putNumber("Avg Speed", getAvgSpeed());

    SmartDashboard.putNumber("Average Drive Motor Temperature", getAvgTemperature());
   }
}