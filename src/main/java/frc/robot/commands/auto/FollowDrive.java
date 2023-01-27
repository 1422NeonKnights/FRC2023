// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class FollowDrive extends CommandBase {
  private DriveTrain driveTrain;
  private Vision vision;

  private double speed = DriveConstants.AUTO_SPEED;
  /** Creates a new FollowDrive. */
  public FollowDrive(DriveTrain driveTrain, Vision vision) {
    this.driveTrain = driveTrain;
    this.vision = vision;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(vision.hasValidTarget()){
      driveTrain.tankDrive(speed, speed);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
