// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class LockTargetCommand extends CommandBase {

  private DriveTrain driveTrain;
  private Vision vision;

  private double turnSpeed = AutonomousConstants.AUTOCORRECTION_TURNSPEED;
  private double hOffsetAllowance = VisionConstants.HORIZONTAL_OFFSET_ALLOWANCE;

  /** Creates a new LockTargetCommand. */
  public LockTargetCommand(DriveTrain driveTrain, Vision vision) {
    this.driveTrain = driveTrain;
    this.vision = vision;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(vision.hasValidTarget()){
      //position robot towards target if detected.
      if(vision.getHorizontalOffset() >= hOffsetAllowance){
        driveTrain.tankDrive(turnSpeed, -turnSpeed);
      }
      if(vision.getHorizontalOffset() <= -hOffsetAllowance){
        driveTrain.tankDrive(-turnSpeed, turnSpeed);
      }
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
