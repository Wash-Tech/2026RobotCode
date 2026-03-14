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
   
    private SparkFlex m_Launcherleft;
    private SparkFlex m_Launcherright;
    private SparkFlex m_Loader;
    private SparkMax m_Conveyor;
    private SparkClosedLoopController m_LauncherPIDleft;
    private SparkClosedLoopController m_LauncherPIDright;
    

public Internal() {
    m_Launcherleft = new SparkFlex(Constants.InternalConstants.kLauncherLeftCanId, MotorType.kBrushless);
    m_Launcherright = new SparkFlex(Constants.InternalConstants.kLauncherRightCanId, MotorType.kBrushless);

    m_LauncherPIDleft = m_Launcherleft.getClosedLoopController();
    m_LauncherPIDright = m_Launcherright.getClosedLoopController();
    SparkMaxConfig config = new SparkMaxConfig();
    config.closedLoop.p(0.08).i(0).d(0);
    m_Launcherleft.configure(config, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
    m_Launcherright.configure(config, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);

    m_Loader = new SparkFlex(Constants.InternalConstants.kLoaderCanId, MotorType.kBrushless);

    m_Conveyor = new SparkMax(Constants.InternalConstants.kConveyorCanId, MotorType.kBrushless);   
    }

public void spinLauncher(double speed) {
    m_LauncherPIDleft.setSetpoint(speed, ControlType.kVelocity);
    m_LauncherPIDright.setSetpoint(-speed, ControlType.kVelocity);
    } 

public void spinLoader(double speed) {
    m_Loader.set(speed);
    }
    
public void spinConveyor(double speed) {
    m_Conveyor.set(speed);
    }

}