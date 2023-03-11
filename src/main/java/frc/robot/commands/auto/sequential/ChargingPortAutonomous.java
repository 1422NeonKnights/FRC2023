// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auto.sequential;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.auto.drive.DriveForDistance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Telemetry;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ChargingPortAutonomous extends SequentialCommandGroup {
  /** Creates a new ChargingPortAutonomous. */
  public ChargingPortAutonomous(DriveTrain driveTrain, Telemetry telemetry) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DriveForDistance(driveTrain, telemetry, 60)
      );
  }
}
