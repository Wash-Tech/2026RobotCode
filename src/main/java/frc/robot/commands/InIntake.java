package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class InIntake extends Command {
    private final Intake m_intake;

  /** Creates a new ExtendArm. */
  public InIntake(Intake subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = subsystem;
    addRequirements(m_intake);
  }
    

 // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //m_intake.grabPosition();
  }


  @Override
  public void execute() {
    m_intake.spinIntake(Constants.IntakeConstants.kIntakeSpeed);
   
    //m_intake.spinIntake(0.1);
  }



 // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.spinIntake(0);

   }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

} 