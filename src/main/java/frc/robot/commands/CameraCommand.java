// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Telemetry;

public class CameraCommand extends CommandBase {

  private Telemetry telemetry;
  private Joystick leftStick;

  private boolean camToggle;

  /** Creates a new CameraServer. */
  public CameraCommand(Telemetry telemetry, Joystick lefStick) {
    this.telemetry = telemetry;
    this.leftStick = lefStick;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(telemetry);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    telemetry.setSource1();
    camToggle = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(leftStick.getTriggerPressed()){
      if(!camToggle){
        telemetry.setSource2();
        camToggle = true;
      }else if(camToggle){
        telemetry.setSource1();
        camToggle = false;
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
