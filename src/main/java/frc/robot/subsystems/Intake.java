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

public class Intake extends SubsystemBase {
    private SparkFlex m_IntakeMotor;
    private SparkClosedLoopController m_IntakePID;
    private RelativeEncoder m_IntakeEncoder;


public Intake() {
    m_IntakeMotor = new SparkFlex(Constants.IntakeConstants.kIntakeCanId, MotorType.kBrushless);
    
    m_IntakePID = m_IntakeMotor.getClosedLoopController();
    SparkFlexConfig config = new SparkFlexConfig();
    config.closedLoop.p(0.08).i(0).d(0);
    m_IntakeMotor.configure(config, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
    m_IntakeEncoder = m_IntakeMotor.getEncoder();

    m_IntakeEncoder.setPosition(0);
    

    
    }
    
public void spinIntake(double speed) {
    //m_IntakePID.setSetpoint(speed, ControlType.kMAXMotionVelocityControl);
    m_IntakeMotor.set(speed);

    } 

public void stopIntake() {
    m_IntakeMotor.stopMotor();


}

}