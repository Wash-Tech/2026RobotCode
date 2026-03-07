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

public class Internal extends SubsystemBase {
    private SparkFlex m_Launcher;
    private SparkFlex m_Loader;
    private SparkMax m_Conveyor;
    private SparkClosedLoopController m_LauncherPID;


public Internal() {
    m_Launcher = new SparkFlex(9, MotorType.kBrushless);
    m_LauncherPID = m_Launcher.getClosedLoopController();
    SparkMaxConfig config = new SparkMaxConfig();
    config.closedLoop.p(0.08).i(0).d(0);
    m_Launcher.configure(config, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
    
    m_Loader = new SparkFlex(10, MotorType.kBrushless);

    m_Conveyor = new SparkMax(11, MotorType.kBrushless);   
    }

public void spinLauncher(double speed) {
    m_LauncherPID.setSetpoint(speed, ControlType.kVelocity);
    } 

public void spinLoader(double speed) {
    m_Loader.set(speed);
    }
    
public void spinConveyor(double speed) {
    m_Conveyor.set(speed);
    }

}