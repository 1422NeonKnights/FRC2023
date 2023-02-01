// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrain extends SubsystemBase {

  public ADXRS450_Gyro gyro; 
  public Accelerometer accelmeter;
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
    rightMotor1.setInverted(false);
    rightMotor2.setInverted(false);
    leftMotor1.setInverted(true);
    leftMotor2.setInverted(true);

    //Open loop ramp(prevent sudden speed changes)
    rightMotor1.configOpenloopRamp(DriveConstants.DRIVE_RAMP_RATE, 15);
    rightMotor2.configOpenloopRamp(DriveConstants.DRIVE_RAMP_RATE, 15);
    leftMotor1.configOpenloopRamp(DriveConstants.DRIVE_RAMP_RATE, 15);
    leftMotor2.configOpenloopRamp(DriveConstants.DRIVE_RAMP_RATE, 15);

    //motor controller groups
    rightMotors = new MotorControllerGroup(rightMotor1, rightMotor2);
    leftMotors = new MotorControllerGroup(leftMotor1, leftMotor2);

    //differential drive for arcade/tank
    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

    gyro = new ADXRS450_Gyro();
    accelmeter = new BuiltInAccelerometer();
  }

  //gryo
  public void resetGyroAngle() {
    gyro.reset();
  }
  public double getGyroAngle() {
    return gyro.getAngle();
  }

  //accel
  public double getAccelZ() {
    return accelmeter.getZ();
  }
  public double getAccelY() {
    return accelmeter.getY();
  }
  public double getAccelX() {
    return accelmeter.getX();
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
    
    SmartDashboard.putNumber("FR Speed", Math.abs(rightMotor1.getMotorOutputPercent()*100));
    SmartDashboard.putNumber("FL Speed", Math.abs(leftMotor1.getMotorOutputPercent()*100));
    SmartDashboard.putNumber("BR Speed", Math.abs(rightMotor2.getMotorOutputPercent()*100));
    SmartDashboard.putNumber("BL Speed", Math.abs(leftMotor2.getMotorOutputPercent()*100));
    SmartDashboard.putNumber("Avg Speed", getAvgSpeed());

    SmartDashboard.putNumber("Average Drive Motor Temperature", getAvgTemperature());

    SmartDashboard.putNumber("Gryo Angle", getGyroAngle());
    
    SmartDashboard.putNumber("AccelX", getAccelX());
    SmartDashboard.putNumber("AccelY", getAccelY());
    SmartDashboard.putNumber("AccelZ", getAccelZ());
   }
}