
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;

public class AlgaeIntake extends SubsystemBase {
  /** Creates a new Arm. */
  private SparkFlex m_TakeIn;
  private SparkMax m_AlgaeRotator;
  private SparkClosedLoopController m_AlgaeController;

  public AlgaeIntake() {
    m_TakeIn = new SparkFlex(9, MotorType.kBrushless);

    m_AlgaeRotator = new SparkMax(10,MotorType.kBrushless);
    m_AlgaeController = m_AlgaeRotator.getClosedLoopController();
    SparkMaxConfig config = new SparkMaxConfig();
    config.closedLoop.p(0.08).i(0).d(0);
    m_AlgaeRotator.configure(config, ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * rotate the shoulder with the given speed
   * @param speed the value given by the RotateShoulder & ReverseRotateSoulder command
   */
  public void spinAlgae(double speed) {
    m_TakeIn.set(speed);
  }
  public void grabPosition(){
    m_AlgaeController.setReference(-15.8, ControlType.kPosition);
  }

  public void restPosition(){
    m_AlgaeController.setReference(-4, ControlType.kPosition);    
  }

  public void startingPosition(){
    m_AlgaeController.setReference(-0.5, ControlType.kPosition);    
  }
}