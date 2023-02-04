// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.subsystems.DriveTrain;

public class AutoCorrectionDrive extends CommandBase {

  private DriveTrain driveTrain;

  private double turnSpeed = AutonomousConstants.AUTOCORRECTION_TURNSPEED;
  private double correctionAngle = AutonomousConstants.AUTOCORRECTION_ANGLE;

  private double angle;
  /** Creates a new AutoCorrectionDrive. */
  public AutoCorrectionDrive(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.resetGyroAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    angle = driveTrain.getGyroAngle();

    if(angle>=correctionAngle){
      driveSpeed(-turnSpeed, turnSpeed);
    }else if(angle<=-correctionAngle){
      driveSpeed(turnSpeed,-turnSpeed);
    }
  }

  public void driveSpeed(double rightSpeed, double leftSpeed){
    driveTrain.tankDrive(rightSpeed, leftSpeed);
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
