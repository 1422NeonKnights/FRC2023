// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Telemetry;

public class ArmCommand extends CommandBase {
  /** Creates a new ArmCommand. */
  Arm arm;
  XboxController xboxStick;
  private Telemetry telemetry;



  public ArmCommand(Arm arm, XboxController xboxStick, Telemetry telemetry) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.arm = arm;
    this.xboxStick = xboxStick;
    this.telemetry = telemetry;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xboxRY = -xboxStick.getRightY();

    if (telemetry.upLimitSwitch.get()) {
      if (xboxRY < 0) {
        arm.armMove(xboxRY);
      }
      else {
        arm.armStop();
      }
    }
    else if (telemetry.downLimitSwitch.get()) {
      if (xboxRY > 0) {
        arm.armMove(xboxRY);
      }
      else {
        arm.armStop();
      }
    }
    else {
      arm.armMove(xboxRY);
    }
    arm.armMove(xboxRY);
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
