// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TelemetryConstants;

public class Telemetry extends SubsystemBase {
  //sensors
  public ADXRS450_Gyro gyro; 
  public Accelerometer accelmeter;
  public DigitalInput upHallSwitch;
  public DigitalInput downHallSwitch;

  //servos
  public Servo camServo;
  
  
  //cameras
  private UsbCamera camera1;
  private UsbCamera camera2;
  private VideoSink server;
  /** Creates a new Telemetry. */
  public Telemetry() {
    gyro = new ADXRS450_Gyro();
    accelmeter = new BuiltInAccelerometer();
    upHallSwitch = new DigitalInput(TelemetryConstants.MAX_HALLSWITCH_ID);
    downHallSwitch = new DigitalInput(TelemetryConstants.MIN_HALLSWITCH_ID);
    camServo = new Servo(TelemetryConstants.CAM_SERVO_ID);

    camera1 = CameraServer.startAutomaticCapture(0);
    camera2 = CameraServer.startAutomaticCapture(1);
    server = CameraServer.getServer();
  }

  //camera
  public void KeepOpen(){
    camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
  }

  //servo
  public void resetServo(){
    camServo.setAngle(90);
  }
  public void setServoAngle(double angle){
    camServo.setAngle(angle);
  }

  public void setSource1(){
    server.setSource(camera1);
  }
  public void setSource2(){
    server.setSource(camera2);
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Gryo Angle", getGyroAngle());
    
    SmartDashboard.putNumber("AccelX", getAccelX());
    SmartDashboard.putNumber("AccelY", getAccelY());
    SmartDashboard.putNumber("AccelZ", getAccelZ());

    SmartDashboard.putNumber("Cam Servo", camServo.getAngle());
    SmartDashboard.putBoolean("Elevator Up", upHallSwitch.get());
    SmartDashboard.putBoolean("Elevator Down", downHallSwitch.get());
  }
}
