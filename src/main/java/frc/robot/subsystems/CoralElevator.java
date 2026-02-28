package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkClosedLoopController;


public class CoralElevator extends SubsystemBase {
  /** Creates a new Arm. */
  private SparkMax m_coralMouth;

  private SparkFlex m_coralElevatorMotor;
  private SparkClosedLoopController m_elevatorController;

  private SparkMax m_coralRotate;
  private SparkClosedLoopController m_coralController;

  public CoralElevator() {
    m_coralMouth = new SparkMax(13, MotorType.kBrushless);

    m_coralElevatorMotor = new SparkFlex(11,MotorType.kBrushless);
    m_elevatorController = m_coralElevatorMotor.getClosedLoopController();

    SparkFlexConfig eleconfig = new SparkFlexConfig();
    eleconfig.closedLoop.p(0.035).i(0).d(0);
    m_coralElevatorMotor.configure(eleconfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);

    m_coralRotate = new SparkMax(12,MotorType.kBrushless);
    m_coralController = m_coralRotate.getClosedLoopController();

    SparkFlexConfig coralconfig = new SparkFlexConfig();
    coralconfig.closedLoop.p(0.035).i(0).d(0);
    m_coralRotate.configure(coralconfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * rotate the shoulder with the given speed
   * @param speed the value given by the RotateShoulder & ReverseRotateSoulder command
   */
  public void spinCoral(double speed) {
    m_coralMouth.set(speed);
  }

  public void elevatorBottom(){
    m_elevatorController.setReference(0, ControlType.kPosition);
  }

  public void elevatorL2(){
    m_elevatorController.setReference(19, ControlType.kPosition);
  }

  public void elevatorL3() {
    //return new InstantCommand( () -> m_elevatorController.setReference(60,ControlType.kPosition ));
    m_elevatorController.setReference(120, ControlType.kPosition);
  }

  

  public void coralInPos(){
   m_coralController.setReference(-10., ControlType.kPosition); 
  }

  public void coralup(){
   m_coralController.setReference(-20,ControlType.kPosition); 
  }

  public void coralDeploy(){
    m_coralController.setReference(-41,ControlType.kPosition); 
   }

   public void coralChill(){
    m_coralRotate.set(0); 
   } 
 
}