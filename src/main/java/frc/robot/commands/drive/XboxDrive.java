// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;

public class XboxDrive extends CommandBase {
  
  private DriveTrain driveTrain;
  private XboxController xboxStick;

  /** Creates a new XboxDrive. */
  public XboxDrive(DriveTrain driveTrain, XboxController xboxStick) {
    this.driveTrain = driveTrain;
    this.xboxStick = xboxStick;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double moveSpeed = xboxStick.getLeftY() * DriveConstants.MAX_SPEED;
    double rotateSpeed = xboxStick.getRightX() * DriveConstants.MAX_SPEED;

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
