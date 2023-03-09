// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Telemetry;

public class DriveForDistance extends CommandBase {

  private DriveTrain driveTrain;
  private Telemetry telemetry;
  private double distanceInInches;

  /** Creates a new DriveForDistance. */
  public DriveForDistance(DriveTrain driveTrain, Telemetry telemetry, double distanceInInches) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveTrain = driveTrain;
    this.telemetry = telemetry;
    this.distanceInInches = distanceInInches;

    addRequirements(driveTrain);
    addRequirements(telemetry);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    telemetry.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(telemetry.getRightEncoderDistance() <= distanceInInches){
      driveTrain.tankDrive(AutonomousConstants.AUTO_SPEED, AutonomousConstants.AUTO_SPEED);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
