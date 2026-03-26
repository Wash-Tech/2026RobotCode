// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoLaunchBot;
import frc.robot.commands.InIntake;
import frc.robot.commands.Launchfuel;
import frc.robot.commands.OutIntake;
import frc.robot.commands.Startlauncher;
import frc.robot.subsystems.DriveSubsystem;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Internal;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final Intake m_intake = new Intake();
  private final Internal m_internal = new Internal();
  private final AutoLaunchBot autolaunchbot = new AutoLaunchBot(m_internal);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);

  private final SendableChooser<Command> autoChooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Build an auto chooser. This will use Commands.none() as the default option.
    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);
    NamedCommands.registerCommand("autolaunchbot", autolaunchbot);

    // Configure the trigger bindings
    configureBindings();
    //CameraServer.startAutomaticCapture();
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                //0.3 * MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),   // kph 2/8/24
                //0.3 * MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),   // kph 2/8/24
                //0.3 * -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),  // kph 2/8/24
                true),
            m_robotDrive));
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
      new JoystickButton(m_driverController, XboxController.Button.kX.value).whileTrue(new RunCommand(
        () -> m_robotDrive.setX(),m_robotDrive));

        Trigger rTrigger = new Trigger(() -> m_driverController.getLeftTriggerAxis() > 0.5);
        rTrigger.whileTrue(new OutIntake(m_intake));

        Trigger rbumper = new JoystickButton(m_driverController, XboxController.Button.kRightBumper.value);
        rbumper.whileTrue(new InIntake(m_intake));

        Trigger lTrigger = new Trigger(() -> m_driverController.getRightTriggerAxis() > 0.5);
        lTrigger.whileTrue(new Launchfuel(m_internal));

        Trigger lbumper = new JoystickButton(m_driverController, XboxController.Button.kLeftBumper.value);
        lbumper.whileTrue(new Startlauncher(m_internal));
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
