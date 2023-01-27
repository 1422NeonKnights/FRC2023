// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
  private final DriveTrain driveTrain;
  private final Joystick leftstick;
  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(DriveTrain driveTrain, Joystick leftstick) {
    this.driveTrain = driveTrain;
    this.leftstick = leftstick;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double moveSpeed = -leftstick.getY() * DriveConstants.MAX_SPEED;
    double rotateSpeed = leftstick.getX() * DriveConstants.MAX_SPEED;

    driveTrain.arcadeDrive(moveSpeed, rotateSpeed);
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
