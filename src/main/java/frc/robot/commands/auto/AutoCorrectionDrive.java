// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.subsystems.DriveTrain;

public class AutoCorrectionDrive extends CommandBase {

  private DriveTrain driveTrain;

  private double rightSpeed = AutonomousConstants.AUTO_SPEED;
  private double leftSpeed = AutonomousConstants.AUTO_SPEED;
  private double turnSpeed = AutonomousConstants.AUTO_TURNSPEED;

  private double angle = driveTrain.getGyroAngle();
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
    driveSpeed(rightSpeed, leftSpeed);
    if(angle>=20){
      driveSpeed(0, turnSpeed);
    }else if(angle<=-20){
      driveSpeed(turnSpeed,0);
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
