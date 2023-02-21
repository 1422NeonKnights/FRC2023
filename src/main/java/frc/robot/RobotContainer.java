// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.CameraMovement;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.Grabber;
import frc.robot.commands.auto.AutoCorrectionDrive;
import frc.robot.commands.auto.DriveForDistance;
import frc.robot.commands.auto.DriveTimedCommand;
import frc.robot.commands.auto.FollowDrive;
import frc.robot.commands.auto.LockTargetCommand;
import frc.robot.commands.auto.sequential.TaxiAutonomous;
import frc.robot.commands.drive.ArcadeDrive;
import frc.robot.commands.drive.XboxDrive;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Telemetry;
import frc.robot.subsystems.Vision;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  //subsystems
  public final DriveTrain drivetrain;
  public final Claw claw;
  public final Elevator elevator;
  private Vision vision;
  private Telemetry telemetry;

  //commands
  public final ArcadeDrive arcadeDrive;
  public final XboxDrive xboxDrive;
  public static ElevatorCommand elevatorCommand;
  public static Grabber grabber;
  public static CameraMovement cameraMovement;

  //Autonomous
  public static DriveTimedCommand driveTimedCommand;
  public static FollowDrive followDrive;
  public static AutoCorrectionDrive autoCorrectionDrive;
  public static LockTargetCommand lockTargetCommand;
  public static DriveForDistance driveForDistance;
  //sequential
  private final TaxiAutonomous taxiAutonomous;

  private SendableChooser<Command> autoChooser = new SendableChooser<>();
  
  //Joysticks
  public static final XboxController XboxStick = new XboxController(DriveConstants.XBOX_JOY);
  public static final Joystick leftStick = new Joystick(DriveConstants.LEFT_JOY);
  public static final Joystick rightStick = new Joystick(DriveConstants.RIGHT_JOY);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //subsystems
    drivetrain = new DriveTrain();
    claw = new Claw();
    vision = new Vision();
    elevator = new Elevator();
    telemetry = new Telemetry();

    //commands
    //drive commands
    arcadeDrive = new ArcadeDrive(drivetrain, leftStick, telemetry);
    xboxDrive = new XboxDrive(drivetrain, XboxStick);
    drivetrain.setDefaultCommand(arcadeDrive);

    grabber = new Grabber(claw, XboxStick);
    claw.setDefaultCommand(grabber);

    //elevator commands
    elevatorCommand = new ElevatorCommand(elevator, XboxStick, vision, telemetry);
    elevator.setDefaultCommand(elevatorCommand);

    //auto commands
    followDrive = new FollowDrive(drivetrain, vision);
    autoCorrectionDrive = new AutoCorrectionDrive(drivetrain, telemetry);
    lockTargetCommand = new LockTargetCommand(drivetrain, vision);

    //squential auto
    taxiAutonomous = new TaxiAutonomous(drivetrain, telemetry);

    //telemetry commands
    cameraMovement = new CameraMovement(telemetry);
    telemetry.setDefaultCommand(cameraMovement);

    // Configure the trigger bindings
    configureBindings();
    //set up autonomous chooser
    configureAutoChooser();

    //silence joystick warning
    DriverStation.silenceJoystickConnectionWarning(true);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //when button held
    new JoystickButton(leftStick, 4).whileTrue(autoCorrectionDrive);
    new JoystickButton(leftStick, 2).whileTrue(followDrive);
    new JoystickButton(leftStick, 3).whileTrue(lockTargetCommand);
    
  }

  private void configureAutoChooser() {
    autoChooser.setDefaultOption("Taxi", taxiAutonomous);
    //autoChooser.addOption();
    
    SmartDashboard.putData(autoChooser);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
