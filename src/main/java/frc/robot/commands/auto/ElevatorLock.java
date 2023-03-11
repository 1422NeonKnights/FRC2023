// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Vision;

public class ElevatorLock extends CommandBase {
  private DriveTrain driveTrain;
  private Elevator elevator;
  private Vision vision;

  private double vOffsetAllowance = VisionConstants.VERTICAL_OFFSET_ALLOWANCE;
  private double elevatorUpSpeed = ElevatorConstants.ELEVATOR_MAX_UP_SPEED;
  private double elevatorDownSpeed = ElevatorConstants.ELEVATOR_MAX_DOWN_SPEED;

  private double speed = 0.3;
  private double offset = 5;
  /** Creates a new ElevatorLock. */
  public ElevatorLock(DriveTrain driveTrain, Elevator elevator, Vision vision) {
    this.driveTrain = driveTrain;
    this.elevator = elevator;
    this.vision = vision;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
    addRequirements(elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    vision.setLEDMode("on");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //move down if its too up
    if(vision.getVerticalOffset() >=  vOffsetAllowance){
      elevator.elevatorMoveDown(-elevatorDownSpeed);
    //move up with its too down
    }else if(vision.getVerticalOffset() <= -vOffsetAllowance){
      elevator.elevatorMoveUp(elevatorUpSpeed);
    }else if(vision.getDistance() >= VisionConstants.CALIBRATED_GOAL_DISTANCE+offset){
      driveTrain.tankDrive(speed, speed);
    }else if(vision.getDistance() <= VisionConstants.CALIBRATED_GOAL_DISTANCE-offset){
      driveTrain.tankDrive(-speed, -speed);
    }else{
      end(true);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.elevatorStop();
    vision.setLEDMode("off");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
