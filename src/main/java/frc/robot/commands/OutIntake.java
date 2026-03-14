package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class OutIntake extends Command {
    private final Intake m_intake;
  /** Creates a new OutIntake. */
  public OutIntake(Intake subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = subsystem;
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_intake.grabPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.spinIntake(-Constants.IntakeConstants.kIntakeSpeed);
  }
 // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.spinIntake(0);

   }
  @Override
  public boolean isFinished() {
    return false;
  }
}
