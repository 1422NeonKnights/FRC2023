// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutonomousConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Telemetry;

public class HoldPositionCommand extends CommandBase {

  private DriveTrain driveTrain;
  private Telemetry telemetry;
  
  private double turnSpeed = AutonomousConstants.AUTOCORRECTION_TURNSPEED;
  private double correctionAngle = AutonomousConstants.AUTOCORRECTION_ANGLE;

  private double angle;

  /** Creates a new HoldPositionCommand. */
  public HoldPositionCommand(DriveTrain driveTrain, Telemetry telemetry) {
    this.driveTrain = driveTrain;
    this.telemetry = telemetry;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.stopMotors();
    telemetry.resetEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    angle = telemetry.getGyroAngle();

    if(angle>=correctionAngle){
      driveSpeed(-turnSpeed, turnSpeed);
    }else if(angle<=-correctionAngle){
      driveSpeed(turnSpeed,-turnSpeed);
    }
  }

  public void driveSpeed(double rightSpeed, double leftSpeed){
    driveTrain.tankDrive(rightSpeed, leftSpeed);
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
