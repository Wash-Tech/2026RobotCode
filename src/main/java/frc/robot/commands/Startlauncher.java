package frc.robot.commands;

import java.util.HashMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Internal;
import frc.robot.utils.LimeTable;
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
    LimeTable table = new LimeTable();
    HashMap<String, Double> limeData = table.getNetworkTable();
    Double ta = limeData.get("ta");
    System.out.println("Area: " + ta);
    Double tx = limeData.get("tx");
    System.out.println("X: " + tx);

    double defaultRpm = Constants.ShooterSubsystemConstants.FlywheelSetpoints.kShootRpm;
    double rpmAdjustment = defaultRpm + (1000 * (ta/2.0)); // Adjust RPM based on target area (ta)
    
    m_Internal.spinLauncher(Constants.ShooterSubsystemConstants.FlywheelSetpoints.kShootRpm);
    // m_Internal.spinLauncher(rpmAdjustment);
  }

 // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_Internal.spinLauncher(0);
    //attempting to let the flywheel spin down.
    m_Internal.stopmotors();
    //do we this?

   }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}