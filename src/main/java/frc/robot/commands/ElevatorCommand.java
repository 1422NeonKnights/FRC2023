// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Telemetry;
import frc.robot.subsystems.Vision;

public class ElevatorCommand extends CommandBase {
  private Elevator elevator;
  private XboxController xboxStick;
  private Vision vision;
  private Telemetry telemetry;

  /** Creates a new ElevatorCommand. */
  public ElevatorCommand(Elevator elevator, XboxController xboxStick, Vision vision, Telemetry telemetry) {
    this.elevator = elevator;
    this.xboxStick = xboxStick;
    this.vision = vision;
    this.telemetry = telemetry;
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
    if (xboxStick.getYButtonPressed()) {
      elevator.elevatorUp();
      //TODO: test out switches
      if(telemetry.hallSwitch.get()){
         elevator.elevatorStop();
       }
    } else if(xboxStick.getYButtonReleased()) {
      elevator.elevatorStop();
    }

    if (xboxStick.getXButtonPressed()) {
      elevator.elevatorDown();
    } else if(xboxStick.getXButtonReleased()) {
      elevator.elevatorStop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    vision.setLEDMode("off");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
