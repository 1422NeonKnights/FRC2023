// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Grabber extends CommandBase {
  XboxController XboxStick = RobotContainer.XboxStick;
  /** Creates a new Grabber. */
  public Grabber() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.claw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(XboxStick.getBButtonPressed()){
      RobotContainer.claw.pitchUp();
    }else if(XboxStick.getAButtonReleased()){
      RobotContainer.claw.pitchDown();
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
