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

public class Intake extends SubsystemBase {
    private SparkFlex m_IntakeMotor;
    private SparkClosedLoopController m_IntakePID;


public Intake() {
    m_IntakeMotor = new SparkFlex(8, MotorType.kBrushless);
    
    m_IntakePID = m_IntakeMotor.getClosedLoopController();
    SparkMaxConfig config = new SparkMaxConfig();
    config.closedLoop.p(0.08).i(0).d(0);
    m_IntakeMotor.configure(config, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
    

    
}
public void spinAlgae(double speed) {
    m_IntakePID.setSetpoint(speed, ControlType.kVelocity);
  } 

}