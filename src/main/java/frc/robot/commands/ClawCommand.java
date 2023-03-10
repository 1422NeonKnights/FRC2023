// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

public class ClawCommand extends CommandBase {

  private final Claw claw;
  private final XboxController XboxStick;

  private boolean toggle = false;
  private boolean clawToggle = false;

  /** Creates a new Grabber. */
  public ClawCommand(Claw claw, XboxController XboxStick) {
    this.claw = claw;
    this.XboxStick = XboxStick;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(claw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(XboxStick.getYButtonPressed()){
      if(toggle){
        claw.disableCompressor();
        toggle = false;
      }else if(!toggle){
        claw.enableCompressor();
        toggle = true;
      }
    }

    //open claw when pressed A for cones
    if(XboxStick.getAButtonPressed()) {
      if(clawToggle) {
        claw.coneCloseClaw();
        clawToggle = false;
      } else {
        claw.openClaw();
        clawToggle = true;
      }
    }

    //open half claw when presesd B for cubes
    if(XboxStick.getBButtonPressed()) {
      if(clawToggle) {
        claw.cubeCloseClaw();
        clawToggle = false;
      } else {
        claw.openClaw();
        clawToggle = true;
      }
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
