// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class FollowDrive extends CommandBase {
  private DriveTrain driveTrain;
  private Vision vision;

  private double speed = AutonomousConstants.AUTOCORRECTION_SPEED;

  private double hOffsetAllowance = VisionConstants.HORIZONTAL_OFFSET_ALLOWANCE;
  private double dOffsetAllowanceP = VisionConstants.DISTANCE_CENTER + VisionConstants.DISTANCE_OFFSET_ALLOWANCE;
  private double dOffsetAllowanceN = VisionConstants.DISTANCE_CENTER - VisionConstants.DISTANCE_OFFSET_ALLOWANCE;
  /** Creates a new FollowDrive. */
  public FollowDrive(DriveTrain driveTrain, Vision vision) {
    this.driveTrain = driveTrain;
    this.vision = vision;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    vision.setLEDMode("on");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(vision.hasValidTarget()){

      if(vision.getHorizontalOffset()>=hOffsetAllowance){
        driveTrain.tankDrive(speed, -speed);
      }else if(vision.getHorizontalOffset()<=-hOffsetAllowance){
        driveTrain.tankDrive(-speed, speed);
      }else{
        if(vision.getDistance()>=dOffsetAllowanceP){
          driveTrain.tankDrive(-speed, -speed);
        }else if(vision.getDistance()<=dOffsetAllowanceN){
          driveTrain.tankDrive(speed, speed);
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stopMotors();
    vision.setLEDMode("off");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
