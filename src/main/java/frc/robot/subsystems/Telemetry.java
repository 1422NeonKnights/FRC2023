// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TelemetryConstants;

public class Telemetry extends SubsystemBase {
  //sensors
  public ADXRS450_Gyro gyro; 
  public Accelerometer accelmeter;
  public DigitalInput upHallSwitch;
  public DigitalInput downHallSwitch;
  public DigitalInput upLimitSwitch;
  public DigitalInput downLimitSwitch;

  //odometry/sensing
  public DifferentialDrivePoseEstimator odometry;
  public DifferentialDriveKinematics kinematics;
  public Field2d field = new Field2d();
  public Encoder leftEncoder;
  public Encoder rightEncoder;
  private DifferentialDrivetrainSim driveSim;
  
  //cameras
  private UsbCamera camera1;
  private UsbCamera camera2;
  private VideoSink server;

  /** Creates a new Telemetry. */
  public Telemetry() {
    //gyro
    gyro = new ADXRS450_Gyro();

    //aceelerometer(built in roborio)
    accelmeter = new BuiltInAccelerometer();

    //hall effect switches
    upHallSwitch = new DigitalInput(TelemetryConstants.MAX_HALLSWITCH_ID);
    downHallSwitch = new DigitalInput(TelemetryConstants.MIN_HALLSWITCH_ID);

    //limit switches
    upLimitSwitch = new DigitalInput(TelemetryConstants.UP_LIMITSWITCH_ID);
    downLimitSwitch = new DigitalInput(TelemetryConstants.DOWN_LIMITSWITCH_ID);

    //encoders
    leftEncoder = new Encoder(TelemetryConstants.LEFT_ENCODER_ID, TelemetryConstants.LEFT_ENCODER_ID2);
    rightEncoder = new Encoder(TelemetryConstants.RIGHT_ENCODER_ID, TelemetryConstants.RIGHT_ENCODER_ID2);

    //cameras
    camera1 = CameraServer.startAutomaticCapture(0);
    camera2 = CameraServer.startAutomaticCapture(1);
    server = CameraServer.getServer();

    //configure
    configureEncoders();

    kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(21));
    odometry = new DifferentialDrivePoseEstimator(kinematics, gyro.getRotation2d(), 
                          leftEncoder.getDistance(), rightEncoder.getDistance(), new Pose2d());
  }

  //camera
  public void KeepOpen(){
    camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
    camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
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

  //encoder
  public void configureEncoders(){
    //2048 pulses per rotation
    //robot moves 6pi in per rotation
    leftEncoder.setDistancePerPulse(TelemetryConstants.WHEEL_CIRCUM/2048);
    leftEncoder.setReverseDirection(true);
    rightEncoder.setDistancePerPulse(TelemetryConstants.WHEEL_CIRCUM/2048);
    rightEncoder.setReverseDirection(false);
  }
  public void resetEncoder(){
    rightEncoder.reset();
    leftEncoder.reset();
  }
  public double getRightEncoderDistance(){
    return rightEncoder.getDistance();
  }
  
  public double getLeftEncoderDistance(){
    return leftEncoder.getDistance();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Gryo Angle", getGyroAngle());
    
    SmartDashboard.putNumber("AccelX", getAccelX());
    SmartDashboard.putNumber("AccelY", getAccelY());
    SmartDashboard.putNumber("AccelZ", getAccelZ());

    SmartDashboard.putBoolean("Elevator Up", upHallSwitch.get());
    SmartDashboard.putBoolean("Elevator Down", downHallSwitch.get());

    SmartDashboard.putNumber("Right Encoder", rightEncoder.getDistance());
    SmartDashboard.putNumber("Left Encoder", leftEncoder.getDistance());

    SmartDashboard.putBoolean("Arm Max", upLimitSwitch.get());
    SmartDashboard.putBoolean("Arm Min", downLimitSwitch.get());
  }
}
