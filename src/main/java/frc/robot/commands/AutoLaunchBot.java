package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Internal;
import frc.robot.Constants;

/**
 * Combines Startlauncher and Launchfuel into a single command so both
 * can run simultaneously without conflicting over the Internal subsystem.
 * Spins the launcher flywheel, conveyor, and loader all at once.
 */
public class AutoLaunchBot extends Command {
    private final Internal m_Internal;

    public AutoLaunchBot(Internal subsystem) {
        m_Internal = subsystem;
        addRequirements(m_Internal);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        // Startlauncher behaviour: spin the flywheel
        m_Internal.spinLauncher(Constants.ShooterSubsystemConstants.FlywheelSetpoints.kShootRpm);
        // Launchfuel behaviour: spin conveyor and loader to feed fuel
        m_Internal.spinConveyor(Constants.InternalConstants.kConveyorSpeed);
        m_Internal.spinLoader(Constants.InternalConstants.kLoaderSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        m_Internal.stopmotors();
        m_Internal.spinConveyor(0);
        m_Internal.spinLoader(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
