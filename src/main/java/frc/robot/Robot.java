package frc.robot;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;



public class Robot extends TimedRobot {
  WPI_VictorSPX V1 = new WPI_VictorSPX(1);
  WPI_VictorSPX V2 = new WPI_VictorSPX(2);
  WPI_VictorSPX V3 = new WPI_VictorSPX(3);
  WPI_VictorSPX V4 = new WPI_VictorSPX(4);
  private final MotorControllerGroup RightMotor = new MotorControllerGroup(V1, V2);
  private final MotorControllerGroup LeftMotor = new MotorControllerGroup(V3 , V4);
  //RightMotor.setSafetyEnable(false);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(RightMotor, LeftMotor);
  
  private final Joystick m_stick = new Joystick(0);
  private double startTime;

  
  public void robotInit() {
  

  }

  @Override
  public void teleopPeriodic() { 
    //double direct_speed=m_stick.getRawAxis(3)-m_stick.getRawAxis(2);
    //double angel_speed=m_stick.getRawAxis(0)*0.3;
    m_robotDrive.curvatureDrive((m_stick.getRawAxis(3)*0.8-m_stick.getRawAxis(2)*0.8>=0) ?m_stick.getRawAxis(0)*0.3:m_stick.getRawAxis(0)*-0.3  ,m_stick.getRawAxis(3)*0.8-m_stick.getRawAxis(2)*0.8,true);
 }
 @Override
 public void  autonomousInit(){
   startTime=Timer.getFPGATimestamp();
 }
 public void autonomousPeriodic(){
  double time=Timer.getFPGATimestamp();
  if (time - startTime <= 3) {
    LeftMotor.set(0.2);
    
    RightMotor.set(-0.2);
    
  } else {
    LeftMotor.set(0);
    LeftMotor.set(0);
    RightMotor.set(0);
    RightMotor.set(0);
  }
}

}
