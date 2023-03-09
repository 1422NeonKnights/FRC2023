// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Vision;

public class ElevatorLock extends CommandBase {

  private Elevator elevator;
  private Vision vision;

  private double vOffsetAllowance = VisionConstants.VERTICAL_OFFSET_ALLOWANCE;
  private double elevatorSpeed = ElevatorConstants.ELEVATOR_MAX_SPEED;

  /** Creates a new ElevatorLock. */
  public ElevatorLock(Elevator elevator, Vision vision) {
    // Use addRequirements() here to declare subsystem dependencies.
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
      elevator.elevatorMove(-elevatorSpeed);
    //move up with its too down
    }else if(vision.getVerticalOffset() <= -vOffsetAllowance){
      elevator.elevatorMove(elevatorSpeed);
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
