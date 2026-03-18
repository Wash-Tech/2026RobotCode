package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Internal;
import frc.robot.Constants;

public class Startlauncher extends Command {
    private final Internal m_Internal;

  public Startlauncher(Internal subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Internal = subsystem;
    addRequirements(m_Internal);
  }
    

 // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_intake.grabPosition();
  }

  @Override
  public void execute() {
    m_Internal.spinLauncher(Constants.ShooterSubsystemConstants.FlywheelSetpoints.kShootRpm);
  }

 // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Internal.spinLauncher(0);
    //do we this?

   }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}