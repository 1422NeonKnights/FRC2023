// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveTimedCommand extends CommandBase {
  private DriveTrain driveTrain;

  private double timeInSeconds;
  private double speedInPercent;

  private Timer timer;
  /** Creates a new DriveTimedCommand. */
  public DriveTimedCommand(DriveTrain driveTrain, double timeInSeconds, double speedInPercent) {
    this.driveTrain = driveTrain;
    this.timeInSeconds = timeInSeconds;
    this.speedInPercent = speedInPercent;

    timer = new Timer();
    
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.tankDrive(speedInPercent, speedInPercent);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() >= timeInSeconds;
  }
}
