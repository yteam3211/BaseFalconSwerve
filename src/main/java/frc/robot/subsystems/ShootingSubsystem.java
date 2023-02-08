// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.text.Position;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.robot.Constants;
import frc.robot.commands.ShootingOutput;
import frc.util.SuperSystem;
import frc.util.PID.Gains;
import frc.util.motor.SuperSparkMax;
import frc.util.motor.SuperTalonFX;


// Yteam Example Subsystem
public class ShootingSubsystem extends SuperSystem {
//  public SuperSparkMax ShooingMotor;
 public Gains shootingGains;
 public DigitalInput upMicroSwitch;
 public DigitalInput downMicroSwitch;
 public SuperTalonFX Shooing;
  // Motors, Selenoid and Sensors declaration
  public ShootingSubsystem() {
    super("ShootingSubsystem");
    shootingGains = new Gains("_shootingGains",0.05, 0,0);
    // upMicroSwitch = new DigitalInput(Constants.UP_MICROSWITCH);
    // downMicroSwitch = new DigitalInput(Constants.DOWN_MICROSWITCH);
    Shooing = new SuperTalonFX(Constants.SHOOTING_MOTOR, 30, false, false, NeutralMode.Brake, shootingGains, TalonFXControlMode.Position);
    // firstShootingSp = new VictorSP(0);
    // secondShootingSp = new VictorSP(1);
    // ShooingMotor = new SuperSparkMax(14,false);
    // ShooingMotor = new SuperSparkMax(14, MotorType.kBrushless, 30, false, 1 ,1 , IdleMode.kBrake, ControlType.kPosition, shootingGains, 0, 0, 0);
    setDefaultCommand(new ShootingOutput(this, 0));
  }

  /** Creates a new ExampleSubsystem. */
  
  @Override
  public void periodic() {
    // getTab().putInDashboard("Position", ShooingMotor.getEncoder().getPosition(), false);
    getTab().putInDashboard("encoder", Shooing.getPosition(), false);
    // System.out.println(Shooing.getPosition());
    // This method will be called once per scheduler run
  }

  public void setOutput(double output){
    // ShooingMotor.setMode(ControlMode.PercentOutput);
     // neo motor 
    Shooing.set(ControlMode.PercentOutput, output);
    // if ((output > 0 && !isShootingUp()) || (output <0 && !isShootingDown())) {
    //   firstShootingSp.set(output);
    //   secondShootingSp.set(output);
    // }
  
  }
  public void resetEncoder(){
    Shooing.reset(0);
  }

  public void setPosition(double position){
    Shooing.set(ControlMode.Position, position);
    // ShooingMotor.setMode(ControlMode.Position);
    // ShooingMotor.getPIDController().setReference(position, ControlType.kPosition);
    // getTab().putInDashboard("encoder_live", ShooingMotor.getPosition(), false);

  }
  public double GetPosition(){
   return Shooing.getPosition();
 

    
  }

  public boolean isShootingUp(){
    
    //  return upMicroSwitch.get();
    return false;
  }

  public boolean isShootingDown(){
    
    // return downMicroSwitch.get();
    return false;
  }
}

