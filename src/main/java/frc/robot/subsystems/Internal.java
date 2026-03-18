package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import frc.robot.Constants;

public class Internal extends SubsystemBase {
   
    private SparkFlex m_LauncherMain;
    private SparkFlex m_LauncherFollower;
    private SparkFlex m_Loader;
    private SparkMax m_Conveyor;
    private SparkClosedLoopController m_LauncherController;
    private RelativeEncoder m_LauncherEncoder;
    private double flywheelTargetVelocity;

public Internal() {
    //launcher declarations
    m_LauncherMain = new SparkFlex(Constants.ShooterSubsystemConstants.kFlywheelMotorCanId, MotorType.kBrushless);
    
    m_LauncherController = m_LauncherMain.getClosedLoopController();
    m_LauncherEncoder = m_LauncherMain.getEncoder();

    //old code commented out DQ 3/18/2026
    //SparkMaxConfig config = new SparkMaxConfig();
    //config.closedLoop.p(0.1).i(0).d(0);
    //m_LauncherMain.configure(config, ResetMode.kResetSafeParameters,
    //    PersistMode.kPersistParameters);
    m_LauncherFollower = new SparkFlex(Constants.ShooterSubsystemConstants.kFlywheelFollowerMotorCanId, MotorType.kBrushless);


    //loader and conveyor declarations
    m_Loader = new SparkFlex(Constants.InternalConstants.kLoaderCanId, MotorType.kBrushless);

    m_Conveyor = new SparkMax(Constants.InternalConstants.kConveyorCanId, MotorType.kBrushless);   
      // Member variables for subsystem state management
    flywheelTargetVelocity = 0.0;

    /*
     * Apply the appropriate configurations to the SPARKs.
     *
     * kResetSafeParameters is used to get the SPARK to a known state. This
     * is useful in case the SPARK is replaced.
     *
     * kPersistParameters is used to ensure the configuration is not lost when
     * the SPARK loses power. This is useful for power cycles that may occur
     * mid-operation.
     */
    m_LauncherMain.configure(
        Configs.ShooterSubsystem.flywheelConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
    m_LauncherFollower.configure(
        Configs.ShooterSubsystem.flywheelFollowerConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
    m_Loader.configure(
        Configs.ShooterSubsystem.feederConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);

    // Zero flywheel encoder on initialization
    m_LauncherEncoder.setPosition(0);    
    }

public void spinLauncher(double speed) {
    m_LauncherController.setSetpoint(speed, ControlType.kMAXMotionVelocityControl);
    flywheelTargetVelocity = speed;
    } 

public void spinLoader(double speed) {
    m_Loader.set(speed);
    }
    
public void spinConveyor(double speed) {
    m_Conveyor.set(speed);
    }

}