// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class FollowDrive extends CommandBase {
  private DriveTrain driveTrain;
  private Vision vision;

  private double speed = AutonomousConstants.AUTO_SPEED;
  /** Creates a new FollowDrive. */
  public FollowDrive(DriveTrain driveTrain, Vision vision) {
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
      if(vision.getDistance()>=16){
        driveTrain.tankDrive(-speed, -speed);
      }else if(vision.getDistance()<=14){
        driveTrain.tankDrive(speed, speed);
      }

      if(vision.getHorizontalOffset()>=21){
        driveTrain.tankDrive(speed, -speed);
      }else if(vision.getHorizontalOffset()<=19){
        driveTrain.tankDrive(-speed, speed);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
